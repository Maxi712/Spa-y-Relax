/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Conexion;
import spaentrededos.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author carrizoAnna
 */
public class entidadData {

    private Connection con = null;

    public entidadData(Conexion conexion) {
        this.con = conexion.getConexion();
    }

    public void guardarCliente(Cliente c) {
        String sql = "INSERT INTO cliente (dni, nombreCompleto, telefono, edad, afecciones, estado)  VALUES (?, ?, ?, ?, ?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, c.getDni());
            ps.setString(2, c.getNombreCompleto());
            ps.setInt(3, c.getTelefono());
            ps.setInt(4, c.getEdad());
            ps.setString(5, c.getAfecciones());
            ps.setBoolean(6, c.isEstado());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                c.setCodCli(rs.getInt(1));

            } else {
                System.out.println(" no se pudo tener id ");
            }
            ps.close();
            System.out.println("guardado");
            JOptionPane.showMessageDialog(null, "Cliente guardado correctamente.");
        } catch (SQLException ex) {
            Logger.getLogger(entidadData.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al guardar cliente.");
        }
    }
        public ArrayList<Cliente> listarCliente(){
        ArrayList <Cliente> listaC = new ArrayList();
        String sql = "SELECT * FROM cliente ";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              int codCli = rs.getInt("codCli");
              int dni = rs.getInt("dni");
              String nombreCompleto = rs.getString("nombreCompleto");
              int telefono = rs.getInt("telefono");
              int edad=rs.getInt("edad");
              String afecciones = rs.getString("afecciones");
              boolean estado = rs.getBoolean("estado");
              Cliente c = new Cliente(codCli, dni, nombreCompleto, telefono, edad, afecciones, estado);
              listaC.add(c);
            }
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno ..."+ex.getMessage());
        }
        return listaC;
    }

    public Cliente buscarCliente(int id) {
        Cliente c = null;
        String sql = "SELECT codCli, dni, nombreCompleto, telefono, edad, afecciones, estado FROM cliente WHERE codCli = ?";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Cliente();
                c.setCodCli(rs.getInt("codCli"));
                c.setDni(rs.getInt("dni"));
                c.setNombreCompleto(rs.getString("nombreCompleto"));
                c.setTelefono(rs.getInt("telefono"));
                c.setEdad(rs.getInt("edad"));
                c.setAfecciones(rs.getString("afecciones"));
                c.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe el cliente");
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Cliente: " + ex.getMessage());
        }

        return c;
    }

    public void actualizarCliente(Cliente c) {
        String sql = "UPDATE cliente SET dni = ?, nombreCompleto = ?, telefono = ?, edad = ?, afecciones = ?, estado = ? WHERE codCli = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, c.getDni());
            ps.setString(2, c.getNombreCompleto());
            ps.setInt(3, c.getTelefono());
            ps.setInt(4, c.getEdad());
            ps.setString(5, c.getAfecciones());
            ps.setBoolean(6, c.isEstado());
            ps.setInt(7, c.getCodCli());

            ps.executeUpdate();
            ps.close();
            System.out.println(" actualizado");

        } catch (SQLException ex) {
            Logger.getLogger(entidadData.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void borrarCliente(Cliente c) {

        String sql = "DELETE FROM cliente WHERE codCli = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, c.getCodCli());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(entidadData.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
