
package com.devsuperior.dscatalog.services.exceptions;

// exception : tem que ser obrigatoriamente tratada. 
// RuntimeException : tratado em tempo de execução
public class EntityNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	public EntityNotFoundException ( String msg )
	{
		super(msg);
	}

}
