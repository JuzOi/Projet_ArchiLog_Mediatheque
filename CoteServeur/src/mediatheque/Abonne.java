package mediatheque;

import document.Document;
import etat.FinReservation;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Abonne implements Runnable {
	private int numero;
	private String nom;
	private LocalDate dateDeNaissance;
	private List<IDocument> documents;
	private Timer finReservation;
	
	public Abonne(int numero, String nom, LocalDate dateDeNaissance) {
		this.numero = numero;
		this.nom = nom;
		this.dateDeNaissance = dateDeNaissance;
		this.documents = new ArrayList<>();
		this.finReservation = null;
	}

	@Override
	public void run() {
		// TO DO
	}

	public void reserve(Document document) {
	}

	public Document getDocumentReserve() {
		return null;
	}
	
	public int numero() {
		return numero;
	}
	
	public int getAge() {
		return Period.between(dateDeNaissance, LocalDate.now()).getYears();
	}

	public String getNom() {
		return nom;
	}
}
