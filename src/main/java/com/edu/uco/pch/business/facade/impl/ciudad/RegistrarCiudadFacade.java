package com.edu.uco.pch.business.facade.impl.ciudad;

import com.edu.uco.pch.business.assembler.dto.impl.CIudadAssembleDTO;
import com.edu.uco.pch.business.facade.FacadeWithoutReturn;
import com.edu.uco.pch.business.usecase.impl.ciudad.RegistrarCiudad;
import com.edu.uco.pch.crosscutting.exceptions.PCHException;
import com.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import com.edu.uco.pch.data.dao.factory.DAOFactory;
import com.edu.uco.pch.dto.CiudadDTO;

public final class RegistrarCiudadFacade implements FacadeWithoutReturn<CiudadDTO> {

	private DAOFactory daoFactory;

	public RegistrarCiudadFacade() {
		daoFactory = DAOFactory.getFactory();
	}

	@Override
	public void exececute(final CiudadDTO dto) {

		daoFactory.iniciarTransaccion();

		try {
			var usecase = new RegistrarCiudad(daoFactory);
			var ciudadDomain = CIudadAssembleDTO.getInstance().toDomain(dto);
			usecase.execute(ciudadDomain);

			daoFactory.confirmarTransaccion();
		} catch (final PCHException exception) {
			daoFactory.cancelarTransaccion();
			throw exception;
		} catch (final Exception exception) {
			daoFactory.cancelarTransaccion();

			var mensajeUsuario = "Se ha presentado un problema registrando la informacion de la ciudad";
			var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de registrar la ciudad";

			throw new BusinessPCHException(mensajeTecnico, mensajeUsuario, exception);

		} finally {
			daoFactory.cerrarConexion();
		}

	}

}
