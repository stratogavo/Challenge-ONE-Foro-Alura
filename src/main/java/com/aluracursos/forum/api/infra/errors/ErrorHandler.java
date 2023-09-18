package com.aluracursos.forum.api.infra.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> error400Handler() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> error400Handler(MethodArgumentNotValidException e) {
		var errors = e.getFieldErrors().stream()
				.map(ValidationErrorData::new).toList();
		return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(IntegrityValidation.class)
	public ResponseEntity<?> errorHandlerBusinessRules(Exception e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	private record ValidationErrorData(String errorField, String errorMessage) {
		public ValidationErrorData(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}
	
}
