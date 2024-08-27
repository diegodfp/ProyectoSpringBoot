package com.projecto.project.survey_json.Infraestructure.Exceptioncontroller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.projecto.project.survey_json.Infraestructure.Models.ErrorCustom;



@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({ArithmeticException.class})

    public ResponseEntity<ErrorCustom> divisionByZero(Exception ex) {

        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Error división por cero!");
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        //return ResponseEntity.internalServerError().body(error);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }

    @ExceptionHandler(NoResourceFoundException.class)

    public ResponseEntity<ErrorCustom> notFoundEx(NoResourceFoundException e) {

        ErrorCustom error = new ErrorCustom();
        error.setDate(new Date());
        error.setError("Api rest no encontrado");
        error.setMessage(e.getMessage());

        error.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
    }
}
