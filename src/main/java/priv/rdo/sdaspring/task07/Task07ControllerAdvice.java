package priv.rdo.sdaspring.task07;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Stream.empty;

@RestControllerAdvice
public class Task07ControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Map<String, Object> handle(HttpMessageNotReadableException e) {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("errorId", 5);
        errorResponse.put("message", "body is missing");

        Map<String, Object> details = new HashMap<>();
        details.put("detail1", "value");
        details.put("detail2", "value");

        errorResponse.put("details", details);

        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public Map<String, Object> handle(AnimalException e) {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("message",
                "you'll always get an exception here");

        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)

    public ErrorResponse handle(MethodArgumentNotValidException e) {
        System.out.println();

        ErrorResponse response = new ErrorResponse();
        Optional.of(e)
                .map(MethodArgumentNotValidException::getBindingResult)
                .map(Errors::getAllErrors)
                .map(Collection::stream)
                .orElse(empty())
                .map(this::asErrorDescription)
                .forEach(response::addErrorDetails);

        return response;
    }

    private ErrorDetail asErrorDescription(ObjectError objectError) {
        if (!(objectError instanceof FieldError)) {
            return new ErrorDetail("message", "weird error, handle later", null);
        }

        FieldError fieldError = (FieldError) objectError;

        return new ErrorDetail(fieldError.getField(),
                fieldError.getDefaultMessage(),
                fieldError.getRejectedValue());
    }
}
