package com.edu.uco.pch.business.assembler.entity.impl;

import java.util.List;

import com.edu.uco.pch.business.assembler.entity.AssemblerEntity;
import com.edu.uco.pch.business.domain.DepartamentoDomain;
import com.edu.uco.pch.business.domain.PaisDomain;
import com.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import com.edu.uco.pch.entity.DepartamentoEntity;
import com.edu.uco.pch.entity.PaisEntity;

public class DepartamentoAssemblerEntity implements AssemblerEntity<DepartamentoDomain, DepartamentoEntity> {

	private static final AssemblerEntity<PaisDomain, PaisEntity> paisAssembler = PaisAssemblerEntity.getInstance();
	private static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> instance = new DepartamentoAssemblerEntity();

	private DepartamentoAssemblerEntity() {
		super();
	}

	public static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> getInstance() {
		return instance;
	}

	@Override
	public final DepartamentoDomain toDomain(final DepartamentoEntity data) {
		var departamentoEntityTmp = ObjectHelper.getObjecHelper().getDefaultValue(data, DepartamentoEntity.build());
		var paisDomain = paisAssembler.toDomain(departamentoEntityTmp.getPais());
		return DepartamentoDomain.build(departamentoEntityTmp.getId(), departamentoEntityTmp.getNombre(), paisDomain);
	}

	@Override
	public final DepartamentoEntity toEntity(final DepartamentoDomain domain) {
		var departamentoDomainTmp = ObjectHelper.getObjecHelper().getDefaultValue(domain, DepartamentoDomain.build());
		var paisEntity = paisAssembler.toEntity(departamentoDomainTmp.getPais());
		return DepartamentoEntity.build().setId(departamentoDomainTmp.getId())
				.setNombre(departamentoDomainTmp.getNombre()).setPais(paisEntity);
	}

	@Override
	public List<DepartamentoDomain> toDomainCollection(List<DepartamentoEntity> entityCollection) {
		// TODO Auto-generated method stub
		return null;
	}

}
