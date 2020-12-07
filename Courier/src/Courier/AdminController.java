package Courier;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private Button butttonOffice;
    @FXML
    private Button buttonCourier;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        butttonOffice.setOnAction(e -> loadSceneAndSendMessage());
        buttonCourier.setOnAction(e -> loadSceneCourier());
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
}
