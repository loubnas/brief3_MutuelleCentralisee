package com.example.brief3_mutuellecentralisee;

import com.example.brief3_mutuellecentralisee.helpers.alertHelper;
import com.example.brief3_mutuellecentralisee.helpers.jsonHelper;
import com.example.brief3_mutuellecentralisee.models.Client;
import com.example.brief3_mutuellecentralisee.models.CountryCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class homeController implements Initializable {

    @FXML private TableColumn col_nameCompany;
    @FXML private TableColumn col_dateStartWork;
    @FXML private TableColumn col_firstName;
    @FXML private TableColumn col_lastName;
    @FXML private TableColumn col_cin;
    @FXML private TableColumn col_phone;
    @FXML private TableColumn col_email;
    @FXML private TableColumn col_adress;

    @FXML private TableView dataGrid;

    @FXML private TextField nameCompany;
    @FXML private DatePicker dateStartWork;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    //@FXML private TextField cin;
    @FXML private MenuButton phone;
    @FXML private TextField email;
    @FXML private TextArea adress;

    private ObservableList<Client> clients;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        col_nameCompany.setCellValueFactory(new PropertyValueFactory<Client, String>("nameCompany"));
        col_dateStartWork.setCellValueFactory(new PropertyValueFactory<Client, String>("dateStartWork"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        col_cin.setCellValueFactory(new PropertyValueFactory<Client, String>("cin"));
        col_phone.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
        col_email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        col_adress.setCellValueFactory(new PropertyValueFactory<Client, String>("adress"));


        clients= FXCollections.<Client>observableArrayList(); // initialisation d'une liste observable de type clients


        dataGrid.setItems(clients); // definie les elements a afficher (liste clients) dans la tableview
    }

    @FXML
    private void addClientClick(ActionEvent event) {

        // Verification des données avant l'ajout du client - erreur en cas de non validité


        // L'ajout du client
        Client c=new Client(nameCompany.getText(),"",firstName.getText(),lastName.getText(),"",phone.getText(),email.getText(),adress.getText());
        clients.add(c);

        // Message d'ajout :
        alertHelper.ShowSuccess("OK","Client ajouter","");
        // Reset les valeurs des controls apres l'ajout
        nameCompany.setText("");
        dateStartWork.setValue(null);
        firstName.setText("");
        lastName.setText("");
        //cin.setText("");
        phone.setText("");
        email.setText("");
        adress.setText("");


    }

}