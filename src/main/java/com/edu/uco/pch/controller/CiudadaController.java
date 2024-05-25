package com.edu.uco.pch.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.uco.pch.business.facade.impl.ciudad.ConsultarCiudadesFacade;
import com.edu.uco.pch.business.facade.impl.ciudad.RegistrarCiudadFacade;
import com.edu.uco.pch.controller.response.CiudadResponse;
import com.edu.uco.pch.crosscutting.exceptions.PCHException;
import com.edu.uco.pch.dto.CiudadDTO;

@RestController
@RequestMapping("/api/v1/ciudades")
public final class CiudadaController {

	@GetMapping("/dummy")
	public CiudadDTO dummy() {
		return CiudadDTO.build();
	}

	@GetMapping("/p")
	public ResponseEntity<CiudadResponse> consultar() {

		var httpStatusCode = HttpStatus.ACCEPTED;
		var ciudadResponse = new CiudadResponse();

		try {

			var ciudadDto = CiudadDTO.build();
			var facade = new ConsultarCiudadesFacade();

			ciudadResponse.setDatos(facade.execute(ciudadDto));
			ciudadResponse.getMensajes().add("Ciudades consultadas exitosamente");
		} catch (PCHException exception) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			ciudadResponse.getMensajes().add(exception.getMensajeUsuario());
			exception.printStackTrace();
		} catch (Exception exception) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = "Se ha presentado un problema tratando de consultar la informacion de las ciudades";
			ciudadResponse.getMensajes().add(mensajeUsuario);

			exception.printStackTrace();
		}
		return new ResponseEntity<>(ciudadResponse, httpStatusCode);
	}
	
	
	@PostMapping
	public ResponseEntity<CiudadResponse> crear(@RequestBody CiudadDTO ciudad) {

		var httpStatusCode = HttpStatus.ACCEPTED;
		var ciudadResponse = new CiudadResponse();

		try {

			var facade = new RegistrarCiudadFacade();

			facade.exececute(ciudad);
			ciudadResponse.getMensajes().add("Ciudad creada existosamente ");
		} catch (final PCHException exception) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			ciudadResponse.getMensajes().add(exception.getMensajeUsuario());
			exception.printStackTrace();
		} catch (final Exception exception) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = "Se ha presentado un problema tratando de registrar la informacion de la ciudades";
			ciudadResponse.getMensajes().add(mensajeUsuario);

			exception.printStackTrace();
		}
		return new ResponseEntity<>(ciudadResponse, httpStatusCode);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CiudadResponse> eliminar(@PathVariable UUID id) {

		var httpStatusCode = HttpStatus.ACCEPTED;
		var ciudadResponse = new CiudadResponse();

		try {

			//var facade = new EliminarCiudadFacade();

			//facade.exececute(id);
			ciudadResponse.getMensajes().add("Ciudad eliminada existosamente ");
		} catch (final PCHException exception) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			ciudadResponse.getMensajes().add(exception.getMensajeUsuario());
			exception.printStackTrace();
		} catch (final Exception exception) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la informacion de la ciudades";
			ciudadResponse.getMensajes().add(mensajeUsuario);

			exception.printStackTrace();
		}
		return new ResponseEntity<>(ciudadResponse, httpStatusCode);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CiudadResponse> modificar(@PathVariable UUID id, @RequestBody CiudadDTO ciudadDto) {

		var httpStatusCode = HttpStatus.ACCEPTED;
		var ciudadResponse = new CiudadResponse();

		try {
			ciudadDto.setId(id);
			//var facade = new ModificarCiudadFacade();

			//facade.exececute(id);
			ciudadResponse.getMensajes().add("Ciudad modificada existosamente ");
		} catch (final PCHException exception) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			ciudadResponse.getMensajes().add(exception.getMensajeUsuario());
			exception.printStackTrace();
		} catch (final Exception exception) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = "Se ha presentado un problema tratando de modificar la informacion de la ciudades";
			ciudadResponse.getMensajes().add(mensajeUsuario);

			exception.printStackTrace();
		}
		return new ResponseEntity<>(ciudadResponse, httpStatusCode);
	}

}
