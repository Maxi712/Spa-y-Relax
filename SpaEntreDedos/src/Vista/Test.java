/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Conexion;
import Persistencia.entidadData;
import java.util.ArrayList;
import spaentrededos.Cliente;

/**
 *
 * @author carri
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Conexion conexion = new Conexion () ; 
       entidadData entidadData = new entidadData (conexion ) ; 
       
        Cliente c2 = new Cliente();
        c2.setDni(12345678);
        c2.setNombreCompleto("Edgardo Barros");
        c2.setTelefono(266498695);
        c2.setEdad(52);
        c2.setAfecciones("Ninguna");
        c2.setEstado(false);
       
       
       
         entidadData.guardarCliente(c2);
          
         Cliente buscado = entidadData.buscarCliente(c2.getCodCli());
        if (buscado != null) {
            System.out.println("Cliente encontrado:" + buscado.getNombreCompleto());
        }
         ArrayList<Cliente> lista = entidadData.listarCliente();
        System.out.println("lista de cliente");
        for (Cliente cliente : lista) {
            System.out.println(cliente.getCodCli() + "*" + cliente.getNombreCompleto());
        }
          
                 
        
      
        
      
    }
}
