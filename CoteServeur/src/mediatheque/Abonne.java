package mediatheque;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Abonne  {
	private int numero;
	private String nom;
	private LocalDate dateDeNaissance;
	private List<IDocument> documents;
	private boolean estBanni;
	private String email;

	public Abonne(int numero, String nom, LocalDate dateDeNaissance, String email) {
		this.numero = numero;
		this.nom = nom;
		this.dateDeNaissance = dateDeNaissance;
		this.estBanni = false;
		this.email = email;
	}

	public void bannir(){
		long unMois = 30L * 24 * 60 * 60 * 1000;
		new Timer(nom).schedule(new Debanissement(this), unMois);
		estBanni = true;
		for (IDocument document : documents) {
			document.retourner();
		}
	}

	public void debannir(){
		estBanni = false;
	}

	public boolean estBanni(){
		return estBanni;
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

	public String getEmail() {
		return email;
	}
}
