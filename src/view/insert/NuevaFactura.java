package view.insert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalDateStringConverter;
import modelo.Administrador;
import modelo.Cliente;
import modelo.Factura;
import modelo.FacturaProducto;
import proyectobaseskywy.PantallaPrincipal;
import view.BlockRelations;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import javafx.application.Platform;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Gabriel Castro
 */
public class NuevaFactura {

    TextField orden = new TextField();
    protected DatePicker fecha = new DatePicker();
    protected TextField ruc = new TextField();
    protected TextField nombre = new TextField();
    protected TextField usuario = new TextField();
    protected TextField subtotal12 = new TextField("0");
    protected TextField subtotal0 = new TextField();
    protected TextField iva = new TextField();
    protected TextField total = new TextField();
    String numFactura;
    int numAutorizacion;
    private BorderPane root;
    private TableView table;
    private GridPane bot;
    private VBox top;
    private GridPane topLeft;
    private VBox topRight;
    private HBox topContent;
    private boolean inst = true;
    private Button btnAceptar;

    public void createContent(PantallaPrincipal pp) {
        subtotal12.setEditable(false);
        btnAceptar = new Button("Aceptar");
        root = new BorderPane();

//        String[] datos = {"#", "CodigoProducto", "Nombre", "OrdenCompra", "Cantidad", "ValorUnidad", "ValorTotal"};
//        table = new TableView();
//        for(String campo: datos){
//            TableColumn<String,Object> column = new TableColumn<>(campo);
//            table.getColumns().add(column);
//        }
//        table.setEditable(true);
        BlockRelations br = new BlockRelations();
        root.setCenter(br.getRoot());

        bot = new GridPane();
        Label sub12 = new Label("SUBTOTAL 12%: ");
        Label sub0 = new Label("SUBTOTAL 0%: ");
        Label labelIva = new Label("IVA: ");
        Label labelTotal = new Label("TOTAL: ");
        bot.add(sub12, 0, 0);
        bot.add(subtotal12, 1, 0);
        bot.add(sub0, 0, 1);
        bot.add(subtotal0, 1, 1);
        bot.add(labelIva, 0, 2);
        bot.add(iva, 1, 2);
        bot.add(labelTotal, 0, 3);
        bot.add(total, 1, 3);
        bot.add(btnAceptar, 1, 4);
        root.setBottom(bot);

        top = new VBox();
        topContent = new HBox();
        topRight = new VBox();

        Label title = new Label("Factura");

        topLeft = new GridPane();
        Label autorizacion = new Label("Autorizacion: ");
        Label labelRuc = new Label("RUC: ");
        Label labelNom = new Label("Nombre: ");
        Label numAut = new Label(Integer.toString(numAutorizacion));
        topLeft.add(autorizacion, 0, 0);
        topLeft.add(numAut, 1, 0);
        topLeft.add(labelRuc, 0, 1);
        topLeft.add(ruc, 1, 1);
        topLeft.add(labelNom, 0, 2);
        topLeft.add(nombre, 1, 2);
        topLeft.add(new Label("Orden"), 0, 3);
        topLeft.add(orden, 1, 3);
        topContent.getChildren().add(topLeft);

        GridPane topRightGrid = new GridPane();
        Label factura = new Label("Facutra #" + numFactura);
        Label labelFecha = new Label("Fecha: ");
        Label labelUsuario = new Label("Usuario: ");

        topRightGrid.add(labelFecha, 0, 0);
        topRightGrid.add(fecha, 1, 0);
        topRightGrid.add(labelUsuario, 0, 1);
        topRightGrid.add(usuario, 1, 1);
        topRight.getChildren().addAll(labelFecha, topRightGrid);

        topContent.getChildren().add(topRight);

        top.getChildren().addAll(title, topContent);
        for (HBox txt : br.getVboxes()) {
            TextField tx = (TextField) txt.getChildren().get(2);
            TextField tx2 = (TextField) txt.getChildren().get(1);
            tx.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent arg0) {
                    Thread th = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    int prev = Integer.parseInt(subtotal12.getText());
                                    try {
                                        subtotal12.setText((Integer.parseInt(tx.getText()) * Integer.parseInt(tx2.getText()) + Integer.parseInt(subtotal12.getText())) + "");

                                    } catch (Exception e) {
                                        subtotal12.setText(prev + "");
                                    }
                                    
                                }
                            });
                        }
                    });
                    th.start();
                }
            });
            tx.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent arg0) {
                    Thread th = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    total.setText(Integer.parseInt(subtotal12.getText())+"");
                                    
                                }
                            });
                        }
                    });
                    th.start();
                }
            });
        }

        root.setTop(top);
        btnAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                if (iva.getText().equals("") || nombre.getText().equals("") || orden.toString().equals("") || ruc.getText().equals("") || total.getText().equals("") || usuario.getText().equals("")) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error:");
                    alerta.setContentText("Llene todos los espacios.");
                    alerta.show();
                } else {
                    pp.setCenterroot();
                    LocalDate ld = fecha.getValue();
                    Calendar c = Calendar.getInstance();
                    c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
                    Date date = c.getTime();
                    Cliente cliente = new Cliente((ruc.getText()));
                    Administrador ad = new Administrador(usuario.getText());
                    Factura fac = new Factura(Integer.parseInt(numFactura), date, Double.parseDouble(total.getText()), cliente, ad, Integer.parseInt(orden.getText()));
                }
            }
        });
    }

    public void createContent(PantallaPrincipal pp, List<FacturaProducto> fp, Factura f) {
//        Calendar c = Calendar.getInstance();
//        c.setTime(f.getFecha());
//        LocalDate ld = f.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        fecha.setValue(ld);
        ruc.setText(f.getCliente().getRuc());
        iva.setText(((f.getVaalor()) / 1.12) + "");
        nombre.setText(f.getCliente().getNombre());
        btnAceptar = new Button("Aceptar");
        root = new BorderPane();

        BlockRelations br = new BlockRelations(fp);
        root.setCenter(br.getRoot());

        bot = new GridPane();
        Label sub12 = new Label("SUBTOTAL 12%: ");
        Label sub0 = new Label("SUBTOTAL 0%: ");
        Label labelIva = new Label("IVA: ");
        Label labelTotal = new Label("TOTAL: ");
        bot.add(sub12, 0, 0);
        bot.add(subtotal12, 1, 0);
        bot.add(sub0, 0, 1);
        bot.add(subtotal0, 1, 1);
        bot.add(labelIva, 0, 2);
        bot.add(iva, 1, 2);
        bot.add(labelTotal, 0, 3);
        bot.add(total, 1, 3);
        bot.add(btnAceptar, 1, 4);
        root.setBottom(bot);

        top = new VBox();
        topContent = new HBox();
        topRight = new VBox();

        Label title = new Label("Factura");

        topLeft = new GridPane();
        Label autorizacion = new Label("Autorizacion: ");
        Label labelRuc = new Label("RUC: ");
        Label labelNom = new Label("Nombre: ");
        Label numAut = new Label(Integer.toString(numAutorizacion));
        topLeft.add(autorizacion, 0, 0);
        topLeft.add(numAut, 1, 0);
        topLeft.add(labelRuc, 0, 1);
        topLeft.add(ruc, 1, 1);
        topLeft.add(labelNom, 0, 2);
        topLeft.add(nombre, 1, 2);
        topLeft.add(new Label("Orden"), 0, 3);
        topLeft.add(orden, 1, 3);
        topContent.getChildren().add(topLeft);

        GridPane topRightGrid = new GridPane();
        Label factura = new Label("Facutra #" + numFactura);
        Label labelFecha = new Label("Fecha: ");
        Label labelUsuario = new Label("Usuario: ");

        topRightGrid.add(labelFecha, 0, 0);
        topRightGrid.add(fecha, 1, 0);
        topRightGrid.add(labelUsuario, 0, 1);
        topRightGrid.add(usuario, 1, 1);
        topRight.getChildren().addAll(labelFecha, topRightGrid);

        topContent.getChildren().add(topRight);

        top.getChildren().addAll(title, topContent);

        root.setTop(top);
    }

    //Los getters retornan el valor en dato primitivo (String, int o double)
    //Segun corresponda de los valores de los TextField.
    public Pane getRoot() {
        return root;
    }

    public String getRuc() {
        return ruc.getText();
    }

    public String getNombre() {
        return nombre.getText();
    }

    public String getUsuario() {
        return usuario.getText();
    }

    public double getSubtotal12() {
        return Double.parseDouble(subtotal12.getText());
    }

    public double getSubtotal0() {
        return Double.parseDouble(subtotal0.getText());
    }

    public double getTotal() {
        return Double.parseDouble(total.getText());
    }

    public int getNumFactura() {
        return Integer.parseInt(numFactura);
    }

}
