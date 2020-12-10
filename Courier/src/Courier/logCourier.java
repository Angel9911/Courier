package Courier;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class logCourier implements Initializable {
    @FXML
    private Button buttonC;
    @FXML
    private TextField inputField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonC.setOnAction(e->{loadSceneAndSendMessage();});
    }
    private void loadSceneAndSendMessage() {
        if (inputField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter your egn.");
        } else if (!inputField.getText().equals("")) {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/menu_courier.fxml"));
                Parent root = loader.load();
                CourierController scene2Controller = loader.getController();
                scene2Controller.transferStr(inputField.getText());
                scene2Controller.SetNameToLabel(inputField.getText());
                stage.setTitle("AB EXPRESS");
                stage.setScene(new Scene(root, 1287, 801));
                //stage.setResizable(false);
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
}
