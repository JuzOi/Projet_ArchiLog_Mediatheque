package etat;

import mediatheque.Abonne;
import mediatheque.IDocument;

import java.util.TimerTask;

public class FinEmprunt extends TimerTask {
    private Abonne abonne;
    private IDocument document;

    public FinEmprunt(IDocument document, Abonne abonne) {
        this.document = document;
        this.abonne = abonne;
    }
    @Override
    public void run() {
        abonne.bannir();
        document.retourner();
    }
}
