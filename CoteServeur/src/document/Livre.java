package document;

import exception.EmpruntException;
import exception.LivreEmpruntException;
import exception.LivreReservationException;
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
	
	@Override
	public void reserver(Abonne ab) throws LivreReservationException {
		try {
			super.reserver(ab);
		} catch (ReservationException e) {
			throw new LivreReservationException();
		}
	}

	@Override
	public void emprunter(Abonne ab) throws EmpruntException {
		try{
			super.emprunter(ab);
		} catch (EmpruntException e) {
			throw new LivreEmpruntException();
		}
	}
}
