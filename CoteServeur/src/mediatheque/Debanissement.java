package mediatheque;

import java.util.TimerTask;

public class Debanissement extends TimerTask {
    private Abonne abonne;

    public Debanissement(Abonne abonne) {
        this.abonne = abonne;
    }


    @Override
    public void run() {
        abonne.debannir();
    }
}
