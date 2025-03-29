package etat;

import document.EtatDocument;
import exception.EmpruntException;
import exception.ReservationException;
import mediatheque.Abonne;
import mediatheque.IDocument;

import java.util.Timer;


public class Disponible extends Etat {
    public Disponible() {
    }
    @Override
    public EtatDocument reserver(IDocument document, Abonne ab) throws ReservationException {
        return new Reserve(document, ab);
    }

    @Override
    public EtatDocument emprunter(IDocument document, Abonne ab) throws EmpruntException {
        long deuxSemaines = 14L * 24 * 60 * 60 * 1000;
        dureeEmprunt = new Timer(ab.getNom());
        dureeEmprunt.schedule(new FinEmprunt(ab), deuxSemaines);
        return new Emprunte(ab);
    }

    @Override
    public EtatDocument retourner(IDocument document) {
        return this;
    }
}
