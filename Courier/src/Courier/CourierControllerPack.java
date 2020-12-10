package Courier;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CourierControllerPack implements Initializable {
    public  String s;
    public String name;
    @FXML
    public TextField namePack;
    @FXML
    private Label nameCr;
    @FXML
    public TextField status;//ZA SPRAVKA
    @FXML
    private TextField statuspack;
    @FXML
    private TextField typepack;
    @FXML
    private TextField pricepack;
    @FXML
    private TextField telclient;
    @FXML
    private TextField sendpack;
    @FXML
    private TextField deliverpack;
    @FXML
    private DatePicker datesend;
    @FXML
    private Button selectAllPack;
    @FXML
    private TableView PackView;
    @FXML
    private TableColumn<Courier, String> type_packColumn;
    @FXML
    private TableColumn<Courier, String>  status_packColumn;
    @FXML
    private TableColumn<Courier, Double>  price_packColumn;
    @FXML
    private TableColumn<Courier, String>  name_clientColumn;
    @FXML
    private TableColumn<Courier, String>  send_packColumn;
    @FXML
    private TableColumn<Courier, String>  deliver_packColumn;
    @FXML
    private TableColumn<Courier, Date>  send_dateColumn;
    @FXML
    private TableColumn<Courier, String>  name_packColumn;
    @FXML
    private TableColumn<Courier, Date>  reg_dateColumn;
    public void transferStr(String str)
    {
        //s=String.valueOf(str);
        s=str;
    }
    @FXML
    public void SetNameToLabel(String st)throws ClassNotFoundException, SQLException
    {
        try {
            name=CourierDAO.SelectNameOfEgn(st);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        nameCr.setText(name);
    }
    public static void insertInfo(String send, String deliv,  Date senddate)throws SQLException,ClassNotFoundException {
        String insertInfo =
                        "insert into INFO_PACKAGE(ID_INFO_PAKAGE,SENT_FROM,DELIVERED_TO,DATE_PACKAGE) \n" +
                        "values(SEQUENCE_INFO.nextval,'" + send + "','" + deliv + "','" + senddate + "')\n";
        try {
            DBConn.executeinsert(insertInfo);
        }catch (SQLException e)
        {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    public static void insertTrans()throws SQLException,ClassNotFoundException {
        String insertTrans=
                        "insert into TRANSPORT_PACKAGE(ID_TRANSPORT_PACKAGE) \n"+
                        "values(SEQUENCE_TRANSPORT.nextval)\n";
        try {
            DBConn.executeinsert(insertTrans);
        }catch (SQLException e)
        {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    public static void insertRegistry(String reg,String id_client,String id_courier,String trans_id)throws SQLException,ClassNotFoundException {
        String registryPack =
                        "insert into Registry(ID_REGISTRY,DATE_REGISTRY,CLIENT_ID_CLIENT,COURIER_ID_COURIER,TRANSP_PACKAGE_ID) \n" +
                        "values(SEQUENCE_REGISTRY.nextval,'" + reg + "','" + id_client + "','" + id_courier + "','" + trans_id + "')\n";

        try {
            DBConn.executeinsert(registryPack);
        }catch (SQLException e)
        {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    public static void insertPackage(String name,String idtype,String idstatus,String idreg,String idinfo,String price)throws SQLException,ClassNotFoundException {
        String insertPack =
                        "insert into PACKAGE(ID_PACKAGE,NAME_PACKAGE,TYPE_PACKAGE_ID,PACKAGE_SATUS_ID,REGISTRY_ID_REGISTRY,INFO_PACKAGE_ID,PRICE) \n" +
                        "values(SEQUENCE_PACKAGE.nextval,'" +name + "','" +idtype + "','" + idstatus + "','" + idreg + "','" + idinfo + "','" + price +"') \n";
        try {
            DBConn.executeinsert(insertPack);
        }catch (SQLException e)
        {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
    @FXML
    private void insertPack() throws SQLException, ClassNotFoundException, ParseException {
        String id_status=CourierDAO.SelectIDofStatus(statuspack.getText());
        String id_type=CourierDAO.SelectIDofType(typepack.getText());
        String id_client=CourierDAO.SelectIDClientOfTel(telclient.getText());
        String id_courier=CourierDAO.SelectIDClientOfTel(nameCr.getText());
        String registryid=CourierDAO.getIdRegistry();
        String infoid=CourierDAO.getIdInfo();
        String trans_id=CourierDAO.getIdTrans();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String reg=dtf.format(now);
        String name_pack=namePack.getText();
        String price_pack=pricepack.getText();
       // insertInfo(sendpack.getText(),deliverpack.getText(),datesend.);
        insertTrans();
        insertRegistry(reg,id_client,id_courier,trans_id);
        insertPackage(name_pack,id_type,id_status,registryid,infoid,price_pack);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reg_dateColumn.setCellValueFactory(cellData->cellData.getValue().RegistryDateProperty());
        send_dateColumn.setCellValueFactory(cellData->cellData.getValue().SendDateProperty());
        name_packColumn.setCellValueFactory(cellData->cellData.getValue().NamePackProperty());
        type_packColumn.setCellValueFactory(cellData->cellData.getValue().TypePackProperty());
        price_packColumn.setCellValueFactory(cellData->cellData.getValue().PricePackProperty().asObject());
        status_packColumn.setCellValueFactory(cellData->cellData.getValue().SendPackProperty());
        name_clientColumn.setCellValueFactory(cellData->cellData.getValue().NamePackProperty());
        send_packColumn.setCellValueFactory(cellData->cellData.getValue().SendPackProperty());
        deliver_packColumn.setCellValueFactory(cellData->cellData.getValue().DeliverPackProperty());
    }
    @FXML
    private void ShowPack(ObservableList<Courier> crdata)throws SQLException,ClassNotFoundException
    {
        PackView.setItems(crdata);
    }
    public void selectAllPack(ActionEvent actionEvent) throws SQLException,ClassNotFoundException{
        try {
            ObservableList<Courier> packList=CourierDAO.selectAllPack(s);
            ShowPack(packList);
        }catch (SQLException e)
        {
            throw e;
        }
    }
    public void selectStatusPack(ActionEvent actionEvent) throws SQLException,ClassNotFoundException{
        try {
            ObservableList<Courier> packList=CourierDAO.searchStatusPack(status.getText());
            ShowPack(packList);
        }catch (SQLException e)
        {
            throw e;
        }
    }
}
