package com.example.brief3_mutuellecentralisee;

import com.example.brief3_mutuellecentralisee.helpers.alertHelper;
import com.example.brief3_mutuellecentralisee.helpers.jsonHelper;
import com.example.brief3_mutuellecentralisee.helpers.validationHelper;
import com.example.brief3_mutuellecentralisee.models.Client;
import com.example.brief3_mutuellecentralisee.models.CountryCode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

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
    @FXML private TableColumn col_cinpass;
    @FXML private TableColumn col_phone;
    @FXML private TableColumn col_email;
    @FXML private TableColumn col_adress;

    @FXML private TableView dataGrid;

    @FXML private TextField nameCompany;
    @FXML private DatePicker dateStartWork;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField cinpass;
    @FXML private RadioButton cin;
    @FXML private RadioButton pass;

    @FXML private ComboBox phoneCode;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private TextArea adress;
    @FXML private ToggleGroup identity;

    private ObservableList<Client> clients;
    private List<CountryCode> countryCodes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
        cin.setSelected(true);

            countryCodes=jsonHelper.GetPhoneCountryCodes();
            for(CountryCode cc : countryCodes) {
                phoneCode.getItems().add(cc.getDial_code());
            }



        col_nameCompany.setCellValueFactory(new PropertyValueFactory<Client, String>("nameCompany"));
        col_dateStartWork.setCellValueFactory(new PropertyValueFactory<Client, String>("dateStartWork"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        col_cinpass.setCellValueFactory(new PropertyValueFactory<Client, String>("cinpass"));
        col_phone.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
        col_email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        col_adress.setCellValueFactory(new PropertyValueFactory<Client, String>("adress"));


        clients= FXCollections.<Client>observableArrayList(); // initialisation d'une liste observable de type clients


        dataGrid.setItems(clients); // definie les elements a afficher (liste clients) dans la tableview
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addClientClick(ActionEvent event) {

        RadioButton selectedRadioButton = (RadioButton) identity.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();



        // Verification des données avant l'ajout du client - erreur en cas de non validité
        if(Validation()) {

            // L'ajout du client
            Client c = new Client(nameCompany.getText(), dateStartWork.getEditor().getText(), firstName.getText(), lastName.getText(), cinpass.getText(), phoneCode.getSelectionModel().getSelectedItem() +"-"+ phone.getText(), email.getText(), adress.getText());
            clients.add(c);

            // Message d'ajout :
            alertHelper.ShowSuccess("OK", "Client ajouter", "Client ajouter avec success");
            // Reset les valeurs des controls apres l'ajout
            nameCompany.setText("");
            dateStartWork.setValue(null);
            firstName.setText("");
            lastName.setText("");
            cinpass.setText("");
            phoneCode.getEditor().setText("");
            phone.setText("");
            email.setText("");
            adress.setText("");
        }

    }

    private boolean Validation() {
        if(!validationHelper.IsValidLength(nameCompany.getText(),50)){
            alertHelper.ShowError("Erreur de validation", "Longueur incorrect.", "champ nameCompany.");
            return  false;
        }

        if(!validationHelper.IsValidLength(firstName.getText(),50)){
            alertHelper.ShowError("Erreur de validation", "Longueur incorrect.", "champ first name.");
            return  false;
        }


        if(!validationHelper.IsValidLength(lastName.getText(),50)){
            alertHelper.ShowError("Erreur de validation", "Longueur incorrect.", "champ last name.");
            return  false;
        }



        if(cin.isSelected()){
            if(!validationHelper.IsValidCIN(cinpass.getText())){
                alertHelper.ShowError("Erreur de validation", "format CIN incorrect.", "example CIN: AA000000");
                return  false;
            }
        }else{
            if(!validationHelper.IsValidPASSPORT(cinpass.getText())){
                alertHelper.ShowError("Erreur de validation", "format PASSPORT incorrect.", "example PASSPORT: AA0000000");
                return  false;
            }
        }

        if(!validationHelper.IsValidPhone(phone.getText())){
            alertHelper.ShowError("Erreur de validation", "format Phone incorrect.", "examples : 600000000 \n 700000000.");
            return  false;
        }

        if(!validationHelper.IsValidEmail(email.getText())){
            alertHelper.ShowError("Erreur de validation", "format Email incorrect.", "example: nomprenom@domaine.com");
            return  false;
        }

        // Champs vide :
       if(!validationHelper.IsNotEmpty(nameCompany.getText())){
           alertHelper.ShowError("Erreur de validation","champ vide","Enter name Comapany ( more than 3 letters )");
           return  false;
       }
        if(!validationHelper.IsNotEmpty(firstName.getText())){
            alertHelper.ShowError("Erreur de validation","champ vide","Enter first name ( more than 3 letters )");
            return  false;
        }
        if(!validationHelper.IsNotEmpty(lastName.getText())){
            alertHelper.ShowError("Erreur de validation","champ vide","Enter last name ( more than 3 letters )");
            return  false;
        }

        if(!validationHelper.IsNotEmpty(adress.getText())){
            alertHelper.ShowError("Erreur de validation","champ vide","Enter your adress  ( more than 3 letters )");
            return  false;
        }

        return true;
    }

}