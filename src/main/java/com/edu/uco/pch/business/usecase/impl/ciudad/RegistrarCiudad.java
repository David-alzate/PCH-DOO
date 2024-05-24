package com.edu.uco.pch.business.usecase.impl.ciudad;

import java.util.UUID;

import com.edu.uco.pch.business.assembler.entity.impl.DepartamentoAssemblerEntity;
import com.edu.uco.pch.business.domain.CiudadDomain;
import com.edu.uco.pch.business.usecase.UseCaseWithoutReturn;
import com.edu.uco.pch.crosscutting.exceptions.custom.BusinessPCHException;
import com.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import com.edu.uco.pch.crosscutting.helpers.UUIDHelper;
import com.edu.uco.pch.data.dao.factory.DAOFactory;
import com.edu.uco.pch.entity.CiudadEntity;
import com.edu.uco.pch.entity.DepartamentoEntity;


public class RegistrarCiudad implements UseCaseWithoutReturn<CiudadDomain> {

	private DAOFactory factory;

	public RegistrarCiudad(final DAOFactory factory) {
		if (ObjectHelper.getObjecHelper().isNull(factory)) {
			var mensajeUsuario = "se ha presentado un error intentando hacer el registro de la ciudad";
			var mensajeTecnico = "El dao factory para crear la ciudad llego nulo..";
			throw new BusinessPCHException(mensajeTecnico, mensajeUsuario);
		}
		this.factory = factory;
	}

	@Override
	public void execute(final CiudadDomain data) {
		// 1. validar que los datos requeridos por el caso de uso sean correctos a nivel
		// de dato, longitud, oblitoriedad.
		// 2. Validar que no exista otra ciudad con el mismo nombre para el mismo
		// departamento
		validarCiudadMismoNombreMismoDepartamento(data.getNombre(), data.getDepartamento().getId());
		// 3. validar que no exista una ciudad con el mismo identificador
		var ciudadEntity = CiudadEntity.build().setId(generarIdentificadorCiudad()).setNombre(data.getNombre())
				.setDepartamento(DepartamentoAssemblerEntity.getInstance().toEntity(data.getDepartamento()));
		// 4. Guardar la nueva ciudad
		factory.getCiudadDAO().crear(ciudadEntity);
	}

	private final UUID generarIdentificadorCiudad() {
		UUID id = UUIDHelper.generate();

		boolean existeId = true;

		while (existeId) {
			id = UUIDHelper.generate();
			var ciudadEntity = CiudadEntity.build().setId(id);
			var resultados = factory.getCiudadDAO().consultar(ciudadEntity);
			existeId = !resultados.isEmpty();
		}
		return id;

	}

	private void validarCiudadMismoNombreMismoDepartamento(final String nombreCiudad, final UUID idDepartamento) {
		var ciudadEntity = CiudadEntity.build().setNombre(nombreCiudad)
				.setDepartamento(DepartamentoEntity.build().setId(idDepartamento));
		var resultados = factory.getCiudadDAO().consultar(ciudadEntity);

		if (!resultados.isEmpty()) {
			var mensajeUsuario = "Ya existe una ciudad con el nombre \"${1}\" asociado al departamento deseado";
			throw new BusinessPCHException(mensajeUsuario);

		}
	}

}
