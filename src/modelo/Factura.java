/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import constantes.CONSTANTES;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author David Santistevan
 */
public class Factura {
    private int numero;
    private Date Fecha;
    private double vaalor;
    private Cliente cliente;
    private Administrador ususarioAdmin;
    private int NumOrdComp;
    private HashMap<Producto,Integer> productos;

    public Factura(int numero, Date Fecha, double vaalor, Cliente Ruccliente, Administrador ususarioAdmin, int NumOrdComp) {
        this.numero = numero;
        this.Fecha = Fecha;
        this.vaalor = vaalor;
        this.cliente = Ruccliente;
        this.ususarioAdmin = (ususarioAdmin);
        this.NumOrdComp = NumOrdComp;
        productos=new HashMap<>();
    }
    
    public Factura(int numero) throws SQLException{
        ResultSet rs=CONSTANTES.statement().executeQuery("select UsuarioAdmin,RUC,NumeroFactura,Fecha,Total,OrdenDeCompra from vistaInfoFactura where NumeroFactura="+numero);
        rs.next();
        this.numero = numero;
        this.Fecha = rs.getDate("Fecha");
        this.vaalor = rs.getDouble("Total");
        this.cliente = new Cliente(rs.getString("RUC"));
        this.ususarioAdmin = new Administrador(rs.getString("UsuarioAdmin"));
        this.NumOrdComp = rs.getInt("OrdenDeCompra");
        productos=new HashMap<>();
        rs=CONSTANTES.statement().executeQuery("select * from VistaProdFactura where NumeroFactura="+numero);
        while(rs.next()){
            productos.put(new Producto(rs.getString("CodigoProducto")), rs.getInt("Cantidad"));
        }
            
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public double getVaalor() {
        return vaalor;
    }

    public void setVaalor(double vaalor) {
        this.vaalor = vaalor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente Ruccliente) {
        this.cliente = Ruccliente;
    }

    public Administrador getAdmin() {
        return ususarioAdmin;
    }

    public void setAdmin(Administrador ususarioAdmin) {
        this.ususarioAdmin = ususarioAdmin;
    }

    public int getNumOrdComp() {
        return NumOrdComp;
    }

    public void setNumOrdComp(int NumOrdComp) {
        this.NumOrdComp = NumOrdComp;
    }
    
    
    public static List<Factura> listaFacturas() throws SQLException{
        ResultSet rs=CONSTANTES.statement().executeQuery("select UsuarioAdmin,RUC,NumeroFactura,Fecha,Total,OrdenDeCompra from vistaInfoFactura");
        List<Factura> list=new LinkedList<>();
        while(rs.next()){
            String a=rs.getString("UsuarioAdmin");
            String c=rs.getString("RUC");
            int i=rs.getInt("OrdenDeCompra");
            Factura f=new Factura(rs.getInt("NumeroFactura"), rs.getDate("Fecha"),
                    rs.getDouble("Total"), new Cliente(c),
                    new Administrador(a), 
                    i);
            ResultSet rs2=CONSTANTES.CONEXION.createStatement().executeQuery("select * from VistaProdFactura where NumeroFactura="+f.numero);
            while(rs2.next()){
                String s=rs2.getString("CodigoProducto");
                f.productos.put(new Producto(s), rs2.getInt("Cantidad"));
            }
            list.add(f);
        }
        return list;
        
    }

    @Override
    public String toString() {
        return "Factura{" + "numero=" + numero + ", Fecha=" + Fecha + ", Valor=" + vaalor + ", cliente=" + cliente + ", ususarioAdmin=" + ususarioAdmin + ", NumOrdComp=" + NumOrdComp + ", productos=" + productos + '}';
    }

    public List<Producto> getProductos() {
        LinkedList<Producto> list=new LinkedList<>();
        productos.forEach((k,v) -> list.add(k));
        return list;
    }
    
    
    
}
