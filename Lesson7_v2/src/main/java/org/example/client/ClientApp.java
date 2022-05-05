package org.example.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.*;

public class ClientApp extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("/resources/Auth-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 152, 195);
        stage.setTitle("Auth");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
