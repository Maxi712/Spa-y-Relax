/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaentrededos;

/**
 *
 * @author Valentin Barros
 */
public class ProductoTratamiento {
    private int idPT;
    private Producto producto;
    private Tratamiento tratamiento;

    public ProductoTratamiento() {
    }

    public ProductoTratamiento(Producto producto, Tratamiento tratamiento) {
        this.producto = producto;
        this.tratamiento = tratamiento;
    }

    public ProductoTratamiento(int idPT, Producto producto, Tratamiento tratamiento) {
        this.idPT = idPT;
        this.producto = producto;
        this.tratamiento = tratamiento;
    }

    public int getIdPT() {
        return idPT;
    }

    public void setIdPT(int idPT) {
        this.idPT = idPT;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }
    
    
}
