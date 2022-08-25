
package view.consulta;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import modelo.Cliente;
import proyectobaseskywy.PantallaPrincipal;
import view.insert.NuevoCliente;

/**
 *
 * @author Gabriel Castro
 */
public class VentanaCliente {
    protected VBox root;
    protected final String titulo = "Cliente";
    
    public VBox getRoot(){
        return this.root;
    }
    
    public VentanaCliente (){
        root=new VBox();
    }
    
    public void createContent(PantallaPrincipal jeje) throws SQLException{
        
        Label title = new Label(titulo);  
        
        TableColumn column = new TableColumn("RUC");
        column.setCellValueFactory(new PropertyValueFactory<>("ruc"));
        TableColumn column2 = new TableColumn("Nombre");
        column2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn column3 = new TableColumn("Direccion");
        column3.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        TableColumn column4 = new TableColumn("Telefono");
        column4.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        column.setMinWidth(150);
        column2.setMinWidth(150);
        column3.setMinWidth(150);   
        column4.setMinWidth(150);
        TableView<Cliente> table = new TableView();
        table.getColumns().addAll(column,column2,column3,column4);
        ScrollPane spn=new ScrollPane(table);
        spn.setFitToWidth(true);
        
        for(Cliente c:Cliente.listaCliente())
            table.getItems().add(c);
        
        Button add = new Button("Editar");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                NuevoCliente nc=new NuevoCliente();
        nc.createContent(jeje, table.getSelectionModel().getSelectedItem());
        jeje.setCenterjeje(nc.getRoot());
            }
        });
        ImageView barra=new ImageView(new Image("/media/silueta.png"));
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
                try {
                    Cliente cl=Cliente.remove((Cliente)table.getSelectionModel().getSelectedItem());
                } catch (SQLException ex) {
                    Logger.getLogger(VentanaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void addProducto(PantallaPrincipal pp) {
        NuevoCliente nc=new NuevoCliente();
        nc.createContent(pp);
        pp.setCenterjeje(nc.getRoot());
        
    }
}
