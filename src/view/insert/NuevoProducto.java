
package view.insert;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Producto;
import proyectobaseskywy.PantallaPrincipal;
import view.SeccionFormulario;

/**
 *
 * @author Gabriel Castro
 */
public class NuevoProducto {
    private final BorderPane root;
    private final VBox info;
    private final SeccionFormulario nombre;
    private final SeccionFormulario codigo;
    private final SeccionFormulario valor;
    private final Button ingresar;
    
    
    public NuevoProducto(PantallaPrincipal jeje) throws SQLException{
        info = new VBox(5);
        
        nombre = new SeccionFormulario("Nombre del producto");        
        codigo = new SeccionFormulario("Codigo del Producto");
        codigo.getCampo().setText(Producto.codigoMinimo());
        codigo.getCampo().setEditable(false);
        valor = new SeccionFormulario("Valor por unidad");
        
        ingresar = new Button("Ingresar");
        
        info.getChildren().addAll(codigo.getRoot(),nombre.getRoot(),valor.getRoot(),ingresar);
        info.setAlignment(Pos.CENTER);
        
        root = new BorderPane();
        root.setCenter(info);
        ingresar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                activarBoton(jeje);
            }
        });
        
                
    }

    public NuevoProducto(PantallaPrincipal jeje,Producto p) throws SQLException{
        info = new VBox(5);
        
        nombre = new SeccionFormulario("Nombre del producto");  
        nombre.getCampo().setText(p.getDescripcion());
        codigo = new SeccionFormulario("Codigo del Producto");
        codigo.getCampo().setText(p.getCodigo());
        codigo.getCampo().setText(Producto.codigoMinimo());
        codigo.getCampo().setEditable(false);
        valor = new SeccionFormulario("Valor por unidad");
        valor.getCampo().setText(""+p.getValor());
        
        ingresar = new Button("Ingresar");
        
        info.getChildren().addAll(codigo.getRoot(),nombre.getRoot(),valor.getRoot(),ingresar);
        info.setAlignment(Pos.CENTER);
        
        root = new BorderPane();
        root.setCenter(info);
        ingresar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                p.setDescripcion(nombre.getCampo().getText());
                p.setValor(Integer.parseInt(valor.getCampo().getText()));
                jeje.setCenterroot();
            }
        });
        
                
    }


    public BorderPane getRoot() {
        return root;
    }
    
    private void activarBoton(PantallaPrincipal jeje){
        ingresar.setOnAction((e) ->{
            try{
                Producto p=new Producto(nombre.getValor(), codigo.getValor(), Double.valueOf(valor.getValor()));
                p.agregarBase();
                jeje.setCenterroot();
            }catch(NumberFormatException nfe){
                valor.activarError("Ingrese un valor numerico por favor (##.##)");
            } catch (SQLException ex) {
                Logger.getLogger(NuevoProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
}
