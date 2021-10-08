package com.project.vivian.entidad;

import javax.persistence.*;

@Table(name = "usuario", indexes = {
        @Index(name = "uq_dni", columnList = "dni", unique = true),
        @Index(name = "uq_email", columnList = "email", unique = true)
})
@Entity
public class Usuario {
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

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "clave", nullable = false)
    private String clave;

    @Column(name = "telefono", nullable = false, length = 9)
    private String telefono;

    @Column(name = "fechaRegistro", nullable = false)
    private String fechaRegistro;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idTipo", nullable = false)
    private Tipo idTipo;

    @Column(name = "activo", nullable = false)
    private Integer activo;

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

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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