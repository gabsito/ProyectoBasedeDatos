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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Santistevan
 */
public class Cliente {
    private String ruc;
    private String nombre;
    private String direccion;
    private String telefono;

    public Cliente(String ruc, String nombre, String direccion, String telefono) {
        this.ruc = ruc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Cliente(String ruc){
        try{
            ResultSet rset=CONSTANTES.statement().executeQuery("select ruc_cliente,nombre,direccion,telefono from cliente where ruc_cliente=\""+ruc+"\";");
            rset.next();
            this.ruc = rset.getString("ruc_cliente");
            this.direccion = rset.getString("direccion");
            this.nombre = rset.getString("nombre");
            this.telefono = rset.getString("telefono");
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getRuc() {
        return ruc;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        try {
            CONSTANTES.statement().executeUpdate("update cliente set nombre=\""+nombre+"\" where ruc_cliente=\""+ruc+"\";");
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
        try {
            CONSTANTES.statement().executeUpdate("update cliente set direccion=\""+direccion+"\" where ruc_cliente=\""+ruc+"\";");
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if(telefono.length()<=14){
            this.telefono = telefono;
            try {
                CONSTANTES.statement().executeUpdate("update cliente set telefono=\""+telefono+"\" where ruc_cliente=\""+ruc+"\";");
            } catch (SQLException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static List<Cliente> listaCliente() throws SQLException{
        return listaCliente(null,null,null,null);
    }
    
    public static List<Cliente> listaCliente(String ruc, String nombre, String direccion, String telefono) throws SQLException{
        String query=selectQuery(ruc,nombre,direccion,telefono);
        ResultSet rset=CONSTANTES.statement().executeQuery(query);
        LinkedList<Cliente> list=new LinkedList<>();
        while(rset.next()){
            Cliente c=new Cliente(rset.getString("ruc_cliente"),rset.getString("nombre"),rset.getString("direccion"),rset.getString("telefono"));
            list.add(c);
        }
        return list;
    }
    
    
    private static String selectQuery(String ruc, String nombre, String direccion, String telefono){
        StringBuilder sb=new StringBuilder();
        sb.append("select ruc_cliente,nombre,direccion,telefono from cliente");
        boolean b= ruc==null&&nombre==null&&direccion==null&&telefono==null;
        
        String where=where(ruc,nombre,direccion,telefono);
        if(!b)
            sb.append(" where ").append(where);
        
        return sb.toString();
    }
    
    private static String where(String ruc, String nombre, String direccion, String telefono){
        boolean b2=false;
        StringBuilder where=new StringBuilder();
        b2=agregar(where,ruc,"ruc_cliente",b2);
        b2=agregar(where,nombre,"nombre",b2);
        b2=agregar(where,direccion,"direccion",b2);
        agregar(where,telefono,"telefono",b2);
        return where.toString();
    }
    
    private static boolean agregar(StringBuilder sb,String s,String cat,boolean b2){
        if(s!=null && !s.equals("")){
            if(b2) sb.append(" and ");
            sb.append(cat).append("=\"").append(s).append("\"");
            b2=true;
        }
        return b2;
    }

    @Override
    public String toString() {
        return "Cliente{" + "ruc=" + ruc + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + '}';
    }
    
    public static Cliente remove(Cliente c) throws SQLException{
        CONSTANTES.statement().executeUpdate("delete from cliente where "+where(c.ruc,null,null,null) );
        return null;
    }
}
