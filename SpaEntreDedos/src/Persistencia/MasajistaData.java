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
import spaentrededos.Masajista;

/**
 *
 * @author carri
 */
class MasajistaData {
    private Connection conexion;
    
    public MasajistaData(){
        conexion = Conexion.getConexion();
    }
    
    public void GuardarMasajista(Masajista masajista){
        String sql = "INSERT INTO masajista(matricula, nombreApellido, telefono, especialidad, estado) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, masajista.getMatricula());
            ps.setString(2, masajista.getNombreApellido());
            ps.setInt(3, masajista.getTelefono());
            ps.setString(4, masajista.getEspecialidad());
            ps.setBoolean(5, masajista.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Masajista guardado exitosamente ...");
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla masajista ..."+ex.getMessage());
        }
    }
    
    public void modificarMasajista(Masajista masajista){
        try{
            String sql = "UPDATE masajista SET nombreApellido=?, telefono=?, especialidad=?, estado=? WHERE matricula=?";
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, masajista.getNombreApellido());
            ps.setInt(2, masajista.getTelefono());
            ps.setString(3, masajista.getEspecialidad());
            ps.setBoolean(4, masajista.isEstado());
            ps.setInt(5, masajista.getMatricula());
            int exito = ps.executeUpdate();
            if(exito == 1){
                JOptionPane.showMessageDialog(null, "Masajista modificado ...");
            }else{
                JOptionPane.showMessageDialog(null, "Masajista no existe...");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla masajista ..."+ex.getMessage());
        }
    }
    
    public void eliminarMasajista(int matricula){
        String sql = "UPDATE masajista SET estado = 0 WHERE matricula=?";
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, matricula);
            int exito = ps.executeUpdate();
            if(exito == 1){
                JOptionPane.showMessageDialog(null, "Masajista eliminado ...");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla masajista ..."+ex.getMessage());
        }
    }
    
    public void altaMasajista(int matricula){
        String sql = "UPDATE masajista SET estado = 1 WHERE matricula =?";
        PreparedStatement ps;
        try{
           ps = conexion.prepareStatement(sql);
           ps.setInt(1, matricula);
           int exito  = ps.executeUpdate();
           if(exito==1){
              JOptionPane.showMessageDialog(null, "Masajista dado de alta ..."); 
           }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla masajista ..."+ex.getMessage());
        }
    }
    
    public ArrayList<Masajista> listarMasajista(){
        ArrayList <Masajista> listaM = new ArrayList();
        String sql = "SELECT * FROM masajista ";
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              int matricula = rs.getInt("matricula");
              String nombreApellido = rs.getString("nombreApellido");
              int telefono = rs.getInt("telefono");
              String especialidad = rs.getString("especialidad");
              boolean estado = rs.getBoolean("estado");
              Masajista m = new Masajista(matricula, nombreApellido, telefono, especialidad, estado);
              listaM.add(m);
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla masajista ..."+ex.getMessage());
        }
        return listaM;
    }
    
    public Masajista buscarPorMatricula(int matricula){
        String sql = "SELECT * FROM masajista WHERE masajista =?";
        Masajista masajista = new Masajista();
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, matricula);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                masajista.setMatricula(rs.getInt("matricula"));
                masajista.setNombreApellido(rs.getString("nombreApellido"));
                masajista.setTelefono(rs.getInt("telefono"));
                masajista.setEspecialidad(rs.getString("especialidad"));
                masajista.setEstado(rs.getBoolean("estado"));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla masajista ..."+ex.getMessage());
        }
        return masajista;
    }
    
    public Masajista buscarPorNombre(String nombreApellido){
        String sql = "SELECT * FROM masajista WHERE nombreApellido =?";
        Masajista masajista = new Masajista();
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombreApellido);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                masajista.setMatricula(rs.getInt("matricula"));
                masajista.setNombreApellido(rs.getString("nombreApellido"));
                masajista.setTelefono(rs.getInt("telefono"));
                masajista.setEspecialidad(rs.getString("especialidad"));
                masajista.setEstado(rs.getBoolean("estado"));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla masajista ..."+ex.getMessage());
        }
        return masajista;
    }
}
