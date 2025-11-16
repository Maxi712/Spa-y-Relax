
package spaentrededos;

public class Producto {
    private int idProducto;
    private String nombre;
    private String tipo;
    private String marca;
    private int stock;
    private boolean estado;

    public Producto() {
    }

   
    
    public Producto(int idProducto, String nombre, String tipo, String marca, int stock, boolean estado) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.tipo = tipo;
        this.marca = marca;
        this.stock = stock;
        this.estado = estado;
    }

    public Producto(String nombre, String tipo, String marca, int stock, boolean estado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.marca = marca;
        this.stock = stock;
        this.estado = estado;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
    
    
}
