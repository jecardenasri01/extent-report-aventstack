package Sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sql_Acciones {
    public static Connection con;
    public  Sql_Acciones(Connection _con){
        con = _con;
    }

    public ResultSet Select(String consulta) {

        HashMap<String,HashMap> td =new HashMap<String,HashMap>();

        Statement query;
        ResultSet select;
        try {
            query = con.createStatement();
            select  = query.executeQuery(consulta);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return select;
    }


    public void Insert(String insert) {

        Statement query;

        try {
            query = con.createStatement();
            query.executeUpdate(insert);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
