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
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import spaentrededos.Dia_De_Spa;

/**
 *
 * @author carri
 */
class Dia_De_SpaData {

    private Connection conexion;

    public Dia_De_SpaData(Conexion conexion) {
        this.conexion = conexion.getConexion();
    }

    Dia_De_SpaData() {
        
         //To change body of generated methods, choose Tools | Templates.
    }

    public void guardarDiaDeSpa(Dia_De_Spa diaSpa) {
        String sql = "INSERT INTO diadespa (fechayHora, preferencias, monto, estado, codCli, codSesion) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Timestamp.valueOf(diaSpa.getFechaYHora()));
            ps.setString(2, diaSpa.getPreferencias());
            ps.setDouble(3, diaSpa.getMonto());
            ps.setBoolean(4, diaSpa.isEstado());
            ps.setInt(5, diaSpa.getCliente().getCodCli());
            ps.setInt(6, diaSpa.getSesiones().get(0).getCodSesion()); // guarda la primera sesión por ahora
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                diaSpa.setCodPack(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Día de Spa guardado correctamente.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Día de Spa: " + ex.getMessage());
        }
    }

    public void modificarDiaDeSpa(Dia_De_Spa diaSpa) {
        String sql = "UPDATE diadespa SET fechayHora=?, preferencias=?, monto=?, estado=?, codCli=?, codSesion=? WHERE codPack=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setTimestamp(1, Timestamp.valueOf(diaSpa.getFechaYHora()));
            ps.setString(2, diaSpa.getPreferencias());
            ps.setDouble(3, diaSpa.getMonto());
            ps.setBoolean(4, diaSpa.isEstado());
            ps.setInt(5, diaSpa.getCliente().getCodCli());
            ps.setInt(6, diaSpa.getSesiones().get(0).getCodSesion());
            ps.setInt(7, diaSpa.getCodPack());

            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Día de Spa modificado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el Día de Spa.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar Día de Spa: " + ex.getMessage());
        }
    }

    public void eliminarDiaDeSpa(int codPack) {
        String sql = "UPDATE diadespa SET estado = 0 WHERE codPack=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, codPack);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Día de Spa eliminado correctamente.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar Día de Spa: " + ex.getMessage());
        }
    }

    public void altaDiaDeSpa(int codPack) {
        String sql = "UPDATE diadespa SET estado = 1 WHERE codPack=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, codPack);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Atualizaacion dia de spa correcto");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Atualizaacion dia de spa incorrecto " + ex.getMessage());
        }
    }

    public ArrayList<Dia_De_Spa> listarDiasDeSpa() {
        ArrayList<Dia_De_Spa> lista = new ArrayList<>();
        String sql = "SELECT * FROM diadespa";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Dia_De_Spa diaSpa = new Dia_De_Spa(
                        rs.getInt("codPack"),
                        rs.getTimestamp("fechayHora").toLocalDateTime(),
                        rs.getString("preferencias"),
                        null,  null, 
                        rs.getDouble("monto"),
                        rs.getBoolean("estado")
                );
                lista.add(diaSpa);
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "errror al listar dias de Spa: " + ex.getMessage());
        }
        return lista;
    }

    public Dia_De_Spa buscarDiaSpaPorId (int codPack) {
        String sql = "SELECT * FROM diadespa WHERE codPack=?";
        Dia_De_Spa diaSpa = null;
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, codPack);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                diaSpa = new Dia_De_Spa(
                        rs.getInt("codPack"),
                        rs.getTimestamp("fechayHora").toLocalDateTime(),
                        rs.getString("preferencias"),
                        null,
                        null,
                        rs.getDouble("monto"),
                        rs.getBoolean("estado")
                );
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al buscar dia de Spa: " + ex.getMessage());
        }
        return diaSpa;
    }
}

