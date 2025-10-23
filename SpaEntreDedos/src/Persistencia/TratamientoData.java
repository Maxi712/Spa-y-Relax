
package Persistencia;

import Modelo.Conexion;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import spaentrededos.Tratamiento;

public class TratamientoData {
    private Connection  con = null;
    
    public TratamientoData(Conexion conexion){
        this.con = Conexion.getConexion();
    }
    public List<Tratamiento> listarTratamientosPorTipo(String tipo) throws SQLException{
        List<Tratamiento> tratamientos = new ArrayList<>();
        String sql = "SELECT codTratam, nombre, tipo, detalle, costo, estado, duracion " + "FROM tratamiento WHERE tipo = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, tipo);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
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
        } catch(SQLException ex){
            System.out.println("Error al acceder a la tabla Tratamiento(listarPorTipo): " +ex.getMessage());
        }
    return tratamientos;
    }
public List<Tratamiento> listarTratamientosMasSesionados(int limite) throws SQLException{
    List<Tratamiento> tratamientos = new ArrayList<>();
    String sql = "SELECT t.codTratam, t.nombre, t.tipo, COUNT(s.codTratam) AS totalSesiones " 
            + "FROM tratamiento t JOIN sesion s ON t.codTratam = s.codTratam " 
            + "GROUP BY t.codTratam, t.nombre, t.tipo " + "ORDER BY totalSesiones DESC LIMIT ?";
    try(PreparedStatement ps = con.prepareStatement(sql)){
        ps.setInt(1, limite);
        try(ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                Tratamiento t = new Tratamiento(
                rs.getInt("codTratam"),
                rs.getString("nombre"),
                rs.getString("tipo"),
                rs.getInt("totalSesiones"));
            tratamientos.add(t);
            }
        }
    } catch(SQLException ex){
        System.out.println("Error al acceder a la tabla Tratamiento(listarMasSesionados): " +ex.getMessage());
    }
return tratamientos;
}
}
