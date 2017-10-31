package priv.rdo.sdaspring.task09;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import priv.rdo.sdaspring.security.model.UsernameExistsException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class Task09ControllerAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handle(UsernameExistsException e) {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("errorId", 5);
        errorResponse.put("message", e.getMessage());

        return errorResponse;
    }

}
