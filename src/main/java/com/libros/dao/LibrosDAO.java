package com.libros.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libros.entitys.Libro;

public interface LibrosDAO extends JpaRepository<Libro, Long>{

}
