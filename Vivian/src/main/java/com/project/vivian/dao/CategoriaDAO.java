package com.project.vivian.dao;

import com.project.vivian.entidad.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaDAO extends JpaRepository<Categoria, Integer> {
}
