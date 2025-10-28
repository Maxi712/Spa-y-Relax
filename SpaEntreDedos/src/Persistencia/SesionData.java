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
import java.util.List;
import javax.swing.JOptionPane;
import spaentrededos.Sesion;

/**
 *
 * @author carri
 */
public class SesionData {

    private Connection conexion;

    public SesionData() {
        conexion = Conexion.getConexion();

    }

    public void guardarSesion(Sesion s) {
        String sql = "INSERR INTO sesion (fechaHoraInicio, fechaHoraFin, tratamiento, nroConsultorio, matricula, codPack, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Timestamp.valueOf(s.getFechaHoraInicio()));
            ps.setTimestamp(2, Timestamp.valueOf(s.getFechaHoraFin()));
            ps.setString(3, s.getTratamiento());
            ps.setInt(4, s.getConsultorio().getNroConsultorio());
            ps.setInt(5, s.getMasajista().getMatricula());
            ps.setInt(6, s.getDiaDeSpa().getCodPack());
            ps.setBoolean(7, s.isEstado());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                s.setCodSesion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Sesión guardada correctamente.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar la sesión: " + ex.getMessage());
        }
    }

    public void modificarSesion(Sesion s) {
        String sql = "UPDATE sesion SET fechaHoraInicio=?, fechaHoraFin=?, tratamiento=?, nroConsultorio=?, matricula=?, codPack=?, estado=? WHERE codSesion=?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Timestamp.valueOf(s.getFechaHoraInicio()));
            ps.setTimestamp(2, Timestamp.valueOf(s.getFechaHoraFin()));
            ps.setString(3, s.getTratamiento());
            ps.setInt(4, s.getConsultorio().getNroConsultorio());
            ps.setInt(5, s.getMasajista().getMatricula());
            ps.setInt(6, s.getDiaDeSpa().getCodPack());
            ps.setBoolean(7, s.isEstado());
            ps.setInt(8, s.getCodSesion());

            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Sesión modificada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la sesión a modificar.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar la sesión: " + ex.getMessage());
        }
    }

    public void eliminarSesion(int codSesion) {
        String sql = "UPDATE sesion SET estado = 0 WHERE codSesion = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, codSesion);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Sesión eliminada (inactiva).");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la sesión a eliminar.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la sesión: " + ex.getMessage());
        }
    }

  

    public List<Sesion> listarSesiones() {
        List<Sesion> sesiones = new ArrayList<>();
        String sql = "SELECT * FROM sesion";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sesion s = new Sesion();
                s.setCodSesion(rs.getInt("codSesion"));
                s.setFechaHoraInicio(rs.getTimestamp("fechaHoraInicio").toLocalDateTime());
                s.setFechaHoraFin(rs.getTimestamp("fechaHoraFin").toLocalDateTime());
                s.setTratamiento(rs.getString("tratamiento"));

                ConsultorioData cd = new ConsultorioData();
                MasajistaData md = new MasajistaData();
                Dia_De_SpaData dd = new Dia_De_SpaData();

                s.setConsultorio(cd.buscarConsultorioPorId(rs.getInt("nroConsultorio")));
                s.setMasajista(md.buscarMasajistaPorId(rs.getInt("matricula")));
                s.setDiaDeSpa(dd.buscarDiaSpaPorId(rs.getInt("codPack")));
                s.setEstado(rs.getBoolean("estado"));

                sesiones.add(s);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar sesiones: " + ex.getMessage());
        }
        return sesiones;
    }

    public List<Sesion> listarSesionesPorMasajista(int matricula) {
        List<Sesion> sesiones = new ArrayList<>();
        String sql = "SELECT * FROM sesion WHERE matricula = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, matricula);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sesion s = new Sesion();
                s.setCodSesion(rs.getInt("codSesion"));
                s.setFechaHoraInicio(rs.getTimestamp("fechaHoraInicio").toLocalDateTime());
                s.setFechaHoraFin(rs.getTimestamp("fechaHoraFin").toLocalDateTime());
                s.setTratamiento(rs.getString("tratamiento"));

                ConsultorioData cd = new ConsultorioData();
                Dia_De_SpaData dd = new Dia_De_SpaData();

                s.setConsultorio(cd.buscarConsultorioPorId(rs.getInt("nroConsultorio")));
                s.setMasajista(new MasajistaData().buscarMasajistaPorId(matricula));
                s.setDiaDeSpa(dd.buscarDiaSpaPorId(rs.getInt("codPack")));
                s.setEstado(rs.getBoolean("estado"));

                sesiones.add(s);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar sesiones del masajista: " + ex.getMessage());
        }
        return sesiones;
    }
}
