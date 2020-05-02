/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
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
 * @author andre
 */
@Entity
@Table(name = "justificantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Justificantes.findAll", query = "SELECT j FROM Justificantes j")
    , @NamedQuery(name = "Justificantes.findById", query = "SELECT j FROM Justificantes j WHERE j.id = :id")
    , @NamedQuery(name = "Justificantes.findAllActivo", query = "SELECT j FROM Justificantes j WHERE j.activo = true")
    , @NamedQuery(name = "Justificantes.findByActivo", query = "SELECT j FROM Justificantes j WHERE j.activo = :activo")
    , @NamedQuery(name = "Justificantes.findByMatriculaAlum", query = "SELECT j FROM Justificantes j WHERE j.matriculaAlum = :matriculaAlum")
    , @NamedQuery(name = "Justificantes.findByFecha", query = "SELECT j FROM Justificantes j WHERE j.fecha = :fecha")
    , @NamedQuery(name = "Justificantes.findByMotivo", query = "SELECT j FROM Justificantes j WHERE j.motivo = :motivo")
    , @NamedQuery(name = "Justificantes.findByFechaRegistro", query = "SELECT j FROM Justificantes j WHERE j.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Justificantes.findByFechaElimino", query = "SELECT j FROM Justificantes j WHERE j.fechaElimino = :fechaElimino")
    , @NamedQuery(name = "Justificantes.findByIdRegistro", query = "SELECT j FROM Justificantes j WHERE j.idRegistro = :idRegistro")
    , @NamedQuery(name = "Justificantes.findByIdElimino", query = "SELECT j FROM Justificantes j WHERE j.idElimino = :idElimino")})
public class Justificantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "activo")
    private Boolean activo;
    @Size(max = 8)
    @Column(name = "matriculaAlum")
    private String matriculaAlum;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 50)
    @Column(name = "motivo")
    private String motivo;
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

    public Justificantes() {
    }

    public Justificantes(Integer id) {
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

    public String getMatriculaAlum() {
        return matriculaAlum;
    }

    public void setMatriculaAlum(String matriculaAlum) {
        this.matriculaAlum = matriculaAlum;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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
        if (!(object instanceof Justificantes)) {
            return false;
        }
        Justificantes other = (Justificantes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smartsoft.uat.entity.Justificantes[ id=" + id + " ]";
    }
    
    public String fechaString(Date fecha) {
        SimpleDateFormat formatoFecha;
        formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
        return fecha!=null ? formatoFecha.format(fecha): "Sin definir" ;
    }
    
    
}
