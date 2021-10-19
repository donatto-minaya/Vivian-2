package com.project.vivian.serviceImpl;

import com.project.vivian.dao.TipoDAO;
import com.project.vivian.dao.UsuarioSpringDAO;
import com.project.vivian.entidad.UsuarioSpring;
import com.project.vivian.service.UsuarioSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("usuarioService")
public class UsuarioSpringServiceImpl implements UsuarioSpringService{

    @Autowired
    UsuarioSpringDAO usuarioSpringDAO;

    @Override
    public List<UsuarioSpring> obtenerAdminUsuarios() throws Exception {
        try {
            List<UsuarioSpring> lista = usuarioSpringDAO.findAll();
            return lista;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public UsuarioSpring obtenerPorEmail(String email) {
        UsuarioSpring usuario = usuarioSpringDAO.findByUsername(email).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
        return usuario;
    }

    @Override
    public UsuarioSpring getById(Integer integer) {
        return null;
    }

    @Override
    public boolean deleteById(Integer integer) {
        return false;
    }

    @Override
    public <S extends UsuarioSpring> S save(S entity) {
        return null;
    }

    @Override
    public <S extends UsuarioSpring> S update(Integer integer, S entity) {
        return null;
    }

//    @Autowired

//

}
