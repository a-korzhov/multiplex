package andrew.korzhov.multiplex.errors.handler;

import andrew.korzhov.multiplex.errors.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Objects;

import static andrew.korzhov.multiplex.errors.ErrorResponseEntityBuilder.buildResponseEntity;

@ControllerAdvice
public class MethodArgumentExceptionHandler {

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        String message = String.format("'%s' should be a valid to '%s', but '%s' is not",
                ex.getName(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName(), ex.getValue());
        apiError.setMessage(message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleNotValid(MethodArgumentNotValidException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String defaultMessage = fieldErrors.get(0).getDefaultMessage();
        apiError.setMessage(defaultMessage);
        return buildResponseEntity(apiError);
    }

}
