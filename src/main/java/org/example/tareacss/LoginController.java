package org.example.tareacss;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    public Label label1;
    @FXML
    public Label label2;
    @FXML
    public Label label3;
    @FXML
    public Label label4;
    @FXML
    public TextField text1;
    @FXML
    public Label label6;
    @FXML
    public Label label7;
    @FXML
    public ComboBox<String> rol;
    @FXML
    public Button Ingreso;
    @FXML
    public Label label9;
    @FXML
    public Label label8;
    @FXML
    public PasswordField text2;


    @FXML
    public void initialize(){
        rol.getItems().addAll("Administrador","Vendedor","Cajero");
    }

    @FXML
    private void Ingresar(){
        String user = text1.getText();
        String contrasenia = text2.getText();
        String rolSelec = rol.getValue();
        if (rolSelec.equals("Administrador")) {
            label8.setText(user + "/" + contrasenia + "/" + rolSelec);
        }else {
            label8.setText("Rol no disponible");
        }
        try {
            if (rolSelec.equals("Administrador")){
                FXMLLoader loader= new FXMLLoader(getClass().getResource("Admi.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                scene.getStylesheets().add(getClass().getResource("/estilos/estilos.css").toExternalForm());

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage actual = (Stage) text1.getScene().getWindow();
                actual.close();
            }
        }catch (Exception e){
            e.printStackTrace();;
        }
    }
}
