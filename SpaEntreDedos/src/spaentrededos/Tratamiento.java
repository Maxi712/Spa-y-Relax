
package spaentrededos;

import java.time.LocalTime;
import java.util.List;

public class Tratamiento {
    private int codTratam;
    private String nombre;
    private String tipo;
    private String detalle;
    private List<Instalacion> productos;
    private LocalTime duracion;
    private double costo;
    private boolean estado;
    private int totalSesiones;

    public Tratamiento(int codTratam, String nombre, String tipo, String detalle, List<Instalacion> productos, LocalTime duracion, double costo, boolean estado) {
        this.codTratam = codTratam;
        this.nombre = nombre;
        this.tipo = tipo;
        this.detalle = detalle;
        this.productos = productos;
        this.duracion = duracion;
        this.costo = costo;
        this.estado = estado;
    }
public Tratamiento(int codTratam, String nombre, String tipo, int totalSesiones){
    this.codTratam = codTratam;
    this.nombre = nombre;
    this.tipo = tipo;
    this.totalSesiones = totalSesiones;
}
    public int getCodTratam() {
        return codTratam;
    }

    public void setCodTratam(int codTratam) {
        this.codTratam = codTratam;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public List<Instalacion> getProductos() {
        return productos;
    }

    public void setProductos(List<Instalacion> productos) {
        this.productos = productos;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
