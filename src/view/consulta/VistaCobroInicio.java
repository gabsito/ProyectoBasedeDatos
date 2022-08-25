
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.consulta;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modelo.Cobro;
import proyectobaseskywy.PantallaPrincipal;
import view.insert.VistaCobroRegistro;

/**
 *
 * @author dylan
 */
public class VistaCobroInicio {
    BorderPane root;
    public VistaCobroInicio(PantallaPrincipal pp){
        createRoot(pp);
    }
    public void createRoot(PantallaPrincipal pp){
        Label cobrolabel=new Label("Cobro");
        Button editar = new Button("Editar");
        HBox hb=new HBox();
        ImageView delete=new ImageView(new Image("/media/tachoBasura.png"));
        ImageView nuevo=new ImageView(new Image("/media/tarjetaCredito.png"));
        
        
        delete.setFitHeight(30);
        nuevo.setFitHeight(30);
        delete.setFitWidth(30);
        nuevo.setFitWidth(30);
        
        hb.getChildren().addAll(nuevo,delete);
        
        TableColumn column = new TableColumn("Fecha");
        column.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        TableColumn column2 = new TableColumn("IdCobro");
        column2.setCellValueFactory(new PropertyValueFactory<>("idCobro"));
        TableColumn column3 = new TableColumn("Valor");
        column.setCellValueFactory(new PropertyValueFactory<>("valor"));
        TableColumn column5 = new TableColumn("MetodoPago");
        column.setCellValueFactory(new PropertyValueFactory<>("MetodoPago"));
        TableColumn column6 = new TableColumn("CodFactura");
        column.setCellValueFactory(new PropertyValueFactory<>("CodFactura"));
        column.setMinWidth(150);
        column2.setMinWidth(150);
        column3.setMinWidth(150);
        column5.setMinWidth(150);
        column6.setMinWidth(150);

        TableView<Cobro> lista = new TableView<>();
        lista.setMinWidth(1000);
        lista.setMinHeight(300);
        lista.getColumns().addAll(column2,column,column3,column5,column6);
        ScrollPane spn=new ScrollPane(lista);
        spn.setFitToWidth(true);
        
        
        
//        Rectangle rct=new Rectangle(1000, 1000, Color.WHITE );
        
        VBox root0=new VBox();
        
        BorderPane bp=new  BorderPane();
        HBox hb2=new HBox();
        
        hb2.getChildren().addAll(editar);
        hb2.setAlignment(Pos.CENTER_LEFT);
        bp.setLeft(hb2);
        bp.setRight(hb);
        
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        root0.setAlignment(Pos.CENTER);
        
        root0.getChildren().addAll(cobrolabel,bp,spn);
        root0.setPadding(new Insets(10,10,10,10));
        root.setCenter(root0);
        
        nuevo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                
                VistaCobroRegistro vc=new VistaCobroRegistro();
                pp.setCenterjeje(vc.getRoot(pp));
                }
        });
        
        delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
               if( lista.getSelectionModel().getSelectedItem()!=null){
                   Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Eliminar:");
                    alerta.setContentText("Esta seguro que desea eliminar?");
                    Button aceptar=new Button("Aceptar");
                    Button cancelar=new Button("Cancelar");
                    HBox hb=new HBox();
                    hb.getChildren().addAll(aceptar,cancelar);
                    alerta.setGraphic(hb);
                    cancelar.setOnAction(new EventHandler<ActionEvent>() {
                       @Override
                       public void handle(ActionEvent arg0) {
                           alerta.close();
                       }
                    });
                    aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                       @Override
                       public void handle(MouseEvent arg0) {
                           lista.getSelectionModel().getSelectedItem().remove();
                       }
                    });
                    
            }
            }
        });
        
    }
    
    public BorderPane getRoot(){
        return root;
    }
    
}
