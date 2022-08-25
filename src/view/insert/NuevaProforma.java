
package view.insert;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Proforma;
import proyectobaseskywy.PantallaPrincipal;
import view.SeccionFormulario;

/**
 *
 * @author Gabriel Castro
 */
public class NuevaProforma {
    protected SeccionFormulario fecha;
    protected SeccionFormulario ruc;
    protected SeccionFormulario nombre;
    protected SeccionFormulario usuario;
    protected SeccionFormulario total;
    int numProforma;
    private BorderPane root;
    private TableView table;
    private HBox top;
    private VBox topLeft;
    private VBox topRight;
    
    
    public NuevaProforma(){
        
    }
    public BorderPane getRoot(){
        return root;
    }
    
    public final void createContent(PantallaPrincipal pp){
        root = new BorderPane();
        
        String[] datos = {"#", "CodigoProducto", "Nombre", "OrdenCompra", "Cantidad", "ValorUnidad", "ValorTotal"};
        table = new TableView();
        for(String campo: datos){
            TableColumn<String,Object> column = new TableColumn<>(campo);
            table.getColumns().add(column);
        }
        table.setEditable(true);
        root.setCenter(table);
        
        
        topLeft = new VBox(5);
        ruc = new SeccionFormulario("Ruc");
        nombre = new SeccionFormulario("Nombre");
        topLeft.getChildren().addAll(ruc.getRoot(), nombre.getRoot());
        
        topRight = new VBox(5);
        Label proforma = new Label("Proforma #" + numProforma);
        fecha = new SeccionFormulario("Fecha");
        usuario = new SeccionFormulario("Usuario:");
        topRight.getChildren().addAll(proforma, fecha.getRoot(), usuario.getRoot());
        
        top = new HBox(50);
        top.getChildren().addAll(topLeft, topRight);
        root.setTop(top);
        
        
        total = new SeccionFormulario("Total");
        root.setBottom(total.getRoot());
        
    }
    
    
    public  void createContent(PantallaPrincipal pp,Proforma pf){
        root = new BorderPane();
        
        String[] datos = {"#", "CodigoProducto", "Nombre", "OrdenCompra", "Cantidad", "ValorUnidad", "ValorTotal"};
        table = new TableView();
        for(String campo: datos){
            TableColumn<String,Object> column = new TableColumn<>(campo);
            table.getColumns().add(column);
        }
        table.setEditable(true);
        root.setCenter(table);
        
        
        topLeft = new VBox(5);
        ruc = new SeccionFormulario("Ruc");
        nombre = new SeccionFormulario("Nombre");
        topLeft.getChildren().addAll(ruc.getRoot(), nombre.getRoot());
        
        topRight = new VBox(5);
        Label proforma = new Label("Proforma #" + numProforma);
        fecha = new SeccionFormulario("Fecha");
        usuario = new SeccionFormulario("Usuario:");
        topRight.getChildren().addAll(proforma, fecha.getRoot(), usuario.getRoot());
        
        top = new HBox(50);
        top.getChildren().addAll(topLeft, topRight);
        root.setTop(top);
        
        
        total = new SeccionFormulario("Total");
        root.setBottom(total.getRoot());
        
    }
    

    public String getFecha() {
        return fecha.getValor();
    }

    public String getRuc() {
        return ruc.getValor();
    }

    public String getNombre() {
        return nombre.getValor();
    }

    public String getUsuario() {
        return usuario.getValor();
    }

    public String getTotal() {
        return total.getValor();
    }

    public int getNumProforma() {
        return numProforma;
    }

    public void setFecha(SeccionFormulario fecha) {
        this.fecha = fecha;
    }

    public void setRuc(SeccionFormulario ruc) {
        this.ruc = ruc;
    }

    public void setNombre(SeccionFormulario nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(SeccionFormulario usuario) {
        this.usuario = usuario;
    }

    public void setTotal(SeccionFormulario total) {
        this.total = total;
    }

    public void setNumProforma(int numProforma) {
        this.numProforma = numProforma;
    }
    
    
}
