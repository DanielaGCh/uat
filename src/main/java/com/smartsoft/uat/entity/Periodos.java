/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chave
 */
@Entity
@Table(name = "periodos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodos.findAll", query = "SELECT p FROM Periodos p")
    , @NamedQuery(name = "Periodos.findById", query = "SELECT p FROM Periodos p WHERE p.id = :id")
    , @NamedQuery(name = "Periodos.findAllActivos", query = "SELECT p FROM Periodos p WHERE p.activo = true" )
    , @NamedQuery(name = "Periodos.findByActivo", query = "SELECT p FROM Periodos p WHERE p.activo = :activo")
    , @NamedQuery(name = "Periodos.existe", query = "SELECT p FROM Periodos p WHERE p.activo = true and p.nombreperiodo = :nombreperiodo")
    , @NamedQuery(name = "Periodos.findByNombreperiodo", query = "SELECT p FROM Periodos p WHERE p.nombreperiodo = :nombreperiodo")
    , @NamedQuery(name = "Periodos.findByFechaRegistro", query = "SELECT p FROM Periodos p WHERE p.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Periodos.findByFechaElimino", query = "SELECT p FROM Periodos p WHERE p.fechaElimino = :fechaElimino")
    , @NamedQuery(name = "Periodos.findByIdRegistro", query = "SELECT p FROM Periodos p WHERE p.idRegistro = :idRegistro")
    , @NamedQuery(name = "Periodos.findByIdElimino", query = "SELECT p FROM Periodos p WHERE p.idElimino = :idElimino")})
public class Periodos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "activo")
    private Boolean activo;
    //@Size(max = 15)
    @Column(name = "nombreperiodo")
    private String nombreperiodo;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fecha_elimino")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaElimino;
    @Column(name = "id_registro")
    private Integer idRegistro;
    @Column(name = "id_elimino")
    private Integer idElimino;

    public Periodos() {
    }

    public Periodos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getNombreperiodo() {
        return nombreperiodo;
    }

    public void setNombreperiodo(String nombreperiodo) {
        this.nombreperiodo = nombreperiodo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaElimino() {
        return fechaElimino;
    }

    public void setFechaElimino(Date fechaElimino) {
        this.fechaElimino = fechaElimino;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Integer getIdElimino() {
        return idElimino;
    }

    public void setIdElimino(Integer idElimino) {
        this.idElimino = idElimino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodos)) {
            return false;
        }
        Periodos other = (Periodos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smartsoft.uat.entity.Periodos[ id=" + id + " ]";
    }
    
}
