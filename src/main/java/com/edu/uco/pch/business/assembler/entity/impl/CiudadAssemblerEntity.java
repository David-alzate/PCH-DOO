package com.edu.uco.pch.business.assembler.entity.impl;

import com.edu.uco.pch.business.assembler.entity.AssemblerEntity;
import com.edu.uco.pch.business.domain.CiudadDomain;
//import com.edu.uco.pch.business.domain.DepartamentoDomain;
import com.edu.uco.pch.entity.CiudadEntity;
//import com.edu.uco.pch.entity.DepartamentoEntity;

public class CiudadAssemblerEntity implements AssemblerEntity<CiudadDomain, CiudadEntity> {

//	private static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> departamentoAssembler = DepartamentoAssemblerEntity
//			.getInstance();
	private static final AssemblerEntity<CiudadDomain, CiudadEntity> INSTANCE = new CiudadAssemblerEntity();

	private CiudadAssemblerEntity() {
		super();
	}

	public static final AssemblerEntity<CiudadDomain, CiudadEntity> getInstance() {
		return INSTANCE;
	}

	@Override
	public final CiudadDomain toDomain(final CiudadEntity data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final CiudadEntity toEntity(final CiudadDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

}
