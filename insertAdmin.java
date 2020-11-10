import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
public class insertAdmin {
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
            stmt=con.prepareStatement("insert into OFFICE(ID_OFFICE,LOCATION_OFFICE,OPEN_OFFICE,CLOSE_OFFICE) \n"+
                    "values(?, ?, ?, ?) ");
            int id_off;
            String loc_off;
            String open;
            String close;
            Scanner sc=new Scanner(System.in);
            System.out.println("input id:");
            id_off=sc.nextInt();
           System.out.println("input location:");
            loc_off=sc.next();
            System.out.println("input open_office:");
            open=sc.next();
            System.out.println("input close_office:");
            close=sc.next();
            SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm:ss");
            Date dateopen=null;
            Date dateclose=null;
            dateopen=  dateFormat.parse(open);
            dateclose=  dateFormat.parse(close);
            Timestamp ts=new Timestamp(dateopen.getTime());
            Timestamp td=new Timestamp(dateclose.getTime());
            stmt.setInt(1,id_off);
            stmt.setString(2,loc_off);
            stmt.setTimestamp(3, ts);
            stmt.setTimestamp(4, td);
            stmt.execute();
            con.close();
        }catch(Exception se)
        {
            System.out.print("error");
            se.printStackTrace();
        }
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
