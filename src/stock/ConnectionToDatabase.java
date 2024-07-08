package stock;

import java.sql.*;
import javax.swing.*;

public class ConnectionToDatabase {
    static Connection conn = null;
    
    public static Connection connectToDb(){
        try{
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/stock","root","root");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
