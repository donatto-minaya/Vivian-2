package com.project.vivian.controller;


import com.project.vivian.entidad.Categoria;
import com.project.vivian.entidad.Producto;
import com.project.vivian.entidad.general.Confirmacion;
import com.project.vivian.service.CategoriaService;
import com.project.vivian.service.ProductoService;
import com.project.vivian.service.constants.ResponseEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    private int codigo = 4;

    @GetMapping("")
    public String listar(Model model) throws Exception {

        List<Producto> productos = productoService.obtenerProductos();
        List<Categoria> categorias = categoriaService.obtenerCategoriasActivas();
        model.addAttribute("producto", new Producto());
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("verFragmento", codigo);
        return "general-summary";
    }


    @PostMapping("")
    public ResponseEntity<Confirmacion> insertarProducto(Producto producto) throws Exception {
        Confirmacion confirmacion = new Confirmacion();
        try {
            productoService.agregarProducto(producto);

            confirmacion.setEstado(ResponseEstado.OK);
            confirmacion.setMensaje("Producto ingresado correctamente.");

            return ResponseEntity.accepted().body(confirmacion);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            confirmacion.setEstado(ResponseEstado.ERROR_APLICACION);
            confirmacion.setMensaje(ex.getMessage());
            return ResponseEntity.badRequest().body(confirmacion);
        }
    }

    @PutMapping("")
    public ResponseEntity<Confirmacion> actualizarProducto(Producto producto) throws Exception {
        Confirmacion confirmacion = new Confirmacion();
        try {
            productoService.actualizarProducto(producto);

            confirmacion.setEstado(ResponseEstado.OK);
            confirmacion.setMensaje("Producto actualizado correctamente." );

            return ResponseEntity.accepted().body(confirmacion);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            confirmacion.setEstado(ResponseEstado.ERROR_APLICACION);
            confirmacion.setMensaje(ex.getMessage());
            return ResponseEntity.badRequest().body(confirmacion);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Confirmacion> eliminarProducto(@RequestParam Integer id) throws Exception {
        Confirmacion confirmacion = new Confirmacion();
        try {
            if (productoService.eliminarProducto(id)){
                confirmacion.setEstado(ResponseEstado.OK);
                confirmacion.setMensaje("Producto eliminado correctamente.");
            } else {
                confirmacion.setEstado(ResponseEstado.ERROR_NEGOCIO);
                confirmacion.setMensaje("Error al eliminar el producto.");
            }
            return ResponseEntity.accepted().body(confirmacion);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            confirmacion.setEstado(ResponseEstado.ERROR_APLICACION);
            confirmacion.setMensaje(ex.getMessage());
            return ResponseEntity.badRequest().body(confirmacion);
        }
    }

}
