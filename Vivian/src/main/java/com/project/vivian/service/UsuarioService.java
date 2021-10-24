package com.project.vivian.service;

import com.project.vivian.entidad.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public List<Usuario> obtenerCustomerUsuarios() throws Exception;
    public Usuario crearCustomerUsuario(Usuario entity) throws Exception;
    Usuario obtenerPorEmail(String email);
    public Optional<Usuario> obtenerPorId(Integer integer);
    public Usuario obtenerPorDni(String dni) throws Exception;
    public boolean eliminarPorId(Integer integer);
    public Integer actualizarCustomerUsuario(Integer id, String dni, String nombresUsuario,String apellidosUsuario, String username, String telefono, Integer estado) throws Exception;
}
