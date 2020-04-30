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
@Table(name = "recuperarcontrase\u00f1a")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recuperarcontrase\u00f1a.findAll", query = "SELECT r FROM Recuperarcontrase\u00f1a r")
    , @NamedQuery(name = "Recuperarcontrase\u00f1a.findById", query = "SELECT r FROM Recuperarcontrase\u00f1a r WHERE r.id = :id")
    , @NamedQuery(name = "Recuperarcontrase\u00f1a.findByParametro", query = "SELECT r FROM Recuperarcontrase\u00f1a r WHERE r.parametro = :parametro")
    , @NamedQuery(name = "Recuperarcontrase\u00f1a.findByValor", query = "SELECT r FROM Recuperarcontrase\u00f1a r WHERE r.valor = :valor")})
public class Recuperarcontraseña implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "parametro")
    private String parametro;
    @Size(max = 255)
    @Column(name = "valor")
    private String valor;

    public Recuperarcontraseña() {
    }

    public Recuperarcontraseña(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
        if (!(object instanceof Recuperarcontraseña)) {
            return false;
        }
        Recuperarcontraseña other = (Recuperarcontraseña) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smartsoft.uat.entity.Recuperarcontrase\u00f1a[ id=" + id + " ]";
    }
    
}
