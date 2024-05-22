package com.edu.uco.pch.business.assembler.dto;

import com.edu.uco.pch.business.assembler.Assembler;

public interface AssemblerDTO<D, K> extends Assembler<D, K>{
	
	K toDTO(D domain);

}
