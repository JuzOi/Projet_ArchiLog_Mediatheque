package exception;

public class AgeDvdEmpruntException extends DvdEmpruntException {
	@Override
	public String getMessage() {
		return "Vous n’avez pas l’âge pour emprunter ce DVD";
	}
}
