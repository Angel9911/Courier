package Others;

import java.sql.*;

public class queryPack {
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
        String s="Angel";
        stmt=con.prepareStatement("SELECT P.NAME_PACKAGE,T.TYPE_PACKAGE,S.TYPE_STATUS,R.DATE_REGISTRY,I.DATE_PACKAGE,I.SENT_FROM,I.DELIVERED_TO,T.PRICE_PACKAGE \n" +
                "FROM PACKAGE P \n" +
                        "INNER JOIN REGISTRY R ON P.REGISTRY_ID_REGISTRY = R.ID_REGISTRY \n" +
                        "INNER JOIN CLIENT C ON C.ID_CLIENT=R.CLIENT_ID_CLIENT \n"+
                        "INNER JOIN TYPE_PACKAGE T ON P.TYPE_PACKAGE_ID = T.ID_TYPE_PACKAGE \n" +
                        "INNER JOIN PACKAGE_SATUS S ON P.PACKAGE_SATUS_ID = S.ID_STATUS_PACKAGE \n" +
                        "INNER JOIN INFO_PACKAGE I ON P.INFO_PACKAGE_ID = I.ID_INFO_PAKAGE \n"+
                        "WHERE C.NAME_CLIENT = 'Angel'");
    }
    static void print() throws SQLException, ClassNotFoundException {
        query();
        ResultSet rs=stmt.executeQuery();
        while(rs.next())
        {
            System.out.format("%s,%s,%s,%s,%tD,%tD",rs.getString("NAME_PACKAGE"),
                    rs.getString("TYPE_STATUS"),rs.getString("SENT_FROM"),rs.getString("DELIVERED_TO"),
                    rs.getDate("DATE_PACKAGE"),rs.getDate("DATE_REGISTRY") );
        }
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
