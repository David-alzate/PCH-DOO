package com.edu.uco.pch.crosscutting.exceptions.messagecatalog.impl;

import java.util.HashMap;
import java.util.Map;

import com.edu.uco.pch.crosscutting.exceptions.custom.CroscuttingPCHException;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.MessegeCatalog;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.Mensaje;
import com.edu.uco.pch.crosscutting.helpers.ObjectHelper;

public final class MessageCatalogBase implements MessegeCatalog {

	private final Map<String, Mensaje> mensajes = new HashMap<>();

	@Override
	public final void inicializar() {
		mensajes.clear();
		mensajes.put(CodigoMensaje.M00001.getIdentificador(), new Mensaje(CodigoMensaje.M00001,
				"El codigo del mensaje que se quiere obtener del catalogo del mensajes llego nulo.."));
		mensajes.put(CodigoMensaje.M00002.getIdentificador(), new Mensaje(CodigoMensaje.M00002,
				"Se ha presentado un problema tratando de llevar a cabo la operacion"));
		mensajes.put(CodigoMensaje.M00003.getIdentificador(), new Mensaje(CodigoMensaje.M00003,
				"El codigo del mensaje \"${1}\" que se intento obtener no esta en el catalogo de mensajes base.."));
		mensajes.put(CodigoMensaje.M00004.getIdentificador(), new Mensaje(CodigoMensaje.M00004,
				"El mensaje con identificador \"${1}\" que se intento obtener, no esta configurado para revisar en el catalogo de mensajes base "));
	}

	@Override
	public final String obtenerContenidoMensaje(final CodigoMensaje codigo, final String... parametros) {
		return obtenerMensaje(codigo, parametros).getContenido();
	}

	@Override
	public final Mensaje obtenerMensaje(final CodigoMensaje codigo, final String... parametros) {

		if (ObjectHelper.getObjecHelper().isNull(codigo)) {
			var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00001);
			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario);
		}

		if (!codigo.isBase()) {
			var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00004, codigo.getIdentificador());
			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario);
		}

		if (!mensajes.containsKey(codigo.getIdentificador())) {
			var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00003, codigo.getIdentificador());
			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario);
		}
		
		

		return mensajes.get(codigo.getIdentificador());
	}

}
