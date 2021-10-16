package com.project.vivian.dao;

import com.project.vivian.entidad.UsuarioSpring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioSpringDAO extends JpaRepository<UsuarioSpring,Integer> {

//    @Query(value = "{call vUsuarioSpring(:email, :clave)}",nativeQuery = true)
//    UsuarioSpring findByEmailPassword(@Param("email") String email, @Param("clave") String clave);

    public Optional<UsuarioSpring> findByUsername(String username);

}
