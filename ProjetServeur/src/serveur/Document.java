package serveur;

import exception.EmpruntException;
import exception.ReservationException;

public abstract class Document implements IDocument {
	private int numero;
	private String titre;
	private Abonne reserver;
	private boolean estEmprunter;
	
	public Document(int numero, String titre) {
		this.numero = numero;
		this.titre = titre;
		this.reserver = null;
		this.estEmprunter = false;
	}
	
	@Override
	public int numero() {
		return numero;
	}
	
	@Override
	public void reserver(Abonne ab) throws ReservationException {
		if (reserver != null || estEmprunter)
			throw new ReservationException();
		reserver = ab;
	}

	@Override
	public void emprunter(Abonne ab) throws EmpruntException {
		if (reserver != ab || estEmprunter)
			throw new EmpruntException();
		estEmprunter = true;
	}

	@Override
	public void retourner() {
		reserver = null;
		estEmprunter = false;
	}

}
