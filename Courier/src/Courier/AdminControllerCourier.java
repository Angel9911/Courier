package Courier;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static Courier.Main.mainstage;

public class AdminControllerCourier implements Initializable {
    @FXML
    private TextField name_c;
    @FXML
    private TextField phone_c;
    @FXML
    private TextField id_office;
    @FXML
    private TextField egn_c;
    @FXML
    private TableView CourierView;
    @FXML
    private TableColumn<Admin, Integer> id_courier ;
    @FXML
    private TableColumn<Admin, String> name_courier ;
    @FXML
    private TableColumn<Admin,String> egn_courier;
    @FXML
    private TableColumn<Admin, String> phone_courier ;
    @FXML
    private TableColumn<Admin, String> location_office ;

    @FXML
    private void ShowCourier(ObservableList<Admin> crdata)throws SQLException,ClassNotFoundException
    {
        CourierView.setItems(crdata);
    }
    @FXML
    public void selectAllCouriers()throws SQLException,ClassNotFoundException
    {
        try {
            ObservableList<Admin> adData = AdminDAO.selectAllCouriers();
            ShowCourier(adData);
        }catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    @FXML
    private void insert_couriers()throws SQLException,ClassNotFoundException {
        int br1 = 0;
        int br = 0;
        if (egn_c.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter egn of Courier.");
        } else if (egn_c.getText().length() < 10) {
            JOptionPane.showMessageDialog(null, "Please enter correct egn of Courier.");
        } else if (!egn_c.getText().equals("")) {
            String egn_courier = egn_c.getText();
            for (int i = 0; i < egn_courier.length(); i++) {
                if (egn_courier.charAt(i) >= '0' && egn_courier.charAt(i) <= '9') {
                    br++;
                }
            }
        }
            if (phone_c.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter telephone of Courier.");
            } else if (phone_c.getText().length() < 10) {
                JOptionPane.showMessageDialog(null, "Please enter correct telephone of Courier.");
            } else if (!phone_c.getText().equals("")) {
                String tel_courier = phone_c.getText();
                for (int i = 0; i < tel_courier.length(); i++) {
                    if (tel_courier.charAt(i) >= '0' && tel_courier.charAt(i) <= '9') {
                        br1++;
                    }
                }
            }
              if(!name_c.getText().matches(".*\\p{InCyrillic}.*")){
                JOptionPane.showMessageDialog(null, "Please enter correct name of package.");
            }
                else {
                    if (br == 10 && br1 == 10) {
                        try {
                            AdminDAO.insertCourier(name_c.getText(), phone_c.getText(), id_office.getText(), egn_c.getText());
                        } catch (SQLException e) {
                            throw e;
                        }
                    }
                }
            }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_courier.setCellValueFactory(cellData->cellData.getValue().CourierIdProperty().asObject());
        name_courier.setCellValueFactory(cellData->cellData.getValue().NameCourierProperty());
        egn_courier.setCellValueFactory(cellData->cellData.getValue().EgnCourierProperty());
        phone_courier.setCellValueFactory(cellData->cellData.getValue().PhoneCourierProperty());
        location_office.setCellValueFactory(cellData->cellData.getValue().LocationOfficeProperty());
    }
    @FXML
    private void loadExitSceneAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/menu_admin.fxml"));
            Parent root = loader.load();
            mainstage.setTitle("AB EXPRESS");
            mainstage.setScene(new Scene(root, 765, 465));
            mainstage.setResizable(false);
            mainstage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
