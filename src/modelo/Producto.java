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
public class Producto {
    private String descripcion;
    private String codigo;
    private double valor;

    public Producto(String desc, String codigo, double valor) throws SQLException {
        this.descripcion = desc;
        this.codigo = codigo;
        this.valor = valor;
        
    }

    public void agregarBase() throws SQLException{
        CONSTANTES.statement().executeUpdate("insert into producto(codigo_producto,descripcion,valor) value (\""+codigo+"\",\""+descripcion+"\","+valor+");");
    }

    public Producto(String cod){
        try{
            ResultSet rset=CONSTANTES.statement().executeQuery("select codigo_producto,descripcion,valor from producto where codigo_producto=\""+cod+";\"");
            if(rset.next()){
                this.descripcion = rset.getString("descripcion");
                this.codigo=rset.getString("codigo_producto");
                this.valor=rset.getDouble("valor");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        try {
            CONSTANTES.statement().executeUpdate("update producto set descripcion=\""+descripcion+"\" where codigo_producto="+codigo+";");
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCodigo() {
        return codigo;
    }


    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
        try {
            CONSTANTES.statement().executeUpdate("update producto set valor="+valor+" where codigo_producto="+codigo+";");
        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @Override
    public String toString() {
        return "Producto{" +  "codigo=" + codigo +", descripcion=" + descripcion + ", valor=" + valor + '}';
    }

    
    public static List<Producto> listaProducto() throws SQLException{
        return listaProducto(null,null,-1);
    }
    
    public static List<Producto> listaProducto(String desc, String codigo, double valor) throws SQLException{
        String query=selectQuery(desc,codigo,valor);
        ResultSet rset=CONSTANTES.statement().executeQuery(query);
        LinkedList<Producto> list=new LinkedList<>();
        while(rset.next()){
            Producto p=new Producto(rset.getString("descripcion"),rset.getString("codigo_producto"),rset.getDouble("valor"));
            list.add(p);
        }
        return list;
    }
    
    
    private static String selectQuery(String desc, String codigo, double valor){
        StringBuilder sb=new StringBuilder();
        sb.append("select codigo_producto,descripcion,valor from producto");
        boolean b= desc==null&&codigo==null&&valor<=0;
        
        String where=where(desc,codigo,valor);
        if(!b)
            sb.append(" where ").append(where);
        
        return sb.toString();
    }
    
    private static String where(String desc, String codigo, double valor){
        boolean b2=false;
        StringBuilder where=new StringBuilder();
        b2=agregaNombre(where,desc,"descripcion",b2);
        b2=agregar(where,codigo,"codigo_producto",b2);
        agregar(where,valor,"valor",b2);
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
    
    private static boolean agregar(StringBuilder sb,double s,String cat,boolean b2){
        if(s>0){
            if(b2) sb.append(" and ");
            sb.append(cat).append("=").append(s);
            b2=true;
        }
        return b2;
    }
    
    private static boolean agregaNombre(StringBuilder sb,String s,String cat,boolean b2){
        if(s!=null && !s.equals("")){
            if(b2) sb.append(" and ");
            sb.append(cat).append(" like \"%").append(s).append("%\"");
            b2=true;
        }
        return b2;
    }
    

    public static String codigoMinimo() throws SQLException{
        ResultSet rset=CONSTANTES.statement().executeQuery("select codigo_producto from producto order by codigo_producto");
        String s="";
        while(rset.next()){
            s=rset.getString("codigo_producto");
        }
        int i=Integer.valueOf(s)+1;
        int d=9;
        for(int j=i/10;j>0;j=j/10)
            d--;
        StringBuilder sb=new StringBuilder();
        for(;d>0;d--)
            sb.append("0");
        sb.append(i);
        return sb.toString();
    }

    public static Producto remove(Producto p) throws SQLException{
        CONSTANTES.statement().executeUpdate("delete from producto where "+where(null,p.codigo,-1) );
        return null;
    }
    
}
