import java.sql.*;
import java.util.Scanner;

public class queryPack {
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1522:XE";
    static final String USER = "HR";
    static final String PASS = "123";

    public static void main(String[] args) throws SQLException {
        Connection con = null;
        PreparedStatement stmt;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Connect to database");
            con= DriverManager.getConnection(DB_URL,USER,PASS);
            stmt=con.prepareStatement("SELECT P.NAME_PACKAGE,C.NAME_CLIENT,S.TYPE_STATUS,I.SENT_FROM,I.DELIVERED_TO,I.DATE_PACKAGE,R.DATE_REGISTRY \n" +
                    "FROM PACKAGE P\n" +
                    "INNER JOIN REGISTRY R ON P.REGISTRY_ID_REGISTRY=R.ID_REGISTRY\n" +
                    "INNER JOIN CLIENT C ON R.CLIENT_ID_CLIENT=C.ID_CLIENT\n" +
                    "INNER JOIN PACKAGE_SATUS S ON P.PACKAGE_SATUS_ID=S.ID_STATUS_PACKAGE\n" +
                    "INNER JOIN INFO_PACKAGE I ON P.INFO_PACKAGE_ID=I.ID_INFO_PAKAGE\n" +
                    "WHERE P.NAME_PACKAGE LIKE ? \n");
            Scanner sc=new Scanner(System.in);
            System.out.println("input name_pratka:");
            String pack=sc.next();
            stmt.setString(1,pack);
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                System.out.format("%s,%s,%s,%s,%s,%tD,%tD",rs.getString("NAME_PACKAGE"),rs.getString("NAME_CLIENT"),
                        rs.getString("TYPE_STATUS"),rs.getString("SENT_FROM"),rs.getString("DELIVERED_TO"),
                        rs.getDate("DATE_PACKAGE"),rs.getDate("DATE_REGISTRY"));
            }
            rs.close();
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
