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
	public synchronized void emprunter(Abonne ab) throws EmpruntException {
		if (adulte && ab.getAge() <= 16)
			throw new EmpruntException("vous n’avez pas l’âge pour emprunter ce DVD");
		super.emprunter(ab);
	}

	@Override
	public String toString() {
		return "DVD";
	}
}
