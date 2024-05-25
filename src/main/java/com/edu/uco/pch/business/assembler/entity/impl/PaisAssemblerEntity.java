package com.edu.uco.pch.business.assembler.entity.impl;

import java.util.List;

import com.edu.uco.pch.business.assembler.entity.AssemblerEntity;
import com.edu.uco.pch.business.domain.PaisDomain;
import com.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import com.edu.uco.pch.entity.PaisEntity;

public class PaisAssemblerEntity implements AssemblerEntity<PaisDomain, PaisEntity>{
	
	private static final AssemblerEntity<PaisDomain, PaisEntity> instance = new PaisAssemblerEntity();
	
	private PaisAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity<PaisDomain, PaisEntity> getInstance() {
		return instance;
	}

	@Override
	public final PaisDomain toDomain(final PaisEntity data) {
		var paisEntityTmp = ObjectHelper.getObjecHelper().getDefaultValue(data, PaisEntity.build());
		return PaisDomain.build(paisEntityTmp.getId(), paisEntityTmp.getNombre());
	}

	@Override
	public final PaisEntity toEntity(final PaisDomain domain) {
		var paisDomainTmp = ObjectHelper.getObjecHelper().getDefaultValue(domain, PaisDomain.build());
		return PaisEntity.build().setId(paisDomainTmp.getId()).setNombre(paisDomainTmp.getNombre());
	}

	@Override
	public List<PaisDomain> toDomainCollection(List<PaisEntity> entityCollection) {
		// TODO Auto-generated method stub
		return null;
	}

}
