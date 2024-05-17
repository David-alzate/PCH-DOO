package com.edu.uco.pch.data.dao.factory.concrete;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.edu.uco.pch.crosscutting.helpers.SQLHelper;
import com.edu.uco.pch.data.dao.entity.CiudadDAO;
import com.edu.uco.pch.data.dao.entity.DepartamentoDAO;
import com.edu.uco.pch.data.dao.entity.PaisDAO;
import com.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import com.edu.uco.pch.data.dao.entity.concrete.azureaql.CiudadAzureSqlDAO;
import com.edu.uco.pch.data.dao.entity.concrete.azureaql.DepartamentoAzureSqlDAO;
import com.edu.uco.pch.data.dao.entity.concrete.azureaql.PaisAzureSqlDAO;
import com.edu.uco.pch.data.dao.factory.DAOFactory;

public final class AzureSQLDAOFactory extends SqlConnection implements DAOFactory {

	public AzureSQLDAOFactory() {
		super();
		abrirConexion();
	}

	@Override
	public void abrirConexion() {
		try {
			String connectionString = "jbc://<server>:<port>...";
			setConexion(DriverManager.getConnection(connectionString));
		} catch (final SQLException exception) {
			// TODO: handle exception
		} catch (final Exception exception) {

		}

	}

	@Override
	public void cerrarConexion() {
		SQLHelper.close(getConnection());

	}

	@Override
	public void iniciarTransaccion() {
		SQLHelper.initTransaction(getConnection());

	}

	@Override
	public void confirmarTransaccion() {
		SQLHelper.commit(getConnection());

	}

	@Override
	public void cancelarTransaccion() {
		SQLHelper.rollback(getConnection());

	}

	@Override
	public PaisDAO getPaisDAO() {
		return new PaisAzureSqlDAO(getConnection());
	}

	@Override
	public DepartamentoDAO getDepartamentoDAO() {
		return new DepartamentoAzureSqlDAO(getConnection());
	}

	@Override
	public CiudadDAO getCiudadDAO() {
		return new CiudadAzureSqlDAO(getConnection());
	}

}
