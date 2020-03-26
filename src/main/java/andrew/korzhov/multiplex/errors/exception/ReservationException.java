package andrew.korzhov.multiplex.errors.exception;

public class ReservationException extends RuntimeException {

    public ReservationException(String message, Object ... args) {
        super(String.format(message, args));
    }
}
