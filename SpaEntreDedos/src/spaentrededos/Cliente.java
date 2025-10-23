
package spaentrededos;

public class Cliente {
    private int codCli;
    private int dni;
    private String nombreCompleto;
    private int telefono;
    private int edad;
    private String afecciones;
    private boolean estado;

    public Cliente() {
    }

    public Cliente(int codCli, int dni, String nombreCompleto, int telefono, int edad, String afecciones, boolean estado) {
        this.codCli = codCli;
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.edad = edad;
        this.afecciones = afecciones;
        this.estado = estado;
    }

    public int getCodCli() {
        return codCli;
    }

    public void setCodCli(int codCli) {
        this.codCli = codCli;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAfecciones() {
        return afecciones;
    }

    public void setAfecciones(String afecciones) {
        this.afecciones = afecciones;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
