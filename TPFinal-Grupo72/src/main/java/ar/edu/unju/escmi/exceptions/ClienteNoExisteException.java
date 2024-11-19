package ar.edu.unju.escmi.exceptions;

public class ClienteNoExisteException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ClienteNoExisteException(String errorMessage) {
		super(errorMessage);
	}

}
