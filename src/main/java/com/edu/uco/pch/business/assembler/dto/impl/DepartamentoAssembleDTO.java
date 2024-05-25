package com.edu.uco.pch.business.assembler.dto.impl;

import static com.edu.uco.pch.crosscutting.helpers.ObjectHelper.getObjecHelper;

import java.util.List;

import com.edu.uco.pch.business.assembler.dto.AssemblerDTO;
import com.edu.uco.pch.business.domain.DepartamentoDomain;
import com.edu.uco.pch.business.domain.PaisDomain;
import com.edu.uco.pch.dto.DepartamentoDTO;
import com.edu.uco.pch.dto.PaisDTO;

public final class DepartamentoAssembleDTO implements AssemblerDTO<DepartamentoDomain, DepartamentoDTO> {

	private static final AssemblerDTO<PaisDomain, PaisDTO> paisAssembler = PaisAssembleDTO.getInstance();
	private static final AssemblerDTO<DepartamentoDomain, DepartamentoDTO> instance = new DepartamentoAssembleDTO();

	private DepartamentoAssembleDTO() {
		super();
	}

	public static final AssemblerDTO<DepartamentoDomain, DepartamentoDTO> getInstance() {
		return instance;
	}

	@Override
	public final DepartamentoDomain toDomain(final DepartamentoDTO data) {
		var departamentoDtoTmp = getObjecHelper().getDefaultValue(data, DepartamentoDTO.build());
		var paisDomain = paisAssembler.toDomain(departamentoDtoTmp.getPais());
		return DepartamentoDomain.build(departamentoDtoTmp.getId(), departamentoDtoTmp.getNombre(), paisDomain);
	}

	@Override
	public DepartamentoDTO toDTO(DepartamentoDomain domain) {
		var departamentoDomainTmp = getObjecHelper().getDefaultValue(domain, DepartamentoDomain.build());
		var paisDTO = paisAssembler.toDTO(departamentoDomainTmp.getPais());
		return DepartamentoDTO.build().setId(departamentoDomainTmp.getId()).setNombre(departamentoDomainTmp.getNombre())
				.setPais(paisDTO);
	}

	@Override
	public List<DepartamentoDomain> toDomainCollection(List<DepartamentoDTO> entityCollection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DepartamentoDTO> toDTOCollection(List<DepartamentoDomain> domainCollection) {
		// TODO Auto-generated method stub
		return null;
	}

}
