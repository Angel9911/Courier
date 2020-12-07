package Courier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.sql.SQLException;

public class CourierController {
    private TextField idClient;
    private TextField idCourier;
    private TextField idTransp;
    private TextField datereg;
    private TextField datesend;
    private TextField nameClient;
    private TextField egnClient;
    private TextField phoneClient;
    private TextField namePackage;
    private TextField typeID;
    private TextField statusID;
    private TextField nameStatus;
    private TextField regID;
    private TextField infoID;
    private TextField send_from;
    private TextField deliv_to;
    private TableView Courierview;
    private TableColumn<Courier, Integer> idClientColumn;
    private TableColumn<Courier, Integer> idPackColumn;
    private TableColumn<Courier, Integer> idTypeColumn;
    private TableColumn<Courier, Integer> idStatusColumn;
    private TableColumn<Courier, Integer> idInfoColumn;
    private TableColumn<Courier, Integer> idRegistyColumn;
    private TableColumn<Courier, String>  name_clientColumn;
    private TableColumn<Courier, String>  egn_clientColumn;
    private TableColumn<Courier, String>  phone_clientColumn;
    private TableColumn<Courier, Date>  reg_dateColumn;
    private TableColumn<Courier, Date>  send_dateColumn;
    private TableColumn<Courier, String>  type_packColumn;
    private TableColumn<Courier, String>  status_packColumn;
    private TableColumn<Courier, Double>  price_packColumn;
    private TableColumn<Courier, String>  name_packColumn;
    private TableColumn<Courier, String>  send_packColumn;
    private TableColumn<Courier, String>  deliver_packColumn;

    public CourierController() {
    }
    private void populateCourier (Courier cr) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Courier> crs = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        crs.add(cr);
        //Set items to the employeeTable
        Courierview.setItems(crs);
    }
    private void ShowPack(Courier cr) throws ClassNotFoundException {
        if (cr != null) {
            populateCourier(cr);
        } else {

        }
    }
    private void populateAllClients(ObservableList<Courier> pack)throws ClassNotFoundException
    {
        Courierview.setItems(pack);
    }
    private void SelectAllClients(ActionEvent actionEvent) throws SQLException,ClassNotFoundException
    {
        try {
            ObservableList<Courier> CLIENTs= CourierDAO.selectAllClients();
            populateAllClients(CLIENTs);
        }catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    private void insert_Client(ActionEvent actionEvent) throws SQLException,ClassNotFoundException {
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
    }
    private void SelectNamePackages(ActionEvent actionEvent) throws SQLException,ClassNotFoundException{
        try {
            Courier cr=CourierDAO.searchNamePack(namePackage.getText());
            ShowPack(cr);
        }catch (SQLException e){
            throw e;
        }
    }
    private void SelectStatusPackages(ActionEvent actionEvent) throws SQLException,ClassNotFoundException{
        try {
            Courier cr=CourierDAO.searchStatusPack(nameStatus.getText());
            ShowPack(cr);
        }catch (SQLException e){
            throw e;
        }
    }
    private void initializeClient()
    {
        idClientColumn.setCellValueFactory(cellData->cellData.getValue().ClientIdProperty().asObject());
        name_clientColumn.setCellValueFactory(cellData->cellData.getValue().ClientNameProperty());
        egn_clientColumn.setCellValueFactory(cellData->cellData.getValue().ClientEgnProperty());
        phone_clientColumn.setCellValueFactory(cellData->cellData.getValue().ClientPhoneProperty());
    }
    private void initializePackage()
    {
        idPackColumn.setCellValueFactory(cellData->cellData.getValue().PackageIdProperty().asObject());
        name_packColumn.setCellValueFactory(cellData->cellData.getValue().NamePackProperty());
        idTypeColumn.setCellValueFactory(cellData->cellData.getValue().TypeIdProperty().asObject());
        idStatusColumn.setCellValueFactory(cellData->cellData.getValue().StatusIdProperty().asObject());
        idRegistyColumn.setCellValueFactory(cellData->cellData.getValue().RegistryIdProperty().asObject());
        idInfoColumn.setCellValueFactory(cellData->cellData.getValue().InfoIdProperty().asObject());
    }
    private void initializeClientPackage()
    {
        name_clientColumn.setCellValueFactory(cellData->cellData.getValue().NamePackProperty());
        egn_clientColumn.setCellValueFactory(cellData->cellData.getValue().ClientEgnProperty());
        reg_dateColumn.setCellValueFactory(cellData->cellData.getValue().RegistryDateProperty());
        name_packColumn.setCellValueFactory(cellData->cellData.getValue().NamePackProperty());
        type_packColumn.setCellValueFactory(cellData->cellData.getValue().TypePackProperty());
        price_packColumn.setCellValueFactory(cellData->cellData.getValue().PricePackProperty().asObject());
        status_packColumn.setCellValueFactory(cellData->cellData.getValue().SendPackProperty());
        send_packColumn.setCellValueFactory(cellData->cellData.getValue().SendPackProperty());
        deliver_packColumn.setCellValueFactory(cellData->cellData.getValue().DeliverPackProperty());

    }
}
