package com.project.vivian.dao;

import com.project.vivian.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("usuarioDAO")
public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
}
