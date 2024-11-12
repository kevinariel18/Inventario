package com.krakedev.inventarios3.exepciones;

public class KrakeDevException extends Exception {

	public KrakeDevException(String mensaje) {
		super();
	}
	
	

    // Constructor con mensaje de error y causa
    public KrakeDevException(String message, Throwable cause) {
        super(message, cause);
    }

	
	
}
