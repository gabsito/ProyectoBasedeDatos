/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobaseskywy;

import constantes.CONSTANTES;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author David Santistevan
 */
public class ProyectoBasesKywy extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            //        VentanaProducto vc=new VentanaProducto();
//        vc.createContent();
//        VistaCobroRegistro vc1=new VistaCobroRegistro();

            CONSTANTES.iniciar();
        } catch (SQLException ex) {
            Logger.getLogger(MainPruebaDylantkmjeje.class.getName()).log(Level.SEVERE, null, ex);
        }
        PantallaPrincipal jeje = new PantallaPrincipal();
        
        Scene scene = new Scene(jeje.getRoot(), 800, 500);
        
        primaryStage.setTitle("Administracion Kywy");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
