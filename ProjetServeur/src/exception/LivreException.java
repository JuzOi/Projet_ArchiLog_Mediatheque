package exception;

public class LivreException extends ReservationException {
	@Override
	public String getMessage() {
		return "Ce livre est déjà emprunté";
	}
}
