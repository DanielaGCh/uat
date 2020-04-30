/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.dao;

import com.smartsoft.uat.entity.Eventos;
import com.smartsoft.uat.entity.Usuarios;
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
public class EventosDAO extends EntitiManager<Eventos>{
        @Inject
    private Persistence persistence;

    public EventosDAO() {
        super(Eventos.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return persistence.getMysql();
    }
    public List<Eventos> listaActivos(){
        return persistence.getMysql().createNamedQuery("Eventos.findAllActivos").getResultList();
    }
    public Eventos existe(String evento){
        List<Eventos> lista = persistence.getMysql().createNamedQuery("Eventos.existe").setParameter("asunto", evento).getResultList();
        return lista.size()>0?lista.get(0):null;
    }
    
}
