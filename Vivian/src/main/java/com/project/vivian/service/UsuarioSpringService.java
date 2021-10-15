package com.project.vivian.service;

import com.project.vivian.entidad.UsuarioSpring;

import java.util.List;


public interface UsuarioSpringService {
    public List<UsuarioSpring> findAll();
    UsuarioSpring findByEmailPassword(String email, String clave) throws Exception;
    UsuarioSpring findByEmail(String email);
    public UsuarioSpring getById(Integer integer);
    public boolean deleteById(Integer integer);
    public <S extends UsuarioSpring> S save(S entity);
    public <S extends UsuarioSpring> S update(Integer integer, S entity);
}
