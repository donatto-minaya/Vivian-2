package com.project.vivian.dao;

import com.project.vivian.entidad.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("productoDAO")
public interface ProductoDAO extends JpaRepository<Producto, Integer> {
}
