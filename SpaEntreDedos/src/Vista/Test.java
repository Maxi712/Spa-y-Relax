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
       final entidadData entidadData = new entidadData (conexion ) ; 
       Cliente a =new Cliente(1,44738237,"Castro Maximiliano",26601927,20,"Ninguna", true);
       entidadData.guardarCliente(a);
       Cliente b =new Cliente(2,40238734,"Barros Edgardo",26632763,26,"Ninguna", true);
       entidadData.guardarCliente(b);
       Cliente c =new Cliente(3,41934744,"Carrizo Anna",266387424,24,"Ninguna", true);
       entidadData.guardarCliente(c);
       Cliente d =new Cliente(4,40238274,"Gimenez Pablo",266372834,23,"Ninguna", true);
       entidadData.guardarCliente(d);
       Cliente e =new Cliente(5,42293843,"Fernandez Rodrigo",26635233,24,"Ninguna", true);
       entidadData.guardarCliente(e);
       entidadData.guardarCliente(a);
       entidadData.guardarCliente(b);
       entidadData.guardarCliente(c);
       entidadData.guardarCliente(d);
       
       java.awt.EventQueue.invokeLater(new Runnable() {
           @Override
           public void run() {
           Menu menu = new Menu(entidadData);
           menu.setVisible(true);
           menu.setLocationRelativeTo(null);
           }
           });
     
        ArrayList<Cliente> cli = new ArrayList();
       cli= entidadData.listarCliente();
       for (Cliente cliente : cli) {
            System.out.println("Los nombres son");
            System.out.println(cliente.getNombreCompleto());;
        }
       
    }
        /*Cliente c2 = new Cliente();
        c2.setDni(12345678);
        c2.setNombreCompleto("Edgardo Barros");
        c2.setTelefono(266498695);
        c2.setEdad(52);
        c2.setAfecciones("Ninguna");
        c2.setEstado(false);*/
  
        
}
    

