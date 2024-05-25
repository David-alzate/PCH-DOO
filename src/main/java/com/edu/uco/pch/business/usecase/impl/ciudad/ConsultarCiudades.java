package com.edu.uco.pch.business.usecase.impl.ciudad;

import java.util.List;

import com.edu.uco.pch.business.assembler.entity.impl.CiudadAssemblerEntity;
import com.edu.uco.pch.business.domain.CiudadDomain;
import com.edu.uco.pch.business.usecase.UseCaseWithReturn;
import com.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import com.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import com.edu.uco.pch.data.dao.factory.DAOFactory;

public class ConsultarCiudades implements UseCaseWithReturn<CiudadDomain, List<CiudadDomain>> {

	private DAOFactory factory;

	public ConsultarCiudades(final DAOFactory factory) {
		if (ObjectHelper.getObjecHelper().isNull(factory)) {
			var mensajeUsuario = "se ha presentado un error intentando hacer la consulta de las ciudad";
			var mensajeTecnico = "El dao factory para consultar la ciudad llego nulo..";
			throw new BusinessPCHException(mensajeTecnico, mensajeUsuario);
		}
		this.factory = factory;
	}

	@Override
	public List<CiudadDomain> execute(final CiudadDomain data) {
		var ciudadEntityTmp = CiudadAssemblerEntity.getInstance().toEntity(data);
		var resultadosEntity = factory.getCiudadDAO().consultar(ciudadEntityTmp);

		return CiudadAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
	}

}
