package com.edu.uco.pch.business.usecase;

public interface UseCaseWithReturn<T, R> {
	
	R execute(T data);

}
