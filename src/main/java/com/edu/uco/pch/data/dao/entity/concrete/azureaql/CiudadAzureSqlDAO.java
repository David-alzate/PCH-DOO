package com.edu.uco.pch.data.dao.entity.concrete.azureaql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.edu.uco.pch.crosscutting.exceptions.custom.DataPCHException;
import com.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import com.edu.uco.pch.crosscutting.helpers.TextHelper;
import com.edu.uco.pch.crosscutting.helpers.UUIDHelper;
import com.edu.uco.pch.data.dao.entity.CiudadDAO;
import com.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import com.edu.uco.pch.entity.CiudadEntity;
import com.edu.uco.pch.entity.DepartamentoEntity;
import com.edu.uco.pch.entity.PaisEntity;

public final class CiudadAzureSqlDAO extends SqlConnection implements CiudadDAO {

	public CiudadAzureSqlDAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public final void crear(final CiudadEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();

		sentenciaSql.append("INSERT INTO Ciudad (id, nombre, departamento) ");
		sentenciaSql.append("SELECT ?, ?, ?");

		try (final PreparedStatement sentenciaSqlPreparada = getConexion()
				.prepareStatement(sentenciaSql.toString())) {

			sentenciaSqlPreparada.setObject(1, data.getId());
			sentenciaSqlPreparada.setString(2, data.getNombre());
			sentenciaSqlPreparada.setObject(3, data.getDepartamento().getId());

			sentenciaSqlPreparada.executeUpdate();

		} catch (final SQLException excepcion) {
			var mensajeUsuario = "Se ha presaentado un problema tratando de crear la ciudad \"${1}\" por favor contatcte al administrador del sistema";
			var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el insert de la ciudad \"${1}\" en la tabla \"Pais\" de la base de datos Azure SQL";

			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {

			var mensajeUsuario = "Se ha presaentado un problema tratando de crear la ciudad \"${1}\" por favor contatcte al administrador del sistema";
			var mensajeTecnico = "Se ha presentado un problema INESPERADO con una exception de tipo exeption tratando de realizar el insert de la ciudad \"${1}\" en la tabla \\\"Pais\\\" de la base de datos Azure SQL";

			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
		}
	}

	@Override
	public List<CiudadEntity> consultar(final CiudadEntity data) {
	    final StringBuilder sentenciaSql = new StringBuilder();
	    sentenciaSql.append("SELECT c.id, c.nombre, d.id as idDepartamento, d.nombre as nombreDepartamento, p.id as idPais, p.nombre as nombrePais");
	    sentenciaSql.append(" FROM Ciudad c");
	    sentenciaSql.append(" INNER JOIN Departamento d ON c.departamento = d.id");
	    sentenciaSql.append(" INNER JOIN Pais p ON d.pais = p.id");
	    sentenciaSql.append(" WHERE 1=1");

	    final List<Object> parametros = new ArrayList<>();

	    if (!ObjectHelper.getObjecHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
	        sentenciaSql.append(" AND c.id = ?");
	        parametros.add(data.getId());
	    }
	    if (!TextHelper.isNullOrEmpty(data.getNombre())) {
	        sentenciaSql.append(" AND c.nombre = ?");
	        parametros.add(data.getNombre());
	    }
	    if (!ObjectHelper.getObjecHelper().isNull(data.getDepartamento()) && 
	        !ObjectHelper.getObjecHelper().isNull(data.getDepartamento().getId()) && 
	        !data.getDepartamento().getId().equals(UUIDHelper.getDefault())) {
	        sentenciaSql.append(" AND c.departamento = ?");
	        parametros.add(data.getDepartamento().getId());
	    }

	    final List<CiudadEntity> ciudades = new ArrayList<>();

	    try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
	        for (int i = 0; i < parametros.size(); i++) {
	            sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
	        }

	        try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
	            while (resultado.next()) {
	                CiudadEntity ciudad = CiudadEntity.build();
	                ciudad.setId(UUIDHelper.convertirToUUID(resultado.getString("id")));
	                ciudad.setNombre(resultado.getString("nombre"));
	                
	                DepartamentoEntity departamento = DepartamentoEntity.build();
	                departamento.setId(UUIDHelper.convertirToUUID(resultado.getString("idDepartamento")));
	                departamento.setNombre(resultado.getString("nombreDepartamento"));

	                PaisEntity pais = PaisEntity.build();
	                pais.setId(UUIDHelper.convertirToUUID(resultado.getString("idPais")));
	                pais.setNombre(resultado.getString("nombrePais"));
	                
	                departamento.setPais(pais);
	                ciudad.setDepartamento(departamento);
	                
	                ciudades.add(ciudad);
	            }
	        }

	    } catch (final SQLException excepcion) {
	        var mensajeUsuario = "Se ha presentado un problema tratando de consultar las ciudades. Por favor, contacte al administrador del sistema.";
	        var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar la consulta de las ciudades en la tabla \"Ciudad\" de la base de datos Azure SQL.";
	        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);

	    } catch (final Exception excepcion) {
	        var mensajeUsuario = "Se ha presentado un problema tratando de consultar las ciudades. Por favor, contacte al administrador del sistema.";
	        var mensajeTecnico = "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar la consulta de las ciudades en la tabla \"Ciudad\" de la base de datos Azure SQL.";
	        throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
	    }

	    return ciudades;
	}








	@Override
	public void modificar(final CiudadEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();

		sentenciaSql.append("UPDATE Ciudad SET nombre = ?, departamento = ? WHERE id = ?");

		try (final PreparedStatement sentenciaSqlPreparada = getConexion()
				.prepareStatement(sentenciaSql.toString())) {

			sentenciaSqlPreparada.setObject(1, data.getId());
			sentenciaSqlPreparada.setString(2, data.getNombre());
			sentenciaSqlPreparada.setObject(3, data.getDepartamento().getId());

			sentenciaSqlPreparada.executeUpdate();

		} catch (final SQLException excepcion) {
			var mensajeUsuario = "Se ha presaentado un problema tratando de crear la ciudad \"${1}\" por favor contatcte al administrador del sistema";
			var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el insert de la ciudad \"${1}\" en la tabla \"Pais\" de la base de datos Azure SQL";

			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {

			var mensajeUsuario = "Se ha presaentado un problema tratando de crear la ciudad \"${1}\" por favor contatcte al administrador del sistema";
			var mensajeTecnico = "Se ha presentado un problema INESPERADO con una exception de tipo exeption tratando de realizar el insert de la ciudad \"${1}\" en la tabla \\\"Pais\\\" de la base de datos Azure SQL";

			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
		}

	}

	@Override
	public void eliminar(final UUID id) {
		final StringBuilder sentenciaSql = new StringBuilder();

		sentenciaSql.append("DELETE FROM Ciudad WHERE id = ?");

		try (final PreparedStatement sentenciaSqlPreparada = getConexion()
				.prepareStatement(sentenciaSql.toString())) {

			sentenciaSqlPreparada.setObject(1, id);

			sentenciaSqlPreparada.executeUpdate();

		} catch (final SQLException excepcion) {
			var mensajeUsuario = "Se ha presaentado un problema tratando de crear la ciudad \"${1}\" por favor contatcte al administrador del sistema";
			var mensajeTecnico = "Se ha presentado una SQLException tratando de realizar el insert de la ciudad \"${1}\" en la tabla \"Pais\" de la base de datos Azure SQL";

			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {

			var mensajeUsuario = "Se ha presaentado un problema tratando de crear la ciudad \"${1}\" por favor contatcte al administrador del sistema";
			var mensajeTecnico = "Se ha presentado un problema INESPERADO con una exception de tipo exeption tratando de realizar el insert de la ciudad \"${1}\" en la tabla \\\"Pais\\\" de la base de datos Azure SQL";

			throw new DataPCHException(mensajeUsuario, mensajeTecnico, excepcion);
		}

	}

}
