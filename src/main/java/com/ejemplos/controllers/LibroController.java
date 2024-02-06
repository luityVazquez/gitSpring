package com.ejemplos.controllers;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ejemplos.models.entity.Libro;
import com.ejemplos.models.entity.Socio;
import com.ejemplos.models.service.ILibroService;
import com.ejemplos.models.service.ISocioService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("libro")
public class LibroController {
	//autoinyecta el bean
	//Es decir busca un componente registrado de Spring
	//que lo implementa y localiza @Service
	
	//@Autowired
	//private ISocioService socioService;
	
	@Autowired
	private ILibroService libroService;
	
	@Autowired
	private ISocioService socioService;

//	@GetMapping(value="/")
//	public String index() {
//		return "index";
//	}
	
	@RequestMapping("/vistaSocio")
	public String crearSocio(Model model) {
		Socio socio = new Socio();
		
		model.addAttribute("socio", socio);
		model.addAttribute("titulo", "Formulario de nuevo socio");
		return "vistaSocio";
	}
	
	@PostMapping(value="/vistaSocio")
	public String registrarSocio(@Valid Socio socio, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de nuevo socio");
			return "vistaSocio";
		}
		
		List<Socio> socios = socioService.findAll();
		
		if(socios.contains(socio)) {
			model.addAttribute("mensajeYaExiste", "Este ID ya existe");
			return "vistaSocio";
		}
		
		model.addAttribute("titulo", "Formulario de nuevo socio");
		model.addAttribute("mensajeCreado", "Socio registrado correctamente");
		socioService.save(socio);
		return "vistaSocio";
	}
	
	@RequestMapping(value="/vistaLibro")
	public String crear(Map<String, Object> model) {
		//objeto de negocio bidireccional entidad mapeado a la BD y al formulario
		Libro libro = new Libro();
		//paso datos a la vista
		model.put("libro", libro);
		model.put("titulo", "Formulario de Libro");
		return "vistaLibro";
		//1ª fase: mostrar el formulario al usuario
	}
	
	@PostMapping(value="/vistaLibro")
	public String registrarLibro(@Valid Libro libro, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Libro");
			return "vistaLibro";
		}
		
		model.addAttribute("creadoCorrecto", "Libro creado correctamente.");
		
		libroService.save(libro);
		status.setComplete();
		return "redirect:listar";
	}
	
	@GetMapping(value="/buscarLibro")
	public String buscar(Model model) {
		Libro libro = new Libro();
		model.addAttribute("libro", libro);
		model.addAttribute("titulo", "Búsqueda de libros");
		return "vistaBuscarLibroISBN";
	}
	
	@PostMapping(value="/comprobarLibro")
	public String buscarLibro(Model model, Libro libro) {
		
		String mensaje = "";
		
		model.addAttribute("titulo", "Datos del libro "+libro.getIsbn());
		
		if(libro.getIsbn() == "") {
			mensaje="DEBES INDICAR EL ISBN";
			model.addAttribute("mensaje", mensaje);
			return "vistaBuscarLibroISBN";
		}
		
		Libro aux = libroService.findOne(libro.getIsbn());
		
		if(aux == null) {
			mensaje="LIBRO NO ENCONTRADO";
			model.addAttribute("mensaje", mensaje);
			return "vistaBuscarLibroISBN";
		}
		
		model.addAttribute("libroFinal", aux);
		return "vistaBuscarLibroISBN";
	}
	
	@GetMapping(value="/buscarLibroModificar")
	public String buscarModificar(Model model) {
		Libro libro = new Libro();
		model.addAttribute("titulo", "Modificar libro");
		model.addAttribute("libro", libro);
		return "vistaModificarLibroISBN";
	}
	
	@PostMapping(value="/modificarLibro")
	public String modificarLibro(Model model, Libro libro) {
		
		model.addAttribute("titulo", "Modificar libro");
		String mensaje = "";
		if(libro.getIsbn() == "") {
			mensaje = "DEBES INDICAR EL ISBN";
			model.addAttribute("mensaje", mensaje);
			return "vistaModificarLibroISBN";
		}
		
		Libro aux = libroService.findOne(libro.getIsbn());
		
		model.addAttribute("mensaje", "Modificar libro "+libro.getIsbn());
		
		if(aux == null) {
			mensaje = "LIBRO NO ENCONTRADO";
			model.addAttribute("mensaje", mensaje);
			return "vistaModificarLibroISBN";
		}
		
		model.addAttribute("libroFinal", aux);
		return "vistaModificarLibroISBN";
	}
	
	@PostMapping(value="/modificar")
	public String modificar(Model model, Libro libro) {
		
		model.addAttribute("titulo", "Modificar libro");
		
		if(libro.getSocio()!=null) {
			libro.setPrestado(true);
		}
		model.addAttribute("libroFinal", libro);
		
		
		libroService.save(libro);
		return "redirect:listar";
	}
	
	@GetMapping({"/", "/listar"})
	public String listar(Model model) {
		model.addAttribute("titulo","LISTADO DE LOS LIBROS");
		model.addAttribute("libros", libroService.findAll());
		return "listar";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
