package Persistencia;

import Modelo.Conexion;
import java.sql.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import spaentrededos.Producto;
import spaentrededos.Tratamiento;

public class TratamientoData {

    private Connection conexion;

    public TratamientoData() {
        this.conexion = Conexion.getConexion();
    }

    public void guardarTratamiento(Tratamiento tratamiento){
        String sql = "INSERT INTO tratamiento(nombre, tipo, detalle, duracion, costo, estado, idProducto ) VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps = conexion.prepareStatement(sql, org.mariadb.jdbc.Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tratamiento.getNombre());
            ps.setString(2, tratamiento.getTipo());
            ps.setString(3, tratamiento.getDetalle());
            ps.setTime(4, Time.valueOf(tratamiento.getDuracion()));
            ps.setDouble(5, tratamiento.getCosto());
            ps.setBoolean(6, tratamiento.isEstado());
            for(Producto p : tratamiento.getProductos()){
                ps.setInt(7, p.getIdProducto());
            }
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                tratamiento.setCodTratam(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Tratamiento guardado exitosamente ...");
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Tratamiento ..."+ex.getMessage());
        }
    }
    
    public void modificarTratamiento(Tratamiento tratamiento){
        try{
            String sql = "UPDATE tratamiento SET nombre=?, tipo=?, detalle=?, duracion=?, costo=?, estado=?, idProducto=? WHERE codTratamiento=?";
            PreparedStatement ps = conexion.prepareStatement(sql, org.mariadb.jdbc.Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tratamiento.getNombre());
            ps.setString(2, tratamiento.getTipo());
            ps.setString(3, tratamiento.getDetalle());
            ps.setTime(4, Time.valueOf(tratamiento.getDuracion()));
            ps.setDouble(5, tratamiento.getCosto());
            ps.setBoolean(6, tratamiento.isEstado());
            for(Producto p : tratamiento.getProductos()){
                ps.setInt(7, p.getIdProducto());
            }
            ps.setInt(8, tratamiento.getCodTratam());
            int exito = ps.executeUpdate();
            if(exito == 1){
                JOptionPane.showMessageDialog(null, "Tratamiento modificado ...");
            }else{
                JOptionPane.showMessageDialog(null, "Tratamiento no existe...");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla tratamiento ..."+ex.getMessage());
        }
    }
    
    public void eliminarTratamiento(int codTratamiento){
        String sql = "UPDATE tratamiento SET estado = 0 WHERE codTratamiento=?";
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, codTratamiento);
            int exito = ps.executeUpdate();
            if(exito == 1){
                JOptionPane.showMessageDialog(null, "Tratamiiento eliminado ...");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla tratamiento ..."+ex.getMessage());
        }
    }
    
    public void altaTratamiento(int codTratamiento){
        String sql = "UPDATE tratamiento SET estado = 1 WHERE codTratamiento =?";
        PreparedStatement ps;
        try{
           ps = conexion.prepareStatement(sql);
           ps.setInt(1, codTratamiento);
           int exito  = ps.executeUpdate();
           if(exito==1){
              JOptionPane.showMessageDialog(null, "Tratamiento dado de alta ..."); 
           }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla tratamiento ..."+ex.getMessage());
        }
    }
    
    public ArrayList<Tratamiento> listarTratamiento(){
        ArrayList <Tratamiento> listaT = new ArrayList();
        String sql = "SELECT * FROM tratamiento ";
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              int codTratamiento = rs.getInt("codTratamiento");
              String nombre = rs.getString("nombre");
              String tipo = rs.getString("tipo");
              String detale = rs.getString("detalle");
              LocalTime duracion = rs.getTime("duracion").toLocalTime();
              double costo = rs.getDouble("costo");
              boolean estado = rs.getBoolean("estado");
              Tratamiento t = new Tratamiento();
              listaT.add(t);
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno ..."+ex.getMessage());
        }
        return listaT;
    }
    
    public Tratamiento buscarPorCod(int codTratamiento){
        String sql = "SELECT * FROM alumno WHERE idAlumno =?";
        Alumno a = new Alumno();
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                a.setIdAlumno(rs.getInt("idAlumno"));
                a.setDni(rs.getInt("dni"));
                a.setApellido(rs.getString("apellido"));
                a.setNombre(rs.getString("nombre"));
                a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                a.setEstado(rs.getBoolean("estado"));
                
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno ..."+ex.getMessage());
        }
        return a;
    } 
    
    public List<Tratamiento> listarTratamientosPorTipo(String tipo) throws SQLException {
        List<Tratamiento> tratamientos = new ArrayList<>();
        String sql = "SELECT codTratam, nombre, tipo, detalle, costo, estado, duracion " + "FROM tratamiento WHERE tipo = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, tipo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tratamiento t = new Tratamiento(
                            rs.getInt("codTratam"),
                            rs.getString("nombre"),
                            rs.getString("tipo"),
                            rs.getString("detalle"),
                            null,
                            rs.getTime("duracion").toLocalTime(),
                            rs.getDouble("costo"),
                            rs.getBoolean("estado"));
                    tratamientos.add(t);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al acceder a la tabla Tratamiento(listarPorTipo): " + ex.getMessage());
        }
        return tratamientos;
    }

    public List<Tratamiento> listarTratamientosMasSesionados(int limite) throws SQLException {
        List<Tratamiento> tratamientos = new ArrayList<>();
        String sql = "SELECT t.codTratam, t.nombre, t.tipo, COUNT(s.codTratam) AS totalSesiones "
                + "FROM tratamiento t JOIN sesion s ON t.codTratam = s.codTratam "
                + "GROUP BY t.codTratam, t.nombre, t.tipo " + "ORDER BY totalSesiones DESC LIMIT ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, limite);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tratamiento t = new Tratamiento(
                            rs.getInt("codTratam"),
                            rs.getString("nombre"),
                            rs.getString("tipo"),
                            rs.getInt("totalSesiones"));
                    tratamientos.add(t);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al acceder a la tabla Tratamiento(listarMasSesionados): " + ex.getMessage());
        }
        return tratamientos;
    }
}
