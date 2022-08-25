/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import constantes.CONSTANTES;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David Santistevan
 */
public class Proforma {
    private int codigo;
    private double valor;
    private String admin;
    private Administrador administrador;
    private Map<Producto,Integer> productos;
    
    public Proforma(Administrador a){
        
        administrador=a;
        admin=administrador.getUsuario();
        productos=new TreeMap<>();
        valor=0;
        agregarProforma();
    }

    public Proforma(int codigo, double valor, String admin, Map<Producto, Integer> productos) {
        this.codigo = codigo;
        this.valor = valor;
        this.admin = admin;
        this.administrador = new Administrador(admin);
        this.productos = productos;
    }
    
    
    
    public void agregarProducto(Producto p,int cantidad){
        productos.put(p,cantidad);
        valor+=p.getValor()*cantidad;
        try {
            CONSTANTES.statement().executeUpdate("update proforma set valor="+valor+" where cod_proforma="+codigo+";");
            CONSTANTES.statement().executeUpdate("insert into proforma_producto(cod_proforma,codigo_producto,cantidad) value ("+codigo+","+p.getCodigo()+","+cantidad+");");
        } catch (SQLException ex) {
            Logger.getLogger(Proforma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public final void agregarProforma(){
        try {
            codigo=CONSTANTES.statement().executeUpdate("insert into proforma(valor,usuario_admin) value ("+valor+",\""+admin+"\");",Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            Logger.getLogger(Proforma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "Proforma{" + "codigo=" + codigo + ", valor=" + valor + ", admin=" + admin + ", productos=" + productos + '}';
    }

    
    
    

    
    public static List<Proforma> listaProforma() throws SQLException{
         return listaProforma(-1,-1,null);
    }
    
    public static List<Proforma> listaProforma(int codigo, double valor, String admin) throws SQLException{
        String query=selectQuery(codigo,valor,admin);
        ResultSet rset=CONSTANTES.statement().executeQuery(query);
        LinkedList<Proforma> list=new LinkedList<>();
        while(!rset.isClosed()&&rset.next()){
            int c=rset.getInt("cod_proforma");
            Proforma p=new Proforma(c,rset.getDouble("valor"),rset.getString("usuario_admin"),joinProductos(c));
            list.add(p);
        }
        return list;
    }
    
    
    private static TreeMap<Producto,Integer> joinProductos(int codigo) throws SQLException{
        StringBuilder sb=new StringBuilder();
        sb.append("select codigo_producto,cantidad from proforma_producto where codigo_producto=\"").append(codigo).append("\"");
        ResultSet rset=CONSTANTES.statement().executeQuery(sb.toString());
        TreeMap<Producto,Integer> map=new TreeMap<>();
        while(rset.next()&&!rset.isClosed()){
            String s=rset.getString("codigo_producto");
            int i=rset.getInt("cantidad");
            map.put(new Producto(s), i);
        }
        return map;
    }
    
    private static String selectQuery(int codigo, double valor, String admin){
        StringBuilder sb=new StringBuilder();
        sb.append("select cod_proforma,valor,usuario_admin from proforma");
        boolean b= admin==null&&codigo<=0&&valor<=0;
        
        String where=where(codigo,valor,admin);
        if(!b)
            sb.append(" where ").append(where);
        
        return sb.toString();
    }
    
    private static String where(int codigo, double valor, String admin){
        boolean b2=false;
        StringBuilder where=new StringBuilder();
        b2=agregar(where,codigo,"cod_proforma",b2);
        b2=agregar(where,valor,"valor",b2);
        agregar(where,admin,"usuario_admin",b2);
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
            sb.append(cat).append("=").append(s).append("");
            b2=true;
        }
        return b2;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getValor() {
        return valor;
    }

    public String getAdmin() {
        return admin;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public Map<Producto, Integer> getProductos() {
        return productos;
    }
    
    
    public static Proforma remove(Proforma p) throws SQLException{
        CONSTANTES.statement().executeUpdate("delete from proforma where "+where(p.codigo,-1,null) );
        return null;
    }
    
}
