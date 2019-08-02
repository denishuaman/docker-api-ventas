package com.dgha.exception;

public class EmptyObjectException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmptyObjectException(String mensaje) {
		super(mensaje);
	}
}
