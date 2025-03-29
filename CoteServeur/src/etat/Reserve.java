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
        long tempsRestant = Duration.between(LocalDateTime.now(), dateFinReservation).toSeconds();

        if (tempsRestant <=60 && abonneA == null) {
            try {
                document.wait();
            } catch (InterruptedException e) {
            }
            abonneA = ab;
            attenteReservation = new Timer(ab.getNom());
            attenteReservation.schedule(new AttenteReservation(document, ab), tempsRestant * 1000);
            throw new ReservationException("Vous écoutez une musique céleste");
        }
        throw new ReservationException("Ce " + document.toString() + " est réservé jusqu’à " + dateFinReservation.getHour() + ":" + dateFinReservation.getMinute());
    }

    @Override
    public EtatDocument emprunter(IDocument document, Abonne ab) throws EmpruntException {
        if (LocalDateTime.now().isAfter(dateFinReservation) || abonneB.equals(ab)) {
            if (abonneA != null) {
                notifyAll();
                attenteReservation.cancel();
            }
            long deuxSemaines = 14L * 24 * 60 * 60 * 1000;
            dureeEmprunt = new Timer(abonneB.getNom());
            dureeEmprunt.schedule(new FinEmprunt(abonneB), deuxSemaines);
            return new Emprunte(abonneB);
        }
        throw new EmpruntException("Ce " + document.toString() + " est déjà réservé");
    }

    @Override
    public EtatDocument retourner(IDocument document) throws EmpruntException {
        throw new EmpruntException("Ce " + document.toString() + " est déjà réservé");
    }
}
