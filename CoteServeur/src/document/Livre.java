package document;

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
	public String toString() {
		return "livre";
	}
}
