package Courier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CourierController implements Initializable {
    public  String s;
    public String name;
    @FXML
    public Label Ime;
    @FXML
    private Button buttonPackage;
    @FXML
    private TextField nameClient;
    @FXML
    private TextField egnClient;
    @FXML
    private TextField phoneClient;
    @FXML
    private TableView CourierClientView;
    @FXML
    private TableColumn<Courier, String>  name_clientColumn;
    @FXML
    private TableColumn<Courier, String>  egn_clientColumn;
    @FXML
    private TableColumn<Courier, String>  phone_clientColumn;
    @FXML
    private TableColumn<Courier, String>  name_packColumn;
    @FXML
    private TableColumn<Courier, Date>  reg_dateColumn;
    @FXML
    private TableColumn<Courier, String>  status_packColumn;
    @FXML
    private TableColumn<Courier, String>  send_packColumn;
    @FXML
    private TableColumn<Courier, String>  deliver_packColumn;
    @FXML
    private TableColumn<Courier, String> type_packColumn;
    @FXML
    private TableColumn<Courier, Double>  price_TypeColumn;
    @FXML
    private TableColumn<Courier, Double>  price_packColumn;

    public void transferStr(String str)
    {
        //s=String.valueOf(str);
        s=str;
    }
    @FXML
    public void SetNameToLabel(String st)throws ClassNotFoundException, SQLException
    {
        try {String cl=st;
            name=CourierDAO.SelectNameOfEgn(cl);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        Ime.setText(name);
    }
    public String get_name()
    {
        return this.s;
    }
    private void populateCourier (Courier cr) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Courier> crs = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        crs.add(cr);
        //Set items to the employeeTable
        CourierClientView.setItems(crs);
    }
    private void ShowPack(Courier cr) throws ClassNotFoundException {
        if (cr != null) {
            populateCourier(cr);
        } else {

        }
    }
    private void populateAllClients(ObservableList<Courier> pack)throws ClassNotFoundException
    {
        CourierClientView.setItems(pack);
    }
    @FXML
    private void SelectAllClients(ActionEvent actionEvent) throws SQLException,ClassNotFoundException
    {
        try {
            ObservableList<Courier> CLIENTs= CourierDAO.selectClients(s);
            populateAllClients(CLIENTs);
        }catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
   /* private void insert_Client(ActionEvent actionEvent) throws SQLException,ClassNotFoundException {
        try{
            CourierDAO.insertClient(nameClient.getText(),egnClient.getText(),phoneClient.getText());

        }catch(SQLException e){
            throw e;
        }
    }
    private void insert_Package(ActionEvent actionEvent) throws SQLException,ClassNotFoundException{
        try {
            CourierDAO.insertPackage(namePackage.getText(),typeID.getId(),statusID.getId(),regID.getId(),infoID.getId());
        }catch (SQLException e){
            throw e;
        }
    }
    private void insert_Registry()throws SQLException,ClassNotFoundException
    {
        try {
            CourierDAO.insertRegistry(datereg.getText(),idClient.getId(),idCourier.getId(),idTransp.getId());
        }catch (SQLException e)
        {
            throw e;
        }
    }
    private void insert_Info()throws SQLException,ClassNotFoundException
    {
        try {
            CourierDAO.insertInfo(send_from.getText(),deliv_to.getText(),datesend.getText());
        }catch (SQLException e)
        {
            throw e;
        }
    }*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonPackage.setOnAction(e->loadScenePackage());
        name_clientColumn.setCellValueFactory(cellData->cellData.getValue().ClientNameProperty());
        egn_clientColumn.setCellValueFactory(cellData->cellData.getValue().ClientEgnProperty());
        phone_clientColumn.setCellValueFactory(cellData->cellData.getValue().ClientPhoneProperty());
        name_packColumn.setCellValueFactory(cellData->cellData.getValue().NamePackProperty());
        reg_dateColumn.setCellValueFactory(cellData->cellData.getValue().RegistryDateProperty());
        type_packColumn.setCellValueFactory(cellData->cellData.getValue().TypePackProperty());
        price_packColumn.setCellValueFactory(cellData->cellData.getValue().PricePackPropertyPrice().asObject());
        price_TypeColumn.setCellValueFactory(cellData->cellData.getValue().PricePackProperty().asObject());
        status_packColumn.setCellValueFactory(cellData->cellData.getValue().SendPackProperty());
        send_packColumn.setCellValueFactory(cellData->cellData.getValue().SendPackProperty());
        deliver_packColumn.setCellValueFactory(cellData->cellData.getValue().DeliverPackProperty());
    }

    private void loadScenePackage() {
        try {
            Stage stage = new Stage();
            String st=Ime.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/menu_courier_pack.fxml"));
            Parent root = loader.load();
            CourierControllerPack scene2Controller = loader.getController();
            scene2Controller.transferStr(s);
            scene2Controller.SetNameToLabel(s);
            stage.setTitle("AB EXPRESS");
            stage.setScene(new Scene(root, 1181, 689));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
