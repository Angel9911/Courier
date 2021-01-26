package Courier;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
public class CourierControllerPack implements Initializable {
   public static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    public  String s;
    public String name;
    @FXML
    public TextField namePack;
    @FXML
    private Button buttonExit;
    @FXML
    private Label nameCr;
    @FXML
    private ComboBox<String> status;//ZA SPRAVKA
    @FXML
    private ComboBox<String> statuspack;
    @FXML
    private ComboBox<String> typepack;
    @FXML
    private TextField pricepack;
    @FXML
    private TextField telclient;
    @FXML
    private TextField sendpack;
    @FXML
    private TextField deliverpack;
    @FXML
    private DatePicker datesend;
    @FXML
    private TableView PackView;
    @FXML
    private TableColumn<Courier, String> type_packColumn;
    @FXML
    private TableColumn<Courier, String>  status_packColumn;
    @FXML
    private TableColumn<Courier, Double>  price_packColumn;
    @FXML
    private TableColumn<Courier, String>  name_clientColumn;
    @FXML
    private TableColumn<Courier, String>  send_packColumn;
    @FXML
    private TableColumn<Courier, String>  deliver_packColumn;
    @FXML
    private TableColumn<Courier, Date>  send_dateColumn;
    @FXML
    private TableColumn<Courier, String>  name_packColumn;
    @FXML
    private TableColumn<Courier, Date>  reg_dateColumn;
    public void transferStr(String str)
    {
        //s=String.valueOf(str);
        s=str;
    }
    @FXML
    public void SetNameToLabel(String st)throws ClassNotFoundException, SQLException
    {
        try {
            name=CourierDAO.SelectNameOfEgn(st);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        nameCr.setText(name);
    }
    @FXML
    private void insertPack() throws SQLException, ClassNotFoundException, ParseException {
        //String telNum=telclient.getText();
        if (telclient.getText().equals("") || pricepack.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter your telephone or price of package.");
        }
        else if(telclient.getText().length()<10 || pricepack.getText().length()<=0) {
            JOptionPane.showMessageDialog(null, "Please enter correct telephone or price of package.");
        }
        else if (!telclient.getText().equals("") || !pricepack.getText().equals("")) {
            int br = 0;
            int br1=0;
            String telNum = telclient.getText();
            String pricePack= pricepack.getText();
            for (int i = 0; i < telNum.length(); i++) {
                if (telNum.charAt(i) >= '0' && telNum.charAt(i) <= '9') {
                    br++;
                }
            }
            for (int i = 0; i < pricePack.length(); i++) {
                if (pricePack.charAt(i) >= '0' && pricePack.charAt(i) <= '9') {
                    br1++;
                }
            }
            if (br == 10 && br1>0) {
                String type = (String) typepack.getValue();
                String status = (String) statuspack.getValue();
                int id_status = CourierDAO.SelectIDofStatus(status);
                int id_type = CourierDAO.SelectIDofType(type);
                int id_client = CourierDAO.SelectIDClientOfTel(telclient.getText());
                int id_courier = CourierDAO.SelectIDOfName(nameCr.getText());
                int id_r = CourierDAO.getIdRegistry();
                int infoid = CourierDAO.getIdnextInfo();
                int id_tr = CourierDAO.getIdTrans();
                LocalDate ld = datesend.getValue();
                Calendar c = Calendar.getInstance();
                c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
                java.util.Date senddat = c.getTime();
                Date regdat = Date.valueOf(LocalDate.now());
                String name_pack = namePack.getText();
                String price_pack = pricepack.getText();
                CourierDAO.insertInfo(sendpack.getText(), deliverpack.getText(), senddat);
                CourierDAO.insertTransp();
                CourierDAO.insertRegistry(id_r, regdat, id_client, id_courier, id_tr);
                CourierDAO.insertPackage(name_pack, id_type, id_status, id_r, infoid, price_pack);
            }
            else{
                JOptionPane.showMessageDialog(null, "The telephone number contains ten numbers.");
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reg_dateColumn.setCellValueFactory(cellData->cellData.getValue().RegistryDateProperty());
        send_dateColumn.setCellValueFactory(cellData->cellData.getValue().SendDateProperty());
        name_packColumn.setCellValueFactory(cellData->cellData.getValue().NamePackProperty());
        type_packColumn.setCellValueFactory(cellData->cellData.getValue().TypePackProperty());
        price_packColumn.setCellValueFactory(cellData->cellData.getValue().PricePackProperty().asObject());
        status_packColumn.setCellValueFactory(cellData->cellData.getValue().StatusPackProperty());
        name_clientColumn.setCellValueFactory(cellData->cellData.getValue().ClientNameProperty());
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
    @FXML
    private void ShowPack(ObservableList<Courier> crdata)throws SQLException,ClassNotFoundException
    {
        PackView.setItems(crdata);
    }
    public void selectAllPack(ActionEvent actionEvent) throws SQLException,ClassNotFoundException{
        try {
            ObservableList<Courier> packList=CourierDAO.selectAllPack(s);
            ShowPack(packList);
        }catch (SQLException e)
        {
            throw e;
        }
    }
    public void selectStatusPack(ActionEvent actionEvent) throws SQLException,ClassNotFoundException{
        String st = (String) status.getValue();
        try {
            ObservableList<Courier> packList=CourierDAO.searchStatusPack(st);
            ShowPack(packList);
        }catch (SQLException e)
        {
            throw e;
        }
    }
    private void loadexitScene() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewFX/menu_courier.fxml"));
            Parent root = loader.load();
            logCourier.controller=loader.getController();
            logCourier.controller.SetName(nameCr.getText());
            logCourier.controller.transferLab(nameCr.getText());
            Main.mainstage.setTitle("AB EXPRESS");
            Main.mainstage.setScene(new Scene(root,1282,674));
            Main.mainstage.setResizable(true);
            Main.mainstage.show();
        }catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
