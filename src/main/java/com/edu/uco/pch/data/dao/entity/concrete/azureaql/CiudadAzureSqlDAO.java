package com.edu.uco.pch.data.dao.entity.concrete.azureaql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.edu.uco.pch.crosscutting.exceptions.custom.DataPCHException;
import com.edu.uco.pch.data.dao.entity.CiudadDAO;
import com.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import com.edu.uco.pch.entity.CiudadEntity;

public final class CiudadAzureSqlDAO extends SqlConnection implements CiudadDAO {

	public CiudadAzureSqlDAO(Connection conexion) {
		super(conexion);
	}

	@Override
	public final void crear(final CiudadEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();

		sentenciaSql.append("INSERT INTO Ciudad (id, nombre, departamento) ");
		sentenciaSql.append("SELECT ?, ?, ?");

		try (final PreparedStatement sentenciaSqlPreparada = getConnection()
				.prepareStatement(sentenciaSql.toString())) {
			
			sentenciaSqlPreparada.setObject(1, data.getId());
			sentenciaSqlPreparada.setString(2, data.getNombre());
			sentenciaSqlPreparada.setObject(3, data.getDepartamento().getId());
			
			sentenciaSqlPreparada.executeUpdate();

		} catch (final SQLException excepcion) {
			var mensajeUsuario = "Se ha presaentado un problema tratando de crear la ciudad \"${1}\" por favor contatcte al administrador del sistema";
			var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el insert de la ciudad \"${1}\" en la tabla Pais de la base de datos";
			
			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
			
		}catch(final Exception excepcion) {
			
			var mensajeUsuario = "Se ha presaentado un problema tratando de crear la ciudad \"${1}\" por favor contatcte al administrador del sistema";
			var mensajeTecnico = "Se ha presentado un proble INESPERADO con una exception de tipo EXEption tratando de realizar el insert de la ciudad \"${1}\" en la tabla Pais de la base de datos";
		
			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
		}
	}

	@Override
	public List<CiudadEntity> consultar(CiudadEntity data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificar(CiudadEntity data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminar(UUID id) {
		// TODO Auto-generated method stub

	}

}
