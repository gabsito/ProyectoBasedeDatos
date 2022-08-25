/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.insert;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Calendar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Cobro;
import view.SeccionFormulario;
import java.time.Instant;
import proyectobaseskywy.PantallaPrincipal;

/**
 *
 * @author dylan
 */
public class VistaCobroRegistro {

    private VBox root;
    private boolean inst=true;

    public VistaCobroRegistro() {
        root = new VBox();
    }

    public VBox getRoot(PantallaPrincipal jeje) {
        HBox hb0 = new HBox();
        Label cobro = new Label("cobro#");
        Label cobro0 = new Label("Cobro");
        SeccionFormulario ruc = new SeccionFormulario("RUC");
        SeccionFormulario nombre = new SeccionFormulario("Nombre");
        SeccionFormulario fecha = new SeccionFormulario("Fecha");
        SeccionFormulario usuario = new SeccionFormulario("Usuario");

        VBox vb0 = new VBox();
        VBox vb1 = new VBox();
        vb0.getChildren().addAll(cobro0, ruc.getRoot(), nombre.getRoot());
        vb1.getChildren().addAll(fecha.getRoot(), cobro, usuario.getRoot());

        vb0.setPadding(new Insets(20));
        vb1.setPadding(new Insets(20));
        vb0.setSpacing(20);
        vb1.setSpacing(20);

        hb0.getChildren().addAll(vb0, vb1);
        hb0.setAlignment(Pos.CENTER);
        SeccionFormulario valorf = new SeccionFormulario("Valor");

        TextField t1 = new TextField("Metodo");
        TextField t2 = new TextField("Factura");
        TextField t3 = new TextField("Fecha");
        TextField t4 = new TextField("Valor");
        TextField t5 = new TextField("SaldoFactura");
        TextField t6 = new TextField("SaldoCobro");
        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        t4.setEditable(false);
        t5.setEditable(false);
        t6.setEditable(false);
        TextField t11 = new TextField();
        TextField t22 = new TextField();
        DatePicker t33 = new DatePicker();
        TextField t44 = new TextField();
        TextField t55 = new TextField();
        TextField t66 = new TextField();
        VBox vbt1 = new VBox();
        VBox vbt2 = new VBox();
        VBox vbt3 = new VBox();
        VBox vbt4 = new VBox();
        VBox vbt5 = new VBox();
        VBox vbt6 = new VBox();
        vbt1.getChildren().addAll(t1, t11);
        vbt2.getChildren().addAll(t2, t22);
        vbt3.getChildren().addAll(t3, t33);
        vbt4.getChildren().addAll(t4, t44);
        vbt5.getChildren().addAll(t5, t55);
        vbt6.getChildren().addAll(t6, t66);
        HBox txt = new HBox();
        txt.getChildren().addAll(vbt1, vbt2, vbt3, vbt4, vbt5, vbt6);
        txt.setAlignment(Pos.CENTER);

        Button guardar = new Button("Guardar");
        BorderPane bp = new BorderPane();
        bp.setRight(valorf.getRoot());
        bp.setLeft(guardar);
        bp.setPadding(new Insets(50, 200, 0, 200));
        valorf.getRoot().setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().addAll(hb0, txt, bp);
        root.setAlignment(Pos.TOP_CENTER);

        guardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                if (t11.getText().equals("") || t22.getText().equals("") || t33.toString().equals("") || t44.getText().equals("") || t55.getText().equals("") || t66.getText().equals("")) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error:");
                    alerta.setContentText("Llene todos los espacios.");
                    alerta.show();
                } else {
                    jeje.setCenterroot();
                    LocalDate ld = t33.getValue();
                    Calendar c = Calendar.getInstance();
                    c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
                    Date date = c.getTime();
                    Cobro cobro = new Cobro(date, Integer.parseInt(t66.getText()), t11.getText(), Integer.parseInt(t22.getText()), usuario.getCampo().getText());
                }
            }
        });

        return root;
    }

    public VBox getRoot(Cobro c) {
        HBox hb0 = new HBox();
        Label cobro = new Label("cobro#");
        Label cobro0 = new Label("Cobro");
        SeccionFormulario ruc = new SeccionFormulario("RUC");
        SeccionFormulario nombre = new SeccionFormulario("Nombre");
        SeccionFormulario fecha = new SeccionFormulario("Fecha");
        SeccionFormulario usuario = new SeccionFormulario("Usuario");

        VBox vb0 = new VBox();
        VBox vb1 = new VBox();
        vb0.getChildren().addAll(cobro0, ruc.getRoot(), nombre.getRoot());
        vb1.getChildren().addAll(fecha.getRoot(), cobro, usuario.getRoot());

        vb0.setPadding(new Insets(20));
        vb1.setPadding(new Insets(20));
        vb0.setSpacing(20);
        vb1.setSpacing(20);

        hb0.getChildren().addAll(vb0, vb1);
        hb0.setAlignment(Pos.CENTER);
        SeccionFormulario valorf = new SeccionFormulario("Valor");

        TextField t1 = new TextField("Metodo");
        TextField t2 = new TextField("Factura");
        TextField t3 = new TextField("Fecha");
        TextField t4 = new TextField("Valor");
        TextField t5 = new TextField("SaldoFactura");
        TextField t6 = new TextField("SaldoCobro");
        t1.setEditable(false);
        t2.setEditable(false);
        t3.setEditable(false);
        t4.setEditable(false);
        t5.setEditable(false);
        t6.setEditable(false);
        TextField t11 = new TextField();
        TextField t22 = new TextField();
        DatePicker t33 = new DatePicker();
        TextField t44 = new TextField();
        TextField t55 = new TextField();
        TextField t66 = new TextField();
        t11.setText(c.getMetodoPago());
        t22.setText(""+c.getCodFactura());
        Instant instant = c.getFecha().toInstant();
        t33.setValue(instant.atZone(ZoneId.systemDefault()).toLocalDate());
        t44.setText(""+c.getValor());
        t66.setText(""+c.getValor());
        
        
                
        VBox vbt1 = new VBox();
        VBox vbt2 = new VBox();
        VBox vbt3 = new VBox();
        VBox vbt4 = new VBox();
        VBox vbt5 = new VBox();
        VBox vbt6 = new VBox();
        vbt1.getChildren().addAll(t1, t11);
        vbt2.getChildren().addAll(t2, t22);
        vbt3.getChildren().addAll(t3, t33);
        vbt4.getChildren().addAll(t4, t44);
        vbt5.getChildren().addAll(t5, t55);
        vbt6.getChildren().addAll(t6, t66);
        HBox txt = new HBox();
        txt.getChildren().addAll(vbt1, vbt2, vbt3, vbt4, vbt5, vbt6);
        txt.setAlignment(Pos.CENTER);

        Button guardar = new Button("Guardar");
        BorderPane bp = new BorderPane();
        bp.setRight(valorf.getRoot());
        bp.setLeft(guardar);
        bp.setPadding(new Insets(50, 200, 0, 200));
        valorf.getRoot().setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().addAll(hb0, txt, bp);
        root.setAlignment(Pos.TOP_CENTER);

        guardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                if (t11.getText().equals("") || t22.getText().equals("") || t33.toString().equals("") || t44.getText().equals("") || t55.getText().equals("") || t66.getText().equals("")) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error:");
                    alerta.setContentText("Llene todos los espacios.");
                    alerta.show();
                } else {
                    LocalDate ld = t33.getValue();
                    Calendar ca = Calendar.getInstance();
                    ca.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
                    Date date = ca.getTime();
                    c.setCodFactura(Integer.parseInt(t22.getText()));
                    c.setFecha(date);
                    c.setMetodoPago(t11.getText());
                    c.setValor(Integer.parseInt(t66.getText()));
//                    Cobro cobro = new Cobro(date, Integer.parseInt(t66.getText()), t11.getText(), Integer.parseInt(t22.getText()), usuario.getCampo().getText());
                }
            }
        });

        return root;
    }
}
