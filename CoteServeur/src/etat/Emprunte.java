package etat;

import document.Document;
import document.EtatDocument;
import exception.EmpruntException;
import exception.ReservationException;
import mediatheque.Abonne;

public class Emprunte extends Etat {
    @Override
    public EtatDocument reserver(Document document, Abonne ab) throws ReservationException {
        throw new ReservationException("Ce " + document.toString() + " est déjà emprunté");
    }

    @Override
    public EtatDocument emprunter(Document document, Abonne ab) throws EmpruntException {
        throw new EmpruntException("Ce " + document.toString() + " est déjà emprunté");
    }

    @Override
    public EtatDocument retourner(Document document) {
        abonne = null;
        reserveJusquA = null;
        return new Disponible();
    }
}
