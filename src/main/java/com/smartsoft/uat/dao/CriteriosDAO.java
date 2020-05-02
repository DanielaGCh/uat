/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.dao;

import com.smartsoft.uat.entity.Criteriosevaluacion;
import com.smartsoft.uat.seguridad.EntitiManager;
import com.smartsoft.uat.seguridad.Persistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author andre
 */
@Stateless
public class CriteriosDAO extends EntitiManager<Criteriosevaluacion> {
    
        
    @Inject
    private Persistence persistence;

    public CriteriosDAO() {
        super(Criteriosevaluacion.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return persistence.getMysql();
    }
    
    
    /*public Justificantes existe(String unidadAprendizaje){
        List<Horarios> lista = persistence.getMysql().createNamedQuery("Justificantes.existe").setParameter("unidadAprendizaje", unidadAprendizaje).getResultList();
       return lista.size()>0?lista.get(0):null;
    }*/
    
    /*public List<Justificantes> obtenerlista(){
        return persistence.getMysql().createNamedQuery("Justificantes.findAll").getResultList();
    }*/
    
    public List<Criteriosevaluacion> listaActivos(){
        return persistence.getMysql().createNamedQuery("Criteriosevaluacion.findAllActivos").getResultList();
    }
    
}
