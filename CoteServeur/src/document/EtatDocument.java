package document;

import exception.EmpruntException;
import exception.ReservationException;
import mediatheque.Abonne;

public interface EtatDocument {
    EtatDocument reserver(Document document, Abonne ab) throws ReservationException;
    EtatDocument emprunter(Document document, Abonne ab) throws EmpruntException;
    EtatDocument retourner(Document document);
}
