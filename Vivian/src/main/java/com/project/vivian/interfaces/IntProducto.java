package com.project.vivian.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.project.vivian.models.Producto;

public interface IntProducto extends CrudRepository<Producto, Integer> {

}
