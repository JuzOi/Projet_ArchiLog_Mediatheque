package exception;

public class LivreReservationException extends ReservationException {
    @Override
    public String getMessage() {
        return "ce livre est réservé jusqu’à";
    }
}
