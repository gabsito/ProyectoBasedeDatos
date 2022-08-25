/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author David Santistevan
 */
public class SeccionFormulario {

    protected VBox root;
    protected Label error;
    protected TextField campo;
    protected Label nombre;
    
    public SeccionFormulario(String nom) {
        root = new VBox();
        nombre = new Label(nom + ":");
        campo = new TextField();
        campo.setMaxWidth(100);
        error = new Label("");
        error.setTextFill(Color.RED);
        root.setSpacing(7);
        root.getChildren().add(nombre);
        root.getChildren().add(campo);
        root.getChildren().add(error);
    }
    
    public SeccionFormulario(String nom, boolean passwordMode) {
        root = new VBox();
        nombre = new Label(nom + ":");
        campo = new PasswordField();
        campo.setMaxWidth(100);
        error = new Label("");
        error.setTextFill(Color.RED);
        root.setSpacing(7);
        root.getChildren().add(nombre);
        root.getChildren().add(campo);
        root.getChildren().add(error);
    }
    
    public VBox getRoot() {
        return root;
    }
    
    public void setRoot(VBox root) {
        this.root = root;
    }
    
    public Label getError() {
        return error;
    }
    
    public void setError(Label error) {
        this.error = error;
    }
     
    public TextField getCampo() {
        return campo;
    }
    
    public void setCampo(TextField campo) {
        this.campo = campo;
    }
    
    public Label getNombre() {
        return nombre;
    }
    
    public void setNombre(Label nombre) {
        this.nombre = nombre;
    }
    
    public void activarError(String err) {
        error.setText(err);
    }
    
    public void clear() {
        campo.setText("");
    }
    
    public String getValor(){
        return campo.getText();
    }
    
    public void setValor(String s){
        campo.setText(s);
    }
}
