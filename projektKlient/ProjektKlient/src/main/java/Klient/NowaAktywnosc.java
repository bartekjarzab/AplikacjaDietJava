package Klient;

import entities.Activity;
import entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;
import server.ServerConnection;

import java.io.IOException;
import java.sql.Date;

public class NowaAktywnosc {

    @FXML
    private TextField nowaAktywnoscNazwa;

    @FXML
    private TextField nowaAktywnoscKalorie;

    @FXML
    private DatePicker nowaAktywnoscData;


    @FXML
    public void dodajAktywnoscButton(){
        Date data = Date.valueOf(nowaAktywnoscData.getValue());
        JSONObject newActivity= new JSONObject();
        JSONObject aktywnoscData = new JSONObject();
        Activity aktywnosc = new Activity(User.maxID()+1,data,nowaAktywnoscNazwa.getText(),Integer.parseInt(nowaAktywnoscKalorie.getText()));
        newActivity.put("request_code",4);
        aktywnoscData.put("nazwa",aktywnosc.getNazwa());
        aktywnoscData.put("spalone_kalorie", aktywnosc.getKalorie());
        aktywnoscData.put("data",aktywnosc.getData());
        newActivity.put("Aktywnosc",aktywnoscData);
        try{
            ServerConnection.bw.write(newActivity.toString());
            ServerConnection.bw.newLine();
            ServerConnection.bw.flush();

            System.out.println(new JSONObject(ServerConnection.br.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        User.getAktywnosci().add(aktywnosc);
        Stage stage = (Stage)nowaAktywnoscData.getScene().getWindow();
        stage.close();
    }
}
