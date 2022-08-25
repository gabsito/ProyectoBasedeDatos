
package proyectobaseskywy;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import view.SeccionFormulario;
import view.consulta.VentanaCliente;
import view.consulta.VentanaFactura;
import view.consulta.VentanaProducto;
import view.consulta.VentanaProforma;
import view.consulta.VistaReportes;
import view.insert.OrdenDeCompra;

/**
 *
 * @author Gabriel Castro
 */
public class PantallaPrincipal {
    protected ImageView wall;
    protected StackPane root;
    protected BorderPane over;
    protected GridPane botones;
    private VBox botonesl;
    SeccionFormulario txt;
    SeccionFormulario txt1;
    
    public PantallaPrincipal(){
      createContent1();
        
    }
    
    public void createContent(){
        
        ImageView backIcn = new ImageView(new Image("/media/backBtn.png"));
        backIcn.setFitWidth(25);
        backIcn.setPreserveRatio(true);
        over.setCenter(botones);
        Button back = new Button("Regresar", backIcn);
        //back.setStyle("-fx-background-color:transparent;");
        back.setOnAction(e->{
            //Regresar a la pantalla principal con botones
            over.setCenter(botones);
        });
        
        Button goProd = new Button("Proforma");
        goProd.setOnAction(e->{
            VentanaProforma ventanaprod = new VentanaProforma();
            ventanaprod.createContent(this);
            over.setCenter(ventanaprod.getRoot());
            
        });
        
        Button goCobroIn = new Button("Producto");
        goCobroIn.setOnAction(e->{
            VentanaProducto ventanaCob = new VentanaProducto();
            ventanaCob.createContent(this);
            over.setCenter(ventanaCob.getRoot());
            
        });
        
        Button Producto = new Button("Cliente");
        Producto.setOnAction(e->{
            VentanaCliente  ventanaCob = new VentanaCliente();
            try {
                ventanaCob.createContent(this);
            } catch (SQLException ex) {
                Logger.getLogger(PantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            over.setCenter(ventanaCob.getRoot());            
        });
        Button goFactura=new Button("Factura");
        goFactura.setOnAction(e->{ 
                VentanaFactura vf=new VentanaFactura();
                vf.createContent(this);
                over.setCenter(vf.getRoot());
        }); 
        Button goView=new Button("Reportes");
        goView.setOnAction(e->{ 
                VistaReportes vf=new VistaReportes();
                vf.createContent(this);
                over.setCenter(vf.getRoot());
        }); 
        Button goOrdComp = new Button("Orden de Compra");
        goOrdComp.setOnAction(e->{
            OrdenDeCompra ord = new OrdenDeCompra();
            over.setCenter(ord.getRoot());
        });
        
        botonesl=new VBox();
        botonesl.getChildren().addAll(goProd,goCobroIn,Producto,goFactura,goView);
        botonesl.setAlignment(Pos.CENTER);
        goProd.setMinWidth(100);
        goCobroIn.setMinWidth(100);
        Producto.setMinWidth(100);
        goFactura.setMinWidth(100);
        goView.setMinWidth(100);
        over.setTop(back);
        over.setLeft(botonesl);
    }        
    
    
    
    
    public void createContent1(){
        
        wall = new ImageView(new Image("/media/back.jpg"));
        root = new StackPane(wall);
        over = new BorderPane();
        
        VBox bv1=new VBox();
        bv1.setAlignment(Pos.CENTER);
        txt=new SeccionFormulario("Usuario");
        txt1=new SeccionFormulario("Contrasena", true);
        txt.getRoot().setAlignment(Pos.CENTER);
        txt1.getRoot().setAlignment(Pos.CENTER);
        txt.getCampo().setMaxWidth(100);
        txt1.getCampo().setMaxWidth(100);
        Button btn=new Button("Aceptar");
        btn.setOnAction((e)-> {
            launch();
        });
        over.setCenter(bv1);
        
        bv1.getChildren().addAll(txt.getRoot(),txt1.getRoot(),btn);
        root.getChildren().add(over);
        
    }
    
    private void launch(){
        boolean b=false;
        try {
//            CallableStatement sp = constantes.CONSTANTES.CONEXION.prepareCall(" CALL login("+txt.getCampo().getText()+","+txt1.getCampo().getText()+")");
            CallableStatement sp = constantes.CONSTANTES.CONEXION.prepareCall("{CALL login(?,?,?)}");
            sp.setString(1,txt.getCampo().getText() );
            sp.setString(2, txt1.getCampo().getText());
            sp.registerOutParameter(3, Types.BOOLEAN);
            sp.execute();
            b = sp.getBoolean(3);
        } catch (SQLException ex) {
            Logger.getLogger(PantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (b)
            createContent();
        else
            txt1.activarError("Error de contrasena");
            txt.activarError("Error de usuario");
            txt1.clear();
            txt.clear();
    }
    
    public StackPane getRoot(){
        return root;
    }
    public void setCenterjeje(Pane jeje){
        over.setCenter(jeje);
    }
    public void setCenterroot(){
        over.setCenter(null);
    }
}
