package Courier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Stage mainstage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        mainstage=primaryStage;
       Parent root4 = FXMLLoader.load(getClass().getResource("viewFX/menu_main.fxml"));
        mainstage.setTitle("AB EXPRESS");
        mainstage.setScene(new Scene(root4, 765, 465));
        mainstage.setResizable(false);
        mainstage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
