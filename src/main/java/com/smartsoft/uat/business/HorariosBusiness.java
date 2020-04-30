package com.smartsoft.uat.business;

import com.smartsoft.uat.dao.HorariosDAO;
import com.smartsoft.uat.dao.PeriodosDAO;
import com.smartsoft.uat.dao.UsuariosDAO;
import com.smartsoft.uat.dao.SemestresDAO;
import com.smartsoft.uat.dao.UnidadaprendizajeDAO;
import com.smartsoft.uat.entity.Horarios;
import com.smartsoft.uat.entity.Periodos;
import com.smartsoft.uat.entity.Usuarios;
import com.smartsoft.uat.entity.Semestres;
import com.smartsoft.uat.entity.Unidadaprendizaje;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class HorariosBusiness {
    
    @Inject
    private HorariosDAO dao;
    @Inject
    private UsuariosDAO daousu;
    @Inject
    private PeriodosDAO daoperi;
    @Inject
    private SemestresDAO daosem;
    @Inject
    private UnidadaprendizajeDAO daouni;

    
    public Horarios guardar(Horarios entity) {
        return dao.saveOrEdit(entity, entity.getIdhorario());
    }
    
     public Periodos guardarPeriodos(Periodos entity) {
        return daoperi.saveOrEdit(entity, entity.getId()); 
    }
     
    public Semestres guardarSemestres(Semestres entity) {
        return daosem.saveOrEdit(entity, entity.getId()); 
    }
    
    public Unidadaprendizaje guardarUnidades(Unidadaprendizaje entity) {
        return daouni.saveOrEdit(entity, entity.getId()); 
    }
    
   public Horarios obtenerFolioMateria(String periodo,String unidadAprendizaje, String grupo) {
        return dao.obtenerFolioMateria(periodo,unidadAprendizaje, grupo);
    }
    
    public Horarios consultar(Integer idhorario) {
        return dao.search(idhorario);
    }

    public void eliminar(Horarios entity, Integer idhorario) {
        entity.setActivo(false);
        dao.deleteLogically(entity);
    }
 
    public Usuarios existeDoc(String matricula){
        return daousu.existeDoc(matricula);
    }
    
    public Periodos existePeri(String periodo){
        return daoperi.existe(periodo);
    }
    
    public Semestres existeSem(String semestres){
        return daosem.existe(semestres);
    }
    
    public Unidadaprendizaje existeUni(String unidad, String matricula){
        return daouni.existe(unidad, matricula);
    }
    
    public List<Horarios> obtenerListaActivos() {
        return dao.listaActivos();
    }
    
        public List<Horarios> obtenerListaUniApren(String periodo, String semestre) {
        return dao.listaUniApren(periodo, semestre);
    }
        

//    public List<Horarios> obtenerMiListaUni(String matricula) {
//        return dao.obtenerMiListaUni(matricula);
//    }
       
}