package exception;

public class DvdEmpruntException extends EmpruntException {
	 @Override
	 public String getMessage() {
		 return "Ce dvd est déjà emprunté";
	 }
}
