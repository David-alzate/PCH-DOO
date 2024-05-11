package com.edu.uco.pch.entity;

import java.util.UUID;

public final class DepartamentoEntity {
	private UUID id;
	private String nombre;
	private PaisEntity pais;
	
	public DepartamentoEntity() {
		super();
	}
	
	public DepartamentoEntity(final UUID id, final String nombre, final PaisEntity pais) {
		setId(id);
		setNombre(nombre);
		setPais(pais);
	}
	
	public static final DepartamentoEntity build() {
		return new DepartamentoEntity();
	}
	
	public final UUID getId() {
		return id;
	}
	public final DepartamentoEntity setId(final UUID id) {
		this.id = id;
		return this;
	}
	public final String getNombre() {
		return nombre;
	}
	public final DepartamentoEntity setNombre(final String nombre) {
		this.nombre = nombre;
		return this;
	}
	public final PaisEntity getPais() {
		return pais;
	}
	public final DepartamentoEntity setPais(final PaisEntity pais) {
		this.pais = pais;
		return this;
	}
	
	
}
