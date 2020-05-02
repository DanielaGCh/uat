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
@Table(name = "eventos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eventos.findAll", query = "SELECT e FROM Eventos e")
    , @NamedQuery(name = "Eventos.findAllActivos", query = "SELECT e FROM Eventos e WHERE e.activo= true")
    , @NamedQuery(name = "Eventos.findById", query = "SELECT e FROM Eventos e WHERE e.id = :id")
    , @NamedQuery(name = "Eventos.findByActivo", query = "SELECT e FROM Eventos e WHERE e.activo = :activo")
    , @NamedQuery(name = "Eventos.findByFecha", query = "SELECT e FROM Eventos e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "Eventos.findByHora", query = "SELECT e FROM Eventos e WHERE e.hora = :hora")
    , @NamedQuery(name = "Eventos.findByLugar", query = "SELECT e FROM Eventos e WHERE e.lugar = :lugar")
    , @NamedQuery(name = "Eventos.findByAsunto", query = "SELECT e FROM Eventos e WHERE e.asunto = :asunto")
    , @NamedQuery(name = "Eventos.findByDescripcion", query = "SELECT e FROM Eventos e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Eventos.findByFechaRegistro", query = "SELECT e FROM Eventos e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Eventos.findByFechaElimino", query = "SELECT e FROM Eventos e WHERE e.fechaElimino = :fechaElimino")
    , @NamedQuery(name = "Eventos.findByIdRegistro", query = "SELECT e FROM Eventos e WHERE e.idRegistro = :idRegistro")
    , @NamedQuery(name = "Eventos.findByIdElimino", query = "SELECT e FROM Eventos e WHERE e.idElimino = :idElimino")})
public class Eventos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Size(max = 255)
    @Column(name = "lugar")
    private String lugar;
    @Size(max = 150)
    @Column(name = "asunto")
    private String asunto;
    @Size(max = 500)
    @Column(name = "descripcion")
    private String descripcion;
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

    public Eventos() {
    }

    public Eventos(Integer id) {
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof Eventos)) {
            return false;
        }
        Eventos other = (Eventos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smartsoft.uat.entity.Eventos[ id=" + id + " ]";
    }
    
        public String fechaString(Date fecha) {
        SimpleDateFormat formatoFecha;
        formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
        return fecha!=null ? formatoFecha.format(fecha): "Sin definir" ;
        }
    public String horaString(Date fecha) {
        SimpleDateFormat formatoFecha;
        formatoFecha = new SimpleDateFormat("HH:MM");
        formatoFecha.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
        return fecha!=null ? formatoFecha.format(fecha): "Sin definir" ;
    }
}
