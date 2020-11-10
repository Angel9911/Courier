import java.sql.*;
import java.util.Scanner;

public class queryStatus {
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
            stmt=con.prepareStatement("SELECT P.NAME_PACKAGE,S.TYPE_STATUS,I.SENT_FROM,I.DELIVERED_TO\n" +
                    "FROM PACKAGE_SATUS S\n" +
                    "INNER JOIN PACKAGE P ON P.PACKAGE_SATUS_ID=S.ID_STATUS_PACKAGE\n" +
                    "INNER JOIN INFO_PACKAGE I ON P.INFO_PACKAGE_ID=I.ID_INFO_PAKAGE\n" +
                    "WHERE S.TYPE_STATUS = ?");
            Scanner sc=new Scanner(System.in);
            System.out.println("input type_status:");
            String status=sc.next();
            stmt.setString(1,status);
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {
                System.out.format("%s,%s,%s,%s \n",rs.getString("NAME_PACKAGE"),rs.getString("TYPE_STATUS"),
                        rs.getString("SENT_FROM"),rs.getString("DELIVERED_TO"));
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
