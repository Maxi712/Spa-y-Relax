/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import spaentrededos.Cliente;

/**
 *
 * @author carrizoAnna
 */
public class ClienteData {
    private Connection con=null; 

public ClienteData (Conexion conexion) { 
    this.con = conexion.getConexion(); 
}

public void guardarCliente (Cliente c ) {
   String sql = "INSERT INTO cliente (INSERT INTO `cliente`( dni, nombreCompleto, telefono, edad, afecciones, estado)  VALUES (?, ?, ?, ?, ?,?) ";
   
try {
   PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    ps.setInt(1, c.getDni());
            ps.setString(2, c.getNombreCompleto());
            ps.setInt(3, c.getTelefono());
            ps.setInt(4, c.getEdad());
            ps.setString(5, c.getAfecciones());
            ps.setBoolean(6, c.isEstado());
            
            ps.executeUpdate();
            ps.close();
   JOptionPane.showMessageDialog(null, "Cliente guardado correctamente.");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteData.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al guardar cliente.");
        }
}
   
}
