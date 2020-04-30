/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.dao;


import com.smartsoft.uat.entity.Periodos;
import com.smartsoft.uat.seguridad.EntitiManager;
import com.smartsoft.uat.seguridad.Persistence;
//import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author DaniGCh
 */
@Stateless
public class PeriodosDAO extends EntitiManager<Periodos> {
    
    @Inject
    private Persistence persistence;

    public PeriodosDAO() {
        super(Periodos.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return persistence.getMysql();
    }
    
        public Periodos existe(String nombreperiodo){
        List<Periodos> lista = persistence.getMysql().createNamedQuery("Periodos.existe").setParameter("nombreperiodo", nombreperiodo).getResultList();
        return lista.size()>0?lista.get(0):null;
    }
 
    public List<Periodos> listaActivos(){
        return persistence.getMysql().createNamedQuery("Periodos.findAllActivos").getResultList();
    }
    
    
}
