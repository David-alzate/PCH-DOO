package com.edu.uco.pch.crosscutting.exceptions.messagecatalog;

import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.Mensaje;

public interface MessegeCatalog {

	void inicializar();

	String obtenerContenidoMensaje(final CodigoMensaje codigo, String... parametros);

	Mensaje obtenerMensaje(final CodigoMensaje codigo, String... parametros);

}
