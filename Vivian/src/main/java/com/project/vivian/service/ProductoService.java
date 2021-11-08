package com.project.vivian.service;

import com.project.vivian.entidad.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductoService {

    public List<Producto> obtenerProductos() throws Exception;
    public Producto agregarProducto(Producto producto) throws Exception;
    public Producto actualizarProducto(Producto producto) throws Exception;
    public boolean eliminarProducto(Integer id) throws Exception;

}
