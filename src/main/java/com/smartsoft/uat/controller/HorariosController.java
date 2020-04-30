package com.smartsoft.uat.controller;

import com.csvreader.CsvReader;
import com.smartsoft.uat.business.HorariosBusiness;
//import com.smartsoft.uat.business.PeriodosBusiness;
import com.smartsoft.uat.controller.view.HorariosView;
//import com.smartsoft.uat.controller.view.PeriodosView;
import com.smartsoft.uat.entity.Horarios;
import com.smartsoft.uat.entity.Periodos;
import com.smartsoft.uat.entity.Semestres;
import com.smartsoft.uat.entity.Unidadaprendizaje;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author hp
 */
@Named(value = "horariosController")
@ViewScoped
public class HorariosController implements Serializable{
    
    @Inject
    private SesionController sesion;
    @Inject
    private HorariosBusiness business;
//    @Inject
//    private PeriodosBusiness businessPeriodo;
    
    private HorariosView view;
//    private PeriodosView viewPeriodo;
    
    @PostConstruct
    public void init() {
        view = new HorariosView();
        mostrarLista();
    }
    
    public void mostrarLista(){
        view.setEntity(null);
        view.setListaEntity(business.obtenerListaActivos());
        //view.setListaEntity(business.obtenerLista());
    }
    
    
    public void nuevo() {
        view.setEntity(new Horarios());
        view.setListaEntity(null);
        view.getEntity().setIdhorario(0);
        view.getEntity().setActivo(true);
        view.getEntity().setIdRegistro(sesion.getView().getUsuario().getId());
        view.getEntity().setFechaRegistro(new Date());
        
        
    }
    
    
//    public void nuevoPeriodo(String nomPeriodo) {
//        viewPeriodo.getEntityperio().setNombreperiodo(nomPeriodo);
//        viewPeriodo.setEntityperio(new Periodos());
//        viewPeriodo.setLista(null);
//        viewPeriodo.getEntityperio().getId();
//        viewPeriodo.getEntityperio().setActivo(true);
//        viewPeriodo.getEntityperio().setIdRegistro(sesion.getView().getUsuario().getId());
//        viewPeriodo.getEntityperio().setFechaRegistro(new Date());
//    }
    
    public void editar(Horarios entity) {
        view.setEntity(entity);
        view.setListaEntity(null);
    }

    public void eliminar(Horarios entity) {
        entity.setActivo(false);
        entity.setIdElimino(sesion.getView().getUsuario().getId());
        entity.setFechaElimino(new Date());
        business.eliminar(entity, null);
        sesion.MessageInfo("Registro eliminado");
        mostrarLista();
    }

    public void guardar() {
        if (existeSemestre()== false) {
        view.setEntitySemestre(new Semestres());
        view.setListaEntitySemestres(null);
        view.getEntitySemestre().setNombresem(view.getEntity().getSemestre());
        view.getEntitySemestre().setId(0);
        view.getEntitySemestre().setActivo(true);
        view.getEntitySemestre().setIdRegistro(sesion.getView().getUsuario().getId());
        view.getEntitySemestre().setFechaRegistro(new Date());
        
        business.guardarSemestres(view.getEntitySemestre());
             }
        
        if (existeUnidad()== false){
        view.setEntityUnidad(new Unidadaprendizaje ());
        view.setListaEntityUnidad(null);
        view.getEntityUnidad().setDescripcion(view.getEntity().getUnidadAprendizaje());
        view.getEntityUnidad().setMatriculaDoc(view.getEntity().getMatriculaDocente());
        view.getEntityUnidad().setId(0);
        view.getEntityUnidad().setActivo(true);
        view.getEntityUnidad().setIdRegistro(sesion.getView().getUsuario().getId());
        view.getEntityUnidad().setFechaRegistro(new Date());
        
        business.guardarUnidades(view.getEntityUnidad());
             }
                
        if (existePeriodo()== false) {
        view.setEntityPeriodos(new Periodos());
        view.setListaEntityPeriodos(null);
        view.getEntityPeriodos().setNombreperiodo(view.getEntity().getPeriodo());
        view.getEntityPeriodos().setId(0);
        view.getEntityPeriodos().setActivo(true);
        view.getEntityPeriodos().setIdRegistro(sesion.getView().getUsuario().getId());
        view.getEntityPeriodos().setFechaRegistro(new Date());
        
        business.guardarPeriodos(view.getEntityPeriodos());
             }
        
        if (existeDoc()== false) {
        sesion.MessageError("La matricula es invalida");
            return;
         }
        business.guardar(view.getEntity());
        sesion.MessageInfo("Registro exitoso");
        mostrarLista();
    }
    
