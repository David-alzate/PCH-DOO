package com.edu.uco.pch.crosscutting.exceptions.custom;

import com.edu.uco.pch.crosscutting.exceptions.PCHException;
import com.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public final class DataPCHException extends PCHException {

	private static final long serialVersionUID = 361322697118899300L;

	public DataPCHException(final String mensajeUsuario) {
		super(mensajeUsuario, Lugar.DATA);
	}

	public DataPCHException(final String mensajeTecnico, final String mensajeUsuario, final Throwable exeptionRaiz) {
		super(mensajeTecnico, mensajeUsuario, Lugar.DATA, exeptionRaiz);
	}

}
