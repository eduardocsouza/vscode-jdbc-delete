package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbException;

public class App {
    public static void main(String[] args){

        Connection conn = null;
        PreparedStatement st = null;


        try{
            conn = DB.gConnection();
            st = conn.prepareStatement("DELETE FROM seller "
                + "WHERE name = ?");

            st.setString(1, "Alex Pink");
            st.executeUpdate();
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
            DB.closeConnection();
        }
        //****** Realizando UPDATE ******/

        // try{
        //     conn = DB.gConnection();
        //     st = conn.prepareStatement(
        //         "UPDATE seller SET basesalary = basesalary + ? "
        //         + "WHERE name = ?"
        //     );

        //     st.setDouble(1, 100);
        //     st.setString(2, "Eduardo Souza");
        //     int line = st.executeUpdate();
        //     System.out.println(line);
        
        // }
        // catch(SQLException e){
        //     e.printStackTrace();
        // }
        // finally{
        //     DB.cloStatement(st);
        //     DB.cloConnection();
        // }
    }
}
