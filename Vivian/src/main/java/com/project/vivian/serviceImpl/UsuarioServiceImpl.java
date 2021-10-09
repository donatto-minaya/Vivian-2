package com.project.vivian.serviceImpl;

import com.project.vivian.dao.TipoDAO;
import com.project.vivian.dao.UsuarioDAO;
import com.project.vivian.entidad.Tipo;
import com.project.vivian.entidad.Usuario;
import com.project.vivian.service.UsuarioService;
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
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    @Autowired
    UsuarioDAO usuarioDAO;

    @Autowired
    TipoDAO tipoDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        List<Tipo> tipos = tipoDAO.findAll();
        Usuario us = usuarioDAO.findByEmail(s);

        List<GrantedAuthority> roles = new ArrayList<>();
        for (int i = 0;i<tipos.size();i++){
            roles.add(new SimpleGrantedAuthority(tipos.get(i).getDescripcion()));
        }

        UserDetails userDetails = new User(us.getEmail(),us.getClave(),roles);

        return userDetails;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioDAO.findAll();
    }

    @Override
    public Usuario findByEmailPassword(String email, String clave) throws Exception {
        try {
            return usuarioDAO.findByEmailPassword(email,clave);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Usuario findByEmail(String email) {
        return null;
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
