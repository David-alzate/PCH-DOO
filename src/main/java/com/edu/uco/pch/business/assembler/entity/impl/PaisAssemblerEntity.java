package com.edu.uco.pch.business.assembler.entity.impl;

import com.edu.uco.pch.business.assembler.entity.AssemblerEntity;
import com.edu.uco.pch.business.domain.PaisDomain;
import com.edu.uco.pch.entity.PaisEntity;

public class PaisAssemblerEntity implements AssemblerEntity<PaisDomain, PaisEntity>{
	
	private static final AssemblerEntity<PaisDomain, PaisEntity> INSTANCE = new PaisAssemblerEntity();
	
	private PaisAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity<PaisDomain, PaisEntity> getInstance() {
		return INSTANCE;
	}

	@Override
	public final PaisDomain toDomain(final PaisEntity data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final PaisEntity toEntity(final PaisDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

}
