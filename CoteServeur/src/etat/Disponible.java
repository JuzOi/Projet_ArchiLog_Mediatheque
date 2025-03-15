package etat;

import document.Document;
import document.EtatDocument;
import exception.EmpruntException;
import exception.ReservationException;
import mediatheque.Abonne;


public class Disponible extends Etat {
    @Override
    public EtatDocument reserver(Document document, Abonne ab) throws ReservationException {
        return new Reserve(ab);
    }

    @Override
    public EtatDocument emprunter(Document document, Abonne ab) throws EmpruntException {
        return new Emprunte();
    }

    @Override
    public EtatDocument retourner(Document document) {
        return this;
    }
}
