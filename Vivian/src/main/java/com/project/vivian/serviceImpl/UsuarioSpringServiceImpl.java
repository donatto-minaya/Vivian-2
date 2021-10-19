package com.project.vivian.serviceImpl;

import com.project.vivian.dao.TipoDAO;
import com.project.vivian.dao.UsuarioSpringDAO;
import com.project.vivian.entidad.Tipo;
import com.project.vivian.entidad.UsuarioSpring;
import com.project.vivian.service.UsuarioSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service("usuarioService")
public class UsuarioSpringServiceImpl implements UsuarioSpringService{

    @Autowired
    UsuarioSpringDAO usuarioSpringDAO;

    @Autowired
    TipoDAO tipoDAO;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

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
    public UsuarioSpring obtenerPorId(Integer integer) {
        return null;
    }

    @Override
    public boolean eliminarPorId(Integer integer) {
        return false;
    }

    @Override
    public UsuarioSpring crearUsuario(UsuarioSpring entity) throws Exception {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Tipo tipoAdmin = tipoDAO.getById(3);
            entity.setIdTipo(tipoAdmin);
            entity.setEstado(1);
            entity.setFechaRegistro(now.toInstant(ZoneOffset.UTC));
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            UsuarioSpring usuarioCreated = usuarioSpringDAO.save(entity);
            return usuarioCreated;
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public <S extends UsuarioSpring> S update(Integer integer, S entity) {
        return null;
    }

//    @Autowired

//

}
