
package view.consulta;

import java.sql.SQLException;
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
import modelo.Producto;
import proyectobaseskywy.PantallaPrincipal;
import view.insert.NuevoProducto;

/**
 *
 * @author Gabriel Castro
 */
public class VentanaProducto {
    protected VBox root;
    protected final String titulo = "Producto";
    
    public VBox getRoot(){
        return this.root;
    }
    
    public VentanaProducto(){
        root=new VBox();
    }
    
    public void createContent(PantallaPrincipal jeje){
        
        Label title = new Label(titulo);  
        
        TableColumn column = new TableColumn("ID");
        column.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        TableColumn column2 = new TableColumn("valor");
        column2.setCellValueFactory(new PropertyValueFactory<>("valor"));
        TableColumn column3 = new TableColumn("Descripcion");
        column3.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        column.setMinWidth(150);
        column2.setMinWidth(150);
        column3.setMinWidth(150);   
        TableView<Producto> table = new TableView();
        table.getColumns().addAll(column,column2,column3);
        try {
            for(Producto p: Producto.listaProducto())
                table.getItems().add(p);
        } catch (SQLException ex) {
            Logger.getLogger(VentanaProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        ScrollPane spn=new ScrollPane(table);
        spn.setFitToWidth(true);
        
        Button add = new Button("Editar");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                NuevoProducto nv=null;
     
        try {
            nv=new NuevoProducto(jeje,table.getSelectionModel().getSelectedItem());
        } catch (SQLException ex) {
            Logger.getLogger(VentanaProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        jeje.setCenterjeje(nv.getRoot());
            }
        });
        ImageView barra=new ImageView(new Image("/media/barra.png"));
        ImageView delete=new ImageView(new Image("/media/tachoBasura.png"));
        delete.setFitHeight(30);
        delete.setFitWidth(30);
        barra.setFitWidth(30);
        barra.setFitHeight(30);
        HBox hb=new HBox();
        hb.getChildren().addAll(barra,delete);
        
        BorderPane bp=new BorderPane();
        bp.setLeft(add);
        bp.setRight(hb);
          
        title.setAlignment(Pos.CENTER);

        root.getChildren().addAll(title,bp,spn);
        root.setAlignment(Pos.CENTER);
        delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                try {
                    Producto p=Producto.remove((Producto)table.getSelectionModel().getSelectedItem());
                } catch (SQLException ex) {
                    Logger.getLogger(VentanaProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        barra.setOnMouseClicked((e) ->{
            addProducto(jeje);
        });
    }
    
    private void addProducto(PantallaPrincipal pp) {
        
     NuevoProducto nv=null;
     
        try {
            nv=new NuevoProducto(pp);
        } catch (SQLException ex) {
            Logger.getLogger(VentanaProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        pp.setCenterjeje(nv.getRoot());
}}
