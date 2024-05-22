package com.edu.uco.pch.crosscutting.exceptions.custom;

import com.edu.uco.pch.crosscutting.exceptions.PCHException;
import com.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public final class ControllerPCHException extends PCHException {

	private static final long serialVersionUID = 361322697118899300L;

	public ControllerPCHException(final String mensajeUsuario) {
		super(mensajeUsuario, Lugar.CRONTROLLER);
	}
	
	public ControllerPCHException(final String mensajeTecnico, final String mensajeUsuario) {
		super(mensajeTecnico, mensajeUsuario, Lugar.CRONTROLLER);
	}

	public ControllerPCHException(final String mensajeTecnico, final String mensajeUsuario,
			final Throwable exeptionRaiz) {
		super(mensajeTecnico, mensajeUsuario, Lugar.CRONTROLLER, exeptionRaiz);
	}

}
