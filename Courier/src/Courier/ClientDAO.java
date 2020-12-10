package Courier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
public class ClientDAO {
    public static String Egn;
    private static String getClientName(ResultSet rs) throws SQLException,ClassNotFoundException{
       String name = null;
        while(rs.next())
        {
            name=rs.getString("NAME_CLIENT");
        }
        return name;
    }
    private static ObservableList<Client> getAllPackList(ResultSet rs) throws SQLException,ClassNotFoundException{
        ObservableList<Client> clList = FXCollections.observableArrayList();
        while(rs.next())
        {
            Client cl=new Client();
            cl.setNamePack(rs.getString("NAME_PACKAGE"));
            cl.setTypePack(rs.getString("TYPE_PACKAGE"));
            cl.setStatus_package(rs.getString("TYPE_STATUS"));
            cl.setRegistryDate(rs.getDate("DATE_REGISTRY"));
            cl.setSendDate(rs.getDate("DATE_PACKAGE"));
            cl.setSendPack(rs.getString("SENT_FROM"));
            cl.setDeliverPack(rs.getString("DELIVERED_TO"));
            cl.setPricePack(rs.getDouble("PRICE_PACKAGE"));
            clList.add(cl);
        }
        return clList;
    }
    private static Client getPackOfName(ResultSet rs)throws SQLException,ClassNotFoundException
    {
        Client c=null;
        if(rs.next())
        {
            c=new Client();
            c.setNamePack(rs.getString("NAME_PACKAGE"));
            c.setTypePack(rs.getString("TYPE_PACKAGE"));
            c.setStatus_package(rs.getString("TYPE_STATUS"));
            c.setRegistryDate(rs.getDate("DATE_REGISTRY"));
            c.setSendDate(rs.getDate("DATE_PACKAGE"));
            c.setSendPack(rs.getString("SENT_FROM"));
            c.setDeliverPack(rs.getString("DELIVERED_TO"));
            c.setPricePack(rs.getDouble("PRICE_PACKAGE"));
        }
        return c;
    }
    public static ObservableList<Client> SelectAllPack (String egn_cl) throws SQLException, ClassNotFoundException {
        String selectall="SELECT P.NAME_PACKAGE,T.TYPE_PACKAGE,S.TYPE_STATUS,R.DATE_REGISTRY,I.DATE_PACKAGE,I.SENT_FROM,I.DELIVERED_TO,T.PRICE_PACKAGE \n" +
                "FROM PACKAGE P \n" +
                "INNER JOIN REGISTRY R ON P.REGISTRY_ID_REGISTRY = R.ID_REGISTRY \n" +
                "INNER JOIN CLIENT C ON R.CLIENT_ID_CLIENT=C.ID_CLIENT \n"+
                "INNER JOIN TYPE_PACKAGE T ON P.TYPE_PACKAGE_ID = T.ID_TYPE_PACKAGE \n" +
                "INNER JOIN PACKAGE_SATUS S ON P.PACKAGE_SATUS_ID = S.ID_STATUS_PACKAGE \n" +
                "INNER JOIN INFO_PACKAGE I ON P.INFO_PACKAGE_ID = I.ID_INFO_PAKAGE \n"+
                "WHERE C.EGN_CLIENT='"+egn_cl+"'";
        Egn=egn_cl;
        try {
            ResultSet rs = DBConn.executeselect(selectall);
            ObservableList<Client> c= getAllPackList(rs);
            return c;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }

    }
    public static String SelectNameOfEgn(String egn_cl)throws SQLException,ClassNotFoundException
    {
        String selectNamePack="SELECT C.NAME_CLIENT\n" +
                "FROM  CLIENT C\n" +
                "WHERE C.EGN_CLIENT='"+egn_cl+"'";
        try {
            ResultSet rs = DBConn.executeselect(selectNamePack);
            String c= getClientName(rs);
            return c;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    public static Client searchNamePack(String namepack)throws SQLException,ClassNotFoundException
    {
        String selectNamePack="SELECT P.NAME_PACKAGE,T.TYPE_PACKAGE,S.TYPE_STATUS,R.DATE_REGISTRY,I.DATE_PACKAGE,I.SENT_FROM,I.DELIVERED_TO,T.PRICE_PACKAGE \n" +
                "FROM PACKAGE P \n" +
                "INNER JOIN REGISTRY R ON P.REGISTRY_ID_REGISTRY = R.ID_REGISTRY \n" +
                "INNER JOIN CLIENT C ON R.CLIENT_ID_CLIENT=C.ID_CLIENT \n"+
                "INNER JOIN TYPE_PACKAGE T ON P.TYPE_PACKAGE_ID = T.ID_TYPE_PACKAGE \n" +
                "INNER JOIN PACKAGE_SATUS S ON P.PACKAGE_SATUS_ID = S.ID_STATUS_PACKAGE \n" +
                "INNER JOIN INFO_PACKAGE I ON P.INFO_PACKAGE_ID = I.ID_INFO_PAKAGE \n"+
                "WHERE P.NAME_PACKAGE='"+namepack+"'AND C.EGN_CLIENT='"+Egn+"'";
        try {
            ResultSet rs=DBConn.executeselect(selectNamePack);
            Client cl=ClientDAO.getPackOfName(rs);
            return cl;
        }catch (SQLException e)
        {
            System.out.println("While searching an employee with " + namepack + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
}
