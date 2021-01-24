package Courier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CourierDAO {
    public static String EGN;
    public static String insertInfo;
    public static int insertInfoNext;
    public static int insertRegNext;
    public static int insertTransNext;
    private static String getCouriertName(ResultSet rs) throws SQLException,ClassNotFoundException{
        String name = null;
        while(rs.next())
        {
            name=rs.getString("NAME_COURIER");
        }
        return name;
    }
    private static String getCourierEgn(ResultSet rs) throws SQLException
    {
        String name = null;
        while(rs.next())
        {
            name=rs.getString("EGN_COURIER");
        }
        return name;
    }
    private static int getIdInfoNext(ResultSet rs) throws SQLException,ClassNotFoundException{
        int name = 0;
        while(rs.next())
        {
            name=rs.getInt("max_id")+1;
        }
        return name;
    }
    private static int getIdRegistryNext(ResultSet rs) throws SQLException,ClassNotFoundException{
        int name = 0;
        while(rs.next())
        {
            name=rs.getInt("max_id")+1;
        }
        return name;
    }
    private static int getIdTransNext(ResultSet rs) throws SQLException,ClassNotFoundException{
        int name = 0;
        while(rs.next())
        {
            name=rs.getInt("max_id")+1;
        }
        return name;
    }
    private static int getCouriertID(ResultSet rs) throws SQLException,ClassNotFoundException{
        int name = 0;
        while(rs.next())
        {
            name=rs.getInt("ID_COURIER");
        }
        return name;
    }
    private static int getIDName(ResultSet rs) throws SQLException,ClassNotFoundException{
        int name = 0;
        while(rs.next())
        {
            name=rs.getInt("ID_CLIENT");
        }
        return name;
    }
    private static int getIDType(ResultSet rs) throws SQLException,ClassNotFoundException{
        int name = 0;
        while(rs.next())
        {
            name=rs.getInt("ID_TYPE_PACKAGE");
        }
        return name;
    }
    private static int getIDStatus(ResultSet rs) throws SQLException,ClassNotFoundException{
        int name = 0;
        while(rs.next())
        {
            name=rs.getInt("ID_STATUS_PACKAGE");
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
    public static String SelectEgnOfName(String name) throws SQLException,ClassNotFoundException
    {
        String selectNamePack="SELECT C.EGN_COURIER\n" +
                "FROM  COURIER C\n" +
                "WHERE C.NAME_COURIER='"+name+"'";
        try {
            ResultSet rs = DBConn.executeselect(selectNamePack);
            String c= getCourierEgn(rs);
            return c;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    public static int SelectIDOfName(String name_cr)throws SQLException,ClassNotFoundException//ID COURIER
    {
        String selectNamePack="SELECT C.ID_COURIER \n" +
                "FROM  COURIER C \n" +
                "WHERE C.NAME_COURIER='"+name_cr+"'";
        try {
            ResultSet rs = DBConn.executeselect(selectNamePack);
            int c= getCouriertID(rs);
            return c;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    public static int SelectIDClientOfTel(String tel)throws SQLException,ClassNotFoundException// ID NA KLIENT OT TELEFON
    {
        String selectNamePack="SELECT C.ID_CLIENT \n" +
                "FROM  CLIENT C \n" +
                "WHERE C.PHONE_CLIENT='"+tel+"'";
        try {
            ResultSet rs = DBConn.executeselect(selectNamePack);
            int c= getIDName(rs);
            return c;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    public static int SelectIDofType(String type)throws SQLException,ClassNotFoundException//ID NA TYPE PRATKA
    {
        int t=0;
        String selectNamePack="SELECT T.ID_TYPE_PACKAGE\n" +
                "FROM  TYPE_PACKAGE T\n" +
                "WHERE T.TYPE_PACKAGE='"+type+"'";
        try {
            ResultSet rs = DBConn.executeselect(selectNamePack);
            t= getIDType(rs);
            return t;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    public static int SelectIDofStatus(String status)throws SQLException,ClassNotFoundException//ID NA STATUS PRATKA
    {
        int s=0;
        String selectNamePack="SELECT S.ID_STATUS_PACKAGE\n" +
                "FROM  PACKAGE_SATUS S\n" +
                "WHERE S.TYPE_STATUS='"+status+"'";
        try {
            ResultSet rs = DBConn.executeselect(selectNamePack);
             s= getIDStatus(rs);
            return s;
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
    public static void insertPackage(String namePackage, int typepackid, int statusid, int registryid, int infoid,String price_pack)throws SQLException,ClassNotFoundException{
        String insertPack=
                "BEGIN \n"+
                        "insert into PACKAGE(ID_PACKAGE,NAME_PACKAGE,TYPE_PACKAGE_ID,PACKAGE_SATUS_ID,REGISTRY_ID_REGISTRY,INFO_PACKAGE_ID,PRICE) \n" +
                        "values(SEQUENCE_PACKAGE.nextval,'"+namePackage+"','"+typepackid+"','"+statusid+"','"+registryid+"','"+infoid+"','"+price_pack+"'); \n"+
        "END;";
        try {
            DBConn.executeinsert(insertPack);
        }catch (SQLException e)
        {
            System.out.print("Error occurred while INSERT_pack Operation: " + e);
            throw e;
        }
    }
    public static void insertRegistry(int regid,Date dat_reg,int id_client,int id_courier,int trans_id)throws SQLException,ClassNotFoundException
    {
      //  SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlcon=new java.sql.Date(dat_reg.getTime());
        String insertReg=
                "insert into REGISTRY(ID_REGISTRY,DATE_REGISTRY,CLIENT_ID_CLIENT,COURIER_ID_COURIER,TRANSP_PACKAGE_ID) \n"+
                        "values(?,?,?,?,?)";
        try {
            DBConn.executeinsertDateReg(insertReg,regid,id_client,id_courier,trans_id,sqlcon);
        }catch (SQLException e)
        {
            System.out.print("Error occurred while INSERT_Registry Operation: " + e);
            throw e;
        }
    }
    public static int getIdnextInfo() throws SQLException, ClassNotFoundException {
        try {
            String trans = "SELECT MAX(ID_INFO_PAKAGE) AS max_id FROM INFO_PACKAGE";
            ResultSet rs=DBConn.executeselect(trans);
            insertInfoNext=getIdInfoNext(rs);
            return insertInfoNext;
        }catch (SQLException e)
        {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    public static void insertInfo(String send, String deliv,  Date send1) throws SQLException, ClassNotFoundException, ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        int a=insertInfoNext;
        java.sql.Date sqlcon=new java.sql.Date(send1.getTime());
        String insertInfo1="insert into INFO_PACKAGE(ID_INFO_PAKAGE,SENT_FROM,DELIVERED_TO,DATE_PACKAGE) \n" +
                "values(?,?,?,?)";
        try {
            DBConn.executeinsertDate(insertInfo1,a,send,  deliv, sqlcon);
        }catch (SQLException e)
        {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    public static void insertTransp()throws SQLException,ClassNotFoundException
    {
        String insertTrans=
                        "insert into TRANSPORT_PACKAGE(ID_TRANSPORT_PACKAGE) \n"+
                        "values(SEQUENCE_TRANSPORT.nextval)\n";
        try {
            DBConn.executeinsert(insertTrans);
        }catch (SQLException e)
        {
            System.out.print("Error occurred while INSERT_transportID Operation: " + e);
            throw e;
        }
    }
    public static int getIdTrans()throws SQLException, ClassNotFoundException
    {
        try {
            String trans = "SELECT MAX(ID_TRANSPORT_PACKAGE) AS max_id FROM TRANSPORT_PACKAGE";
            ResultSet rs=DBConn.executeselect(trans);
            insertTransNext=getIdTransNext(rs);
            return insertTransNext;
        }catch (SQLException e)
        {
            System.out.print("Error occurred while SELECT_ID_Transport Operation: " + e);
            throw e;
        }
    }
    public static int getIdRegistry()throws SQLException, ClassNotFoundException
    {
        try {
            String trans = "SELECT MAX(ID_REGISTRY) AS max_id FROM REGISTRY";
            ResultSet rs = DBConn.executeselect(trans);
            insertRegNext = getIdRegistryNext(rs);
            return insertRegNext;
        }catch (SQLException e)
        {
            System.out.print("Error occurred while SELECT_ID_REGISTRY Operation: " + e);
            throw e;
        }
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
    public static ObservableList<Courier> searchClient(String egn_cr,String tel_cl)throws SQLException,ClassNotFoundException
    {
        String searchClient="SELECT C.NAME_CLIENT,C.EGN_CLIENT,C.PHONE_CLIENT,R.DATE_REGISTRY,P.NAME_PACKAGE,T.TYPE_PACKAGE,T.PRICE_PACKAGE,P.PRICE,S.TYPE_STATUS,I.SENT_FROM,I.DELIVERED_TO \n" +
                "FROM CLIENT C \n" +
                "INNER JOIN REGISTRY R ON R.CLIENT_ID_CLIENT = C.ID_CLIENT \n" +
                "INNER JOIN COURIER CR ON R.COURIER_ID_COURIER = CR.ID_COURIER\n"+
                "INNER JOIN PACKAGE P ON P.REGISTRY_ID_REGISTRY = R.ID_REGISTRY \n" +
                "INNER JOIN TYPE_PACKAGE T ON P.TYPE_PACKAGE_ID = T.ID_TYPE_PACKAGE \n" +
                "INNER JOIN PACKAGE_SATUS S ON P.PACKAGE_SATUS_ID = S.ID_STATUS_PACKAGE \n" +
                "INNER JOIN INFO_PACKAGE I ON P.INFO_PACKAGE_ID = I.ID_INFO_PAKAGE \n"+
                "WHERE CR.EGN_COURIER='"+egn_cr+"'AND CR.EGN_COURIER='"+tel_cl+"'";
        try{
            ResultSet rs=DBConn.executeselect(searchClient);
            ObservableList<Courier> client=getClientList(rs);
            return client;
        }catch (SQLException e)
        {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
}
