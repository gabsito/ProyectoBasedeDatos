
package view.insert;

import com.mysql.cj.xdevapi.Client;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modelo.Cliente;
import proyectobaseskywy.PantallaPrincipal;
import view.SeccionFormulario;

/**
 *
 * @author Gabriel Castro
 */
public class NuevoCliente {
    protected BorderPane root;
    protected VBox content;
    protected SeccionFormulario nombre;
    protected SeccionFormulario ruc;
    protected SeccionFormulario telefono;
    protected SeccionFormulario direccion;
    
    public NuevoCliente(){
    }
    
    public BorderPane getRoot(){
        return this.root;
    }
    
    public void createContent(PantallaPrincipal jeje){
        root = new BorderPane();
        content = new VBox(10);
        nombre = new SeccionFormulario("Nombre");
        ruc = new SeccionFormulario("RUC");
        telefono = new SeccionFormulario("Telefono");
        direccion = new SeccionFormulario("Dirección");
        
        Button ingresar = new Button("Ingresar");
        ingresar.setOnAction(e->{
            Cliente client=new Cliente(ruc.getCampo().getText(), nombre.getCampo().getText(), direccion.getCampo().getText(), telefono.getCampo().getText());
            jeje.setCenterroot();
        });
        
        content.getChildren().addAll(nombre.getRoot(), ruc.getRoot(), telefono.getRoot(), direccion.getRoot());
        content.setAlignment(Pos.CENTER);
        root.setCenter(content);
        
        root.setBottom(ingresar);
    }
    public void createContent(PantallaPrincipal jeje,Cliente cl){
        root = new BorderPane();
        content = new VBox(10);
        nombre = new SeccionFormulario("Nombre");
        nombre.getCampo().setText(cl.getNombre());
        ruc = new SeccionFormulario("RUC");
        ruc.getCampo().setText(cl.getRuc());
        telefono = new SeccionFormulario("Telefono");
        telefono.getCampo().setText(cl.getTelefono());
        direccion = new SeccionFormulario("Dirección");
        direccion.getCampo().setText(cl.getDireccion());
        
        Button ingresar = new Button("Ingresar");
        ingresar.setOnAction(e->{
            cl.setDireccion(direccion.getCampo().getText());
            cl.setTelefono(telefono.getCampo().getText());
            cl.setNombre(nombre.getCampo().getText());
            jeje.setCenterroot();
        });
        
        content.getChildren().addAll(nombre.getRoot(), ruc.getRoot(), telefono.getRoot(), direccion.getRoot());
        content.setAlignment(Pos.CENTER);
        root.setCenter(content);
        
        root.setBottom(ingresar);
    }
}
