package info.san.mtg.card.manager.rest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.sqlite.SQLiteException;

import info.san.mtg.card.manager.rest.dto.ErrorDto;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorDto( HttpStatus.NOT_FOUND.value(), "Object with given id does not exist."), 
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
    @ExceptionHandler(value = SQLiteException.class)
    protected ResponseEntity<Object> handleSqlException(SQLiteException ex, WebRequest request) {
    	if (ex.getErrorCode() == 19) {
    		return handleExceptionInternal(ex, new ErrorDto(HttpStatus.CONFLICT.value(), "A unique field with the same value already exists."), 
                    new HttpHeaders(), HttpStatus.CONFLICT, request);
    	}
    	
        return handleExceptionInternal(ex, new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An internal error occurs."), 
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
    		HttpHeaders headers, HttpStatus status, WebRequest request) {
    	Map<String, String> fieldsErrors = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        List<String> globalErrors = ex.getBindingResult().getGlobalErrors().stream().map(ObjectError::getDefaultMessage).toList();
        
        return new ResponseEntity<>(Map.of("errors", Map.of("global", globalErrors, "fields", fieldsErrors)), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
}