    public void  cargarhorarios (FileUploadEvent event) throws IOException{
        
        if (existePeriodoSubir()== false) {
        view.setEntityPeriodos(new Periodos());
        view.setListaEntityPeriodos(null);
        view.getEntityPeriodos().setNombreperiodo(view.getPeriodo());
        view.setPeriodo(view.getEntityPeriodos().getNombreperiodo());
        view.getEntityPeriodos().setId(0);
        view.getEntityPeriodos().setActivo(true);
        view.getEntityPeriodos().setIdRegistro(sesion.getView().getUsuario().getId());
        view.getEntityPeriodos().setFechaRegistro(new Date());
        
        business.guardarPeriodos(view.getEntityPeriodos());
             }
       
        UploadedFile archivo = event.getFile();
        
        Reader br = new BufferedReader (new InputStreamReader (archivo.getInputstream()));
        CsvReader csv = new CsvReader(br);
        csv.readHeaders();
        String fecha = "01/01/1999";
        
                try{
            while(csv.readRecord()){
               view.setEntity(new Horarios());
               view.getEntity().setIdhorario(0);
               view.getEntity().setActivo(true);
               //horario.setPeriodo(view.getEntity().getPeriodo());
               view.getEntity().setPeriodo(view.getPeriodo());
               view.setPeriodo(view.getEntity().getPeriodo());
               view.getEntity().setFolio(csv.get("folio"));
               view.getEntity().setSemestre(csv.get("semestre"));
               
               if (existeSemestreSubir(view.getEntity().getSemestre())== false) {
                    view.setEntitySemestre(new Semestres());
                    view.setListaEntitySemestres(null);
                    view.getEntitySemestre().setNombresem(view.getEntity().getSemestre());
                    view.getEntitySemestre().setId(0);
                    view.getEntitySemestre().setActivo(true);
                    view.getEntitySemestre().setIdRegistro(sesion.getView().getUsuario().getId());
                    view.getEntitySemestre().setFechaRegistro(new Date());

                    business.guardarSemestres(view.getEntitySemestre());
                }
               
               
               view.getEntity().setMatriculaDocente(csv.get("matricula_docente"));
               view.getEntity().setUnidadAprendizaje(csv.get("unidadAprendizaje"));
               
                if (existeUnidadSubir(view.getEntity().getUnidadAprendizaje(), view.getEntity().getMatriculaDocente())== false){
                    view.setEntityUnidad(new Unidadaprendizaje ());
                    view.setListaEntityUnidad(null);
                    view.getEntityUnidad().setDescripcion(view.getEntity().getUnidadAprendizaje());
                    view.getEntityUnidad().setMatriculaDoc(view.getEntity().getMatriculaDocente());
                    view.getEntityUnidad().setId(0);
                    view.getEntityUnidad().setActivo(true);
                    view.getEntityUnidad().setIdRegistro(sesion.getView().getUsuario().getId());
                    view.getEntityUnidad().setFechaRegistro(new Date());

                    business.guardarUnidades(view.getEntityUnidad());
                }
               
               if(csv.get("lunesi")!= ""){
                    view.getEntity().setLunesi(view.getEntity().setHoras(fecha, csv.get("lunesi")));
               }else{
                   view.getEntity().setLunesi(null);
               }
               if(csv.get("lunesf")!= ""){
               view.getEntity().setLunesf(view.getEntity().setHoras(fecha, csv.get("lunesf")));
               }else{
                   view.getEntity().setLunesf(null);
               }
               if(csv.get("martesi")!= ""){
               view.getEntity().setMartesi(view.getEntity().setHoras(fecha, csv.get("martesi")));
               }else{
                   view.getEntity().setMartesi(null);
               }
               if(csv.get("martesf")!= ""){
               view.getEntity().setMartesf(view.getEntity().setHoras(fecha, csv.get("martesf")));
               }else{
                   view.getEntity().setMartesf(null);
               }
               if(csv.get("miercolesi")!= ""){
               view.getEntity().setMiercolesi(view.getEntity().setHoras(fecha, csv.get("miercolesi")));
               }else{
                   view.getEntity().setMiercolesi(null);
               }
               if(csv.get("miercolesf")!= ""){
               view.getEntity().setMiercolesf(view.getEntity().setHoras(fecha, csv.get("miercolesf")));
               }else{
                   view.getEntity().setMiercolesf(null);
               }
               if(csv.get("juevesi")!= ""){
               view.getEntity().setJuevesi(view.getEntity().setHoras(fecha, csv.get("juevesi")));
               }else{
                   view.getEntity().setJuevesi(null);
               }
               if(csv.get("juevesf")!= ""){
               view.getEntity().setJuevesf(view.getEntity().setHoras(fecha, csv.get("juevesf")));
               }else{
                  view.getEntity().setJuevesf(null);
               }
               if(csv.get("viernesi")!= ""){
              view.getEntity().setViernesi(view.getEntity().setHoras(fecha, csv.get("viernesi")));
               }else{
                   view.getEntity().setJuevesi(null);
               }
               if(csv.get("viernesf")!= ""){
               view.getEntity().setViernesf(view.getEntity().setHoras(fecha, csv.get("viernesf")));
               }else{
                   view.getEntity().setViernesf(null);
               }
               view.getEntity().setGrupo(csv.get("grupo"));
               view.getEntity().setAula(csv.get("aula"));
               view.getEntity().setFechaRegistro(new Date());
               view.getEntity().setIdRegistro(sesion.getView().getUsuario().getId());
                
               business.guardar(view.getEntity());
               sesion.MessageInfo("Registro exitoso");
            } 
        }catch(Exception e){
                    sesion.MessageError("Error en el registro");
                    e.printStackTrace();
            }
        finally{
            if(null!=br){
                br.close();
            }
        }
    }
    

