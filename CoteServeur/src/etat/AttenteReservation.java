package etat;

import exception.ReservationException;
import mediatheque.Abonne;
import mediatheque.IDocument;

import java.util.TimerTask;

public class AttenteReservation extends TimerTask {
    private IDocument document;
    private Abonne abonneA;
    public AttenteReservation(IDocument document, Abonne abonneA) {
        this.document = document;
        this.abonneA = abonneA;
    }
    @Override
    public void run() {
        try{
            notifyAll();
            document.reserver(abonneA);
        } catch(ReservationException e){

        }
    }

}
