package com.example.brief3_mutuellecentralisee;

import com.example.brief3_mutuellecentralisee.helpers.alertHelper;
import com.example.brief3_mutuellecentralisee.helpers.fileHelper;
import com.example.brief3_mutuellecentralisee.helpers.jsonHelper;
import com.example.brief3_mutuellecentralisee.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    private Stage parentStage;
    @FXML private TextField username;
    @FXML private TextField password;

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }

    @FXML
    private void loginClick(ActionEvent event)
    {
        try {
            String user=username.getText();
            String pass=password.getText();

            boolean connected=false;

            List<User> users= jsonHelper.GetUsersList();
            for(User u: users){
                if(u.getEmail().equals(user) && u.getPassword().equals(pass)){
                    System.out.println("connecter");
                    connected=true;

                    // Ouvrir la nouvelle fenetre
                    FXMLLoader fxmlLoader = new FXMLLoader(application.class.getResource("home-view.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 700, 500);
                    this.parentStage.setScene(scene);
                    this.parentStage.setTitle("Home");

                }
            }
            if(!connected){
                System.out.println("Erreur Connexion");
                alertHelper.ShowError("Erreur","Erreur de connexion","login ou mot de passe incorrect");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}