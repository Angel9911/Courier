package Courier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourierDAO {
    public static String EGN;
    public static String insertTrans;
    private static String getCouriertName(ResultSet rs) throws SQLException,ClassNotFoundException{
        String name = null;
        while(rs.next())
        {
            name=rs.getString("NAME_COURIER");
        }
        return name;
    }
    private static String getCouriertID(ResultSet rs) throws SQLException,ClassNotFoundException{
        String name = null;
        while(rs.next())
        {
            name=rs.getString("ID_COURIER");
        }
        return name;
    }
    private static String getIDName(ResultSet rs) throws SQLException,ClassNotFoundException{
        String name = null;
        while(rs.next())
        {
            name=rs.getString("ID_CLIENT");
        }
        return name;
    }
    private static String getIDType(ResultSet rs) throws SQLException,ClassNotFoundException{
        String name = null;
        while(rs.next())
        {
            name=rs.getString("ID_TYPE_PACKAGE");
        }
        return name;
    }
    private static String getIDStatus(ResultSet rs) throws SQLException,ClassNotFoundException{
        String name = null;
        while(rs.next())
        {
            name=rs.getString("ID_STATUS_PACKAGE");
        }
        return name;
    }
    private static ObservableList<Courier> getClientList(ResultSet rs) throws SQLException, ClassNotFoundException
    {
        ObservableList<Courier> clientsList=FXCollections.observableArrayList();
        while(rs.next())
        {
            Courier cl=new Courier();
            cl.setNameClient(rs.getString("NAME_CLIENT"));
            cl.setEgnClient(rs.getString("EGN_CLIENT"));
            cl.setPhoneClient(rs.getString("PHONE_CLIENT"));
            cl.setRegistryDate(rs.getDate("DATE_REGISTRY"));
            cl.setNamePack(rs.getString("NAME_PACKAGE"));
            cl.setTypePack(rs.getString("TYPE_PACKAGE"));
            cl.setPricePack(rs.getDouble("PRICE_PACKAGE"));
            cl.setPricePack(rs.getDouble("PRICE"));
            cl.setStatus_package(rs.getString("TYPE_STATUS"));
            cl.setSendPack(rs.getString("SENT_FROM"));
            cl.setDeliverPack(rs.getString("DELIVERED_TO"));
            clientsList.add(cl);
        }
        return clientsList;
    }
    private static ObservableList<Courier> getPackList(ResultSet rs) throws SQLException,ClassNotFoundException
    {
        ObservableList<Courier> packList=FXCollections.observableArrayList();
        while(rs.next())
        {
            Courier cl=new Courier();
            cl.setRegistryDate(rs.getDate("DATE_REGISTRY"));
            cl.setNamePack(rs.getString("NAME_PACKAGE"));
            cl.setTypePack(rs.getString("TYPE_PACKAGE"));
            cl.setPricePack(rs.getDouble("PRICE_PACKAGE"));
            cl.setStatus_package(rs.getString("TYPE_STATUS"));
            cl.setNameClient(rs.getString("NAME_CLIENT"));
            cl.setSendPack(rs.getString("SENT_FROM"));
            cl.setDeliverPack(rs.getString("DELIVERED_TO"));
            cl.setSendDate(rs.getDate("DATE_PACKAGE"));
            packList.add(cl);
        }
        return packList;
    }
    public static String SelectNameOfEgn(String egn_cl)throws SQLException,ClassNotFoundException
    {
        String selectNamePack="SELECT C.NAME_COURIER\n" +
                "FROM  COURIER C\n" +
                "WHERE C.EGN_COURIER='"+egn_cl+"'";
        EGN=egn_cl;
        try {
            ResultSet rs = DBConn.executeselect(selectNamePack);
            String c= getCouriertName(rs);
            return c;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    public static String SelectIDOfName(String name_cr)throws SQLException,ClassNotFoundException//ID COURIER
    {
        String selectNamePack="SELECT C.ID_COURIER\n" +
                "FROM  COURIER C\n" +
                "WHERE C.NAME_COURIER='"+name_cr+"'";
        try {
            ResultSet rs = DBConn.executeselect(selectNamePack);
            String c= getCouriertID(rs);
            return c;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    public static String SelectIDClientOfTel(String tel)throws SQLException,ClassNotFoundException// ID NA KLIENT OT TELEFON
    {
        String selectNamePack="SELECT C.ID_CLIENT\n" +
                "FROM  CLIENT C\n" +
                "WHERE C.PHONE_CLIENT='"+tel+"'";
        try {
            ResultSet rs = DBConn.executeselect(selectNamePack);
            String c= getIDName(rs);
            return c;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    public static String SelectIDofType(String type)throws SQLException,ClassNotFoundException//ID NA TYPE PRATKA
    {
        String selectNamePack="SELECT T.ID_TYPE_PACKAGE\n" +
                "FROM  TYPE_PACKAGE T\n" +
                "WHERE T.TYPE_PACKAGE='"+type+"'";
        try {
            ResultSet rs = DBConn.executeselect(selectNamePack);
            String c= getIDType(rs);
            return c;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    public static String SelectIDofStatus(String status)throws SQLException,ClassNotFoundException//ID NA STATUS PRATKA
    {
        String selectNamePack="SELECT S.ID_STATUS_PACKAGE\n" +
                "FROM  PACKAGE_SATUS S\n" +
                "WHERE S.TYPE_STATUS='"+status+"'";
        try {
            ResultSet rs = DBConn.executeselect(selectNamePack);
            String c= getIDStatus(rs);
            return c;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    public static void insertClient(String nameClient,String egnClient,String phoneClient) throws SQLException,ClassNotFoundException{
        String insertCl=
                "BEGIN \n"+
                "insert into CLIENT(ID_CLIENT,NAME_CLIENT,EGN_CLIENT,PHONE_CLIENT) \n"+
                        "values(sequence_client.nextval,'"+nameClient+"','"+egnClient+"','"+phoneClient+"');\n"+
        "END;";
        try {
            DBConn.executeinsert(insertCl);
        }catch (SQLException e)
        {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    public static void insertPackage(String namePackage, String typepackid, String statusid, String registryid, String infoid)throws SQLException,ClassNotFoundException{
        String insertPack=
                "BEGIN \n"+
                        "insert into PACKAGE(ID_PACKAGE,NAME_PACKAGE,TYPE_PACKAGE_ID,PACKAGE_SATUS_ID,REGISTRY_ID_REGISTRY,INFO_PACKAGE_ID) \n" +
                        "values(sequence_client.nextval,'"+namePackage+"','"+typepackid+"','"+statusid+"','"+registryid+",'"+infoid+"'') \n" +
                        "END;";
        try {
            DBConn.executeinsert(insertPack);
        }catch (SQLException e)
        {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    public static void insertRegistry(String dat_reg,String id_client,String id_courier,String trans_id)throws SQLException,ClassNotFoundException
    {
        String insertReg=
                "BEGIN \n"+
                        "insert into Registry(ID_REGISTRY,DATE_REGISTRY,CLIENT_ID_CLIENT,COURIER_ID_COURIER,TRANSP_PACKAGE_ID) \n"+
                        "values(sequence_client.nextval,'"+dat_reg+"','"+id_client+"','"+id_courier+"','"+trans_id+"'')\n"+
                        "END;";
        try {
            DBConn.executeinsert(insertReg);
        }catch (SQLException e)
        {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    public static void insertInfo(String from,String deliv,String date)throws SQLException,ClassNotFoundException
    {
        String insertInfo=
                "BEGIN \n"+
                        "insert into INFO_PACKAGE(ID_INFO_PAKAGE,SENT_FROM,DELIVERED_TO,DATE_PACKAGE) \n"+
                        "values(sequence_client.nextval,'"+from+"','"+deliv+"','"+date+"')\n"+
                        "END;";
        try {
            DBConn.executeinsert(insertInfo);
        }catch (SQLException e)
        {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    public static void insertTransp()throws SQLException,ClassNotFoundException
    {
        insertTrans=
                "BEGIN \n"+
                        "insert into TRANSPORT_PACKAGE(ID_TRANSPORT_PACKAGE) \n"+
                        "values(SEQUENCE_TRANSPORT.nextval)\n"+
                        "END;";
        try {
            DBConn.executeinsert(insertTrans);
        }catch (SQLException e)
        {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    public static String getIdTrans()
    {
        String trans="SELECT * FROM TRANSPORT_PACKAGE \n"+
        "WHERE ID = (SELECT MAX(ID) FROM TRANSPORT_PACKAGE); \n";
        return trans;
    }
    public static String getIdRegistry()
    {
        String trans="SELECT R.ID_REGISTRY \n"+
                "From REGISTRY R \n"+
                "WHERE ID = (SELECT MAX(ID) FROM REGISTRY); \n";
        return trans;
    }
    public static String getIdInfo()
    {
        String trans="SELECT I.ID_INFO_PAKAGE \n"+
                "From INFO_PACKAGE I \n"+
                "WHERE ID = (SELECT MAX(ID) FROM INFO_PACKAGE); \n";
        return trans;
    }
    public static ObservableList<Courier> searchStatusPack(String statuspack)throws SQLException,ClassNotFoundException
    {
        String selectStatus="SELECT R.DATE_REGISTRY,P.NAME_PACKAGE,T.TYPE_PACKAGE,C.NAME_CLIENT,T.PRICE_PACKAGE,S.TYPE_STATUS,C.NAME_CLIENT,I.SENT_FROM,I.DELIVERED_TO,I.DATE_PACKAGE \n" +
                "FROM CLIENT C \n" +
                "INNER JOIN REGISTRY R ON R.CLIENT_ID_CLIENT = C.ID_CLIENT \n" +
                "INNER JOIN COURIER CR ON R.COURIER_ID_COURIER = CR.ID_COURIER\n"+
                "INNER JOIN PACKAGE P ON P.REGISTRY_ID_REGISTRY = R.ID_REGISTRY \n" +
                "INNER JOIN TYPE_PACKAGE T ON P.TYPE_PACKAGE_ID = T.ID_TYPE_PACKAGE \n" +
                "INNER JOIN PACKAGE_SATUS S ON P.PACKAGE_SATUS_ID = S.ID_STATUS_PACKAGE \n" +
                "INNER JOIN INFO_PACKAGE I ON P.INFO_PACKAGE_ID = I.ID_INFO_PAKAGE \n"+
                "WHERE S.TYPE_STATUS='"+statuspack+"'AND CR.EGN_COURIER='"+EGN+"'";
        try{
            ResultSet rs=DBConn.executeselect(selectStatus);
            ObservableList<Courier> packList=getPackList(rs);
            return packList;
        }catch (SQLException e)
        {
            System.out.println("While searching an employee with " + statuspack + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
    public static ObservableList<Courier> selectAllPack(String egn_cr)throws SQLException,ClassNotFoundException
    {
        String selectstatus="SELECT R.DATE_REGISTRY,P.NAME_PACKAGE,T.TYPE_PACKAGE,C.NAME_CLIENT,T.PRICE_PACKAGE,S.TYPE_STATUS,C.NAME_CLIENT,I.SENT_FROM,I.DELIVERED_TO,I.DATE_PACKAGE \n" +
                "FROM CLIENT C \n" +
                "INNER JOIN REGISTRY R ON R.CLIENT_ID_CLIENT = C.ID_CLIENT \n" +
                "INNER JOIN COURIER CR ON R.COURIER_ID_COURIER = CR.ID_COURIER\n"+
                "INNER JOIN PACKAGE P ON P.REGISTRY_ID_REGISTRY = R.ID_REGISTRY \n" +
                "INNER JOIN TYPE_PACKAGE T ON P.TYPE_PACKAGE_ID = T.ID_TYPE_PACKAGE \n" +
                "INNER JOIN PACKAGE_SATUS S ON P.PACKAGE_SATUS_ID = S.ID_STATUS_PACKAGE \n" +
                "INNER JOIN INFO_PACKAGE I ON P.INFO_PACKAGE_ID = I.ID_INFO_PAKAGE \n"+
                "WHERE CR.EGN_COURIER='"+egn_cr+"'";
        try { ResultSet rs=DBConn.executeselect(selectstatus);
            ObservableList<Courier> packList=getPackList(rs);
            return packList;
        }catch (SQLException e){
            System.out.println("While searching an employee with " + egn_cr + " id, an error occurred: " + e);
            throw e;
        }
    }
    public static ObservableList<Courier> selectAllClients()throws SQLException,ClassNotFoundException
    {
        String selectAllClients="SELECT * FROM CLIENT;";
        try {
            ResultSet rs=DBConn.executeselect(selectAllClients);
            ObservableList<Courier> crList=getClientList(rs);//?
            return  crList;
        }catch (SQLException e)
        {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
    public static ObservableList<Courier> selectClients(String egn_cr)throws SQLException,ClassNotFoundException
    {
        String selectAllPackages="SELECT C.NAME_CLIENT,C.EGN_CLIENT,C.PHONE_CLIENT,R.DATE_REGISTRY,P.NAME_PACKAGE,T.TYPE_PACKAGE,T.PRICE_PACKAGE,P.PRICE,S.TYPE_STATUS,I.SENT_FROM,I.DELIVERED_TO \n" +
                "FROM CLIENT C \n" +
                "INNER JOIN REGISTRY R ON R.CLIENT_ID_CLIENT = C.ID_CLIENT \n" +
                "INNER JOIN COURIER CR ON R.COURIER_ID_COURIER = CR.ID_COURIER\n"+
                "INNER JOIN PACKAGE P ON P.REGISTRY_ID_REGISTRY = R.ID_REGISTRY \n" +
                "INNER JOIN TYPE_PACKAGE T ON P.TYPE_PACKAGE_ID = T.ID_TYPE_PACKAGE \n" +
                "INNER JOIN PACKAGE_SATUS S ON P.PACKAGE_SATUS_ID = S.ID_STATUS_PACKAGE \n" +
                "INNER JOIN INFO_PACKAGE I ON P.INFO_PACKAGE_ID = I.ID_INFO_PAKAGE \n"+
                "WHERE CR.EGN_COURIER='"+egn_cr+"'";
        try {
            ResultSet rs=DBConn.executeselect(selectAllPackages);
            ObservableList<Courier> crList=getClientList(rs);
            return  crList;
        }catch (SQLException e)
        {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
}
