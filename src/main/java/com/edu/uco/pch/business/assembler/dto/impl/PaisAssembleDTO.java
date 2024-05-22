package com.edu.uco.pch.business.assembler.dto.impl;

import com.edu.uco.pch.business.assembler.dto.AssemblerDTO;
import com.edu.uco.pch.business.domain.PaisDomain;

import com.edu.uco.pch.dto.PaisDTO;

import static com.edu.uco.pch.crosscutting.helpers.ObjectHelper.getObjecHelper;

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
		var PaisDtoTmp = getObjecHelper().getDefaultValue(data, PaisDTO.build());
		return PaisDomain.build(PaisDtoTmp.getId(), PaisDtoTmp.getNombre());
	}

	@Override
	public final PaisDTO toDTO(final PaisDomain domain) {
		var PaisDomainTmp = getObjecHelper().getDefaultValue(domain, PaisDomain.build());
		return PaisDTO.build().setId(PaisDomainTmp.getId()).setNombre(PaisDomainTmp.getNombre());
	}

}
