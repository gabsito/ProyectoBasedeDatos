/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import constantes.CONSTANTES;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Santistevan
 */
public class Administrador {
    private String usuario;
    private String password;
    private String nombre;
    private String apellido;
    private String cedula;

    public Administrador(String usuario, String password, String nombre, String apellido, String cedula) {
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
    }
    
    public Administrador(String usu){
        try{
            ResultSet rset=CONSTANTES.statement().executeQuery("select usuario,nombre,apellido,cedula,contrasena from administrador where usuario=\""+usu+"\";");
            rset.next();
            this.usuario = rset.getString("usuario");
            this.password = rset.getString("contrasena");
            this.nombre = rset.getString("nombre");
            this.apellido = rset.getString("apellido");
            this.cedula = rset.getString("cedula");
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public String getUsuario() {
        return usuario;
    }


    public String getPassword() {
        return password;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        try {
            CONSTANTES.statement().executeUpdate("update administrador set nombre=\""+nombre+"\" where usuario=\""+usuario+"\";");
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
        try {
            CONSTANTES.statement().executeUpdate("update administrador set apellido=\""+apellido+"\" where usuario=\""+usuario+"\";");
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCedula() {
        return cedula;
    }

    @Override
    public String toString() {
        return "Administrador{" + "usuario=" + usuario + ", nombre=" + nombre + ", apellido=" + apellido + ", cedula=" + cedula + '}';
    }
    
    public static List<Administrador> listaAdmin() throws SQLException{
        return listaAdmin(null,null,null,null);
    }

    public static List<Administrador> listaAdmin(String usuario, String nombre, String apellido, String cedula) throws SQLException{
        String query=selectQuery(usuario,nombre,apellido,cedula);
        ResultSet rset=CONSTANTES.statement().executeQuery(query);
        LinkedList<Administrador> list=new LinkedList<>();
        while(rset.next()){
            Administrador a=new Administrador(rset.getString("usuario"),rset.getString("contrasena"),rset.getString("nombre"),rset.getString("apellido"),rset.getString("cedula"));
            list.add(a);
        }
        return list;
    }
    
    
    private static String selectQuery(String usuario, String nombre, String apellido, String cedula){
        StringBuilder sb=new StringBuilder();
        sb.append("select usuario,nombre,apellido,cedula,contrasena from administrador");
        boolean b= usuario==null&&nombre==null&&apellido==null&&cedula==null;
        
        String where=where(usuario,nombre,apellido,cedula);
        if(!b)
            sb.append(" where ").append(where);
        
        return sb.toString();
    }
    
    private static String where(String usuario, String nombre, String apellido, String cedula){
        boolean b2=false;
        StringBuilder where=new StringBuilder();
        b2=agregar(where,usuario,"usuario",b2);
        b2=agregar(where,nombre,"nombre",b2);
        b2=agregar(where,apellido,"apellido",b2);
        agregar(where,cedula,"cedula",b2);
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
    
    public static Administrador remove(Administrador a) throws SQLException{
        CONSTANTES.statement().executeUpdate("delete from administrador where "+where(a.usuario,null,null,null) );
        return null;
    }
}
