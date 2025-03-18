package etat;

import document.EtatDocument;
import exception.EmpruntException;
import exception.ReservationException;
import mediatheque.Abonne;
import mediatheque.IDocument;

public class Emprunte extends Etat {
    public Emprunte() {
        this.dateFinReservation = null;
        this.finReservation = null;
    }
    @Override
    public EtatDocument reserver(IDocument document, Abonne ab) throws ReservationException {
        throw new ReservationException("Ce " + document.toString() + " est déjà emprunté");
    }

    @Override
    public EtatDocument emprunter(IDocument document, Abonne ab) throws EmpruntException {
        throw new EmpruntException("Ce " + document.toString() + " est déjà emprunté");
    }

    @Override
    public EtatDocument retourner(IDocument document) {
        return new Disponible();
    }
}
