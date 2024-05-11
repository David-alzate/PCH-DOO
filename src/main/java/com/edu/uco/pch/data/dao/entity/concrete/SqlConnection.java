package com.edu.uco.pch.data.dao.entity.concrete;

import java.sql.Connection;

import com.edu.uco.pch.crosscutting.exceptions.custom.DataPCHException;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import com.edu.uco.pch.crosscutting.helpers.SQLHelper;

public class SqlConnection {
	
	private Connection conexion;
	
	protected SqlConnection(final Connection conexion) {
		setConnection(conexion);
	}
	
	protected SqlConnection() {
		super();
	}

	protected final Connection getConnection() {
		return conexion;
	}

	protected final void setConnection(final Connection connection) {
		
		if (!SQLHelper.isOpen(conexion)) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var MensajeTecnico = "No es posible crear el DAO deseado con una conexion cerrada";
			
			throw new DataPCHException(mensajeUsuario, MensajeTecnico);
		}
		
		this.conexion = connection;
	}
	
	

}
