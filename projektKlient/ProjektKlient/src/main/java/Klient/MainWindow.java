package Klient;

import entities.Activity;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.json.JSONObject;
import server.ServerConnection;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    @FXML
    private Label loginLabel;
    @FXML
    private Label zapotrzebowanieLabel;
    @FXML
    private Label spaloneKalorie;
    @FXML
    private Label zjedzoneKalorie;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label bilansLabel;
    @FXML
    private Button dataButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginLabel.setText(User.getLogin());
        zapotrzebowanieLabel.setText(User.getZapotrzebowanie_kaloryczne().toString());

    }

    @FXML
    public void nowaAkt(){
        Scene scena = null;
        try {
             scena = new Scene(App.loadFXML("nowaAktywnosc"),300,300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage nowyAkt = new Stage();
        nowyAkt.setScene(scena);
        nowyAkt.show();
    }

    @FXML
    public void nowyPos(){
        Scene scena = null;
        try {
            scena = new Scene(App.loadFXML("nowyPosilek"),300,300);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage nowyAkt = new Stage();
        nowyAkt.setScene(scena);
        nowyAkt.show();
    }

    @FXML
    public void logout(){
        JSONObject logoutRequest = new JSONObject();
        logoutRequest.put("request_code",2);
        try{
            ServerConnection.bw.write(logoutRequest.toString());
            ServerConnection.bw.newLine();
            ServerConnection.bw.flush();
            System.out.println(new JSONObject(ServerConnection.br.readLine()));
            Stage stage = (Stage) bilansLabel.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void usunAkt() {

        JSONObject removeActivity = new JSONObject();
        removeActivity.put("request_code", 6);
        removeActivity.put("userID", User.getId());
        System.out.println(User.getId());
        User.getAktywnosci().clear();
        try {
            ServerConnection.bw.write(removeActivity.toString());
            ServerConnection.bw.newLine();
            ServerConnection.bw.flush();

            System.out.println(new JSONObject(ServerConnection.br.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void reload(){
        Date data = Date.valueOf(datePicker.getValue());
        int spalone = User.spaloneKalorie(data);
        int zjedzone = User.zjedzoneKalorie(data);
        int bilans = zjedzone - spalone;
        spaloneKalorie.setText(String.valueOf(spalone));
        zjedzoneKalorie.setText(String.valueOf(zjedzone));
        bilansLabel.setText(String.valueOf(bilans));

    }
}
