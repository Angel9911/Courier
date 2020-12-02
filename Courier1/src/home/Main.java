package home;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
<<<<<<< HEAD
{
    //This is our PrimaryStage (It contains everything)
    private Stage primaryStage;
=======
{ Scene scene1;


    @FXML Button btn_client_login=new Button();
>>>>>>> b900884c76f99bba89cf90dcf3a166b1f151fc29

    //This is the BorderPane of RootLayout
    private BorderPane rootLayout;
    @Override
    public void start(Stage primaryStage) throws Exception{
<<<<<<< HEAD
        //1) Declare a primary stage (Everything will be on this stage)
        this.primaryStage = primaryStage;

        //Optional: Set a title for primary stage
        this.primaryStage.setTitle("SW Test Academy - Sample JavaFX App");
        try {
            //First, load EmployeeView from EmployeeView.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("viewFX/menu_client.fxml"));
            rootLayout = (BorderPane) loader.load();

            //Second, show the scene containing the root layout.
            Scene scene = new Scene(rootLayout); //We are sending rootLayout to the Scene.
            primaryStage.setScene(scene); //Set the scene in primary stage.

            /*//Give the controller access to the main.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);*/

            //Third, show the primary stage
            primaryStage.show(); //Display the primary stage
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    /*public void showCientView() {
=======

        Parent root = FXMLLoader.load(getClass().getResource("fxml/Main.fxml"));
        Parent root1 = FXMLLoader.load(getClass().getResource("fxml/login_client.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("fxml/login_admin.fxml"));
        Parent root3 = FXMLLoader.load(getClass().getResource("fxml/login_courier.fxml"));
        Parent root4 = FXMLLoader.load(getClass().getResource("fxml/menu_client.fxml"));
        Parent root5 = FXMLLoader.load(getClass().getResource("fxml/menu_admin.fxml"));
        Parent root6 = FXMLLoader.load(getClass().getResource("fxml/menu_courier.fxml"));
        primaryStage.setTitle("AB EXPRESS");
        primaryStage.setScene(new Scene(root, 765, 465));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
>>>>>>> b900884c76f99bba89cf90dcf3a166b1f151fc29

    }*/
    public static void main(String[] args) {
        launch(args);
    }
}
