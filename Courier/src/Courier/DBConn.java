package Courier;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DBConn {
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1522:XE";
    static final String USER = "HR";
    static final String PASS = "123";
    public static Connection con;
    public static Statement stmt;

    public static void dbConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Error with Oracle JDBC Driver?");
            e.printStackTrace();
            throw e;
        }
        try {
            con = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            throw e;
        }
    }
    public static void dbDisconnect() throws SQLException {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception e){
            throw e;
        }
    }
    public static ResultSet executeselect(String query) throws SQLException,ClassNotFoundException{
        stmt=null;
        con=null;
        ResultSet rs=null;
        CachedRowSet crs ;
        try{
            dbConnect();
            stmt=con.createStatement();
            rs=stmt.executeQuery(query);
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rs);
        }catch(SQLException e){
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        }finally {
            if (rs != null) {
                //Close resultSet
                rs.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
        return crs;
    }
    public static void executeinsert(String query)throws SQLException,ClassNotFoundException {
        stmt=null;
        try{
            dbConnect();
            stmt=con.createStatement();
            stmt.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
}
