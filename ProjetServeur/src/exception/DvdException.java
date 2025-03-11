package exception;

public class DvdException extends ReservationException {
	 @Override
	 public String getMessage() {
		 return "Ce dvd est déjà emprunté";
	 }
}
