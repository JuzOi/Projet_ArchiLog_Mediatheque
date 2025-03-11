package document;

import exception.EmpruntException;
import exception.ReservationException;
import mediatheque.Abonne;

public class Livre extends Document {
	private int nbPages;

	public Livre(int numero, String titre, int nbPages) {
		super(numero, titre);
		this.nbPages = nbPages;
	}
	
	public int getNombreDePages() {
		return nbPages;
	}
}
