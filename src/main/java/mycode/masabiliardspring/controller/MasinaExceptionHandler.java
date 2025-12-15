package mycode.masabiliardspring.controller;

import mycode.masabiliardspring.exceptions.MasinaAlreadyExistsException;
import mycode.masabiliardspring.exceptions.MasinaDoesntExistException;
import mycode.masabiliardspring.exceptions.NoMasinaFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MasinaExceptionHandler {

    @ExceptionHandler({MasinaDoesntExistException.class, NoMasinaFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RuntimeException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MasinaAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleConflictException(RuntimeException ex) {
        return ex.getMessage();
    }
}