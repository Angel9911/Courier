package Courier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ResourceBundle;

import static Courier.Main.mainstage;

public class AdminController implements Initializable {
    @FXML
    private Button butttonOffice;
    @FXML
    private Label namelab;
    @FXML
    private Button buttonCourier;
    @FXML
    private Button buttonExit;
    @FXML
    private Button buttonStat;
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
        buttonStat.setOnAction(e->loadSceneStat());
        namelab.setText("Administrator");
        buttonCourier.setOnAction(e -> loadSceneCourier());
        buttonExit.setOnAction(e-> {
            try {
                loadexitScene();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        location_office.setCellValueFactory(cellData->cellData.getValue().LocationOfficeProperty());
        open_office.setCellValueFactory(cellData->cellData.getValue().OpenOfficeProperty());
        close_office.setCellValueFactory(cellData->cellData.getValue().CloseOfficeProperty());
        name_courier.setCellValueFactory(cellData->cellData.getValue().NameCourierProperty());
        egn_courier.setCellValueFactory(cellData->cellData.getValue().EgnCourierProperty());
        phone_courier.setCellValueFactory(cellData->cellData.getValue().PhoneCourierProperty());
    }
    @FXML
    private void loadSceneAndSendMessage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/menu_admin_office.fxml"));
            Parent root = loader.load();
            AdminControllerOffice scene2Controller = loader.getController();
            mainstage.setTitle("AB EXPRESS");
            mainstage.setScene(new Scene(root, 765, 535));
            mainstage.setResizable(false);
            mainstage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    @FXML
    private void loadSceneCourier() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/menu_admin_courier.fxml"));
            Parent root = loader.load();
            AdminControllerCourier scene2Controller = loader.getController();
            mainstage.setTitle("AB EXPRESS");
            mainstage.setScene(new Scene(root, 765, 520));
            mainstage.setResizable(false);
            mainstage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    @FXML
    private void loadSceneStat()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/menu_admin_statistics.fxml"));
            Parent root = loader.load();
            mainstage.setTitle("AB EXPRESS");
            mainstage.setScene(new Scene(root, 1265, 480));
            mainstage.setResizable(false);
            mainstage.show();
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
    private void loadexitScene() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/login_admin.fxml"));
            Parent root = loader.load();
            Main.mainstage.setTitle("AB EXPRESS");
            Main.mainstage.setScene(new Scene(root, 765, 465));
            Main.mainstage.setResizable(false);
            Main.mainstage.show();
        }catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
