package com.dgha.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dgha.model.Persona;
import com.dgha.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {

	private static final Logger log = LoggerFactory.getLogger(PersonaServiceImpl.class);

	@Value("${url.api.persona.buscar-por-dni}")
	private String urlApiPersonaBuscarPorDni;

	@Autowired
	private RestTemplate restTemplate;

	public Persona buscarPorDni(String dni) {
		ResponseEntity<Persona> response = restTemplate.getForEntity(urlApiPersonaBuscarPorDni.concat("/").concat(dni),
				Persona.class);
		if (response != null && response.getStatusCode().equals(HttpStatus.OK)) {
			log.info("Persona encontrada=" + response.getBody());
			return response.getBody();
		}
		return null;
	}
}
