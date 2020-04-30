/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.uat.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chave
 */
@Entity
@Table(name = "horarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horarios.findAll", query = "SELECT h FROM Horarios h")
    , @NamedQuery(name = "Horarios.findByIdhorario", query = "SELECT h FROM Horarios h WHERE h.idhorario = :idhorario")
    , @NamedQuery(name = "Horarios.findByActivo", query = "SELECT h FROM Horarios h WHERE h.activo = :activo")
    , @NamedQuery(name = "Horarios.findAllActivo", query = "SELECT h FROM Horarios h WHERE h.activo = true")
    , @NamedQuery(name = "Horarios.findByPeriodoSemestre", query = "SELECT h FROM Horarios h WHERE h.activo = true and h.periodo = :periodo and h.semestre = :semestre")
    , @NamedQuery(name = "Horarios.findByPeriodo", query = "SELECT h FROM Horarios h WHERE h.periodo = :periodo")
    , @NamedQuery(name = "Horarios.findBySemestre", query = "SELECT h FROM Horarios h WHERE h.activo = true and h.semestre = :semestre")
    , @NamedQuery(name = "Horarios.findByFolio", query = "SELECT h FROM Horarios h WHERE h.folio = :folio")
    , @NamedQuery(name = "Horarios.findFolio", query = "SELECT h FROM Horarios h WHERE h.activo = true and h.periodo = :periodo and h.unidadAprendizaje = :unidadAprendizaje and h.grupo = :grupo")
    , @NamedQuery(name = "Horarios.findByMatriculaDocente", query = "SELECT h FROM Horarios h WHERE h.matriculaDocente = :matriculaDocente")
    , @NamedQuery(name = "Horarios.findByUnidadAprendizaje", query = "SELECT h FROM Horarios h WHERE h.unidadAprendizaje = :unidadAprendizaje")
    , @NamedQuery(name = "Horarios.findByLunesi", query = "SELECT h FROM Horarios h WHERE h.lunesi = :lunesi")
    , @NamedQuery(name = "Horarios.findByLunesf", query = "SELECT h FROM Horarios h WHERE h.lunesf = :lunesf")
    , @NamedQuery(name = "Horarios.findByMartesi", query = "SELECT h FROM Horarios h WHERE h.martesi = :martesi")
    , @NamedQuery(name = "Horarios.findByMartesf", query = "SELECT h FROM Horarios h WHERE h.martesf = :martesf")
    , @NamedQuery(name = "Horarios.findByMiercolesi", query = "SELECT h FROM Horarios h WHERE h.miercolesi = :miercolesi")
    , @NamedQuery(name = "Horarios.findByMiercolesf", query = "SELECT h FROM Horarios h WHERE h.miercolesf = :miercolesf")
    , @NamedQuery(name = "Horarios.findByJuevesi", query = "SELECT h FROM Horarios h WHERE h.juevesi = :juevesi")
    , @NamedQuery(name = "Horarios.findByJuevesf", query = "SELECT h FROM Horarios h WHERE h.juevesf = :juevesf")
    , @NamedQuery(name = "Horarios.findByViernesi", query = "SELECT h FROM Horarios h WHERE h.viernesi = :viernesi")
    , @NamedQuery(name = "Horarios.findByViernesf", query = "SELECT h FROM Horarios h WHERE h.viernesf = :viernesf")
    , @NamedQuery(name = "Horarios.findByGrupo", query = "SELECT h FROM Horarios h WHERE h.grupo = :grupo")
    , @NamedQuery(name = "Horarios.findByAula", query = "SELECT h FROM Horarios h WHERE h.aula = :aula")
    , @NamedQuery(name = "Horarios.findByFechaRegistro", query = "SELECT h FROM Horarios h WHERE h.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Horarios.findByFechaElimino", query = "SELECT h FROM Horarios h WHERE h.fechaElimino = :fechaElimino")
    , @NamedQuery(name = "Horarios.findByIdRegistro", query = "SELECT h FROM Horarios h WHERE h.idRegistro = :idRegistro")
    , @NamedQuery(name = "Horarios.findByIdElimino", query = "SELECT h FROM Horarios h WHERE h.idElimino = :idElimino")})
