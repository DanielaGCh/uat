/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.dao;

import com.smartsoft.uat.entity.Horarios;
import com.smartsoft.uat.seguridad.EntitiManager;
import com.smartsoft.uat.seguridad.Persistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author DaniGCh
 */
@Stateless
public class HorariosDAO extends EntitiManager<Horarios> {
    
    @Inject
    private Persistence persistence;

    public HorariosDAO() {
        super(Horarios.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return persistence.getMysql();
    }
    
    public List<Horarios> listaActivos(){
        return persistence.getMysql().createNamedQuery("Horarios.findAllActivo").getResultList();
    }
 
        public List<Horarios> listaUniApren(String periodo, String semestre){
        return persistence.getMysql().createNamedQuery("Horarios.findByPeriodoSemestre").setParameter("periodo", periodo).setParameter("semestre", semestre).getResultList();
    }
        
    public Horarios obtenerFolioMateria(String periodo,String unidadAprendizaje, String grupo){
        List<Horarios> lista = persistence.getMysql().createNamedQuery("Horarios.findFolio").setParameter("periodo", periodo).setParameter("unidadAprendizaje", unidadAprendizaje).setParameter("grupo", grupo).getResultList();
        return lista.size()>0?lista.get(0):null;
    }
       
        
//    public List<Horarios> obtenerMiListaUni(String matricula){
//        return persistence.getMysql().createNamedQuery("Horarios.Horarios.findAllMatriculaDocente").setParameter("matricula", matricula).getResultList();
//    }
}
