/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "criteriosevaluacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Criteriosevaluacion.findAll", query = "SELECT c FROM Criteriosevaluacion c")
    ,@NamedQuery(name = "Criteriosevaluacion.findAllActivos", query = "SELECT c FROM Criteriosevaluacion c WHERE c.activo= true")
    , @NamedQuery(name = "Criteriosevaluacion.findByIdCriterio", query = "SELECT c FROM Criteriosevaluacion c WHERE c.idCriterio = :idCriterio")
    , @NamedQuery(name = "Criteriosevaluacion.findByMatriculaDocente", query = "SELECT c FROM Criteriosevaluacion c WHERE c.matriculaDocente = :matriculaDocente")
    , @NamedQuery(name = "Criteriosevaluacion.findByPorcentaje", query = "SELECT c FROM Criteriosevaluacion c WHERE c.porcentaje = :porcentaje")
    , @NamedQuery(name = "Criteriosevaluacion.findByActivo", query = "SELECT c FROM Criteriosevaluacion c WHERE c.activo = :activo")
    , @NamedQuery(name = "Criteriosevaluacion.findByNombre", query = "SELECT c FROM Criteriosevaluacion c WHERE c.nombre = :nombre")})
public class Criteriosevaluacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCriterio")
    private Integer idCriterio;
    @Size(max = 255)
    @Column(name = "matriculaDocente")
    private String matriculaDocente;
    @Column(name = "porcentaje")
    private Integer porcentaje;
    @Column(name = "activo")
    private Boolean activo;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;

    public Criteriosevaluacion() {
    }

    public Criteriosevaluacion(Integer idCriterio) {
        this.idCriterio = idCriterio;
    }

    public Integer getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(Integer idCriterio) {
        this.idCriterio = idCriterio;
    }

    public String getMatriculaDocente() {
        return matriculaDocente;
    }

    public void setMatriculaDocente(String matriculaDocente) {
        this.matriculaDocente = matriculaDocente;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCriterio != null ? idCriterio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Criteriosevaluacion)) {
            return false;
        }
        Criteriosevaluacion other = (Criteriosevaluacion) object;
        if ((this.idCriterio == null && other.idCriterio != null) || (this.idCriterio != null && !this.idCriterio.equals(other.idCriterio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smartsoft.uat.entity.Criteriosevaluacion[ idCriterio=" + idCriterio + " ]";
    }
    
}
