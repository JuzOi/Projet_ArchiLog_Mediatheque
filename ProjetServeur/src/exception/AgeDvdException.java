package exception;

public class AgeDvdException extends ReservationException{
	@Override
	public String getMessage() {
		return "Vous n’avez pas l’âge pour emprunter ce DVD";
	}
}
