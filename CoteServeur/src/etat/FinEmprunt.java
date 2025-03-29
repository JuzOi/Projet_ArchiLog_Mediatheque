package etat;

import mediatheque.Abonne;
import mediatheque.IDocument;

import java.util.TimerTask;

public class FinEmprunt extends TimerTask {
    private Abonne abonne;

    public FinEmprunt(Abonne abonne) {
        this.abonne = abonne;
    }
    @Override
    public void run() {
        abonne.bannir();
    }
}
