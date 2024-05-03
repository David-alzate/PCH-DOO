package com.edu.uco.pch.crosscutting.exceptions.custom;

import com.edu.uco.pch.crosscutting.exceptions.PCHException;
import com.edu.uco.pch.crosscutting.exceptions.enums.Lugar;

public final class EntityPCHException extends PCHException {

	private static final long serialVersionUID = 361322697118899300L;

	public EntityPCHException(final String mensajeUsuario) {
		super(mensajeUsuario, Lugar.ENTITY);
	}

	public EntityPCHException(final String mensajeTecnico, final String mensajeUsuario, final Throwable exeptionRaiz) {
		super(mensajeTecnico, mensajeUsuario, Lugar.ENTITY, exeptionRaiz);
	}

}
