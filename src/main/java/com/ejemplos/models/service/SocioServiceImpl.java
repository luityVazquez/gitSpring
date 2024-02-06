package com.ejemplos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.ISocioaDao;
import com.ejemplos.models.entity.Socio;


/*
 * Una clase Service esta basada en el patron de diseño Facade:
 * un unico punto de acceso a clase DAO. Por cada clase DAO hay una clase Service
 * */

@Service
public class SocioServiceImpl implements ISocioService{
	
	@Autowired
	private ISocioaDao socioDao;
	
	//el tratamiento de las transacciones va en la clase Service (no en los DAO)
	//tambien en un metodo Service podemos usar varios metodos DAO
	//dentro de la misma transaccion

	@Override
	@Transactional(readOnly = true)
	public List<Socio> findAll() {
		// TODO Auto-generated method stub
		return (List<Socio>) socioDao.findAll();
	}

	@Override
	@Transactional
	public void save(Socio socio) {
		socioDao.save(socio);
	}

	@Override
	public Socio findOne(Long id) {
		// TODO Auto-generated method stub
		return socioDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		socioDao.deleteById(id);
	}

}
