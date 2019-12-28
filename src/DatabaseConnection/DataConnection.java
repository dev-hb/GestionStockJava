package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {
    public Connection conn;
    public static DataConnection db;
    private DataConnection(){
        try{
            String url = "jdbc:mysql://localhost:3306/exam?serverTimezone=Africa/Casablanca";
            conn = DriverManager.getConnection(url , "root" ,"");
        }catch(SQLException e){
           e.printStackTrace();
        }
    }
     public static DataConnection getConnection(){
       if ( db == null ) {
            db = new DataConnection();
        }
        return db;
    }
}
