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
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Statement;
import spaentrededos.Producto;
import spaentrededos.ProductoTratamiento;

/**
 *
 * @author Valentin Barros
 */
public class ProductoTratamientoData {

    private Connection conexion;
    private ProductoData pd;
    private TratamientoData td;

    private ProductoTratamientoData() {
        this.conexion = Conexion.getConexion();
        pd = new ProductoData();
        td = new TratamientoData();
    }

    public void agregarProducto(ProductoTratamiento pt) {
        String sql = "INSERT INTO productotratamiento(idProducto, codTratam) VALUES (?,?)";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pt.getProducto().getIdProducto());
            ps.setInt(2, pt.getTratamiento().getCodTratam());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                pt.setIdPT(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Producto agregado exitosamente ...");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla ProductoTratamiento ..." + ex.getMessage());
        }
    }

    public void borrarProducto(int idProducto, int codTratam) {
        String sql = "DELETE FROM productoTratamiento WHERE idProductoa= ? AND codTratam= ? ";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idProducto);
            ps.setInt(2, codTratam);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Producto eliminado ...");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripción ..." + ex.getMessage());
        }
    }

    public ArrayList<Producto> listarProductos(int codTratam) {
        ArrayList<Producto> producto = new ArrayList();
        String sql = "SELECT p.idProducto, nombre, tipo, marca, stock, estado" + "FROM productoTratamiento pt, producto p WHERE pt.idProducto = p.idProducto AND codTratam = ? AND a.estado = 1";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1,  codTratam);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                String marca = rs.getString("marca");
                int stock = rs.getInt("stock");
                boolean estado = rs.getBoolean("estado");
                Producto p = new Producto (idProducto, nombre, tipo, marca, stock, estado) ;
                producto.add(p);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion ..." + ex.getMessage());
        }
        return producto;
    }

}
