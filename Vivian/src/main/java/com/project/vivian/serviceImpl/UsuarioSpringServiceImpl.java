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

@Service("usuarioService")
public class UsuarioSpringServiceImpl implements UsuarioSpringService{


    @Override
    public List<UsuarioSpring> findAll() {
        return null;
    }

    @Override
    public UsuarioSpring findByEmailPassword(String email, String clave) throws Exception {
        return null;
    }

    @Override
    public UsuarioSpring findByEmail(String email) {
        return null;
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
