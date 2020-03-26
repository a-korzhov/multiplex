package andrew.korzhov.multiplex.errors.exception;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
