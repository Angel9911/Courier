package Courier;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
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
   /* public void getStage()
    {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/login_client.fxml"));
            Parent root = loader.load();
            logClient scene2Controller = loader.getController();
            Scene scene=root.getScene();
            stage.setTitle("AB EXPRESS");
            stage.setScene(Scene(root, 765, 465));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }*/
    private void loadSceneAndSendMessage() {
        if (inputField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter your egn.");
            //etStage();g
        } else if (!inputField.getText().equals("")) {
            try {
                String valid = inputField.getText();
                //Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/menu_client.fxml"));
                Parent root = loader.load();
                ClientController scene2Controller = loader.getController();
                scene2Controller.transferStr(inputField.getText());
                scene2Controller.SetNameToLabel(inputField.getText());
                Main.mainstage.setTitle("AB EXPRESS");
                Main.mainstage.setScene(new Scene(root, 765, 465));
                Main.mainstage.setResizable(false);
                Main.mainstage.show();
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