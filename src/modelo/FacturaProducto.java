/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import constantes.CONSTANTES;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dylan
 */
public class FacturaProducto {
    int codigoFac;
    int codProd;
    int Cantidad;
    double valor;

    public int getCodigoFac() {
        return codigoFac;
    }

    public void setCodigoFac(int codigoFac) {
        this.codigoFac = codigoFac;
    }

    public int getCodProd() {
        return codProd;
    }

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public FacturaProducto(int codigoFac, int codProd, int Cantidad, double valor) {
        this.codigoFac = codigoFac;
        this.codProd = codProd;
        this.Cantidad = Cantidad;
        this.valor = valor;
    }
    public static List<FacturaProducto> listaFacturas(int NumFac) throws SQLException{
        ResultSet rs=CONSTANTES.statement().executeQuery("select numero_factura,codigo_producto,cantidad,valor from factura_producto where numero_factura="+NumFac);
        List<FacturaProducto> list=new LinkedList<>();
        while(rs.next()){
            FacturaProducto fp=new FacturaProducto(Integer.parseInt(rs.getString("numero_factura")),Integer.parseInt(rs.getString( "codigo_producto")), Integer.parseInt(rs.getString( "cantidad")), Double.parseDouble(rs.getString( "valor")));
            list.add(fp);
        }
        return list;
        
    }
    
}
