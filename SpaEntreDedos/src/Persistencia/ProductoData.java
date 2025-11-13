/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Conexion;
import java.sql.Connection;

/**
 *
 * @author Valentin Barros
 */
public class ProductoData {
    private Connection conexion;

    public ProductoData(Connection conexion) {
        this.conexion = Conexion.getConexion();
    }
    
    
    
}
