package com.project.vivian.dao;

import com.project.vivian.entidad.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("categoriaDAO")
public interface CategoriaDAO extends JpaRepository<Categoria, Integer> {
}
