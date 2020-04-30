/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.dao;

import com.smartsoft.uat.entity.Enrolar;
import com.smartsoft.uat.seguridad.EntitiManager;
import com.smartsoft.uat.seguridad.Persistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author DaniGCh
 */
@Stateless
public class EnrolarDAO extends EntitiManager<Enrolar> {
    
    @Inject
    private Persistence persistence;

    public EnrolarDAO() {
        super(Enrolar.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return persistence.getMysql();
    }
    
    public List<Enrolar> listaActivos(String matriculaUsu){
        return persistence.getMysql().createNamedQuery("Enrolar.findAllActivos").setParameter("matriculaUsu", matriculaUsu).getResultList();
    }
    
    public List<Enrolar> listaEnrolados (String folio){
        return persistence.getMysql().createNamedQuery("Enrolar.findEnrolados").setParameter("folioHorario", folio).getResultList();
    }
}
