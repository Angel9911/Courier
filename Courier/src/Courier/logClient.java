package Courier;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class logClient implements Initializable {

    @FXML
    private Button actionBtn;
    @FXML
    private  TextField inputField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actionBtn.setOnAction(e ->
                loadSceneAndSendMessage());

    }

    private void loadSceneAndSendMessage() {
        try {
            Stage stage=new Stage();
            FXMLLoader loader = new  FXMLLoader(getClass().getResource("viewFX/menu_client.fxml"));
            Parent root = loader.load();
            ClientController scene2Controller = loader.getController();
            scene2Controller.transferStr(inputField.getText());
            scene2Controller.SetNameToLabel(inputField.getText());
            stage.setTitle("AB EXPRESS");
            stage.setScene(new Scene(root, 765, 465));
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