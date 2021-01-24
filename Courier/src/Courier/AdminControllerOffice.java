package Courier;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static Courier.Main.mainstage;

public class AdminControllerOffice implements Initializable {
    @FXML
    private TextField location_off;
    @FXML
    private ComboBox<String> open;
    @FXML
    private ComboBox<String> close;
    @FXML
    private TableView OfficeView;
    @FXML
    private TableColumn<Admin, Integer> id_office ;
    @FXML
    private TableColumn<Admin, String> loc_office ;
    @FXML
    private TableColumn<Admin, Timestamp> open_office ;
    @FXML
    private TableColumn<Admin,Timestamp> close_office;
    @FXML
    private TableColumn<Admin, String> name_courier ;
    @FXML
    private TableColumn<Admin, String> egn_courier ;

    @FXML
    private void ShowOffice(ObservableList<Admin> offData)throws SQLException,ClassNotFoundException
    {
        OfficeView.setItems(offData);
    }
    @FXML
    private void selectAllOffices(ActionEvent actionEvent)throws SQLException,ClassNotFoundException,ParseException
    {
        try {
            ObservableList<Admin> adData = AdminDAO.selectAllOffices();
            ShowOffice(adData);
        }catch (SQLException | ParseException e){
            System.out.println("Error occurred while getting employees information from DB.\n" + e);
            throw e;
        }
    }
    @FXML
    private void insert_Office() throws SQLException, ClassNotFoundException, ParseException {
        try
        {
            String open_off=(String)open.getValue();
            String close_off=(String)close.getValue();
            SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm:ss");
            Date dateopen=null;
            Date dateclose=null;
            dateopen=  dateFormat.parse(open_off);
            dateclose=  dateFormat.parse(close_off);
            Timestamp ts=new Timestamp(dateopen.getTime());
            Timestamp td=new Timestamp(dateclose.getTime());
            int off=AdminDAO.getIdOfiice();
            AdminDAO.insertOffice(off,location_off.getText(),ts,td);
        }catch (ParseException e){
            throw e;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_office.setCellValueFactory(cellData->cellData.getValue().OfficeIdProperty().asObject());
        loc_office.setCellValueFactory(cellData->cellData.getValue().LocationOfficeProperty());
        open_office.setCellValueFactory(cellData->cellData.getValue().OpenOfficeProperty());
        close_office.setCellValueFactory(cellData->cellData.getValue().CloseOfficeProperty());
        name_courier.setCellValueFactory(cellData->cellData.getValue().NameCourierProperty());
        egn_courier.setCellValueFactory(cellData->cellData.getValue().EgnCourierProperty());
        //type_pack.setCellValueFactory(cellData->cellData.getValue().TypePackProperty());
       // status_pack.setCellValueFactory(cellData->cellData.getValue().StatusPackProperty());
        //reg_pack.setCellValueFactory(cellData->cellData.getValue().RegistryDateProperty());
       // sendDate_pack.setCellValueFactory(cellData->cellData.getValue().SendDateProperty());
    }
    @FXML
    private void loadExitSceneOffice() {
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
