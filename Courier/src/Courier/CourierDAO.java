package Courier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourierDAO {
    private static Courier getPackClientFromResultSet(ResultSet rs) throws SQLException,ClassNotFoundException{
        Courier cl=null;
        if(rs.next())
        {
            cl=new Courier();

            cl.setNameClient(rs.getString("NAME_CLIENT"));
            cl.setEgnClient(rs.getString("EGN_CLIENT"));
            cl.setRegistryDate(rs.getDate("DATE_REGISTRY"));
            cl.setNamePack(rs.getString("NAME_PACKAGE"));
            cl.setTypePack(rs.getString("TYPE_PACKAGE"));
            cl.setPricePack(rs.getDouble("PRICE_PACKAGE"));
            cl.setStatus_package(rs.getString("TYPE_STATUS"));
            cl.setSendPack(rs.getString("SENT_FROM"));
            cl.setDeliverPack(rs.getString("DELIVERED_TO"));
            cl.setSendDate(rs.getDate("DATE_PACKAGE"));

        }
        return cl;
    }
    private static ObservableList<Courier> getClientList(ResultSet rs) throws SQLException, ClassNotFoundException
    {
        ObservableList<Courier> clientsList=FXCollections.observableArrayList();
        while(rs.next())
        {
            Courier cl=new Courier();
            cl.setClientId(rs.getInt("ID_CLIENT"));
            cl.setNameClient(rs.getString("NAME_CLIENT"));
            cl.setEgnClient(rs.getString("EGN_CLIENT"));
            cl.setPhoneClient(rs.getString("PHONE_CLIENT"));
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
            cl.setNameClient(rs.getString("NAME_CLIENT"));
            cl.setEgnClient(rs.getString("EGN_CLIENT"));
            cl.setRegistryDate(rs.getDate("DATE_REGISTRY"));
            cl.setNamePack(rs.getString("NAME_PACKAGE"));
            cl.setTypePack(rs.getString("TYPE_PACKAGE"));
            cl.setPricePack(rs.getDouble("PRICE_PACKAGE"));
            cl.setStatus_package(rs.getString("TYPE_STATUS"));
            cl.setSendPack(rs.getString("SENT_FROM"));
            cl.setDeliverPack(rs.getString("DELIVERED_TO"));
            cl.setSendDate(rs.getDate("DATE_PACKAGE"));
            packList.add(cl);
        }
        return packList;
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
    public static Courier searchNamePack(String namepack)throws SQLException,ClassNotFoundException
    {
        String selectall="SELECT C.NAME_CLIENT,C.EGN_CLIENT,R.DATE_REGISTRY,P.NAME_PACKAGE,T.TYPE_PACKAGE,T.PRICE_PACKAGE,S.TYPE_STATUS,I.SENT_FROM,I.DELIVERED_TO,I.DATE_PACKAGE \n" +
                "FROM CLIENT C \n" +
                "INNER JOIN REGISTRY R ON R.CLIENT_ID_CLIENT = C.ID_CLIENT \n" +
                "INNER JOIN PACKAGE P ON P.REGISTRY_ID_REGISTRY = R.ID_REGISTRY \n" +
                "INNER JOIN TYPE_PACKAGE T ON P.TYPE_PACKAGE_ID = T.ID_TYPE_PACKAGE \n" +
                "INNER JOIN PACKAGE_SATUS S ON P.PACKAGE_SATUS_ID = S.ID_STATUS_PACKAGE \n" +
                "INNER JOIN INFO_PACKAGE I ON P.INFO_PACKAGE_ID = I.ID_INFO_PAKAGE \n"+
                "WHERE P.NAME_PACKAGE="+namepack;
        try{
            ResultSet rs=DBConn.executeselect(selectall);
            Courier cln=getPackClientFromResultSet(rs);
            return cln;
        }catch (SQLException e)
        {
            System.out.println("While searching an employee with " + namepack + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
    public static Courier searchStatusPack(String statuspack)throws SQLException,ClassNotFoundException
    {
        String selectstatus="SELECT C.NAME_CLIENT,C.EGN_CLIENT,R.DATE_REGISTRY,P.NAME_PACKAGE,T.TYPE_PACKAGE,T.PRICE_PACKAGE,S.TYPE_STATUS,I.SENT_FROM,I.DELIVERED_TO,I.DATE_PACKAGE \n" +
                "FROM CLIENT C \n" +
                "INNER JOIN REGISTRY R ON R.CLIENT_ID_CLIENT = C.ID_CLIENT \n" +
                "INNER JOIN PACKAGE P ON P.REGISTRY_ID_REGISTRY = R.ID_REGISTRY \n" +
                "INNER JOIN TYPE_PACKAGE T ON P.TYPE_PACKAGE_ID = T.ID_TYPE_PACKAGE \n" +
                "INNER JOIN PACKAGE_SATUS S ON P.PACKAGE_SATUS_ID = S.ID_STATUS_PACKAGE \n" +
                "INNER JOIN INFO_PACKAGE I ON P.INFO_PACKAGE_ID = I.ID_INFO_PAKAGE \n"+
                "WHERE P.NAME_PACKAGE="+statuspack;
        try { ResultSet rs=DBConn.executeselect(selectstatus);
            Courier cln=getPackClientFromResultSet(rs);
            return cln;
        }catch (SQLException e){
            System.out.println("While searching an employee with " + statuspack + " id, an error occurred: " + e);
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
    public static ObservableList<Courier> selectAllPackages()throws SQLException,ClassNotFoundException
    {
        String selectAllPackages="SELECT C.NAME_CLIENT,C.EGN_CLIENT,R.DATE_REGISTRY,P.NAME_PACKAGE,T.TYPE_PACKAGE,T.PRICE_PACKAGE,S.TYPE_STATUS,I.SENT_FROM,I.DELIVERED_TO,I.DATE_PACKAGE \n" +
                "FROM CLIENT C \n" +
                "INNER JOIN REGISTRY R ON R.CLIENT_ID_CLIENT = C.ID_CLIENT \n" +
                "INNER JOIN PACKAGE P ON P.REGISTRY_ID_REGISTRY = R.ID_REGISTRY \n" +
                "INNER JOIN TYPE_PACKAGE T ON P.TYPE_PACKAGE_ID = T.ID_TYPE_PACKAGE \n" +
                "INNER JOIN PACKAGE_SATUS S ON P.PACKAGE_SATUS_ID = S.ID_STATUS_PACKAGE \n" +
                "INNER JOIN INFO_PACKAGE I ON P.INFO_PACKAGE_ID = I.ID_INFO_PAKAGE \n";
        try {
            ResultSet rs=DBConn.executeselect(selectAllPackages);
            ObservableList<Courier> crList=getPackList(rs);//?
            return  crList;
        }catch (SQLException e)
        {
            System.out.println("SQL select operation has been failed: " + e);
            throw e;
        }
    }
}
