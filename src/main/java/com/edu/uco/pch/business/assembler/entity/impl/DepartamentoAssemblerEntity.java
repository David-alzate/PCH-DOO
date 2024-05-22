package com.edu.uco.pch.business.assembler.entity.impl;

import com.edu.uco.pch.business.assembler.entity.AssemblerEntity;
import com.edu.uco.pch.business.domain.DepartamentoDomain;
//import com.edu.uco.pch.business.domain.PaisDomain;
import com.edu.uco.pch.entity.DepartamentoEntity;
//import com.edu.uco.pch.entity.PaisEntity;

public class DepartamentoAssemblerEntity implements AssemblerEntity<DepartamentoDomain, DepartamentoEntity> {
	
	// private static final AssemblerEntity<PaisDomain, PaisEntity> paisAssembler = PaisAssemblerEntity.getInstance();
	private static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> INSTANCE = new DepartamentoAssemblerEntity();
	
	private DepartamentoAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> getInstance() {
		return INSTANCE;
	}
	
	@Override
	public final DepartamentoDomain toDomain(final DepartamentoEntity data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final DepartamentoEntity toEntity(final DepartamentoDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

}
