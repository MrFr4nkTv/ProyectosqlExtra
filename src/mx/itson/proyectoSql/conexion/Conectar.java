/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mx.itson.proyectoSql.conexion;

/**
 *
 * @author Frank
 */
import java.sql.Connection;
import java.sql.DriverManager;
 
public class Conectar {
  //declaramos dentro de la clase la constante URL, tanto como la de usuario y la clave necesarias para ingresar
 public static final String URL = "jdbc:mysql://localhost:3307/sqltarea"; 
    public static final String USER = "Frank";
    public static final String CLAVE = "guaymas";
   
    // se crea un método que regresa un valor de tipo Connection.
    public Connection getConexion(){
        Connection con = null; // Se instancia el objeto Connection.
        try{ // Se llama a la clase que maneja el Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USER, CLAVE); // Se hace la conexion con las 3 constantes iniciales
        }catch(Exception e){ // Muestra un mensaje en caso de error
            System.out.println("Error: " + e.getMessage());
        }
        return con; // Se devuelve un tipo conexion
    }
}