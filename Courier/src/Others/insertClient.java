package Others;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class insertClient {
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
        stmt=con.prepareStatement("insert into CLIENT(ID_CLIENT,NAME_CLIENT,EGN_CLIENT,PHONE_CLIENT) \n"+
                "values(?, ?, ?, ?) ");
    }
    static void input_clients() throws SQLException, ClassNotFoundException, ParseException {
        query();
        int id_client;
        String name_client;
        String egn;
        String phone_client;
        Scanner sc=new Scanner(System.in);
        System.out.println("input id_client:");
        id_client=sc.nextInt();
        System.out.println("input name_client:");
        name_client=sc.next();
        System.out.println("input egn_client:");
        egn=sc.next();
        System.out.println("input phone_client:");
        phone_client=sc.next();
        stmt.setInt(1,id_client);
        stmt.setString(2,name_client);
        stmt.setString(3, egn);
        stmt.setString(4, phone_client);
        stmt.execute();
    }
    public static void main(String[] args) throws SQLException {
        try {
            input_clients();
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