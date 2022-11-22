/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.proyectoSql.entidades;

import mx.itson.proyectoSql.conexion.Conectar;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
/**
 *
 * @author Frank
 */
public class ControlDatos {

private Conectar conectar;
private ModeloDatos modelo;
private Connection con;

//  Se crea el constructor de la clase y dentro se instancian los objetos de tipo Conectar y ModeloDatos.
   public ControlDatos(){
   conectar = new Conectar();
   modelo = new ModeloDatos();
   }
   public void insertar(String nombre, int edad, String sexo){ //Se crea el método Insertar con 3 parámetros: nombre, edad y sexo.
        // Se crea una variable de tipo PreparedStatement, un String para el sql y se carga el modelo con los parametros del mismo método.
        PreparedStatement ps;
        String sql;
        modelo.setNombre(nombre);
        modelo.setEdad(edad);
        modelo.setSexo(sexo);
        // Se coloca un try – catch con un mensaje de advertencia
        try{
            con = conectar.getConexion(); // establecer la conexión:
            sql = "insert into datos(nombre, edad, sexo) values(?,?,?)"; // se crea cadena SQL en formato de sentencia preparada
            ps = con.prepareStatement(sql); // sentencia con el SQL que acabamos de crear.
            // se remplezan los datos que estaban cargados en el modelo, con setString y setInt
            ps.setString(1, modelo.getNombre()); //
            ps.setInt(2, modelo.getEdad());
            ps.setString(3, modelo.getSexo());
            ps.executeUpdate(); // Ejecutamos la sentencia preparada y lanzamos un mensaje de confirmación
            JOptionPane.showMessageDialog(null, "Se han insertado los datos");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
        }
        
    }
   // Se añade un metodo el cual regresa un ArrayList de tipo ModeloDatos
    public ArrayList<ModeloDatos> llenarDatos(){
        // Se agregan las variables necesarias ademas un ResulSet para trabajar con los resultados de la tabla
        ModeloDatos datos;
        ResultSet rs;
        PreparedStatement ps;
        ArrayList<ModeloDatos> lista = new ArrayList<>();
        String sql = "select id, nombre, edad, sexo from datos"; // Se hace una seleccion de la base de datos
        try{ // crear un try – catch para atrapar todas las excepciones de tipo SQLException	
        con = conectar.getConexion(); //Inicia la conexion
        ps = con.prepareStatement(sql); // Se prepara la sentencia con el SQL    
	rs = ps.executeQuery(); // ejecutamos la sentencia SQL y se guarda en el resultset
        while(rs.next()){ //Se recorren los registros uno por uno 
        // Se utilizan los setters de cada variable para obtener los datos del ResultSet, y cada uno con su parametro correspondiente
        datos = new ModeloDatos();
        datos.setId(rs.getInt("id"));
        datos.setNombre(rs.getString("nombre"));
        datos.setEdad(rs.getInt("edad"));
        datos.setSexo(rs.getString("sexo"));
        lista.add(datos); // Se añade el modelo que ya llenamos a nuestro ArrayLi
        }
        // Cerramos el ResultSet, luego el PreparedStatement y la conexión.
        rs.close();
        ps.close();
        con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
        
        }
    return lista; //regresamos la lista 
    }
}
