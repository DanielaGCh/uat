/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.dao;

import com.smartsoft.uat.entity.Puntos;
import com.smartsoft.uat.seguridad.EntitiManager;
import com.smartsoft.uat.seguridad.Persistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author hp
 */
@Stateless
public class PuntosDAO extends EntitiManager<Puntos>{
    
    @Inject
    private Persistence persistence;

    public PuntosDAO() {
        super(Puntos.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return persistence.getMysql();
    }
    
    public Puntos verificarAccesos(String correo, String contrasena) {
        List<Puntos> lista = persistence.getMysql().createQuery("SELECT c from Usuarios c WHERE c.activo=true and c.correo =:correo and c.contrasena =:contrasena").setParameter("correo", correo).setParameter("contrasena", contrasena).getResultList();
        if (lista.isEmpty()) {
            return null;
        }
        return lista.get(0);
    }
    
    public Puntos existe(String correo){
        List<Puntos> lista = persistence.getMysql().createNamedQuery("Usuarios.existe").setParameter("correo", correo).getResultList();
        return lista.size()>0?lista.get(0):null;
    }
    
    public List<Puntos> listaActivos(){
        return persistence.getMysql().createNamedQuery("Usuarios.findAllActivos").getResultList();
    }
    public List<Puntos> obtenerListaActivos(){
        return(List<Puntos>) persistence.getMysql().createNamedQuery("Perfiles.findByIdElimino").setParameter("idElimino",this).getResultList();
    }
    
}
