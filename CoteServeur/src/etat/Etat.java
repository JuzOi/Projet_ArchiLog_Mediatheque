package etat;

import document.Document;
import document.EtatDocument;
import exception.EmpruntException;
import exception.ReservationException;
import mediatheque.Abonne;

import java.time.LocalDateTime;

public abstract class Etat implements EtatDocument {
    protected LocalDateTime reserveJusquA;
    protected Abonne abonne;
}
