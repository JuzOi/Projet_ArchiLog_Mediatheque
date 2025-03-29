package etat;

import document.EtatDocument;
import mediatheque.Abonne;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Timer;

public abstract class Etat implements EtatDocument {
    protected Timer dureeEmprunt;
}
