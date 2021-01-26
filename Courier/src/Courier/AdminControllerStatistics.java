package Courier;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static Courier.Main.mainstage;

public class AdminControllerStatistics implements Initializable {
    @FXML
    private TableView StaticView;
    @FXML
    private TableColumn<Admin, String> name_client ;
    @FXML
    private TableColumn<Admin, String> tel_client ;
    @FXML
    private TableColumn<Admin,String> loc_office;
    @FXML
    private TableColumn<Admin, Integer> Otkazani_pratki ;
    @FXML
    private TableColumn<Admin, Integer> Polucheni_pratki ;
    @FXML
    private TableColumn<Admin, Integer> Nepolucheni_pratki ;
    @FXML
    private TableColumn<Admin, Double> Percent_package ;
    @FXML
    private Button buttonOffice;

    @FXML
    private void ShowCStatic(ObservableList<Admin> crdata)throws SQLException,ClassNotFoundException
    {
        StaticView.setItems(crdata);
    }
    @FXML
    public void selectAllClientsStat()throws SQLException,ClassNotFoundException
    {
        try {
            ObservableList<Admin> adData = AdminDAO.selectClientsStat();
            ShowCStatic(adData);
        }catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name_client.setCellValueFactory(cellData->cellData.getValue().ClientNameProperty());
        tel_client.setCellValueFactory(cellData->cellData.getValue().ClientPhoneProperty());
        loc_office.setCellValueFactory(cellData->cellData.getValue().LocationOfficeProperty());
        Polucheni_pratki.setCellValueFactory(cellData->cellData.getValue().PolucheniBrProperty().asObject());
        Nepolucheni_pratki.setCellValueFactory(cellData->cellData.getValue().NePolucheniBrProperty().asObject());
        Otkazani_pratki.setCellValueFactory(cellData->cellData.getValue().OtkazaniBrProperty().asObject());
        buttonOffice.setOnAction(e->loadSceneOffice());
        Percent_package.setCellValueFactory(cellData->cellData.getValue().PercentProperty().asObject());
    }
    @FXML
    private void loadSceneOffice()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/menu_admin_statOffice.fxml"));
            Parent root = loader.load();
            mainstage.setTitle("AB EXPRESS");
            mainstage.setScene(new Scene(root, 1265, 480));
            mainstage.setResizable(false);
            mainstage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
