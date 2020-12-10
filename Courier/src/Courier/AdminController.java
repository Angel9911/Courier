package Courier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private Button butttonOffice;
    @FXML
    private Button buttonCourier;
    @FXML
    private TextField tel;
    @FXML
    private TableView AdminView;
    @FXML
    private TableColumn<Admin, String> name_courier ;
    @FXML
    private TableColumn<Admin,String> egn_courier;
    @FXML
    private TableColumn<Admin, String> phone_courier ;
    @FXML
    private TableColumn<Admin, String> location_office ;
    @FXML
    private TableColumn<Admin, Timestamp> open_office ;
    @FXML
    private TableColumn<Admin,Timestamp> close_office;
    @FXML
    private void showCouriers(Admin emp) throws ClassNotFoundException {
        if (emp != null) {
            showtableCouriers(emp);
        }
    }


    @FXML
    private void showtableCouriers (Admin emp) throws ClassNotFoundException {
        ObservableList<Admin> adData = FXCollections.observableArrayList();
        adData.add(emp);
        AdminView.setItems(adData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        butttonOffice.setOnAction(e -> loadSceneAndSendMessage());
        buttonCourier.setOnAction(e -> loadSceneCourier());
        location_office.setCellValueFactory(cellData->cellData.getValue().LocationOfficeProperty());
        open_office.setCellValueFactory(cellData->cellData.getValue().OpenOfficeProperty());
        close_office.setCellValueFactory(cellData->cellData.getValue().CloseOfficeProperty());
        name_courier.setCellValueFactory(cellData->cellData.getValue().NameCourierProperty());
        egn_courier.setCellValueFactory(cellData->cellData.getValue().EgnCourierProperty());
        phone_courier.setCellValueFactory(cellData->cellData.getValue().PhoneCourierProperty());
    }

    private void loadSceneAndSendMessage() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/menu_admin_office.fxml"));
            Parent root = loader.load();
            AdminControllerOffice scene2Controller = loader.getController();
            stage.setTitle("AB EXPRESS");
            stage.setScene(new Scene(root, 765, 465));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    private void loadSceneCourier() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/menu_admin_courier.fxml"));
            Parent root = loader.load();
            AdminControllerCourier scene2Controller = loader.getController();
            stage.setTitle("AB EXPRESS");
            stage.setScene(new Scene(root, 765, 465));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void searchCourier(javafx.event.ActionEvent actionEvent) throws ParseException, SQLException, ClassNotFoundException {
        try {
            Admin ad = AdminDAO.selectNameCourier(tel.getText());
            showCouriers(ad);
        }catch (SQLException | ParseException | ClassNotFoundException e)
        {
            throw e;
        }
    }
}
