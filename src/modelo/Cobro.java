/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import constantes.CONSTANTES;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dylan
 */
public class Cobro {
    Date fecha;
    int idCobro;
    int valor;
    String MetodoPago;
    int CodFactura;
    String UsuarioAdmin;

    public Cobro(Date fecha, int valor, String MetodoPago, int CodFactura, String UsuarioAdmin) {
        this.fecha = fecha;
        this.valor = valor;
        this.MetodoPago = MetodoPago;
        this.CodFactura = CodFactura;
        this.UsuarioAdmin = UsuarioAdmin;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
         this.fecha = fecha;
        try {
            CONSTANTES.statement().executeUpdate("update cobro set fecha=\""+fecha+"\" where codigo_cobro="+idCobro+";");
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getIdCobro() {
        return idCobro;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
        try {
            CONSTANTES.statement().executeUpdate("update cobro set valor=\""+valor+"\" where codigo_cobro="+idCobro+";");
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getMetodoPago() {
        return MetodoPago;
    }

    public void setMetodoPago(String MetodoPago) {
        this.MetodoPago = MetodoPago;
        try {
            CONSTANTES.statement().executeUpdate("update cobro set metodo_pago=\""+MetodoPago+"\" where codigo_cobro="+idCobro+";");
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getCodFactura() {
        return CodFactura;
    }

    public void setCodFactura(int CodFactura) {
        this.CodFactura = CodFactura;
        try {
            CONSTANTES.statement().executeUpdate("update cobro set cod_fadtura=\""+CodFactura+"\" where codigo_cobro="+idCobro+";");
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    public String getUsuarioAdmin() {
        return UsuarioAdmin;
    }    
    public void remove(){
        
    }
    
}
