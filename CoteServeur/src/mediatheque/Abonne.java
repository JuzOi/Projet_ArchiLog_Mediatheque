package mediatheque;

import document.Document;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Abonne implements Runnable {
	private int numero;
	private String nom;
	private LocalDate dateDeNaissance;
	List<IDocument> documents;
	
	public Abonne(int numero, String nom, LocalDate dateDeNaissance) {
		this.numero = numero;
		this.nom = nom;
		this.dateDeNaissance = dateDeNaissance;
		this.documents = new ArrayList<>();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public void reserve(Document document) {
		documents.add(document);
	}

	public Document getDocumentReserve() {
		return null;
	}
	
	public int numero() {
		return numero;
	}
	
	public int getAge() {
		LocalDate today = LocalDate.now();
		Period age = Period.between(dateDeNaissance, today);
		return age.getYears();
	}
}
