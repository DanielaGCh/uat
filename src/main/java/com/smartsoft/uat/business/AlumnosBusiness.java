package com.smartsoft.uat.business;

import com.smartsoft.uat.dao.AlumnosDAO;
import com.smartsoft.uat.entity.Alumnos;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class AlumnosBusiness {
    
    @Inject
    private AlumnosDAO dao;
    
    public Alumnos guardar(Alumnos entity) {
        return dao.saveOrEdit(entity, entity.getId());
    }

    public Alumnos consultar(Integer id) {
        return dao.search(id);
    }

    public void eliminar(Alumnos entity, Integer idalumno) {
        entity.setActivo(false);
        dao.deleteLogically(entity);
    }
 
    public List<Alumnos> obtenerListaActivos() {
        return dao.listaActivos();
    }
    
    public Alumnos existeAlum(String alumno){
        return dao.existeAlum(alumno);
    }    
}