/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.dao;
import com.smartsoft.uat.entity.Semestres;
import com.smartsoft.uat.seguridad.EntitiManager;
import com.smartsoft.uat.seguridad.Persistence;
//import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
/**
 *
 * @author chave
 */
@Stateless
public class SemestresDAO extends EntitiManager<Semestres>{
     @Inject
    private Persistence persistence;

    public SemestresDAO() {
        super(Semestres.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return persistence.getMysql();
    }
    
        public Semestres existe(String nombresem){
        List<Semestres> lista = persistence.getMysql().createNamedQuery("Semestres.findByNombresem").setParameter("nombresem", nombresem).getResultList();
        return lista.size()>0?lista.get(0):null;
    }
 
    public List<Semestres> listaActivos(){
        return persistence.getMysql().createNamedQuery("Semestres.findAllActivos").getResultList();
    }
}
