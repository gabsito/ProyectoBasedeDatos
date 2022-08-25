/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.control.CheckBox;

/**
 *
 * @author David Santistevan
 */
public class SeccionBooleana extends SeccionFormulario{
    
    private CheckBox checkBox;
    
    public SeccionBooleana(String nom) {
        super(nom);
        checkBox=new CheckBox();
        root.getChildren().remove(campo);
        root.getChildren().add(1, checkBox);
    }
    
    public boolean isChecked(){
        return checkBox.isSelected();
    }
    
    public CheckBox getCheckBox(){
        return checkBox;
    }
    
}
