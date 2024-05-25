package com.edu.uco.pch.business.assembler.dto.impl;

import com.edu.uco.pch.business.assembler.dto.AssemblerDTO;
import com.edu.uco.pch.business.domain.PaisDomain;

import com.edu.uco.pch.dto.PaisDTO;

import static com.edu.uco.pch.crosscutting.helpers.ObjectHelper.getObjecHelper;

import java.util.List;

public final class PaisAssembleDTO implements AssemblerDTO<PaisDomain, PaisDTO> {

	private static final AssemblerDTO<PaisDomain, PaisDTO> instance = new PaisAssembleDTO();

	private PaisAssembleDTO() {
		super();
	}

	public static final AssemblerDTO<PaisDomain, PaisDTO> getInstance() {
		return instance;
	}

	@Override
	public final PaisDomain toDomain(final PaisDTO data) {
		var paisDtoTmp = getObjecHelper().getDefaultValue(data, PaisDTO.build());
		return PaisDomain.build(paisDtoTmp.getId(), paisDtoTmp.getNombre());
	}

	@Override
	public final PaisDTO toDTO(final PaisDomain domain) {
		var paisDomainTmp = getObjecHelper().getDefaultValue(domain, PaisDomain.build());
		return PaisDTO.build().setId(paisDomainTmp.getId()).setNombre(paisDomainTmp.getNombre());
	}

	@Override
	public List<PaisDomain> toDomainCollection(List<PaisDTO> entityCollection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaisDTO> toDTOCollection(List<PaisDomain> domainCollection) {
		// TODO Auto-generated method stub
		return null;
	}

}
