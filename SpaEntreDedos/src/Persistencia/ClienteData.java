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
import spaentrededos.Cliente;

/**
 *
 * @author carri
 */
public class ClienteData {
    private Connection conexion;
    
    public ClienteData(){
        conexion = Conexion.getConexion();
    }
    
   
      public void guardarCliente(Cliente c) {
        String sql = "INSERT INTO cliente (dni, nombreCompleto, telefono, edad, afecciones, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getDni());               
            ps.setString(2, c.getNombreCompleto());     
            ps.setString(3, c.getTelefono());           
            ps.setInt(4, c.getEdad());                 
            ps.setString(5, c.getAfecciones());        
            ps.setBoolean(6, c.isEstado());          

            ps.executeUpdate();

            // Obtener el ID generado automáticamente
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                c.setCodCli(rs.getInt(1)); // asigna el codCli generado al objeto
            }

            ps.close();
            JOptionPane.showMessageDialog(null, "Cliente guardado correctamente.");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar el cliente: " + ex.getMessage());
        }
    }
}

   