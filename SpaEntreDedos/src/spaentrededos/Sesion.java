
package spaentrededos;

import java.time.LocalDateTime;
import java.util.List;

public class Sesion {
    private int codSesion;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String tratamiento;
    private Consultorio consultorio;
    private Masajista masajista;
    private List<Instalacion> instalacion;
    private Dia_De_Spa diaDeSpa;
    private boolean estado;

    public Sesion() {
    }

    public Sesion(int codSesion, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String tratamiento, Consultorio consultorio, Masajista masajista, List<Instalacion> instalacion, Dia_De_Spa diaDeSpa, boolean estado) {
        this.codSesion = codSesion;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.tratamiento = tratamiento;
        this.consultorio = consultorio;
        this.masajista = masajista;
        this.instalacion = instalacion;
        this.diaDeSpa = diaDeSpa;
        this.estado = estado;
    }

    public Sesion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCodSesion() {
        return codSesion;
    }

    public void setCodSesion(int codSesion) {
        this.codSesion = codSesion;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public Masajista getMasajista() {
        return masajista;
    }

    public void setMasajista(Masajista masajista) {
        this.masajista = masajista;
    }

    public List<Instalacion> getInstalacion() {
        return instalacion;
    }

    public void setInstalacion(List<Instalacion> instalacion) {
        this.instalacion = instalacion;
    }

    public Dia_De_Spa getDiaDeSpa() {
        return diaDeSpa;
    }

    public void setDiaDeSpa(Dia_De_Spa diaDeSpa) {
        this.diaDeSpa = diaDeSpa;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
