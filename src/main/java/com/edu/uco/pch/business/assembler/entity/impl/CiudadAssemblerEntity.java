package com.edu.uco.pch.business.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import com.edu.uco.pch.business.assembler.entity.AssemblerEntity;
import com.edu.uco.pch.business.domain.CiudadDomain;
import com.edu.uco.pch.business.domain.DepartamentoDomain;
import com.edu.uco.pch.crosscutting.helpers.ObjectHelper;
import com.edu.uco.pch.entity.CiudadEntity;
import com.edu.uco.pch.entity.DepartamentoEntity;

public class CiudadAssemblerEntity implements AssemblerEntity<CiudadDomain, CiudadEntity> {

	private static final AssemblerEntity<DepartamentoDomain, DepartamentoEntity> departamentoAssembler = DepartamentoAssemblerEntity
			.getInstance();
	private static final AssemblerEntity<CiudadDomain, CiudadEntity> instance = new CiudadAssemblerEntity();

	private CiudadAssemblerEntity() {
		super();
	}

	public static final AssemblerEntity<CiudadDomain, CiudadEntity> getInstance() {
		return instance;
	}

	@Override
	public final CiudadDomain toDomain(final CiudadEntity data) {
		var ciudadEntityTmp = ObjectHelper.getObjecHelper().getDefaultValue(data, CiudadEntity.build());
		var departamentoDomain = departamentoAssembler.toDomain(ciudadEntityTmp.getDepartamento());
		return CiudadDomain.build(ciudadEntityTmp.getId(), ciudadEntityTmp.getNombre(), departamentoDomain);
	}

	@Override
	public final CiudadEntity toEntity(final CiudadDomain domain) {
		var ciudadDomainTmp = ObjectHelper.getObjecHelper().getDefaultValue(domain, CiudadDomain.build());
		var departamentoEntity = departamentoAssembler.toEntity(ciudadDomainTmp.getDepartamento());
		return CiudadEntity.build().setId(ciudadDomainTmp.getId()).setNombre(ciudadDomainTmp.getNombre())
				.setDepartamento(departamentoEntity);
	}

	@Override
	public List<CiudadDomain> toDomainCollection(List<CiudadEntity> entityCollection) {
		var entityCollectionTmp = ObjectHelper.getObjecHelper().getDefaultValue(entityCollection,
				new ArrayList<CiudadEntity>());

		return entityCollectionTmp.stream().map(this::toDomain).toList();
	}

}
