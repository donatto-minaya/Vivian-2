package com.project.vivian.service;

import com.project.vivian.entidad.Usuario;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UsuarioService {
    public List<Usuario> findAll();
    Usuario findByEmail(String email, String clave) throws Exception;

    public Usuario getById(Integer integer);
    public boolean deleteById(Integer integer);
    public <S extends Usuario> S save(S entity);
    public <S extends Usuario> S update(Integer integer, S entity);
}
