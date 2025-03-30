package etat;

import document.EtatDocument;
import exception.EmpruntException;
import exception.ReservationException;
import mediatheque.Abonne;
import mediatheque.IDocument;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;

public class Reserve extends Etat {
    private Abonne abonneB;
    private LocalDateTime dateFinReservation;
    private Timer finReservation;
    private Abonne abonneA;
    private Timer attenteReservation;

    public Reserve(IDocument document, Abonne ab) {
        this.abonneB = ab;
        this.dateFinReservation = LocalDateTime.now().plusHours(1);
        this.finReservation = new Timer(ab.getNom());
        this.finReservation.schedule(new FinReservation(document), 3600000);
    }

    @Override
    public EtatDocument reserver(IDocument document, Abonne ab) throws ReservationException {
        synchronized (document) {
            long tempsRestant = Duration.between(LocalDateTime.now(), dateFinReservation).toSeconds();
            if(abonneA == null && ab != abonneB && tempsRestant <= 60) {
                finReservation.cancel();
                abonneA = ab;
                attenteReservation = new Timer(ab.getNom());
                attenteReservation.schedule(new AttenteReservation(document, ab), tempsRestant * 1000);
                throw new ReservationException("Vous écoutez une musique céleste");
            }
            if (tempsRestant > 0 && tempsRestant <= 60 && abonneA == ab) {
                try {
                    document.wait();
                } catch (InterruptedException e) {
                }
                tempsRestant = Duration.between(LocalDateTime.now(), dateFinReservation).toSeconds();
                if (tempsRestant > 0)
                    throw new ReservationException("Vous n'avez pas de chance pour ce document mais vous bénéficiez d'un concert céleste gratuit, vous auriez dû faire une offrande plus importante au grand chaman");
                return new Reserve(document, abonneA);
            }
            throw new ReservationException("Ce " + document.toString() + " est réservé jusqu’à " + dateFinReservation.getHour() + ":" + dateFinReservation.getMinute());

        }
    }

    @Override
    public EtatDocument emprunter(IDocument document, Abonne ab) throws EmpruntException {
        if (LocalDateTime.now().isAfter(dateFinReservation) || abonneB.equals(ab)) {
            if (abonneA != null) {
                document.notifyAll();
                attenteReservation.cancel();
                abonneA = null;
            }
            finReservation.cancel();
            return new Emprunte(document, abonneB);
        }
        throw new EmpruntException("Ce " + document.toString() + " est déjà réservé");
    }

    @Override
    public EtatDocument retourner(IDocument document){
        return new Disponible();
    }
}
