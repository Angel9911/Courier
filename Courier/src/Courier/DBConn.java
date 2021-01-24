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
    public static  PreparedStatement prepStatement;
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
    public static void executeinsertDate(String query,int n,String send,String delic,Date dat)throws SQLException,ClassNotFoundException {
          prepStatement=null;
        try{
            dbConnect();
            prepStatement=con.prepareStatement(query);
            prepStatement.setInt(1,n);
            prepStatement.setString(2,send);
            prepStatement.setString(3,delic);
            prepStatement.setDate(4,dat);
            prepStatement.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (prepStatement != null) {
                //Close statement
                prepStatement.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
    public static void executeinsertOffice(String query,int id_off,String loc, Timestamp op,Timestamp cl)throws SQLException,ClassNotFoundException
    {
        prepStatement=null;
        try {
            dbConnect();
            prepStatement = con.prepareStatement(query);
            prepStatement.setInt(1,id_off);
            prepStatement.setString(2,loc);
            prepStatement.setTimestamp(3,op);
            prepStatement.setTimestamp(4,cl);
            prepStatement.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (prepStatement != null) {
                //Close statement
                prepStatement.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
    public static void executeinsertDateReg(String query,int idReg,int idClient,int idCourier,int idTrans,Date reg)throws SQLException,ClassNotFoundException
    {
        prepStatement=null;
        try {
            dbConnect();
            prepStatement = con.prepareStatement(query);
            prepStatement.setInt(1,idReg);
            prepStatement.setDate(2,reg);
            prepStatement.setInt(3,idClient);
            prepStatement.setInt(4,idCourier);
            prepStatement.setInt(5,idTrans);
            prepStatement.executeUpdate();
        }catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (prepStatement != null) {
                //Close statement
                prepStatement.close();
            }
            //Close connection
            dbDisconnect();
        }
    }
}
