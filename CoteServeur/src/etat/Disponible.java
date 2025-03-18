package etat;

import document.EtatDocument;
import exception.EmpruntException;
import exception.ReservationException;
import mediatheque.Abonne;
import mediatheque.IDocument;


public class Disponible extends Etat {
    public Disponible() {
        this.abonne = null;
        this.dateFinReservation = null;
        this.finReservation = null;
    }
    @Override
    public EtatDocument reserver(IDocument document, Abonne ab) throws ReservationException {
        return new Reserve(document, ab);
    }

    @Override
    public EtatDocument emprunter(IDocument document, Abonne ab) throws EmpruntException {
        return new Emprunte();
    }

    @Override
    public EtatDocument retourner(IDocument document) {
        return this;
    }
}
