package com.edu.uco.pch.crosscutting.exceptions.custom;

import com.edu.uco.pch.crosscutting.exceptions.PCHException;
import com.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public final class InitializerPCHException extends PCHException {

	private static final long serialVersionUID = 361322697118899300L;

	public InitializerPCHException(final String mensajeUsuario) {
		super(mensajeUsuario, Lugar.INITIALIZER);
	}
	
	public InitializerPCHException(final String mensajeTecnico, final String mensajeUsuario) {
		super(mensajeTecnico, mensajeUsuario, Lugar.INITIALIZER);
	}

	public InitializerPCHException(final String mensajeTecnico, final String mensajeUsuario,
			final Throwable exeptionRaiz) {
		super(mensajeTecnico, mensajeUsuario, Lugar.INITIALIZER, exeptionRaiz);
	}

}
