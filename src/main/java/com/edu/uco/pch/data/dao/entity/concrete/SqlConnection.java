package com.edu.uco.pch.data.dao.entity.concrete;

import java.sql.Connection;

import com.edu.uco.pch.crosscutting.exceptions.custom.DataPCHException;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import com.edu.uco.pch.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import com.edu.uco.pch.crosscutting.helpers.SQLHelper;

public class SqlConnection {

	protected Connection conexion;

	protected SqlConnection(final Connection conexion) {
		setConexion(conexion);
	}

	protected SqlConnection() {
		super();
	}

	protected final Connection getConexion() {
		return conexion;
	}

	protected final void setConexion(final Connection conexion) {

		if (!SQLHelper.isOpen(conexion)) {
			var mensajeUsuario = MessageCatalogStrategy.getConetenidoMensaje(CodigoMensaje.M00002);
			var MensajeTecnico = "No es posible crear el DAO deseado con una conexion cerrada";

			throw new DataPCHException(mensajeUsuario, MensajeTecnico);
		}

		this.conexion = conexion;
	}

}
