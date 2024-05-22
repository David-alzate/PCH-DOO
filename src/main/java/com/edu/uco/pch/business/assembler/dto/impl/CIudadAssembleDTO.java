package com.edu.uco.pch.business.assembler.dto.impl;

import com.edu.uco.pch.business.assembler.dto.AssemblerDTO;
import com.edu.uco.pch.business.domain.CiudadDomain;
import com.edu.uco.pch.business.domain.DepartamentoDomain;
import com.edu.uco.pch.dto.CiudadDTO;
import com.edu.uco.pch.dto.DepartamentoDTO;
import static com.edu.uco.pch.crosscutting.helpers.ObjectHelper.getObjecHelper;

public final class CIudadAssembleDTO implements AssemblerDTO<CiudadDomain, CiudadDTO> {

	private static final AssemblerDTO<DepartamentoDomain, DepartamentoDTO> departamentoAssembler = DepartamentoAssembleDTO
			.getInstance();
	private static final AssemblerDTO<CiudadDomain, CiudadDTO> instance = new CIudadAssembleDTO();

	private CIudadAssembleDTO() {
		super();
	}

	public static final AssemblerDTO<CiudadDomain, CiudadDTO> getInstance() {
		return instance;
	}

	@Override
	public CiudadDomain toDomain(CiudadDTO data) {
		var ciudadDtoTmp = getObjecHelper().getDefaultValue(data, CiudadDTO.build());
		var departamentoDomain = departamentoAssembler.toDomain(ciudadDtoTmp.getDepartamento());
		return CiudadDomain.build(ciudadDtoTmp.getId(), ciudadDtoTmp.getNombre(), departamentoDomain);
	}

	@Override
	public CiudadDTO toDTO(CiudadDomain domain) {
		var ciudadDomainTmp = getObjecHelper().getDefaultValue(domain, CiudadDomain.build());
		var departamentoDTO = departamentoAssembler.toDTO(ciudadDomainTmp.getDepartamento());
		return CiudadDTO.build().setId(ciudadDomainTmp.getId()).setNombre(ciudadDomainTmp.getNombre())
				.setDepartamento(departamentoDTO);
	}

}
