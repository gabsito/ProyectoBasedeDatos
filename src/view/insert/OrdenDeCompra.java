/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.insert;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Cliente;
import view.SeccionFormulario;
import view.SeccionRegistroProducto;

/**
 *
 * @author Gabriel
 */
public class OrdenDeCompra {
    private VBox root;
    private ScrollPane productos;
    private Cliente cliente;
    private static Label title = new Label("Orden De Compra");
    private SeccionFormulario fecha;
    private SeccionFormulario ruc;
    private SeccionFormulario fCliente;
    private static int numOrd = 1;
    private SeccionRegistroProducto prods;
    
    public OrdenDeCompra(Cliente cliente){
        this.cliente = cliente;
        createContent();
        numOrd += 1;
    }
    
    public OrdenDeCompra(){
        createContent();
        numOrd += 1;
    }
    
    public void createContent(){
        root = new VBox(5);
        Label num = new Label("Orden de compra " + numOrd);
        ruc = new SeccionFormulario("RUC");
        fCliente = new SeccionFormulario("Cliente");
        fecha = new SeccionFormulario("Fecha");
        prods = new SeccionRegistroProducto();
        HBox prodsNode = prods.getRoot();
        prodsNode.setAlignment(Pos.CENTER);
        num.setAlignment(Pos.TOP_RIGHT);
        root.getChildren().addAll(num,fecha.getRoot(), ruc.getRoot(),fCliente.getRoot(),prodsNode);
        
    }
    
    public VBox getRoot(){
        return root;
    }
}
