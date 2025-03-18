package etat;

import document.EtatDocument;
import mediatheque.Abonne;

import java.time.LocalDateTime;
import java.util.Timer;

public abstract class Etat implements EtatDocument {
    protected Abonne abonne;
    protected LocalDateTime dateFinReservation;
    protected Timer finReservation;
}
