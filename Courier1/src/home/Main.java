package home;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{ Scene scene1;


    @FXML Button btn_client_login=new Button();

    @Override
    public void start(Stage primaryStage) throws Exception{

      //  Parent root = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));
        Parent root1 = FXMLLoader.load(getClass().getResource("fxml/login_client.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("fxml/login_admin.fxml"));
        Parent root3 = FXMLLoader.load(getClass().getResource("fxml/login_courier.fxml"));
        Parent root4 = FXMLLoader.load(getClass().getResource("fxml/menu_client.fxml"));
        Parent root5 = FXMLLoader.load(getClass().getResource("fxml/menu_admin.fxml"));
        Parent root6 = FXMLLoader.load(getClass().getResource("fxml/menu_courier.fxml"));
        primaryStage.setTitle("AB EXPRESS");
        primaryStage.setScene(new Scene(root1, 765, 465));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
