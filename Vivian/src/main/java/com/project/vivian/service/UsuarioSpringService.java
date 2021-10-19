package com.project.vivian.service;

import com.project.vivian.entidad.UsuarioSpring;

import java.util.List;


public interface UsuarioSpringService {
    public List<UsuarioSpring> obtenerAdminUsuarios() throws Exception;
    UsuarioSpring obtenerPorEmail(String email);
    public UsuarioSpring obtenerPorId(Integer integer);
    public boolean eliminarPorId(Integer integer);
    public UsuarioSpring crearUsuario(UsuarioSpring entity) throws Exception;
    public <S extends UsuarioSpring> S update(Integer integer, S entity);
}
