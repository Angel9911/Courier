package Courier;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ResourceBundle;

public class AdminControllerOffice implements Initializable {
    @FXML
    private TextField Location;
    @FXML
    private Timestamp open_of;
    @FXML
    private Timestamp close_of;
    @FXML
    private Button insert;
    @FXML
    private Button select;
    @FXML
    private TableView OfficeView;
    @FXML
    private TableColumn<Admin, Integer> id_office ;
    @FXML
    private TableColumn<Admin, String> loc_office ;
    @FXML
    private TableColumn<Admin, Timestamp> open_office ;
    @FXML
    private TableColumn<Admin,Timestamp> close_office;
    @FXML
    private TableColumn<Admin, String> name_courier ;
    @FXML
    private TableColumn<Admin, String> egn_courier ;

    @FXML
    private void ShowOffice(ObservableList<Admin> offData)throws SQLException,ClassNotFoundException
    {
        OfficeView.setItems(offData);
    }
    @FXML
    private void selectAllOffices(ActionEvent actionEvent)throws SQLException,ClassNotFoundException,ParseException
    {
        try {
            ObservableList<Admin> adData = AdminDAO.selectAllOffices();
            ShowOffice(adData);
        }catch (SQLException | ParseException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_office.setCellValueFactory(cellData->cellData.getValue().OfficeIdProperty().asObject());
        loc_office.setCellValueFactory(cellData->cellData.getValue().LocationOfficeProperty());
        open_office.setCellValueFactory(cellData->cellData.getValue().OpenOfficeProperty());
        close_office.setCellValueFactory(cellData->cellData.getValue().CloseOfficeProperty());
        name_courier.setCellValueFactory(cellData->cellData.getValue().NameCourierProperty());
        egn_courier.setCellValueFactory(cellData->cellData.getValue().EgnCourierProperty());
        //type_pack.setCellValueFactory(cellData->cellData.getValue().TypePackProperty());
       // status_pack.setCellValueFactory(cellData->cellData.getValue().StatusPackProperty());
        //reg_pack.setCellValueFactory(cellData->cellData.getValue().RegistryDateProperty());
       // sendDate_pack.setCellValueFactory(cellData->cellData.getValue().SendDateProperty());
    }
}
