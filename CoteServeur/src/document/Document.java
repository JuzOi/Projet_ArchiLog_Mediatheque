package document;

import exception.EmpruntException;
import exception.ReservationException;
import mediatheque.Abonne;
import mediatheque.IDocument;

public abstract class Document implements IDocument {
	private int numero;
	private String titre;
	private EtatDocument etat;
	
	public Document(int numero, String titre, EtatDocument etatInitial) {
		this.numero = numero;
		this.titre = titre;
		this.etat = etatInitial;
	}
	
	@Override
	public int numero() {
		return numero;
	}
	
	@Override
	public synchronized void reserver(Abonne ab) throws ReservationException {
		etat = etat.reserver(this, ab);
	}

	@Override
	public synchronized void emprunter(Abonne ab) throws EmpruntException {
		etat = etat.emprunter(this, ab);
	}

	@Override
	public synchronized void retourner() {
		etat = etat.retourner(this);
	}
}
