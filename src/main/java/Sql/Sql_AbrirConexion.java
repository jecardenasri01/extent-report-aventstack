package Sql;

import org.junit.Assert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Sql_AbrirConexion {
    public Connection con;
    String driver = "com.mysql.cj.jdbc.Driver";
    String user = "root01";
    String pass = "qwerty123456";
    String url = "jdbc:mysql://localhost:3306/pruebait";

    public Connection ConexionSql() {

        // Reseteamos a null la conexion a la bd
        con = null;
        try {
            Class.forName(driver);
            // Nos conectamos a la bd
            con = (Connection) DriverManager.getConnection(url, user, pass);
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (con != null) {
                System.out.println("funciono parce");
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("fallo");
            System.out.println(e);
            Assert.fail(e.toString());
        }
        return con;
    }
}
