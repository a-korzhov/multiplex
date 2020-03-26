package andrew.korzhov.multiplex.errors.handler;

import andrew.korzhov.multiplex.errors.ApiError;
import andrew.korzhov.multiplex.errors.exception.NotFoundException;
import andrew.korzhov.multiplex.errors.exception.ReservationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static andrew.korzhov.multiplex.errors.ErrorResponseEntityBuilder.buildResponseEntity;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    //NOT FOUND
    @ExceptionHandler(value = {
            NotFoundException.class,
    })
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }


    // FOUND
    @ExceptionHandler(value = {DataIntegrityViolationException.class,})
    protected ResponseEntity<Object> handleSeats() {
        ApiError apiError = new ApiError(HttpStatus.FOUND);
        apiError.setMessage("Those seats are already reserved");
        return buildResponseEntity(apiError);
    }

    // BAD REQUEST
    @ExceptionHandler(value = {ReservationException .class})
    protected ResponseEntity<Object> handleBadRequest(RuntimeException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

}
