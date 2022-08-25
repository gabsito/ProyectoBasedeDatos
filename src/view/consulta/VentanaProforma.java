
package view.consulta;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
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
import javafx.stage.Stage;
import modelo.Producto;
import modelo.Proforma;
import proyectobaseskywy.MainPruebaDylantkmjeje;
import proyectobaseskywy.PantallaPrincipal;
import view.insert.NuevaProforma;
import view.insert.NuevoProducto;

/**
 *
 * @author Gabriel Castro
 */
public class VentanaProforma {
    protected VBox root;
    protected final String titulo = "Proforma";
    
    public VBox getRoot(){
        return this.root;
    }
    
    public VentanaProforma(){
        root=new VBox();
    }
    
    public void createContent(PantallaPrincipal jeje){
        
        Label title = new Label(titulo);  
        
        TableColumn column = new TableColumn("ID");
        column.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        TableColumn column2 = new TableColumn("valor");
        column2.setCellValueFactory(new PropertyValueFactory<>("valor"));
        TableColumn column3 = new TableColumn("usuario");
        column3.setCellValueFactory(new PropertyValueFactory<>("admin"));
        TableColumn column4 = new TableColumn("Productos");
        column4.setCellValueFactory(new PropertyValueFactory<>("productos"));
        column.setMinWidth(150);
        column2.setMinWidth(150);
        column3.setMinWidth(150);   
        column4.setMinWidth(150);
        TableView<Proforma> table = new TableView();
        table.getColumns().addAll(column,column2,column3,column4);
        ScrollPane spn=new ScrollPane(table);
        spn.setFitToWidth(true);
        
        try {
            for(Proforma p: Proforma.listaProforma())
                table.getItems().add(p);
        } catch (SQLException ex) {
            Logger.getLogger(VentanaProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Button add = new Button("Editar");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
            NuevaProforma vp=new NuevaProforma();
            vp.createContent(jeje,(Proforma)table.getSelectionModel().getSelectedItem());
            jeje.setCenterjeje(vp.getRoot());
            }
        });
        ImageView barra=new ImageView(new Image("/media/factura.png"));
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
        barra.setOnMouseClicked((e) ->{
            addProducto(jeje);
        });
        delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                 delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                try {
                    Proforma pf=Proforma.remove((Proforma)table.getSelectionModel().getSelectedItem());
                } catch (SQLException ex) {
                    Logger.getLogger(VentanaProforma.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
        });
                }
        });
    }
    
    private void addProducto(PantallaPrincipal pp) {
     //La futura dunción del botón para añadir producto.
        NuevaProforma vf=new NuevaProforma();
        vf.createContent(pp);
        pp.setCenterjeje(vf.getRoot());
    }}