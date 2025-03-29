package document;

import exception.EmpruntException;
import exception.ReservationException;
import mediatheque.Abonne;
import mediatheque.IDocument;

public interface EtatDocument {
    EtatDocument reserver(IDocument document, Abonne ab) throws ReservationException;
    EtatDocument emprunter(IDocument document, Abonne ab) throws EmpruntException;
    EtatDocument retourner(IDocument document) throws EmpruntException;
}
