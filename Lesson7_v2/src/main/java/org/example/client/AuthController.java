package org.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AuthController {

    public Label errorLabel;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    public TextField loginField;
    public PasswordField passwordField;

    private boolean authOK;
    private String authString = "";

    public void initialize(){
        authOK = false;
        try {
            socket = new Socket("127.0.0.1", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sumbitButton(ActionEvent actionEvent) throws IOException {
        while (authOK != true){
            try {
                out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
                loginField.clear();
                passwordField.clear();
                Thread.sleep(1000);
                authString = in.readUTF();
                if (authString.startsWith("/authok")){
                    authOK = true;
                } else {
                    errorLabel.setVisible(true);
                    errorLabel.setText(authString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        FXMLLoader loader = new FXMLLoader(AuthController.class.getResource("Client-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.NONE);
        stage.initOwner(this.loginField.getScene().getWindow());
        stage.showAndWait();

    }
}
