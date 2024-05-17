package com.edu.uco.pch.data.dao.factory;

import com.edu.uco.pch.data.dao.entity.CiudadDAO;
import com.edu.uco.pch.data.dao.entity.DepartamentoDAO;
import com.edu.uco.pch.data.dao.entity.PaisDAO;
import com.edu.uco.pch.data.dao.factory.concrete.AzureSQLDAOFactory;

public interface DAOFactory {
	
	default DAOFactory getFactory() {
		return new AzureSQLDAOFactory();
	}

	void abrirConexion();

	void cerrarConexion();

	void iniciarTransaccion();

	void confirmarTransaccion();

	void cancelarTransaccion();
	
	PaisDAO getPaisDAO();
	
	DepartamentoDAO getDepartamentoDAO();
	
	CiudadDAO getCiudadDAO();

}
