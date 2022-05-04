package com.libros.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.libros.dao.LibrosDAO;
import com.libros.entitys.Libro;


@RestController
@RequestMapping("libros")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE, RequestMethod.PUT})
public class LibrosREST {
	
	@Autowired
	private LibrosDAO libroDAO;
	
	@GetMapping
	public ResponseEntity<List<Libro>> getLibro(){
		List<Libro> libros = libroDAO.findAll();
		return ResponseEntity.ok(libros);
	}
	
	@RequestMapping(value="{libroId}")
	public ResponseEntity<Libro> getLibroById(@PathVariable("libroId")Long libroId){
		Optional<Libro> optionalLibro= libroDAO.findById(libroId);
		if(optionalLibro.isPresent()) {
			return ResponseEntity.ok(optionalLibro.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Libro> createLibro(@RequestBody Libro libro) {
		Libro newLibro = libroDAO.save(libro);
		return ResponseEntity.ok(newLibro);
	}
	
	@DeleteMapping(value="{libroId}")
	public ResponseEntity<Void> deleteLibro(@PathVariable("libroId")Long libroId) {
		libroDAO.deleteById(libroId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping
	public ResponseEntity<Libro> updateLibro(@RequestBody Libro libro) {
		Optional<Libro> optionalLibro= libroDAO.findById(libro.getId());
		if(optionalLibro.isPresent()) {
			Libro updateLibro = optionalLibro.get();
			updateLibro.setTitulo(libro.getTitulo());
			updateLibro.setDescripcion(libro.getDescripcion());
			updateLibro.setAutor(libro.getAutor());
			updateLibro.setPublicado(libro.getPublicado());
			libroDAO.save(updateLibro);
			
			return ResponseEntity.ok(updateLibro);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
}
