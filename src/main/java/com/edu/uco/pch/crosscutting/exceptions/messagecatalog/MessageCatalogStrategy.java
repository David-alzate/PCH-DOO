package com.edu.uco.pch.crosscutting.exceptions.messagecatalog;

import com.edu.uco.pch.crosscutting.exceptions.custom.CroscuttingPCHException;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.Mensaje;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.impl.MessageCatalogBase;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.impl.MessageCatalogBaseExternalService;
import com.edu.uco.pch.crosscutting.helpers.ObjectHelper;

public final class MessageCatalogStrategy {
	
	private static final MessegeCatalog base = new MessageCatalogBase();
	private static final MessegeCatalog externalService = new MessageCatalogBaseExternalService();
	
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
	
	public static final Mensaje getMensaje9(final CodigoMensaje codigo, 
			final String...parametros) {
		
		if (ObjectHelper.getObjecHelper().isNull(codigo)) {
			throw new CroscuttingPCHException(null, null, null);
		}
		return getStrategy(codigo.isBase()).obtenerMensaje(codigo, parametros);
	}

}
