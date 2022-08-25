
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 *
 * @author David Santistevan
 */
public class SeccionSeleccion<E> extends SeccionFormulario{
    
    private ComboBox comboBox;
    
    public SeccionSeleccion(String nom,List<E> lista) {
        super(nom);
        ObservableList<E> obList=FXCollections.observableArrayList(lista);
        comboBox=new ComboBox(obList);
        root.getChildren().remove(campo);
        root.getChildren().add(1, comboBox);
        
    }

    public ComboBox getComboBox() {
        return comboBox;
    }
    
    @Override
    public String getValor(){
        if(comboBox.getValue() instanceof String)
            return (String) comboBox.getValue();
        else
            return ((E) comboBox.getValue()).toString();
    }
    
    public E getValorComboBox(){
        return (E) comboBox.getValue();
    }
    
}
