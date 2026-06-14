package org.example.tareacss;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 380);
        scene.getStylesheets().add(HelloApplication.class.getResource("/estilos/estilos.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }
}
