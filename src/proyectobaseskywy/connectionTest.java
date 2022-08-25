/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobaseskywy;

import constantes.CONSTANTES;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Administrador;
import modelo.Cliente;
import modelo.Factura;
import modelo.Producto;
import modelo.Proforma;

/**
 *
 * @author David Santistevan
 */
public class connectionTest {
    public static void main(String[] args){
        try{
            CONSTANTES.iniciar();
            System.out.println(
                    Administrador.listaAdmin(null, null, null, null)+"\n"+
                            Cliente.listaCliente(null, null, null, null)+"\n"+
                            Producto.listaProducto(null, null, -1)+"\n"+
                            Proforma.listaProforma()+"\n"+
                            Factura.listaFacturas());
        }catch (SQLException ex) {
            Logger.getLogger(connectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
