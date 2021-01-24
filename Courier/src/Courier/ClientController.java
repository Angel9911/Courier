package Courier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public  String s;
    @FXML
    private Label Ime;
    @FXML
    private Button buttonExit;
    @FXML
    private TextField namepack;
    @FXML
    private TableView ClientView;
    @FXML
    private TableColumn<Client, String> name_pack ;
    @FXML
    private TableColumn<Client, String> type_pack ;
    @FXML
    private TableColumn<Client,String> status_pack;
    @FXML
    private TableColumn<Client, Date> reg_pack ;
    @FXML
    private TableColumn<Client, Date> sendDate_pack ;
    @FXML
    private TableColumn<Client, String> sendfrom_pack ;
    @FXML
    private TableColumn<Client, String> deliverto_pack ;
    @FXML
    private TableColumn<Client, Double> price_pack;

    public void transferStr(String str)
    {
        //s=String.valueOf(str);
        s=str;
    }
    @FXML
    private void ShowClients (ObservableList<Client> empData) throws ClassNotFoundException {
        //Set items to the employeeTable
        ClientView.setItems(empData);
    }
    @FXML
    private void selectAllPack(ActionEvent actionEvent)throws SQLException,ClassNotFoundException
    {
        try {
            String cl=s;
            ObservableList<Client> empData = ClientDAO.SelectAllPack(cl);
            ShowClients(empData);
        }catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    @FXML
    private void ShowCurrentPackage(Client c) throws ClassNotFoundException {
        if (c != null) {
            populatePackage(c);
        }
    }
    @FXML
    private void populatePackage (Client c) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<Client> cData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        cData.add(c);
        //Set items to the employeeTable
        ClientView.setItems(cData);
    }
    @FXML
    private void SelectPackOfName(ActionEvent actionEvent)throws SQLException,ClassNotFoundException {
        int brpack = 0;
        if (namepack.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter name of package.");
        } else  if(!namepack.getText().matches(".*\\p{InCyrillic}.*")){
                JOptionPane.showMessageDialog(null, "Please enter correct name of package.");
        }
        else{
            try {
                String n = namepack.getText();
                Client cdata = ClientDAO.searchNamePack(n);
                ShowCurrentPackage(cdata);
            } catch (SQLException e) {
                System.out.println("Error occurred while getting employees information from DB.\n" + e);
                throw e;
            }
        }
    }
    @FXML
    public void SetNameToLabel(String st)throws ClassNotFoundException, SQLException
    {
        String name=null;
        try {String cl=st;
        name=ClientDAO.SelectNameOfEgn(cl);
    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    }
        Ime.setText(name);
    }
    @FXML
    public void initialize(URL url, ResourceBundle rb)
    {
        name_pack.setCellValueFactory(cellData->cellData.getValue().NamePackProperty());
        type_pack.setCellValueFactory(cellData->cellData.getValue().TypePackProperty());
        status_pack.setCellValueFactory(cellData->cellData.getValue().StatusPackProperty());
        reg_pack.setCellValueFactory(cellData->cellData.getValue().RegistryDateProperty());
        sendDate_pack.setCellValueFactory(cellData->cellData.getValue().SendDateProperty());
        sendfrom_pack.setCellValueFactory(cellData->cellData.getValue().SendPackProperty());
        deliverto_pack.setCellValueFactory(cellData->cellData.getValue().DeliverPackProperty());
        buttonExit.setOnAction(e-> {
            try {
                loadexitScene();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
    private void loadexitScene() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/login_client.fxml"));
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
