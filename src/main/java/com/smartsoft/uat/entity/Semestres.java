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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chave
 */
@Entity
@Table(name = "semestres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Semestres.findAll", query = "SELECT s FROM Semestres s")
    , @NamedQuery(name = "Semestres.findAllActivos", query = "SELECT s FROM Semestres s WHERE s.activo = true")
    , @NamedQuery(name = "Semestres.findById", query = "SELECT s FROM Semestres s WHERE s.id = :id")
    , @NamedQuery(name = "Semestres.findByActivo", query = "SELECT s FROM Semestres s WHERE s.activo = :activo")
    , @NamedQuery(name = "Semestres.findByNombresem", query = "SELECT s FROM Semestres s WHERE s.activo = true and s.nombresem = :nombresem")
    , @NamedQuery(name = "Semestres.findByFechaRegistro", query = "SELECT s FROM Semestres s WHERE s.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Semestres.findByFechaElimino", query = "SELECT s FROM Semestres s WHERE s.fechaElimino = :fechaElimino")
    , @NamedQuery(name = "Semestres.findByIdRegistro", query = "SELECT s FROM Semestres s WHERE s.idRegistro = :idRegistro")
    , @NamedQuery(name = "Semestres.findByIdElimino", query = "SELECT s FROM Semestres s WHERE s.idElimino = :idElimino")})
public class Semestres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "activo")
    private Boolean activo;
    @Size(max = 15)
    @Column(name = "nombresem")
    private String nombresem;
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

    public Semestres() {
    }

    public Semestres(Integer id) {
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

    public String getNombresem() {
        return nombresem;
    }

    public void setNombresem(String nombresem) {
        this.nombresem = nombresem;
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
        if (!(object instanceof Semestres)) {
            return false;
        }
        Semestres other = (Semestres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smartsoft.uat.entity.Semestres[ id=" + id + " ]";
    }
    
}
