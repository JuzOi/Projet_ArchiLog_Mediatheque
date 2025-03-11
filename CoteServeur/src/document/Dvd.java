package document;

import exception.*;
import mediatheque.Abonne;

public class Dvd extends Document {
	private boolean adulte;

	public Dvd(int numero, String titre, boolean adulte) {
		super(numero, titre);
		this.adulte = adulte;
	}
	
	public boolean isAdulte() {
		return adulte;
	}
	
	@Override
	public void reserver(Abonne ab) throws DvdReservationException {
		try {
			super.reserver(ab);
		} catch (ReservationException e) {
			throw new DvdReservationException();
		}
	}

	@Override
	public void emprunter(Abonne ab) throws EmpruntException {
		try{
			if (adulte && ab.getAge() <= 16)
				throw new AgeDvdEmpruntException();
			super.emprunter(ab);
 		} catch (EmpruntException e) {
			throw new DvdEmpruntException();
		}
	}
}
