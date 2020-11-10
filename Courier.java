import java.sql.*;
import java.util.Scanner;
public class Courier {
    static final String JDBC_DRIVER="oracle.jdbc.driver.OracleDriver";
    static final String DB_URL="jdbc:oracle:thin:@localhost:1522:XE";
    static final String USER="HR";
    static final String PASS="123";

    public static void main(String[] args)  throws SQLException{
       Connection con=null;
       PreparedStatement stmt;
        try{
           // String drv="oracle.jdbc.driver.OracleDriver";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Connect to database");
             con= DriverManager.getConnection(DB_URL,USER,PASS);
           stmt=con.prepareStatement("SELECT C.EGN_CLIENT,R.DATE_REGISTRY,P.NAME_PACKAGE,T.TYPE_PACKAGE,T.PRICE_PACKAGE,S.TYPE_STATUS,I.SENT_FROM,I.DELIVERED_TO,I.DATE_PACKAGE \n" +
                    "FROM CLIENT C \n" +
                    "INNER JOIN REGISTRY R ON R.CLIENT_ID_CLIENT = C.ID_CLIENT \n" +
                    "INNER JOIN PACKAGE P ON P.REGISTRY_ID_REGISTRY = R.ID_REGISTRY \n" +
                    "INNER JOIN TYPE_PACKAGE T ON P.TYPE_PACKAGE_ID = T.ID_TYPE_PACKAGE \n" +
                    "INNER JOIN PACKAGE_SATUS S ON P.PACKAGE_SATUS_ID = S.ID_STATUS_PACKAGE \n" +
                    "INNER JOIN INFO_PACKAGE I ON P.INFO_PACKAGE_ID = I.ID_INFO_PAKAGE \n" +
                    "WHERE C.EGN_CLIENT LIKE ? ");
            Scanner sc=new Scanner(System.in);
            System.out.println("input EGN_client:");
            String egn=sc.next();
            stmt.setString(1,egn);
            ResultSet rs=stmt.executeQuery();
            while(rs.next())
            {

               System.out.format(("%s,%tD,%s,%s,%d,%s,%s,%s,%tD "),rs.getString("EGN_CLIENT"),rs.getDate("DATE_REGISTRY"),
                       rs.getString("NAME_PACKAGE"),rs.getString("TYPE_PACKAGE"),rs.getInt("PRICE_PACKAGE"),rs.getString("TYPE_STATUS"),
                       rs.getString("SENT_FROM"),rs.getString("DELIVERED_TO"),rs.getDate("DATE_PACKAGE"));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch(Exception se){
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
    }//end main
}//end JDBCExample
