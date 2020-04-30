package com.smartsoft.uat.dao;

import com.smartsoft.uat.entity.Alumnos;
import com.smartsoft.uat.seguridad.EntitiManager;
import com.smartsoft.uat.seguridad.Persistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *DAO solo retorna lista o entitys
 * @author hp
 */
@Stateless
public class AlumnosDAO extends EntitiManager<Alumnos>{
    
    @Inject
    private Persistence persistence;

    public AlumnosDAO() {
        super(Alumnos.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return persistence.getMysql();
    }
    
    public List<Alumnos> listaActivos(){
        return persistence.getMysql().createNamedQuery("Alumnos.findAllActivos").getResultList();
    }
    
    public Alumnos existeAlum(String matricula){
        List<Alumnos> lista = persistence.getMysql().createNamedQuery("Alumnos.existeAlum").setParameter("matricula", matricula).getResultList();
        return lista.size()>0?lista.get(0):null;
    }
}