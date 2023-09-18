package com.aluracursos.forum.api.infra.errors;

import jakarta.validation.ValidationException;

@SuppressWarnings("serial")
public class IntegrityValidation extends ValidationException {

	public IntegrityValidation(String error) {
		super(error);
	}
	
}
