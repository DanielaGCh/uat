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
@Table(name = "unidadaprendizaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unidadaprendizaje.findAll", query = "SELECT u FROM Unidadaprendizaje u")
    , @NamedQuery(name = "Unidadaprendizaje.findById", query = "SELECT u FROM Unidadaprendizaje u WHERE u.id = :id")
    , @NamedQuery(name = "Unidadaprendizaje.findByActivo", query = "SELECT u FROM Unidadaprendizaje u WHERE u.activo = :activo")
    , @NamedQuery(name = "Unidadaprendizaje.findByDescripcion", query = "SELECT u FROM Unidadaprendizaje u WHERE u.descripcion = :descripcion")
    , @NamedQuery(name = "Unidadaprendizaje.findByDescripANDMatriculaActivos", query = "SELECT u FROM Unidadaprendizaje u WHERE u.activo = true and u.descripcion = :descripcion and u.matriculaDoc = :matriculaDoc")
    , @NamedQuery(name = "Unidadaprendizaje.findByMatriculaDoc", query = "SELECT u FROM Unidadaprendizaje u WHERE u.matriculaDoc = :matriculaDoc")
    , @NamedQuery(name = "Unidadaprendizaje.findAllMatriculaDoc", query = "SELECT u FROM Unidadaprendizaje u WHERE u.activo = true and u.matriculaDoc = :matriculaDoc")
    , @NamedQuery(name = "Unidadaprendizaje.findByFechaRegistro", query = "SELECT u FROM Unidadaprendizaje u WHERE u.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Unidadaprendizaje.findByFechaEllimino", query = "SELECT u FROM Unidadaprendizaje u WHERE u.fechaEllimino = :fechaEllimino")
    , @NamedQuery(name = "Unidadaprendizaje.findByIdRegistro", query = "SELECT u FROM Unidadaprendizaje u WHERE u.idRegistro = :idRegistro")
    , @NamedQuery(name = "Unidadaprendizaje.findByIdElimino", query = "SELECT u FROM Unidadaprendizaje u WHERE u.idElimino = :idElimino")})
public class Unidadaprendizaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "activo")
    private Boolean activo;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 8)
    @Column(name = "matricula_Doc")
    private String matriculaDoc;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fecha_ellimino")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEllimino;
    @Column(name = "id_registro")
    private Integer idRegistro;
    @Column(name = "id_elimino")
    private Integer idElimino;

    public Unidadaprendizaje() {
    }

    public Unidadaprendizaje(Integer id) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMatriculaDoc() {
        return matriculaDoc;
    }

    public void setMatriculaDoc(String matriculaDoc) {
        this.matriculaDoc = matriculaDoc;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaEllimino() {
        return fechaEllimino;
    }

    public void setFechaEllimino(Date fechaEllimino) {
        this.fechaEllimino = fechaEllimino;
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
        if (!(object instanceof Unidadaprendizaje)) {
            return false;
        }
        Unidadaprendizaje other = (Unidadaprendizaje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smartsoft.uat.entity.Unidadaprendizaje[ id=" + id + " ]";
    }
    
}
