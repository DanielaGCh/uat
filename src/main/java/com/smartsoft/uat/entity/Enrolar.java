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
 * @author DaniGCh
 */
@Entity
@Table(name = "enrolar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enrolar.findAll", query = "SELECT e FROM Enrolar e")
    , @NamedQuery(name = "Enrolar.findById", query = "SELECT e FROM Enrolar e WHERE e.id = :id")
    , @NamedQuery(name = "Enrolar.findByActivo", query = "SELECT e FROM Enrolar e WHERE e.activo = :activo")
    , @NamedQuery(name = "Enrolar.findAllActivos", query = "SELECT e FROM Enrolar e WHERE e.activo = true and e.matriculaUsu = :matriculaUsu")
    , @NamedQuery(name = "Enrolar.findByAutorizacion", query = "SELECT e FROM Enrolar e WHERE e.autorizacion = :autorizacion")
    , @NamedQuery(name = "Enrolar.findByClave", query = "SELECT e FROM Enrolar e WHERE e.clave = :clave")
    , @NamedQuery(name = "Enrolar.findByMatriculaUsu", query = "SELECT e FROM Enrolar e WHERE e.matriculaUsu = :matriculaUsu")
    , @NamedQuery(name = "Enrolar.findByFolioHorario", query = "SELECT e FROM Enrolar e WHERE e.folioHorario = :folioHorario")
    , @NamedQuery(name = "Enrolar.findEnrolados", query = "SELECT e FROM Enrolar e WHERE e.activo = true and e.autorizacion = true and e.folioHorario = :folioHorario")
    , @NamedQuery(name = "Enrolar.findByFechaRegistro", query = "SELECT e FROM Enrolar e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Enrolar.findByFechaElimino", query = "SELECT e FROM Enrolar e WHERE e.fechaElimino = :fechaElimino")
    , @NamedQuery(name = "Enrolar.findByIdRegistro", query = "SELECT e FROM Enrolar e WHERE e.idRegistro = :idRegistro")
    , @NamedQuery(name = "Enrolar.findByIdElimino", query = "SELECT e FROM Enrolar e WHERE e.idElimino = :idElimino")})
public class Enrolar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "autorizacion")
    private Boolean autorizacion;
    @Size(max = 5)
    @Column(name = "clave")
    private String clave;
    @Size(max = 8)
    @Column(name = "matricula_usu")
    private String matriculaUsu;
    @Size(max = 10)
    @Column(name = "folio_horario")
    private String folioHorario;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "fecha_elimino")
    @Temporal(TemporalType.DATE)
    private Date fechaElimino;
    @Column(name = "id_registro")
    private Integer idRegistro;
    @Column(name = "id_elimino")
    private Integer idElimino;

    public Enrolar() {
    }

    public Enrolar(Integer id) {
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

    public Boolean getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Boolean autorizacion) {
        this.autorizacion = autorizacion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMatriculaUsu() {
        return matriculaUsu;
    }

    public void setMatriculaUsu(String matriculaUsu) {
        this.matriculaUsu = matriculaUsu;
    }

    public String getFolioHorario() {
        return folioHorario;
    }

    public void setFolioHorario(String folioHorario) {
        this.folioHorario = folioHorario;
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
        if (!(object instanceof Enrolar)) {
            return false;
        }
        Enrolar other = (Enrolar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smartsoft.uat.entity.Enrolar[ id=" + id + " ]";
    }
    
}
