package document;

import exception.EmpruntException;
import exception.ReservationException;
import mediatheque.Abonne;
import mediatheque.IDocument;

import java.time.LocalDateTime;

public abstract class Document implements IDocument {
	private int numero;
	private String titre;
	private Abonne reserverPar;
	private boolean estEmprunter;
	private LocalDateTime reserveJusquA;
	
	public Document(int numero, String titre) {
		this.numero = numero;
		this.titre = titre;
		this.reserverPar = null;
		this.estEmprunter = false;
	}
	
	@Override
	public int numero() {
		return numero;
	}
	
	@Override
	public void reserver(Abonne ab) throws ReservationException {
		if (reserverPar != null || estEmprunter)
			throw new ReservationException("Ce " + this.getClass().getName().toLowerCase() + " est réservé jusqu’à " + reserveJusquA);
		reserverPar = ab;
		reserveJusquA = LocalDateTime.now().plusHours(1);
	}

	@Override
	public void emprunter(Abonne ab) throws EmpruntException {
		if (reserverPar != ab || estEmprunter)
			throw new EmpruntException("Ce " + this.getClass().getName().toLowerCase() + " est déjà emprunté");
		estEmprunter = true;
	}

	@Override
	public void retourner() {
		reserverPar = null;
		estEmprunter = false;
	}
}
