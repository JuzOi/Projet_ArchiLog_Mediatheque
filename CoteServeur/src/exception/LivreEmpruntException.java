package exception;

public class LivreEmpruntException extends EmpruntException {
	@Override
	public String getMessage() {
		return "Ce livre est déjà emprunté";
	}
}
