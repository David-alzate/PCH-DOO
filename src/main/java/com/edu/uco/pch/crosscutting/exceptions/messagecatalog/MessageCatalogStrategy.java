package com.edu.uco.pch.crosscutting.exceptions.messagecatalog;

import com.edu.uco.pch.crosscutting.exceptions.custom.CroscuttingPCHException;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.Mensaje;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.impl.MessageCatalogBase;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.impl.MessageCatalogExternalService;
import com.edu.uco.pch.crosscutting.helpers.ObjectHelper;

public final class MessageCatalogStrategy {
	
	private static final MessegeCatalog base = new MessageCatalogBase();
	private static final MessegeCatalog externalService = new MessageCatalogExternalService();
	
	static {
		inicializar();
	}
	
	private MessageCatalogStrategy() {
		super();
		
	}
	
	public static void inicializar() {
		base.inicializar();
		externalService.inicializar();		

	}
	
	private static final MessegeCatalog getStrategy(final boolean isBase) {
		return isBase ? base : externalService;
	}
	
	public static final Mensaje getMensaje(final CodigoMensaje codigo, 
			final String...parametros) {
		
		if (ObjectHelper.getObjecHelper().isNull(codigo)) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00001);
			throw new CroscuttingPCHException(mensajeTecnico, mensajeUsuario);
		}
		
		return getStrategy(codigo.isBase()).obtenerMensaje(codigo, parametros);
	}
	
	public static final String getConetenidoMensaje(final CodigoMensaje codigo, final String... parametros) {
		return getMensaje(codigo, parametros).getContenido();
	}

}
