package etat;

import document.Document;
import document.EtatDocument;
import exception.EmpruntException;
import exception.ReservationException;
import mediatheque.Abonne;

import java.time.LocalDateTime;

public class Reserve extends Etat {
    public Reserve(Abonne ab) {
        this.abonne = ab;
        this.reserveJusquA = LocalDateTime.now().plusHours(1);
    }

    @Override
    public EtatDocument reserver(Document document, Abonne ab) throws ReservationException {
        throw new ReservationException("Ce " + document.toString() + " est réservé jusqu’à " + reserveJusquA.getHour() + ":" + reserveJusquA.getMinute());
    }

    @Override
    public EtatDocument emprunter(Document document, Abonne ab) throws EmpruntException {
        if (!abonne.equals(ab))
            throw new EmpruntException("Ce " + document.toString() + " est déjà réservé");
        return new Emprunte();
    }

    @Override
    public EtatDocument retourner(Document document) {
        abonne = null;
        reserveJusquA = null;
        return new Disponible();
    }
}
