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

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static Courier.Main.mainstage;

public class CourierController implements Initializable {
    public  String s;
    @FXML
    private TextField tel_client;
    public String name;
    @FXML
    public Label Ime;
    @FXML
    private Button buttonExit;
    @FXML
    private Button buttonPackage;
    @FXML
    private TextField nameClient;
    @FXML
    private TextField egnClient;
    @FXML
    private TextField phoneClient;
    @FXML
    private TableView CourierClientView;
    @FXML
    private TableColumn<Courier, String>  name_clientColumn;
    @FXML
    private TableColumn<Courier, String>  egn_clientColumn;
    @FXML
    private TableColumn<Courier, String>  phone_clientColumn;
    @FXML
    private TableColumn<Courier, String>  name_packColumn;
    @FXML
    private TableColumn<Courier, Date>  reg_dateColumn;
    @FXML
    private TableColumn<Courier, String>  status_packColumn;
    @FXML
    private TableColumn<Courier, String>  send_packColumn;
    @FXML
    private TableColumn<Courier, String>  deliver_packColumn;
    @FXML
    private TableColumn<Courier, String> type_packColumn;
    @FXML
    private TableColumn<Courier, Double>  price_TypeColumn;
    @FXML
    private TableColumn<Courier, Double>  price_packColumn;
    public void transferStr(String str)
    {
        //s=String.valueOf(str);
        s=str;
    }
    @FXML
    public void SetNameToLabel(String st)throws ClassNotFoundException, SQLException
    {
        try {String cl=st;
            name=CourierDAO.SelectNameOfEgn(cl);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        Ime.setText(name);
    }
    @FXML
    public void SetName(String st)throws ClassNotFoundException,SQLException
    {
        Ime.setText(st);
    }
    @FXML
    public void transferLab(String st)throws ClassNotFoundException,SQLException
    {
        try{
            String egn=CourierDAO.SelectEgnOfName(st);
            s=egn;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            throw e;
        }
    }
    public String get_name()
    {
        return this.s;
    }
    private void populateCourier (Courier cr) throws ClassNotFoundException {
        ObservableList<Courier> crs = FXCollections.observableArrayList();
        crs.add(cr);
        CourierClientView.setItems(crs);
    }
    private void ShowPack(Courier cr) throws ClassNotFoundException {
        if (cr != null) {
            populateCourier(cr);
        } else {

        }
    }
    private void populateAllClients(ObservableList<Courier> pack)throws ClassNotFoundException
    {
        CourierClientView.setItems(pack);
    }
    @FXML
    private void SelectAllClients(ActionEvent actionEvent) throws SQLException,ClassNotFoundException
    {
        try {
            ObservableList<Courier> CLIENTs= CourierDAO.selectClients(s);
            populateAllClients(CLIENTs);
        }catch (SQLException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    @FXML
    private void SelectClient(ActionEvent actionEvent)throws SQLException,ClassNotFoundException
    {
        try{
            ObservableList<Courier> client=CourierDAO.searchClient(s,tel_client.getText());
            populateAllClients(client);
        }catch(SQLException e)
        {
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    @FXML
    private void insertClient(ActionEvent actionEvent)throws SQLException,ClassNotFoundException
    {
      CourierDAO.insertClient(nameClient.getText(),egnClient.getText(),phoneClient.getText());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonPackage.setOnAction(e->loadScenePackage());
        name_clientColumn.setCellValueFactory(cellData->cellData.getValue().ClientNameProperty());
        egn_clientColumn.setCellValueFactory(cellData->cellData.getValue().ClientEgnProperty());
        phone_clientColumn.setCellValueFactory(cellData->cellData.getValue().ClientPhoneProperty());
        name_packColumn.setCellValueFactory(cellData->cellData.getValue().NamePackProperty());
        reg_dateColumn.setCellValueFactory(cellData->cellData.getValue().RegistryDateProperty());
        type_packColumn.setCellValueFactory(cellData->cellData.getValue().TypePackProperty());
        price_packColumn.setCellValueFactory(cellData->cellData.getValue().PricePackPropertyPrice().asObject());
        price_TypeColumn.setCellValueFactory(cellData->cellData.getValue().PricePackProperty().asObject());
        status_packColumn.setCellValueFactory(cellData->cellData.getValue().SendPackProperty());
        send_packColumn.setCellValueFactory(cellData->cellData.getValue().SendPackProperty());
        deliver_packColumn.setCellValueFactory(cellData->cellData.getValue().DeliverPackProperty());
        buttonExit.setOnAction(e-> {
            try {
                loadexitScene();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    private void loadScenePackage() {
        try {
            String st=Ime.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/menu_courier_pack.fxml"));
            Parent root = loader.load();
            CourierControllerPack scene2Controller = loader.getController();
            scene2Controller.transferStr(s);
            scene2Controller.SetNameToLabel(s);
            mainstage.setTitle("AB EXPRESS");
            //Scene scene=new Scene(root,1181,689);
            mainstage.setScene(new Scene(root,1181,689));
            mainstage.setResizable(false);
            mainstage.show();
        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadexitScene() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/login_courier.fxml"));
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
