package com.project.vivian.dao;

import com.project.vivian.entidad.Reserva;
import com.project.vivian.entidad.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("reservacionesDAO")
public interface ReservacionesDAO extends JpaRepository<Reserva,Integer> {

    public List<Reserva> findAllByOrderByFechaReservacionDesc();

    public Optional<Reserva> findByUsuarioId	(Usuario usuario);
    
    public Reserva getById(int id);

}
