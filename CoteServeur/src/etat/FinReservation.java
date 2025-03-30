package etat;

import mediatheque.IDocument;

import java.util.TimerTask;

public class FinReservation extends TimerTask {
    private IDocument document;
    public FinReservation(IDocument document) {
        this.document = document;
    }
    @Override
    public void run() {
        document.retourner();
    }
}
