package Others;

import java.sql.*;

public class queryStatus {
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1522:XE";
    static final String USER = "HR";
    static final String PASS = "123";
    public static Connection con = null;
    public static PreparedStatement stmt;
    static void set_connection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        System.out.println("Connect to database");
        con= DriverManager.getConnection(DB_URL,USER,PASS);
    }
    static void query() throws SQLException, ClassNotFoundException {
        set_connection();
        stmt=con.prepareStatement("SELECT C.NAME_CLIENT\n" +
                "FROM  CLIENT C\n" +
                "WHERE C.EGN_CLIENT= '9204041213' ");
    }
    static void print() throws SQLException, ClassNotFoundException {
        query();
        ResultSet rs=stmt.executeQuery();
        String name = null;
        while(rs.next())
        {
           name=rs.getString("NAME_CLIENT");
        }
        System.out.println("name"+name);
        rs.close();
    }
    public static void main(String[] args) throws SQLException {
        try {
            print();
            stmt.close();
            con.close();
        }catch(Exception se){
            //Handle errors for JDBC
            System.out.print("error");
            se.printStackTrace();
        }//Handle errors for Class.forName
        finally{
            //finally block used to close resources
            try{
                if(con!=null)
                    con.close();
            }catch(SQLException se){
                System.out.print("error1");
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
}
