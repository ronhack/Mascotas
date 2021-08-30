package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    //Parametros para la conexion
    private static String bd = "mascotas";
    private static String user = "root";
    private static String pass = "usam2021";
    private static String url = "jdbc:mysql://localhost:3306/"+bd;
    //Libreria de conexion
    Connection conn = null;
    
    public Conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            if(conn != null){
                System.out.println("¡Éxito en la conexión!\n");
            }
        }catch(Exception e){
            System.out.println("Error de conexión: "+e+"\n");
        }
    }
    
    public Connection conectar(){
        return conn;
    }
    
    public void desconectar() throws Exception{
        conn.close();
    }
    
}
