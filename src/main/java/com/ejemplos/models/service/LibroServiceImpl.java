package com.ejemplos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.ILibroDao;
import com.ejemplos.models.entity.Libro;



/*
 * Una clase Service esta basada en el patron de diseño Facade:
 * un unico punto de acceso a clase DAO. Por cada clase DAO hay una clase Service
 * */

@Service
public class LibroServiceImpl implements ILibroService{
	
	@Autowired
	private ILibroDao libroDao;
	
	//el tratamiento de las transacciones va en la clase Service (no en los DAO)
	//tambien en un metodo Service podemos usar varios metodos DAO
	//dentro de la misma transaccion

	@Override
	@Transactional(readOnly = true)
	public List<Libro> findAll() {
		// TODO Auto-generated method stub
		return (List<Libro>) libroDao.findAll();
	}

	@Override
	@Transactional
	public void save(Libro libro) {
		libroDao.save(libro);
	}

	@Override
	public Libro findOne(String isbn) {
		// TODO Auto-generated method stub
		return libroDao.findById(isbn).orElse(null);
	}

	@Override
	public void delete(String isbn) {
		// TODO Auto-generated method stub
		libroDao.deleteById(isbn);
	}

//	@Override
//	public Libro buscarPorEmail(String email) {
//		Cliente cliente = clienteDao.findByEmailLikeIgnoreCase(email);
//		return cliente;
//	}

}
