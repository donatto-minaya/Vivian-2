package com.project.vivian.serviceImpl;

import com.project.vivian.dao.UsuarioDAO;
import com.project.vivian.entidad.Usuario;
import com.project.vivian.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioDAO usuarioDAO;


    @Override
    public List<Usuario> findAll() {
        return usuarioDAO.findAll();
    }

    @Override
    public Usuario findByEmail(String email, String clave) throws Exception {
        return usuarioDAO.findByEmail(email,clave);
    }

    @Override
    public Usuario getById(Integer integer) {
        return null;
    }

    @Override
    public boolean deleteById(Integer integer) {
        return false;
    }

    @Override
    public <S extends Usuario> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Usuario> S update(Integer integer, S entity) {
        return null;
    }
}
