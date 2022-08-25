/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.FacturaProducto;

/**
 *
 * @author dylan
 */
public class BlockRelations {

    VBox root;
    ArrayList<HBox> vboxes;
    List<FacturaProducto> facobr;

    public BlockRelations() {
        root = new VBox();
        vboxes = new ArrayList<>();
        facobr = new ArrayList<>();
        HBox vblabel = new HBox();
        TextField txt0 = new TextField("Codigo");
        TextField txt01 = new TextField("Cantidad");
        TextField txt02 = new TextField("Valor");
        vblabel.getChildren().addAll(txt0, txt01, txt02);
        root.getChildren().add(vblabel);
        for (int z = 0; z < 5; z++) {
            HBox hb = new HBox();
            TextField txt1 = new TextField();
            TextField txt2 = new TextField();
            TextField txt3 = new TextField();
            hb.getChildren().addAll(txt1, txt2, txt3);
            vboxes.add(hb);
            root.getChildren().add(hb);
        }
    }

    public VBox getRoot() {
        return root;
    }

    public BlockRelations(List<FacturaProducto> arr) {
        root = new VBox();
        vboxes = new ArrayList<>();
        facobr = arr;
        HBox vblabel = new HBox();
        TextField txt0 = new TextField("Codigo");
        TextField txt01 = new TextField("Cantidad");
        TextField txt02 = new TextField("Valor");
        txt0.setEditable(false);
        txt01.setEditable(false);
        txt02.setEditable(false);
        vblabel.getChildren().addAll(txt0, txt01, txt02);
        root.getChildren().add(vblabel);
        for (int z = 0; z < facobr.size(); z++) {
            
            HBox hb = new HBox();
            TextField txt1 = new TextField();
            TextField txt2 = new TextField();
            TextField txt3 = new TextField();
            
            txt1.setText(""+facobr.get(z).getCodProd());
            txt2.setText(""+facobr.get(z).getCantidad());
            txt3.setText(""+facobr.get(z).getValor());
            txt1.setEditable(false);
            hb.getChildren().addAll(txt1, txt2, txt3);
            vboxes.add(hb);
            root.getChildren().add(hb);
        }
        for (int z = 0; z < 5; z++) {
            HBox hb = new HBox();
            TextField txt1 = new TextField();
            TextField txt2 = new TextField();
            TextField txt3 = new TextField();
            hb.getChildren().addAll(txt1, txt2, txt3);
            vboxes.add(hb);
            root.getChildren().add(hb);
        }

    }

    public ArrayList<HBox> getVboxes() {
        return vboxes;
    }

    public List<FacturaProducto> getFacobr() {
        return facobr;
    }
}
