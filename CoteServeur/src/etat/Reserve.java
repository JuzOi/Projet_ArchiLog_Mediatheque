package etat;

import document.EtatDocument;
import exception.EmpruntException;
import exception.ReservationException;
import mediatheque.Abonne;
import mediatheque.IDocument;

import java.time.LocalDateTime;
import java.util.Timer;

public class Reserve extends Etat {
    public Reserve(IDocument document, Abonne ab) {
        this.abonne = ab;
        this.dateFinReservation = LocalDateTime.now().plusHours(1);
        this.finReservation = new Timer(ab.getNom());
        this.finReservation.schedule(new FinReservation(document), 5000);
    }

    @Override
    public EtatDocument reserver(IDocument document, Abonne ab) throws ReservationException {
//        Duration tempsRestant = Duration.between(LocalDateTime.now(), reserveJusquA);
//        if (tempsRestant.toMinutes() >0 && tempsRestant.toSeconds() <= 60) {
//            try {
//                document.wait();
//            } catch (InterruptedException e) {
//            }
//            throw new ReservationException("Vous écoutez une musique céleste");
//        }
//        if (tempsRestant.toSeconds() <= 0) {
//
//        }
        throw new ReservationException("Ce " + document.toString() + " est réservé jusqu’à " + dateFinReservation.getHour() + ":" + dateFinReservation.getMinute());
    }

    @Override
    public EtatDocument emprunter(IDocument document, Abonne ab) throws EmpruntException {
        if (LocalDateTime.now().isAfter(dateFinReservation) || abonne.equals(ab))
            return new Emprunte();
        throw new EmpruntException("Ce " + document.toString() + " est déjà réservé");
    }

    @Override
    public EtatDocument retourner(IDocument document) {
        return new Disponible();
    }
}
