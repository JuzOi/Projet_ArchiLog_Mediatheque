package serveur;

import exception.LivreException;
import exception.ReservationException;

public class Livre extends Document {
	private int nbPages;

	public Livre(int numero, String titre, int nbPages) {
		super(numero, titre);
		this.nbPages = nbPages;
	}
	
	public int getNombreDePages() {
		return nbPages;
	}
	
	@Override
	public void reserver(Abonne ab) throws LivreException{
		try {
			super.reserver(ab);
		} catch (ReservationException e) {
			throw new LivreException();
		}
	}

}
