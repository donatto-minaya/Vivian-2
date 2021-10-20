package com.project.vivian.dao;

import com.project.vivian.entidad.UsuarioSpring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.OptionalInt;

@Repository("usuarioSpringDAO")
public interface UsuarioSpringDAO extends JpaRepository<UsuarioSpring,Integer> {

    public Optional<UsuarioSpring> findByUsername(String username);

    public Optional<UsuarioSpring> findByDni(String dni);
}
