package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
    

    // private static String url = "jdbc:postgresql://localhost:5432/jdbc_teste";
    // private static String user = "postgres";
    // private static String password = "8D36$se$";
    private static Connection conn = null;

    public static Connection gConnection(){
        if(conn == null){
            try{
                Properties pt = loadProperties();
                String url = pt.getProperty("dburl");
                conn = DriverManager.getConnection(url, pt);
            }
            catch(SQLException e){
                throw new DbException(e.getMessage());

            }
        }
        return conn;
    }

    public static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties pt = new Properties();
            pt.load(fs);
            return pt;
        }
        catch(IOException e){
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st){
        if(st != null){
            try{
                st.close();
            }
            catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeConnection(){
        if(conn != null){
            try{
                conn.close();
            }
            catch(SQLException e){
                throw new DbException(e.getMessage());
            }
        }
    }
}
