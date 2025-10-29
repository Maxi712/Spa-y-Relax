
package Persistencia;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import spaentrededos.Instalacion;


public class InstalacionData {
    private Connection c=null;

    public InstalacionData(Conexion conexion) {
        this.c = conexion.getConexion();
    }
    
    public List <Instalacion>listarInstalaciones() {
        List <Instalacion> listaI=new ArrayList<>();
        String sql="SELECT * FROM instalacion ";
        try{
            PreparedStatement ps=c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int codInstal = rs.getInt("codInstal");
                String nombre = rs.getString("nombre");
                String detalleUso = rs.getString("detalleUso");
                double precio30M = rs.getDouble("precio30M");
                boolean estado = rs.getBoolean("estado");
                Instalacion i = new Instalacion(codInstal, nombre, detalleUso, precio30M, estado);
                listaI.add(i);
            }
            ps.close();
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno ..."+ex.getMessage());
        }
        return listaI;
        
    }
    
    
}
