package com.ejemplos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ejemplos.models.entity.Socio;

public interface ISocioaDao extends CrudRepository<Socio, Long>{

}
