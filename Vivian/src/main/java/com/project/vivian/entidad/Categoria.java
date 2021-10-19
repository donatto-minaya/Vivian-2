package com.project.vivian.entidad;

import javax.persistence.*;

@Table(name = "categoria")
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoria", nullable = false)
    private Integer id;

    @Column(name = "descripcionCategoria", nullable = false, length = 45)
    private String descripcionCategoria;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    public String estadoString(){
        if (estado == 1){
            return "Habilitado";
        }else{
            return "Deshabilitado";
        }
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}