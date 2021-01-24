package Courier;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Courier.Main.mainstage;

public class Menu implements Initializable {
    @FXML
    private Button btnUser;
    @FXML
    private Button btnCourier;
    @FXML
    private Button btnAdmin;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnUser.setOnAction(e -> loadSceneUser());
        btnAdmin.setOnAction(e -> loadSceneAdmin());
        btnCourier.setOnAction(e-> loadSceneCourier());
    }
    @FXML
    private void loadSceneUser() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/login_client.fxml"));
            Parent root = loader.load();
            //logClient scene2Controller = loader.getController();
            mainstage.setTitle("AB EXPRESS");

            mainstage.setScene(new Scene(root, 765, 465));
            mainstage.setResizable(false);
            mainstage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    private void loadSceneCourier() {
        try {
         //   Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/login_courier.fxml"));
            Parent root = loader.load();
            logCourier scene2Controller = loader.getController();
            mainstage.setTitle("AB EXPRESS");
            mainstage.setScene(new Scene(root, 765, 465));
            mainstage.setResizable(false);
            mainstage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    @FXML
    private void loadSceneAdmin() {
        try {
         //   Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/login_admin.fxml"));
            Parent root = loader.load();
            logAdmin scene2Controller = loader.getController();
            mainstage.setTitle("AB EXPRESS");
            mainstage.setScene(new Scene(root, 765, 465));
            mainstage.setResizable(false);
            mainstage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
