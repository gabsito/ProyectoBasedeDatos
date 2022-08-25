/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author David Santistevan
 */
public class CONSTANTES {
    public static final String RUTACONEXION="jdbc:mysql://localhost:3306/proyecto_kywy?zeroDateTimeBehavior=convertToNull&noAccessToProcedureBodies=true";
    public static Connection CONEXION;
    
    
    public static Statement statement() throws SQLException{
        
            return CONEXION.createStatement();
        
        
    }
    
    public static void iniciar() throws SQLException{
        CONEXION=DriverManager.getConnection(RUTACONEXION, "dsanti", "contra");;
    }
    
}
