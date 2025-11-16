/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;
import spaentrededos.Consultorio;

/**
 *
 * @author carri
 */
class ConsultorioData {
    private Connection conexion;
    
    public ConsultorioData(){
        conexion = Conexion.getConexion();
    }
    
    public void guardarConsultorio(Consultorio consultorio){
        String sql = "INSERT INTO alumno(nroConsultorio, usos, equipamiento, apto) VALUES (?,?,?,?)";
        try{
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, consultorio.getNroConsultorio());
            ps.setString(2, consultorio.getUsos());
            ps.setString(3, consultorio.getEquipamiento());
            ps.setBoolean(4, consultorio.isApto());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Consultorio guardado exitosamente ...");
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla consultorio ..."+ex.getMessage());
        }
    }
    
    public void modificarConsultorio(Consultorio consultorio){
        try{
            String sql = "UPDATE alumno SET usos=?, equipamiento=?, apto=? WHERE nroConsultorio=?";
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, consultorio.getUsos());
            ps.setString(2, consultorio.getEquipamiento());
            ps.setBoolean(3, consultorio.isApto());
            ps.setInt(4, consultorio.getNroConsultorio());
            int exito = ps.executeUpdate();
            if(exito == 1){
                JOptionPane.showMessageDialog(null, "Consultorio modificado ...");
            }else{
                JOptionPane.showMessageDialog(null, "Consultorio no existe...");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Consultorio ..."+ex.getMessage());
        }
    }
    
    public void eliminarConsultorio(int nroConsultorio){
        String sql = "UPDATE consultorio SET apto = 0 WHERE nroConsultorio=?";
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, nroConsultorio);
            int exito = ps.executeUpdate();
            if(exito == 1){
                JOptionPane.showMessageDialog(null, "Consultorio eliminado ...");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Consultorio ..."+ex.getMessage());
        }
    }
    
    public void altaConsultorio(int nroConsultorio){
        String sql = "UPDATE consultorio SET apto = 1 WHERE nroConsultorio =?";
        PreparedStatement ps;
        try{
           ps = conexion.prepareStatement(sql);
           ps.setInt(1, nroConsultorio);
           int exito  = ps.executeUpdate();
           if(exito==1){
              JOptionPane.showMessageDialog(null, "Consultorio dado de alta ..."); 
           }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Consultorio ..."+ex.getMessage());
        }
    }
    
    public ArrayList<Consultorio> listarConsultorio(){
        ArrayList <Consultorio> listaC = new ArrayList();
        String sql = "SELECT * FROM consultorio ";
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              int nroConsultorio = rs.getInt("nroConsultorio");
              String usos = rs.getString("usos");
              String equipamiento = rs.getString("equipamiento");
              boolean apto = rs.getBoolean("apto");
             Consultorio c= new Consultorio(nroConsultorio, usos, equipamiento, apto);
              listaC.add(c);
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Consultorio ..."+ex.getMessage());
        }
        return listaC;
    }
    
    public Consultorio buscarPorNro(int nroConsultorio){
        String sql = "SELECT * FROM consultorio WHERE nroConsultorio =?";
        Consultorio c = new Consultorio();
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, nroConsultorio);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                c.setNroConsultorio(rs.getInt("nroConsultorio"));
                c.setUsos(rs.getString("usos"));
                c.setEquipamiento(rs.getString("equipamiento"));
                c.setApto(rs.getBoolean("apto"));
                
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Consultorio ..."+ex.getMessage());
        }
        return c;
    }
    
}
