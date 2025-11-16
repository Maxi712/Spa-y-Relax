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
import spaentrededos.Producto;

/**
 *
 * @author Valentin Barros
 */
public class ProductoData {
    private Connection conexion;

    public ProductoData(Connection conexion) {
        this.conexion = Conexion.getConexion();
    }
    
    public void guardarProducto(Producto producto){
        String sql = "INSERT INTO producto(nombre, tipo, marca, stock, estado) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getTipo());
            ps.setString(3, producto.getMarca());
            ps.setInt(4, producto.getStock());
            ps.setBoolean(5, producto.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                producto.setIdProducto(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Producto guardado exitosamente ...");
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla producto ..."+ex.getMessage());
        }
    }
    
    public void modificarProducto(Producto producto){
        try{
            String sql = "UPDATE producto SET nombre=?, tipo=?, marca=?, stock=?, estado=? WHERE idProducto=?";
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getTipo());
            ps.setString(3, producto.getMarca());
            ps.setInt(4, producto.getStock());
            ps.setBoolean(5, producto.isEstado());
            ps.setInt(6, producto.getIdProducto());
            int exito = ps.executeUpdate();
            if(exito == 1){
                JOptionPane.showMessageDialog(null, "Producto modificado ...");
            }else{
                JOptionPane.showMessageDialog(null, "Producto no existe...");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Producto ..."+ex.getMessage());
        }
    }
    
    public void eliminarProducto(int idProducto){
        String sql = "UPDATE producto SET estado = 0 WHERE idProducto=?";
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, idProducto);
            int exito = ps.executeUpdate();
            if(exito == 1){
                JOptionPane.showMessageDialog(null, "Producto eliminado ...");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Producto ..."+ex.getMessage());
        }
    }
    
    public void altaProducto(int idProducto){
        String sql = "UPDATE producto SET estado = 1 WHERE idProducto =?";
        PreparedStatement ps;
        try{
           ps = conexion.prepareStatement(sql);
           ps.setInt(1, idProducto);
           int exito  = ps.executeUpdate();
           if(exito==1){
              JOptionPane.showMessageDialog(null, "Producto dado de alta ..."); 
           }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Producto ..."+ex.getMessage());
        }
    }
    
    public ArrayList<Producto> listarProducto(){
        ArrayList <Producto> listaP = new ArrayList();
        String sql = "SELECT * FROM producto ";
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              int idProducto = rs.getInt("idProducto");
              String nombre= rs.getString("nombre");
              String tipo = rs.getString("tipo");
              String marca = rs.getString("marca");
              int stock = rs.getInt("stock");
              boolean estado = rs.getBoolean("estado");
              Producto p = new Producto(idProducto, nombre, tipo, marca, stock, estado);
              listaP.add(p);
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Producto ..."+ex.getMessage());
        }
        return listaP;
    }
    
    public Producto buscarPorId(int idProducto){
        String sql = "SELECT * FROM producto WHERE idProducto =?";
        Producto p = new Producto();
        try{
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                p.setIdProducto(rs.getInt("idProducto"));
                p.setNombre(rs.getString("nombre"));
                p.setTipo(rs.getString("tipo"));
                p.setMarca(rs.getString("marca"));
                p.setStock(rs.getInt("stock"));
                p.setEstado(rs.getBoolean("estado"));
                
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Producto ..."+ex.getMessage());
        }
        return p;
    }
    
}
