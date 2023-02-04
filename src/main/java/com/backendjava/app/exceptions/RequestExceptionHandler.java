package com.backendjava.app.exceptions;

import com.backendjava.app.models.dto.ExceptionResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@ControllerAdvice
public class RequestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(LocalDate.now(), ex.getMessage());
        request.getDescription(false);
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) throws Exception {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(LocalDate.now(), ex.getMessage());
        request.getDescription(false);
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PublicationNotFoundException.class)
    public final ResponseEntity<Object> handlePublicationNotFoundException(PublicationNotFoundException ex, WebRequest request) throws Exception {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(LocalDate.now(), ex.getMessage());
        request.getDescription(false);
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public final ResponseEntity<Object> handleRoleNotFoundException(RoleNotFoundException ex, WebRequest request) throws Exception {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(LocalDate.now(), ex.getMessage());
        request.getDescription(false);
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errores = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> {
                    return "El campo ".concat(error.getField()).concat(" : ").concat(error.getDefaultMessage());
                })
                .collect(Collectors.toList());

        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(LocalDate.now(), ex.getMessage(), errores);
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }
}
