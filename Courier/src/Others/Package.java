package Others;

import java.sql.*;

public class Package {
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
        stmt=con.prepareStatement("SELECT P.NAME_PACKAGE,S.TYPE_STATUS,I.SENT_FROM,I.DELIVERED_TO\n" +
                "FROM PACKAGE_SATUS S\n" +
                "INNER JOIN PACKAGE P ON P.PACKAGE_SATUS_ID=S.ID_STATUS_PACKAGE\n" +
                "INNER JOIN INFO_PACKAGE I ON P.INFO_PACKAGE_ID=I.ID_INFO_PAKAGE\n");
    }
    static void print() throws SQLException, ClassNotFoundException {
        query();
        ResultSet rs=stmt.executeQuery();
        int brpack=0;
        int brpol=0;
        String s;
        double percent;
        while(rs.next())
        {
            s=rs.getString("TYPE_STATUS");
            if(s.equals("poluchena"))
            {
                brpol++;
            }
            brpack++;
            System.out.format("%s,%s,%s,%s \n",rs.getString("NAME_PACKAGE"),rs.getString("TYPE_STATUS"),
                    rs.getString("SENT_FROM"),rs.getString("DELIVERED_TO"));
        }
        percent=(brpol*100)/brpack;
        System.out.println("brpack: "+brpack+" brpol:"+brpol+"\n");
        System.out.println("percent: "+percent);
        rs.close();
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
