package com.project.vivian.dao;

import com.project.vivian.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario,Integer> {

    @Query(value = "{call vUsuario(:email, :clave)}",nativeQuery = true)
    Usuario findByEmailPassword(@Param("email") String email, @Param("clave") String clave);

    @Query(value = "SELECT * FROM Usuario where email=:email", nativeQuery = true)
    Usuario findByEmail(@Param("email") String email);
}
