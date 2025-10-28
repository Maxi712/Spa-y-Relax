/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import spaentrededos.Cliente;

/**
 *
 * @author carri
 */
public class ClienteData {

    private Connection conexion;

    public ClienteData() {
        conexion = Conexion.getConexion();
    }

    public void guardarCliente(Cliente c) {
        String sql = "INSERT INTO cliente (dni, nombreCompleto, telefono, edad, afecciones, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, c.getDni());
            ps.setString(2, c.getNombreCompleto());
            ps.setInt(3, c.getTelefono());
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

    public void modificarCliente(Cliente c) {
        String sql = "UPDATE cliente SET dni=?, nombreCompleto=?, telefono=?, edad=?, afecciones=?, estado=? WHERE codCli=?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, c.getDni());
            ps.setString(2, c.getNombreCompleto());
            ps.setInt(3, c.getTelefono());
            ps.setInt(4, c.getEdad());
            ps.setString(5, c.getAfecciones());
            ps.setBoolean(6, c.isEstado());
              ps.setInt(7, c.getCodCli());

           
            // Obtener el ID generado automáticamente
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Cliente modificado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el cliente a modificar.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar el cliente: " + ex.getMessage());
        }
    }

    public void eliminarCliente(int codCli) {
        String sql = "UPDATE cliente SET estado = 0 WHERE codCli = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, codCli);

            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado (inactivo).");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el cliente a eliminar.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente: " + ex.getMessage());
        }
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE estado = 1"; // solo activos
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setCodCli(rs.getInt("codCli"));
                c.setDni(rs.getInt("dni"));
                c.setNombreCompleto(rs.getString("nombreCompleto"));
                c.setTelefono(rs.getInt("telefono"));
                c.setEdad(rs.getInt("edad"));
                c.setAfecciones(rs.getString("afecciones"));
                c.setEstado(rs.getBoolean("estado"));

                clientes.add(c);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar clientes: " + ex.getMessage());
        }
        return clientes;
    }

    public Cliente buscarClientePorId(int codCli) {
        Cliente c = null;
        String sql = "SELECT * FROM cliente WHERE codCli = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, codCli);
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
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar el cliente: " + ex.getMessage());
        }
        return c;
    }

    public Cliente buscarClientePorDni(String dni) {
        Cliente c = null;
        String sql = "SELECT * FROM cliente WHERE dni = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, dni);
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
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar el cliente por DNI: " + ex.getMessage());
        }
        return c;
    }
}
