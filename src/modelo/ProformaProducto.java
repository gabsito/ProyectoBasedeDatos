/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author dylan
 */
public class ProformaProducto {
    int CodProforma;
    String CodigoProducto;
    int cantidad;

    public ProformaProducto(int CodProforma, String CodigoProducto, int cantidad) {
        this.CodProforma = CodProforma;
        this.CodigoProducto = CodigoProducto;
        this.cantidad = cantidad;
    }

    public int getCodProforma() {
        return CodProforma;
    }

    public void setCodProforma(int CodProforma) {
        this.CodProforma = CodProforma;
    }

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(String CodigoProducto) {
        this.CodigoProducto = CodigoProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
