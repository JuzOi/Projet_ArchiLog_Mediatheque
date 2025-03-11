package exception;

public class DvdReservationException extends ReservationException {
    @Override
    public String getMessage() {
        return "ce livre est réservé jusqu’à";
    }
}