     public boolean existeDoc() {
        if (business.existeDoc(view.getEntity().getMatriculaDocente()) != null) {
            return true;
        }
        return false;
    }
     
    public boolean existePeriodo() {
        if (business.existePeri(view.getEntity().getPeriodo())!= null) {
            return true;
        }
        return false;
    }
    
     public boolean existePeriodoSubir() {
        if (business.existePeri(view.getPeriodo())!= null) {
            return true;
        }
        return false;
    }
    
    public boolean existeSemestre() {
        if (business.existeSem(view.getEntity().getPeriodo())!= null) {
            return true;
        }
        return false;
    }
    
        public boolean existeSemestreSubir(String semestre) {
        if (business.existeSem(semestre)!= null) {
            return true;
        }
        return false;
    }
     
    public boolean existeUnidad() {
        if (business.existeUni(view.getEntity().getUnidadAprendizaje(),view.getEntity().getMatriculaDocente())!= null) {
            return true;
        }
        return false;
    }
    
        public boolean existeUnidadSubir(String unidad, String matricula) {
        if (business.existeUni(unidad, matricula)!=null) {
            return true;
        }
        return false;
    }    
        
    public HorariosView getView(){
        return view;
    }
    
//    public PeriodosView getViewPerio(){
//        return viewPeriodo;
//    }
}
