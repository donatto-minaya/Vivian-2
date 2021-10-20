package com.project.vivian.service;

import com.project.vivian.entidad.UsuarioSpring;

import java.util.List;


public interface UsuarioSpringService {
    public List<UsuarioSpring> obtenerAdminUsuarios() throws Exception;
    public UsuarioSpring crearUsuario(UsuarioSpring entity) throws Exception;
    UsuarioSpring obtenerPorEmail(String email);
    public UsuarioSpring obtenerPorId(Integer integer);
    public UsuarioSpring obtenerPorDni(String dni) throws Exception;
    public boolean eliminarPorId(Integer integer);
    public <S extends UsuarioSpring> S update(Integer integer, S entity);
}
