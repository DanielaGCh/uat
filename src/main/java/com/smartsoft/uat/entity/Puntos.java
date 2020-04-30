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
 * @author DaniGCh
 */
@Entity
@Table(name = "puntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puntos.findAll", query = "SELECT p FROM Puntos p")
    , @NamedQuery(name = "Puntos.findByIdPuntos", query = "SELECT p FROM Puntos p WHERE p.idPuntos = :idPuntos")
    , @NamedQuery(name = "Puntos.findByIdArea", query = "SELECT p FROM Puntos p WHERE p.idArea = :idArea")
    , @NamedQuery(name = "Puntos.findByLatitud", query = "SELECT p FROM Puntos p WHERE p.latitud = :latitud")
    , @NamedQuery(name = "Puntos.findByLongitud", query = "SELECT p FROM Puntos p WHERE p.longitud = :longitud")})
public class Puntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_puntos")
    private Integer idPuntos;
    @Column(name = "id_area")
    private Integer idArea;
    @Size(max = 255)
    @Column(name = "latitud")
    private String latitud;
    @Size(max = 255)
    @Column(name = "longitud")
    private String longitud;

    public Puntos() {
    }

    public Puntos(Integer idPuntos) {
        this.idPuntos = idPuntos;
    }

    public Integer getIdPuntos() {
        return idPuntos;
    }

    public void setIdPuntos(Integer idPuntos) {
        this.idPuntos = idPuntos;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPuntos != null ? idPuntos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puntos)) {
            return false;
        }
        Puntos other = (Puntos) object;
        if ((this.idPuntos == null && other.idPuntos != null) || (this.idPuntos != null && !this.idPuntos.equals(other.idPuntos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smartsoft.uat.entity.Puntos[ idPuntos=" + idPuntos + " ]";
    }
    
}
