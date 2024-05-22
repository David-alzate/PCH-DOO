package com.edu.uco.pch.crosscutting.exceptions.custom;

import com.edu.uco.pch.crosscutting.exceptions.PCHException;
import com.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public final class DtoPCHException extends PCHException {

	private static final long serialVersionUID = 361322697118899300L;

	public DtoPCHException(final String mensajeUsuario) {
		super(mensajeUsuario, Lugar.DTO);
	}
	
	public DtoPCHException(final String mensajeTecnico, final String mensajeUsuario) {
		super(mensajeTecnico, mensajeUsuario, Lugar.DTO);
	}

	public DtoPCHException(final String mensajeTecnico, final String mensajeUsuario, final Throwable exeptionRaiz) {
		super(mensajeTecnico, mensajeUsuario, Lugar.DTO, exeptionRaiz);
	}

}