package Courier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;

public class AdminDAO {
    public static int id_office;
    private static Admin getCourierQuery(ResultSet rs) throws SQLException, ClassNotFoundException, ParseException {
        Admin cr=null;
        if(rs.next())
        {
             cr=new Admin();
            cr.setNameCourier(rs.getString("NAME_COURIER"));
            cr.setEgnCourier(rs.getString("EGN_COURIER"));
            cr.setPhoneCourier(rs.getString("PHONE_COURIER"));
            cr.setLocationOffice(rs.getString("LOCATION_OFFICE"));
            cr.setOpenOffice(rs.getTimestamp("OPEN_OFFICE"));
            cr.setCloseOffice(rs.getTimestamp("CLOSE_OFFICE"));
        }
        return cr;
    }
    private static ObservableList<Admin> getAllCouriers(ResultSet rs)throws SQLException,ClassNotFoundException
    {
        ObservableList<Admin> crList= FXCollections.observableArrayList();
        while(rs.next())
        {
            Admin cr=new Admin();
            cr.setCourierId(rs.getInt("ID_COURIER"));
            cr.setNameCourier(rs.getString("NAME_COURIER"));
            cr.setEgnCourier(rs.getString("EGN_COURIER"));
            cr.setPhoneCourier(rs.getString("PHONE_COURIER"));
            cr.setLocationOffice(rs.getString("LOCATION_OFFICE"));
            crList.add(cr);
        }
        return crList;
    }
    private static ObservableList<Admin> getAllStatOfClients(ResultSet rs) throws SQLException,ClassNotFoundException
    {
        ObservableList<Admin> crList= FXCollections.observableArrayList();
        while(rs.next())
        {
            Admin cl=new Admin();
            cl.setNameClient(rs.getString("NAME_CLIENT"));
            cl.setPhoneClient(rs.getString("PHONE_CLIENT"));
            cl.setLocationOffice(rs.getString("LOCATION_OFFICE"));
            cl.setPolucheniBr(rs.getInt("count_p"));
            cl.setNePolucheniBr(rs.getInt("count_n"));
            cl.setOtkazaniBr(rs.getInt("count_o"));
            cl.setPercent(rs.getDouble("count_per"));
            crList.add(cl);
        }
        return crList;
    }
    private static ObservableList<Admin> getAllStatOffices(ResultSet rs) throws SQLException
    {
        ObservableList<Admin> crList= FXCollections.observableArrayList();
        while(rs.next()) {
            Admin cl=new Admin();
            cl.setLocationOffice(rs.getString("LOCATION_OFFICE"));
            cl.setBrClients(rs.getInt("count_cl"));
            cl.setBrCouriers(rs.getInt("count_cr"));
            cl.setPolucheniBr(rs.getInt("count_p"));
            cl.setNePolucheniBr(rs.getInt("count_n"));
            cl.setOtkazaniBr(rs.getInt("count_o"));
            cl.setPercent(rs.getDouble("count_per"));
            crList.add(cl);
        }
        return crList;
    }
    private static int getIdOfficeNext(ResultSet rs) throws SQLException,ClassNotFoundException{
        int id = 0;
        while(rs.next())
        {
            id=rs.getInt("max_id")+1;
        }
        return id;
    }
    private static ObservableList<Admin> getAllOffice(ResultSet rs) throws SQLException, ClassNotFoundException, ParseException {
        ObservableList<Admin> ofList=FXCollections.observableArrayList();
        while(rs.next())
        {
            Admin cr=new Admin();
            cr.setOfficeId(rs.getInt("ID_OFFICE"));
            cr.setLocationOffice(rs.getString("LOCATION_OFFICE"));
            cr.setOpenOffice(rs.getTimestamp("OPEN_OFFICE"));
            cr.setCloseOffice(rs.getTimestamp("CLOSE_OFFICE"));
            cr.setNameCourier(rs.getString("NAME_COURIER"));
            cr.setEgnCourier(rs.getString("EGN_COURIER"));
            ofList.add(cr);
        }
        return ofList;
    }
    public static int getIdOfiice()throws SQLException, ClassNotFoundException
    {
        try {
            String trans = "SELECT MAX(ID_OFFICE) AS max_id FROM OFFICE";
            ResultSet rs=DBConn.executeselect(trans);
            id_office=getIdOfficeNext(rs);
            return id_office;
        }catch (SQLException e)
        {
            System.out.print("Error occurred while SELECT_ID_Transport Operation: " + e);
            throw e;
        }
    }
    public static void insertOffice(int off,String location, Timestamp open,Timestamp close)throws SQLException,ClassNotFoundException
    {
        String insertO=
                        "insert into OFFICE(ID_OFFICE,LOCATION_OFFICE,OPEN_OFFICE,CLOSE_OFFICE) \n"+
                        "values(?,?,?,?)\n";
        try {
            DBConn.executeinsertOffice(insertO,off,location,open,close);
        }catch (SQLException e){
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    public static void insertCourier(String name_c,String phone,String id_office,String egn)throws SQLException,ClassNotFoundException
    {
        String insertC=
                        "insert into COURIER(ID_COURIER,NAME_COURIER,PHONE_COURIER,OFFICE_ID_OFFICE,EGN_COURIER)" +
                        "values(SEQUENCE_CR.nextval,'"+name_c+"','"+phone+"','"+id_office+"','"+egn+"')\n";
        try {
            DBConn.executeinsert(insertC);
        }catch (SQLException e){
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    public static ObservableList<Admin> selectAllCouriers()throws SQLException,ClassNotFoundException
    {
        String selectallCourier="SELECT C.ID_COURIER,C.NAME_COURIER,C.EGN_COURIER,C.PHONE_COURIER,O.LOCATION_OFFICE \n"+
                "FROM COURIER C \n"+
                "INNER JOIN OFFICE O ON C.OFFICE_ID_OFFICE = O.ID_OFFICE \n";
        try {
            ResultSet rs=DBConn.executeselect(selectallCourier);
            ObservableList<Admin> crList=getAllCouriers(rs);
            return  crList;
        }catch (SQLException e)
        {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
    public static ObservableList<Admin> selectClientsStat()throws SQLException,ClassNotFoundException
    {
        String selectallCourier="SELECT C.NAME_CLIENT,C.PHONE_CLIENT,O.LOCATION_OFFICE,COUNT(CASE WHEN s.type_status='получена' THEN 1 END) as count_p,COUNT(CASE WHEN s.type_status='неполучена' THEN 1 END) as count_n,COUNT(CASE WHEN s.type_status='отказана' THEN 1 END) as count_o,(COUNT(CASE WHEN s.type_status='получена' THEN 1 END)*100.0)/(COUNT(CASE WHEN s.type_status='отказана' THEN 1 END)+COUNT(CASE WHEN s.type_status='неполучена' THEN 1 END)+COUNT(CASE WHEN s.type_status='получена' THEN 1 END)) as count_per \n"+
                "FROM CLIENT C \n"+
                "INNER JOIN REGISTRY R ON R.CLIENT_ID_CLIENT = C.ID_CLIENT \n"+
                "INNER JOIN PACKAGE P ON P.REGISTRY_ID_REGISTRY = R.ID_REGISTRY \n"+
                "INNER JOIN PACKAGE_SATUS S ON P.PACKAGE_SATUS_ID = S.ID_STATUS_PACKAGE \n"+
                "INNER JOIN COURIER CR ON R.COURIER_ID_COURIER = CR.ID_COURIER \n"+
                "INNER JOIN OFFICE O ON CR.OFFICE_ID_OFFICE = O.ID_OFFICE \n"+
                "GROUP BY C.NAME_CLIENT, C.PHONE_CLIENT, O.LOCATION_OFFICE  \n";
        try {
            ResultSet rs=DBConn.executeselect(selectallCourier);
            ObservableList<Admin> crList=getAllStatOfClients(rs);
            return  crList;
        }catch (SQLException e)
        {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
    public static ObservableList<Admin> SelectStatOffice()throws SQLException,ClassNotFoundException
    {
        String selectallOffices="SELECT O.LOCATION_OFFICE,(SELECT COUNT(*)FROM   COURIER) AS count_cr,(SELECT COUNT(*)FROM   CLIENT) AS count_cl,COUNT(CASE WHEN s.type_status='получена' THEN 1 END) as count_p,COUNT(CASE WHEN s.type_status='неполучена' THEN 1 END) as count_n,COUNT(CASE WHEN s.type_status='отказана' THEN 1 END) as count_o,(COUNT(CASE WHEN s.type_status='получена' THEN 1 END)*100.0)/(COUNT(CASE WHEN s.type_status='отказана' THEN 1 END)+COUNT(CASE WHEN s.type_status='неполучена' THEN 1 END)+COUNT(CASE WHEN s.type_status='получена' THEN 1 END)) as count_per \n"+
                "FROM OFFICE O \n"+
                "INNER JOIN COURIER CR ON CR.OFFICE_ID_OFFICE = O.ID_OFFICE \n"+
                "INNER JOIN REGISTRY R ON R.COURIER_ID_COURIER=CR.ID_COURIER \n"+
                "INNER JOIN PACKAGE P ON P.REGISTRY_ID_REGISTRY = R.ID_REGISTRY \n"+
                "INNER JOIN PACKAGE_SATUS S ON P.PACKAGE_SATUS_ID = S.ID_STATUS_PACKAGE \n"+
                "INNER JOIN CLIENT C ON R.CLIENT_ID_CLIENT=C.ID_CLIENT \n"+
                "GROUP BY O.LOCATION_OFFICE \n";
        try {
            ResultSet rs=DBConn.executeselect(selectallOffices);
            ObservableList<Admin> crList=getAllStatOffices(rs);
            return  crList;
        }catch (SQLException e)
        {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
    public static Admin selectNameCourier(String phone) throws SQLException, ClassNotFoundException, ParseException {
        String namecourier=
                "SELECT C.NAME_COURIER,C.EGN_COURIER,C.PHONE_COURIER,O.LOCATION_OFFICE,O.OPEN_OFFICE,O.CLOSE_OFFICE \n"+
                        "FROM COURIER C \n"+
                        "INNER JOIN OFFICE O ON C.OFFICE_ID_OFFICE = O.ID_OFFICE \n"+
                        "WHERE C.PHONE_COURIER="+phone;
        try{
            ResultSet rsEmp = DBConn.executeselect(namecourier);

            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Admin courier = getCourierQuery(rsEmp);

            //Return employee object
            return courier;
        }catch (SQLException | ParseException e)
        {
            System.out.println("While searching an employee with " + phone + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
    public static ObservableList<Admin> selectAllOffices() throws SQLException, ClassNotFoundException, ParseException {
        String selectalloffices="SELECT O.ID_OFFICE,O.LOCATION_OFFICE,O.OPEN_OFFICE,O.CLOSE_OFFICE,O.LOCATION_OFFICE,C.NAME_COURIER,C.EGN_COURIER \n"+
                "FROM OFFICE O \n"+
                "INNER JOIN COURIER C ON C.OFFICE_ID_OFFICE = O.ID_OFFICE \n";
        try {
            ResultSet rs=DBConn.executeselect(selectalloffices);
            ObservableList<Admin> ofList=getAllOffice(rs);
            return  ofList;
        }catch (SQLException | ParseException e)
        {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
}
