package com.project.vivian.entidad;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "usuario_spring", indexes = {
        @Index(name = "uq_dni_spring", columnList = "dni", unique = true),
        @Index(name = "uq_username_spring", columnList = "username", unique = true)
})
@Entity
public class UsuarioSpring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario", nullable = false)
    private Integer id;

    @Column(name = "dni", nullable = false, length = 8)
    private String dni;

    @Column(name = "nombresUsuario", nullable = false, length = 45)
    private String nombresUsuario;

    @Column(name = "apellidosUsuario", nullable = false, length = 45)
    private String apellidosUsuario;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "telefono", nullable = false, length = 9)
    private String telefono;

    @Column(name = "fechaRegistro", nullable = false)
    private LocalDate fechaRegistro;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idTipo", nullable = false)
    private Tipo idTipo;

    @Column(name = "activo", nullable = false)
    private Integer activo;

    public String activoString(){
        if (activo == 1){
            return "Activo";
        }else{
            return "De baja";
        }
    }


    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public Tipo getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Tipo idTipo) {
        this.idTipo = idTipo;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public String getNombresUsuario() {
        return nombresUsuario;
    }

    public void setNombresUsuario(String nombresUsuario) {
        this.nombresUsuario = nombresUsuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}