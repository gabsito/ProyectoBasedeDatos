/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.consulta;

import constantes.CONSTANTES;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import proyectobaseskywy.PantallaPrincipal;

/**
 *
 * @author dylan
 */
public class VistaReportes {

    StackPane vista1;
    StackPane vista2;
    StackPane vista3;
    StackPane vista4;
    VBox root;

    public VistaReportes() {
        vista1 = new StackPane();
        vista2 = new StackPane();
        vista3 = new StackPane();
        vista4 = new StackPane();
        root = new VBox();
    }

    public void createContent(PantallaPrincipal pp) {
        Label lb1 = new Label("Reporte Ventas Por Vendedor");
        Label lb2 = new Label("Reporte Compras Por Cliente");
        Label lb3 = new Label("Reporte Productos Mas Vendidos");
        Label lb4 = new Label("Reporte Productos/Factura");
        Rectangle r1 = new Rectangle(500, 30);
        Rectangle r2 = new Rectangle(500, 30);
        Rectangle r3 = new Rectangle(500, 30);
        Rectangle r4 = new Rectangle(500, 30);
        r1.setFill(Color.CADETBLUE);
        r2.setFill(Color.CADETBLUE);
        r3.setFill(Color.CADETBLUE);
        r4.setFill(Color.CADETBLUE);
        vista1.getChildren().addAll(r1, lb1);
        vista2.getChildren().addAll(r2, lb2);
        vista3.getChildren().addAll(r3, lb3);
        vista4.getChildren().addAll(r4, lb4);
        vista1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                TableView<ResultadoV1> tb = new TableView();

                TableColumn coll = new TableColumn<>("nombre");
                coll.setCellValueFactory(new PropertyValueFactory("nom"));
                TableColumn coll2 = new TableColumn<>("apellido");
                coll2.setCellValueFactory(new PropertyValueFactory("ape"));
                TableColumn coll3 = new TableColumn<>("total");
                coll3.setCellValueFactory(new PropertyValueFactory("tot"));
                tb.getColumns().addAll(coll, coll2, coll3);

                for (ResultadoV1 r : VistaReportes.vista1list()) {
                    tb.getItems().add(r);
                }

                VBox vb = new VBox(tb);
                pp.setCenterjeje(vb);
            }
        });
        vista2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                TableView<ResultadoV2> tb = new TableView();

                TableColumn coll = new TableColumn<>("nombre");
                coll.setCellValueFactory(new PropertyValueFactory("nom"));
                TableColumn coll2 = new TableColumn<>("total");
                coll2.setCellValueFactory(new PropertyValueFactory("total"));
                tb.getColumns().addAll(coll, coll2);

                for (ResultadoV2 r : VistaReportes.vista2list()) {
                    tb.getItems().add(r);
                }

                VBox vb = new VBox(tb);
                pp.setCenterjeje(vb);
            }
        });
        vista3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                TableView<ResultadoV3> tb = new TableView();
                TableColumn coll = new TableColumn<>("codigo");
                coll.setCellValueFactory(new PropertyValueFactory("nom"));
                TableColumn coll2 = new TableColumn<>("descripcion");
                coll2.setCellValueFactory(new PropertyValueFactory("descripcion"));
                TableColumn coll3 = new TableColumn<>("total");
                coll3.setCellValueFactory(new PropertyValueFactory("total"));
                tb.getColumns().addAll(coll, coll2, coll3);

                for (ResultadoV3 r : VistaReportes.vista3list()) {
                    tb.getItems().add(r);
                }

                VBox vb = new VBox(tb);
                pp.setCenterjeje(vb);
            }
        });
        vista4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                TableView<ResultadoV4> tb = new TableView();
                TableColumn coll = new TableColumn<>("numFact");
                coll.setCellValueFactory(new PropertyValueFactory("NumeroFactura"));
                TableColumn coll2 = new TableColumn<>("Producto");
                coll2.setCellValueFactory(new PropertyValueFactory("CodigoProducto"));
                TableColumn coll3 = new TableColumn<>("Descripcion");
                coll3.setCellValueFactory(new PropertyValueFactory("descripcion"));
                TableColumn coll4 = new TableColumn<>("PrecioUnit");
                coll4.setCellValueFactory(new PropertyValueFactory("PrecioUnitario"));
                TableColumn coll5 = new TableColumn<>("Cantidad");
                coll5.setCellValueFactory(new PropertyValueFactory("cantidad"));
                TableColumn coll6 = new TableColumn<>("total");
                coll6.setCellValueFactory(new PropertyValueFactory("total"));
                tb.getColumns().addAll(coll, coll2, coll3,coll4,coll5,coll6);

                for (ResultadoV4 r : VistaReportes.vista4list()) {
                    tb.getItems().add(r);
                }

                VBox vb = new VBox(tb);
                pp.setCenterjeje(vb);
            }
        });
        root.getChildren().addAll(vista1, vista2, vista3, vista4);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);
    }

    public VBox getRoot() {
        return root;
    }

    public static ArrayList<ResultadoV1> vista1list() {
        ArrayList<ResultadoV1> list = new ArrayList();
        try {
            ResultSet rset = CONSTANTES.statement().executeQuery("select * from vista1");
            while (rset.next()) {

                ResultadoV1 r = new ResultadoV1(rset.getString("nombre"), rset.getString("apellido"), rset.getString("total"));
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static class ResultadoV1 {

        public String nom;
        public String ape;
        public String tot;

        public ResultadoV1(String nom, String ape, String tot) {
            this.nom = nom;
            this.ape = ape;
            this.tot = tot;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getApe() {
            return ape;
        }

        public void setApe(String ape) {
            this.ape = ape;
        }

        public String getTot() {
            return tot;
        }

        public void setTot(String tot) {
            this.tot = tot;
        }

    }

    public static ArrayList<ResultadoV2> vista2list() {
        ArrayList<ResultadoV2> list = new ArrayList();
        try {
            ResultSet rset = CONSTANTES.statement().executeQuery("select * from vista2");
            while (rset.next()) {

                ResultadoV2 r = new ResultadoV2(rset.getString("nombre"), rset.getDouble("total"));
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static class ResultadoV2 {

        public String nom;
        public double total;

        public ResultadoV2(String nom, double total) {
            this.nom = nom;
            this.total = total;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        

    }

    public static ArrayList<ResultadoV3> vista3list() {
        ArrayList<ResultadoV3> list = new ArrayList();
        try {
            ResultSet rset = CONSTANTES.statement().executeQuery("select * from vista3");
            while (rset.next()) {

                ResultadoV3 r = new ResultadoV3(rset.getString("codigo"), rset.getString("descri"), rset.getDouble("total"));
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static class ResultadoV3 {

        public String nom;
        public String descripcion;
        public double total;

        public ResultadoV3(String nom, String descripcion, double total) {
            this.nom = nom;
            this.descripcion = descripcion;
            this.total = total;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

    }
    public static ArrayList<ResultadoV4> vista4list() {
        ArrayList<ResultadoV4> list = new ArrayList();
        try {
            ResultSet rset = CONSTANTES.statement().executeQuery("select * from vistaProdFactura");
            while (rset.next()) {

                ResultadoV4 r = new ResultadoV4(rset.getInt("NumeroFactura"),rset.getString("CodigoProducto"),rset.getString("Producto"),rset.getDouble("PrecioUnitario"),rset.getInt("Cantidad"),rset.getDouble("TotalUS$"));
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static class ResultadoV4 {

        public int NumeroFactura;
        public String CodigoProducto;
        public String descripcion;
        private double PrecioUnitario;
        private int cantidad;
        private double total;

        public ResultadoV4(int NumeroFactura, String CodigoProducto, String descripcion, double PrecioUnitario, int cantidad, double total) {
            this.NumeroFactura = NumeroFactura;
            this.CodigoProducto = CodigoProducto;
            this.descripcion = descripcion;
            this.PrecioUnitario = PrecioUnitario;
            this.cantidad = cantidad;
            this.total = total;
        }

        public int getNumeroFactura() {
            return NumeroFactura;
        }

        public void setNumeroFactura(int NumeroFactura) {
            this.NumeroFactura = NumeroFactura;
        }

        public String getCodigoProducto() {
            return CodigoProducto;
        }

        public void setCodigoProducto(String CodigoProducto) {
            this.CodigoProducto = CodigoProducto;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public double getPrecioUnitario() {
            return PrecioUnitario;
        }

        public void setPrecioUnitario(double PrecioUnitario) {
            this.PrecioUnitario = PrecioUnitario;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }
        
    }

}
