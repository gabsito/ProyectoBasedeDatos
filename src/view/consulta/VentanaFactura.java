/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.consulta;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modelo.Factura;
import modelo.FacturaProducto;
import modelo.Proforma;
import proyectobaseskywy.PantallaPrincipal;
import view.BlockRelations;
import view.insert.NuevaFactura;

/**
 *
 * @author Usuario
 */
public class VentanaFactura {
    
    protected VBox root;
    protected final String titulo = "Factura";
    
    public VBox getRoot(){
        return this.root;
    }
    
    public void createContent(PantallaPrincipal jeje){
        root = new VBox();
        VBox box = new VBox(5);
        
        ImageView barra=new ImageView(new Image("/media/factura.png"));
        ImageView delete=new ImageView(new Image("/media/tachoBasura.png"));
        delete.setFitHeight(30);
        delete.setFitWidth(30);
        barra.setFitWidth(30);
        barra.setFitHeight(30);
        HBox hb=new HBox();
        hb.getChildren().addAll(barra,delete);
        
        TableColumn column = new TableColumn("#");
        column.setCellValueFactory(new PropertyValueFactory<>("numero"));
        TableColumn column2 = new TableColumn("Cliente");
        column2.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        TableColumn column4 = new TableColumn("FechaCreacion");
        column4.setCellValueFactory(new PropertyValueFactory<>("ususarioAdmin"));
        TableColumn column5 = new TableColumn("Monto");
        column5.setCellValueFactory(new PropertyValueFactory<>("vaalor"));
        TableColumn column6 = new TableColumn("Fecha");
        column6.setCellValueFactory(new PropertyValueFactory<>("Fecha"));
        column.setMinWidth(150);
        column2.setMinWidth(150);  
        TableView table = new TableView();
        try {
            for(Factura p: Factura.listaFacturas())
                table.getItems().add(p);
        } catch (SQLException ex) {
            Logger.getLogger(VentanaProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.getColumns().addAll(column,column2,column4,column5);
        ScrollPane spn=new ScrollPane(table);
        spn.setFitToWidth(true);
        
        Rectangle top = new Rectangle(1, 60);
        top.setStroke(Color.WHITE);
        
        Label title = new Label(titulo);
        
        Button add = new Button("Editar");
        
        
        title.setAlignment(Pos.CENTER);
        
        BorderPane bp=new BorderPane();
        bp.setLeft(add);
        bp.setRight(hb);
        

        root.getChildren().addAll(title,bp,spn);
        root.setAlignment(Pos.CENTER);
        
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                if(table.getSelectionModel().getSelectedItems()!=null){
                    NuevaFactura nf=new NuevaFactura();
                    Factura fac=(Factura)(table.getSelectionModel().getSelectedItem());
                    try {
                        nf.createContent(jeje,FacturaProducto.listaFacturas(fac.getNumero()) , fac);
                        jeje.setCenterjeje(nf.getRoot());
                    } catch (SQLException ex) {
                        Logger.getLogger(VentanaFactura.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        });
        barra.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                NuevaFactura nf=new NuevaFactura();
                nf.createContent(jeje);
                jeje.setCenterjeje(nf.getRoot());
            }
        });
        
    }
}
