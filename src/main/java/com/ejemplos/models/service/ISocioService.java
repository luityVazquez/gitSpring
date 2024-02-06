package com.ejemplos.models.service;

import java.util.List;

import com.ejemplos.models.entity.Socio;

public interface ISocioService {

	public List<Socio> findAll();
	
	public void save (Socio socio);
	
	public Socio findOne(Long id);
	
	public void delete(Long id);
}
