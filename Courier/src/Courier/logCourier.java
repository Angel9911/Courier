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

import static Courier.Main.mainstage;

public class logCourier implements Initializable {
    static CourierController controller;
    @FXML
    private Button buttonC;
    @FXML
    private Button actionBtnexit;
    @FXML
    private TextField inputField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonC.setOnAction(e->{loadSceneAndSendMessage();});
        actionBtnexit.setOnAction(e-> {
            try {
                loadexitScene();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
    private void loadSceneAndSendMessage() {
        if (inputField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter your egn.");
        }
        else if(inputField.getText().length()<10) {
            JOptionPane.showMessageDialog(null, "Please enter correct egn.");
        }
        else if (!inputField.getText().equals("")) {
            int br = 0;
            String egn_courier = inputField.getText();
            for (int i = 0; i < egn_courier.length(); i++) {
                if (egn_courier.charAt(i) >= '0' && egn_courier.charAt(i) <= '9') {
                    br++;
                }
            }
            if (br == 10) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/menu_courier.fxml"));
                    Parent root = loader.load();
                    controller=loader.getController();
                    controller.transferStr(inputField.getText());
                    controller.SetNameToLabel(inputField.getText());
                    mainstage.setTitle("AB EXPRESS");
                    mainstage.setScene(new Scene(root, 1282, 674));
                    mainstage.setResizable(true);
                    mainstage.show();
                } catch (IOException ex) {
                    System.err.println(ex);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "The egn contains ten numbers.");
            }
        }
    }
    private void loadexitScene() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/menu_main.fxml"));
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
