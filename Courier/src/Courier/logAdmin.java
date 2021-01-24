package Courier;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Courier.Main.mainstage;

public class logAdmin implements Initializable {
    public String adminus="Admin";
    public String adminpas="admin101010";
    @FXML
    private Button actionBtn;
    @FXML
    private Button actionBtnexit;
    @FXML
    private TextField adminUser;
    @FXML
    private PasswordField adminpass;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actionBtn.setOnAction(e -> loadSceneAndSendMessage());
        actionBtnexit.setOnAction(e-> {
            try {
                loadexitScene();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    private void loadSceneAndSendMessage() {
        if ((adminUser.getText().equals("")) || (adminpass.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Please enter value your user or pass.");
        } else if ((!adminUser.getText().equals(adminus))||(!adminpass.getText().equals(adminpas))) {
            JOptionPane.showMessageDialog(null, "Please enter correct value your user or pass.");
        }
        else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/menu_admin.fxml"));
                Parent root = loader.load();
                AdminController scene2Controller = loader.getController();
                mainstage.setTitle("AB EXPRESS");
                mainstage.setScene(new Scene(root, 765, 465));
                mainstage.setResizable(false);
                mainstage.show();
            } catch (IOException ex) {
                System.err.println(ex);
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
