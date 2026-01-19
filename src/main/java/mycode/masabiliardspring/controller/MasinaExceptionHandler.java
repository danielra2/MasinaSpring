package mycode.masabiliardspring.controller;

import mycode.masabiliardspring.exceptions.MasinaAlreadyExistsException;
import mycode.masabiliardspring.exceptions.MasinaDoesntExistException;
import mycode.masabiliardspring.exceptions.NoMasinaFoundException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class MasinaExceptionHandler {

    @ExceptionHandler(MasinaAlreadyExistsException.class)
    public ResponseEntity<Map<String,Object>>handleAlreadyExists(MasinaAlreadyExistsException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler({MasinaDoesntExistException.class, NoMasinaFoundException.class})
    public ResponseEntity<Map<String,Object>>handleNotFound(RuntimeException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class,MethodArgumentTypeMismatchException.class, PropertyReferenceException.class})
    public ResponseEntity<Map<String, Object>> handleBadRequest(Exception ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Parametrii furnizati sunt invalizi");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>>handleGeneric(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "A aparut o eroare neasteptata.");
    }

    // Metoda ajutatoare pentru construirea raspunsului JSON
    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = Map.of(
                "timestamp",Instant.now().toString(),
                "status",status.value(),
                "error",message
        );
        return ResponseEntity.status(status).body(body);
    }
}