/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.insert;

import javafx.scene.layout.VBox;
import proyectobaseskywy.PantallaPrincipal;
import view.SeccionFormulario;

/**
 *
 * @author dylan
 */
public class NuevoAdmin {
    SeccionFormulario txt1;
    SeccionFormulario txt2;
    SeccionFormulario txt3;
    SeccionFormulario txt4;
    SeccionFormulario txt5;
    VBox root;
    public NuevoAdmin(){
        txt1=new SeccionFormulario("usuario");
        txt2=new SeccionFormulario("password");
        txt3=new SeccionFormulario("nombre");
        txt4=new SeccionFormulario("apellido");
        txt5=new SeccionFormulario("cedula");
        root=new VBox();
    }
    public void createContent(){
        
    }
    public VBox getRoot(PantallaPrincipal pp){
        return root;
    }
    
}
