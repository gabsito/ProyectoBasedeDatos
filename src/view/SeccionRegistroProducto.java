/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import modelo.Producto;

/**
 *
 * @author Gabriel
 */
public class SeccionRegistroProducto {
    HBox root;
    SeccionFormulario producto;
    SeccionFormulario cantidad;
    ComboBox combo;
    
    public SeccionRegistroProducto(){
        root = new HBox();
        producto = new SeccionFormulario("Producto");
        cantidad = new SeccionFormulario("Cantidad");
        try {
            combo = new ComboBox((ObservableList) Producto.listaProducto());
            root.setOnKeyPressed(e->{
                try {
                    combo.setItems((ObservableList) Producto.listaProducto(producto.getValor(), null, -1));
                } catch (SQLException ex) {
                    Logger.getLogger(SeccionRegistroProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        } catch (SQLException ex) {
            Logger.getLogger(SeccionRegistroProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        root.getChildren().addAll(producto.getRoot(), cantidad.getRoot(), combo);
        
    }
    
    public HBox getRoot(){
        return root;
    }
}