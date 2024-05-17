package com.edu.uco.pch.data.dao.entity.concrete.azureaql;

import java.sql.Connection;
import java.util.List;

import com.edu.uco.pch.data.dao.entity.DepartamentoDAO;
import com.edu.uco.pch.data.dao.entity.concrete.SqlConnection;
import com.edu.uco.pch.entity.DepartamentoEntity;

public class DepartamentoAzureSqlDAO extends SqlConnection  implements DepartamentoDAO{
	
	public DepartamentoAzureSqlDAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public List<DepartamentoEntity> consultar(DepartamentoEntity data) {
		// TODO Auto-generated method stub
		return null;
	}



}
