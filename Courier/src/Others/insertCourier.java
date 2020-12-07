package Others;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
public class insertCourier {
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
        stmt=con.prepareStatement("insert into COURIER(ID_COURIER,NAME_COURIER,PHONE_COURIER,OFFICE_ID_OFFICE) \n"+
                "values(?, ?, ?, ?) ");
    }
    static void input_couriers() throws SQLException, ClassNotFoundException, ParseException {
        query();
        int id_courier;
        String name_courier;
        String phone_courier;
        int id_office;
        Scanner sc=new Scanner(System.in);
        System.out.println("input id_courier:");
        id_courier=sc.nextInt();
        System.out.println("input name_courier:");
        name_courier=sc.next();
        System.out.println("input phone_courier:");
        phone_courier=sc.next();
        System.out.println("input id_office:");
        id_office=sc.nextInt();
        stmt.setInt(1,id_courier);
        stmt.setString(2,name_courier);
        stmt.setString(3, phone_courier);
        stmt.setInt(4, id_office);
        stmt.execute();
    }
    public static void main(String[] args) throws SQLException {
        try {
            input_couriers();
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