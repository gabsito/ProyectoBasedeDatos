/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author dylan
 */
public class OrdenCompra {
    int NumOrdComra;
    String descripcion;
    Date Fecha;
    String RucCliente;

    public OrdenCompra(int NumOrdComra, String descripcion, Date Fecha, String RucCliente) {
        this.NumOrdComra = NumOrdComra;
        this.descripcion = descripcion;
        this.Fecha = Fecha;
        this.RucCliente = RucCliente;
    }
    

    public int getNumOrdComra() {
        return NumOrdComra;
    }

    public void setNumOrdComra(int NumOrdComra) {
        this.NumOrdComra = NumOrdComra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getRucCliente() {
        return RucCliente;
    }

    public void setRucCliente(String RucCliente) {
        this.RucCliente = RucCliente;
    }
    
    
}