public class Horarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhorario")
    private Integer idhorario;
    @Column(name = "activo")
    private Boolean activo;
    @Size(max = 15)
    @Column(name = "periodo")
    private String periodo;
    @Size(max = 2)
    @Column(name = "semestre")
    private String semestre;
    @Size(max = 10)
    @Column(name = "folio")
    private String folio;
    @Size(max = 8)
    @Column(name = "matricula_docente")
    private String matriculaDocente;
    @Size(max = 255)
    @Column(name = "unidadAprendizaje")
    private String unidadAprendizaje;
    @Column(name = "lunesi")
    @Temporal(TemporalType.TIME)
    private Date lunesi;
    @Column(name = "lunesf")
    @Temporal(TemporalType.TIME)
    private Date lunesf;
    @Column(name = "martesi")
    @Temporal(TemporalType.TIME)
    private Date martesi;
    @Column(name = "martesf")
    @Temporal(TemporalType.TIME)
    private Date martesf;
    @Column(name = "miercolesi")
    @Temporal(TemporalType.TIME)
    private Date miercolesi;
    @Column(name = "miercolesf")
    @Temporal(TemporalType.TIME)
    private Date miercolesf;
    @Column(name = "juevesi")
    @Temporal(TemporalType.TIME)
    private Date juevesi;
    @Column(name = "juevesf")
    @Temporal(TemporalType.TIME)
    private Date juevesf;
    @Column(name = "viernesi")
    @Temporal(TemporalType.TIME)
    private Date viernesi;
    @Column(name = "viernesf")
    @Temporal(TemporalType.TIME)
    private Date viernesf;
    @Size(max = 1)
    @Column(name = "grupo")
    private String grupo;
    @Size(max = 2)
    @Column(name = "aula")
    private String aula;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "fecha_elimino")
    @Temporal(TemporalType.DATE)
    private Date fechaElimino;
    @Column(name = "id_registro")
    private Integer idRegistro;
    @Column(name = "id_elimino")
    private Integer idElimino;

    public Horarios() {
    }

    public Horarios(Integer idhorario) {
        this.idhorario = idhorario;
    }

    public Integer getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(Integer idhorario) {
        this.idhorario = idhorario;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getMatriculaDocente() {
        return matriculaDocente;
    }

    public void setMatriculaDocente(String matriculaDocente) {
        this.matriculaDocente = matriculaDocente;
    }

    public String getUnidadAprendizaje() {
        return unidadAprendizaje;
    }

    public void setUnidadAprendizaje(String unidadAprendizaje) {
        this.unidadAprendizaje = unidadAprendizaje;
    }

    public Date getLunesi() {
        return lunesi;
    }

    public void setLunesi(Date lunesi) {
        this.lunesi = lunesi;
    }

    public Date getLunesf() {
        return lunesf;
    }

    public void setLunesf(Date lunesf) {
        this.lunesf = lunesf;
    }

    public Date getMartesi() {
        return martesi;
    }

    public void setMartesi(Date martesi) {
        this.martesi = martesi;
    }

    public Date getMartesf() {
        return martesf;
    }

    public void setMartesf(Date martesf) {
        this.martesf = martesf;
    }

    public Date getMiercolesi() {
        return miercolesi;
    }

    public void setMiercolesi(Date miercolesi) {
        this.miercolesi = miercolesi;
    }

    public Date getMiercolesf() {
        return miercolesf;
    }

    public void setMiercolesf(Date miercolesf) {
        this.miercolesf = miercolesf;
    }

    public Date getJuevesi() {
        return juevesi;
    }

    public void setJuevesi(Date juevesi) {
        this.juevesi = juevesi;
    }

    public Date getJuevesf() {
        return juevesf;
    }

    public void setJuevesf(Date juevesf) {
        this.juevesf = juevesf;
    }

    public Date getViernesi() {
        return viernesi;
    }

    public void setViernesi(Date viernesi) {
        this.viernesi = viernesi;
    }

    public Date getViernesf() {
        return viernesf;
    }

    public void setViernesf(Date viernesf) {
        this.viernesf = viernesf;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaElimino() {
        return fechaElimino;
    }

    public void setFechaElimino(Date fechaElimino) {
        this.fechaElimino = fechaElimino;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Integer getIdElimino() {
        return idElimino;
    }

    public void setIdElimino(Integer idElimino) {
        this.idElimino = idElimino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhorario != null ? idhorario.hashCode() : 0);
        return hash;
    }
    
    public Date setHoras(String fecha, String hora) throws Exception {
        String fechahora = fecha + " " + hora;
        System.out.print(fechahora);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
        Date d = dateFormat.parse(fechahora);
        return d;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horarios)) {
            return false;
        }
        Horarios other = (Horarios) object;
        if ((this.idhorario == null && other.idhorario != null) || (this.idhorario != null && !this.idhorario.equals(other.idhorario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.smartsoft.uat.entity.Horarios[ idhorario=" + idhorario + " ]";
    }
    
}
