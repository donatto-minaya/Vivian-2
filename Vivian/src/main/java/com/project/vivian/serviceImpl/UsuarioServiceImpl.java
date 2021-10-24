package com.project.vivian.serviceImpl;

import com.project.vivian.dao.UsuarioDAO;
import com.project.vivian.entidad.Usuario;
import com.project.vivian.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public List<Usuario> obtenerCustomerUsuarios() throws Exception {
        try{
            return usuarioDAO.findAll();
        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Usuario crearCustomerUsuario(Usuario entity) throws Exception {
        return null;
    }

    @Override
    public Usuario obtenerPorEmail(String email) {
        return null;
    }

    @Override
    public Optional<Usuario> obtenerPorId(Integer integer) {
        return Optional.empty();
    }

    @Override
    public Usuario obtenerPorDni(String dni) throws Exception {
        return null;
    }

    @Override
    public boolean eliminarPorId(Integer integer) {
        return false;
    }

    @Override
    public Integer actualizarCustomerUsuario(Integer id, String dni, String nombresUsuario, String apellidosUsuario, String username, String telefono, Integer estado) throws Exception {
        return null;
    }
}
