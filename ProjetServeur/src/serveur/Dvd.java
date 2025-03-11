package serveur;

import exception.AgeDvdException;
import exception.DvdException;
import exception.ReservationException;

public class Dvd extends Document {
	private boolean adulte;

	public Dvd(int numero, String titre, boolean adulte) {
		super(numero, titre);
		this.adulte = adulte;
	}
	
	public boolean getAdulte() {
		return adulte;
	}
	
	@Override
	public void reserver(Abonne ab) throws DvdException{
		try {
			super.reserver(ab);
		} catch (ReservationException e) {
			throw new DvdException();
		}
		if (adulte && ab.getAge() <= 16)
			throw new AgeDvdException();
	}
}
