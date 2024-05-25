package com.edu.uco.pch.business.facade.impl.ciudad;

import java.util.List;

import com.edu.uco.pch.business.assembler.dto.impl.CIudadAssembleDTO;
import com.edu.uco.pch.business.facade.FacadeWithReturn;
import com.edu.uco.pch.business.usecase.impl.ciudad.ConsultarCiudades;
import com.edu.uco.pch.crosscutting.exceptions.PCHException;
import com.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import com.edu.uco.pch.data.dao.factory.DAOFactory;
import com.edu.uco.pch.dto.CiudadDTO;

public final class ConsultarCiudadesFacade implements FacadeWithReturn<CiudadDTO, List<CiudadDTO>> {

	private DAOFactory daoFactory;

	public ConsultarCiudadesFacade() {
		daoFactory = DAOFactory.getFactory();
	}

	@Override
	public List<CiudadDTO> execute(final CiudadDTO dto) {

		try {
			var usecase = new ConsultarCiudades(daoFactory);
			var ciudadDomain = CIudadAssembleDTO.getInstance().toDomain(dto);
			var resultadosDomain = usecase.execute(ciudadDomain);
			return CIudadAssembleDTO.getInstance().toDTOCollection(resultadosDomain);

		} catch (final PCHException exception) {
			throw exception;
		} catch (final Exception exception) {

			var mensajeUsuario = "Se ha presentado un problema consultar la informacion de las ciudad";
			var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de consultar la ciudad";

			throw new BusinessPCHException(mensajeTecnico, mensajeUsuario, exception);

		} finally {
			daoFactory.cerrarConexion();
		}
	}

}
