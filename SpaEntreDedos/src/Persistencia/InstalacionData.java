
package Persistencia;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import spaentrededos.Instalacion;


public class InstalacionData {
    private Connection c=null;

    public InstalacionData(Conexion conexion) {
        this.c = conexion.getConexion();
    }
    
    public void agregarInstalacion(Instalacion ins){
        String sql = "INSERT INTO instalacion (codInstal, nombre, detalleUso, precio30M, estado) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ins.getCodInstal());
            ps.setString(2, ins.getNombre());
            ps.setString(3, ins.getDetalleUso());
            ps.setDouble(4, ins.getPrecio30M());
            ps.setBoolean(5, ins.isEstado());
            ps.executeUpdate();       
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ins.setCodInstal(rs.getInt(1)); 
            }
            ps.close();
            JOptionPane.showMessageDialog(null, "Instalacion guardada correctamente.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar la instalacion: " + ex.getMessage());
        }
    }
    public void modificarInstalacion(Instalacion ins){
        try{
            String sql = "UPDATE masajista SET nombre=?, detalleUso=?, precio30M=?, estado=? WHERE codInstal=?";
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ins.getNombre());
            ps.setString(2, ins.getDetalleUso());
            ps.setDouble(3, ins.getPrecio30M());
            ps.setBoolean(4, ins.isEstado());
            ps.setInt(5, ins.getCodInstal());
            int exito = ps.executeUpdate();
            if(exito == 1){
                JOptionPane.showMessageDialog(null, "Instalacion modificada ...");
            }else{
                JOptionPane.showMessageDialog(null, "La instalacion no existe...");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla instalacion ..."+ex.getMessage());
        }
    }
    
    public void eliminarInstalacion (Instalacion ins){
        String sql="UPDATE instalacion SET estado=0 WHERE codInstal=?";
        try{
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setInt(1, ins.getCodInstal());
            int e=ps.executeUpdate();
            if(e == 1){
                JOptionPane.showMessageDialog(null, "Instalacion eliminada ...");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla instalacion ..."+ex.getMessage());
        }
    }
    
    public void altaInstalacion(Instalacion ins){
        String sql="UPDATE instalacion SET estado=1 WHERE codInstal=?";
        try{
            PreparedStatement ps=c.prepareStatement(sql);
            ps.setInt(1, ins.getCodInstal());
            int e=ps.executeUpdate();
            if(e == 1){
                JOptionPane.showMessageDialog(null, "Instalacion dado de alta ...");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla instalacion ..."+ex.getMessage());
        }
    }
    
    public List <Instalacion>listarInstalaciones() {
        List <Instalacion> listaI=new ArrayList<>();
        String sql="SELECT * FROM instalacion ";
        try{
            PreparedStatement ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int codInstal = rs.getInt("codInstal");
                String nombre = rs.getString("nombre");
                String detalleUso = rs.getString("detalleUso");
                double precio30M = rs.getDouble("precio30M");
                boolean estado = rs.getBoolean("estado");
                Instalacion i = new Instalacion(codInstal, nombre, detalleUso, precio30M, estado);
                listaI.add(i);
            }
            ps.close();
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla instalacion ..."+ex.getMessage());
        }
        return listaI;
    }
    
    public List <Instalacion>listarInstalacionesLibresHora (LocalDate fecha, LocalTime hi, LocalTime hf){
        List <Instalacion> listaI=new ArrayList<>();
        String sql="SELECT * FROM instalacion WHERE estado=1 AND codInstal("
                + "SELECT codInstal FROM sesion WHERE fechaHoraInicio < ?"
                + "AND fechaHoraFin > ?";
        try{
            PreparedStatement ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ps.setDate(1, Date.valueOf(fecha));
            ps.setTime(2, Time.valueOf(hf));
            ps.setTime(3, Time.valueOf(hi));
            while (rs.next()){
                int codInstal = rs.getInt("codInstal");
                String nombre = rs.getString("nombre");
                String detalleUso = rs.getString("detalleUso");
                double precio30M = rs.getDouble("precio30M");
                boolean estado = rs.getBoolean("estado");
                Instalacion i = new Instalacion(codInstal, nombre, detalleUso, precio30M, estado);
                listaI.add(i);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla instalacion ..."+ex.getMessage());
        }
        return listaI;
    }
    
    
}
