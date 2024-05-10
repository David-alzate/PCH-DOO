package com.edu.uco.pch.crosscutting.exceptions.messagecatalog.impl;

import java.util.HashMap;
import java.util.Map;

import com.edu.uco.pch.crosscutting.exceptions.custom.CroscuttingPCHException;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.MessegeCatalog;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.Mensaje;
import com.edu.uco.pch.crosscutting.helpers.ObjectHelper;

public final class MessageCatalogExternalService implements MessegeCatalog{
	
	private final Map<String, Mensaje> mensajes = new HashMap<>();


	@Override
	public final void inicializar() {
       mensajes.clear();
       mensajes.put(CodigoMensaje.M00007.getIdentificador(), new Mensaje(CodigoMensaje.M00007,
				"La transaccion se ha completado de forma satisfactoria.."));
	}

	@Override
	public final String obtenerContenidoMensaje(final CodigoMensaje codigo, final String... parametros) {
		return obtenerMensaje(codigo, parametros).getContenido();
	}

	@Override
	public final Mensaje obtenerMensaje(final CodigoMensaje codigo, final String... parametros) {
		
		if (ObjectHelper.getObjecHelper().isNull(codigo)) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00001);
			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario);
		}

		if (codigo.isBase()) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00005, codigo.getIdentificador());
			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario);
		}

		if (!mensajes.containsKey(codigo.getIdentificador())) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00006, codigo.getIdentificador());
			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario);
		}
		
		

		return mensajes.get(codigo.getIdentificador());
	}

	}

