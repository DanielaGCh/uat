package com.smartsoft.uat.dao;

import com.smartsoft.uat.entity.Unidadaprendizaje;
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
public class UnidadaprendizajeDAO extends EntitiManager<Unidadaprendizaje>{
    
    @Inject
    private Persistence persistence;

    public UnidadaprendizajeDAO() {
        super(Unidadaprendizaje.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return persistence.getMysql();
    }
    
    public List<Unidadaprendizaje> listaActivos(){
        return persistence.getMysql().createNamedQuery("Unidadaprendizaje.findAllActivos").getResultList();
    }
        
   public Unidadaprendizaje existe(String descripcion, String matriculaDoc){
        List<Unidadaprendizaje> lista = persistence.getMysql().createNamedQuery("Unidadaprendizaje.findByDescripANDMatriculaActivos").setParameter("descripcion", descripcion).setParameter("matriculaDoc", matriculaDoc).getResultList();
        return lista.size()>0?lista.get(0):null;
    }
   
    public List<Unidadaprendizaje> obtenerUnidadesPorDoc(String matricula){
        return persistence.getMysql().createNamedQuery("Unidadaprendizaje.findAllMatriculaDoc").setParameter("matriculaDoc", matricula).getResultList();
    }
    
//    public Alumnos existeAlum(String matricula){
//        List<Alumnos> lista = persistence.getMysql().createNamedQuery("Alumnos.existeAlum").setParameter("matricula", matricula).getResultList();
//        return lista.size()>0?lista.get(0):null;
//    }
}