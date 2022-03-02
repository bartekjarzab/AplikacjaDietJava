package Klient;

import entities.Meal;
import entities.Product;
import entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import server.ServerConnection;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class NowyPosilek {
    @FXML
    private TextField nowyPosilekNazwa;
    @FXML
    private DatePicker datePickerNowyPosilek;
    @FXML
    private TextField nowyProduktNazwa;
    @FXML
    private TextField nowyProduktKalorie;
    @FXML
    private Label nowyPosilekLabel;
    static int id = 0;

    private List<Product> produkty = new ArrayList<>();

    @FXML
    public void nowyPosilek(){
        JSONObject request = new JSONObject();
        JSONObject posilek = new JSONObject();
        JSONArray produktyLista = new JSONArray();
        request.put("request_code",5);
        Date data = Date.valueOf(datePickerNowyPosilek.getValue());
        Meal meal = new Meal(User.mealMaxID(), nowyPosilekNazwa.getText(),data);
        posilek.put("Nazwa",nowyPosilekNazwa.getText());
        posilek.put("Data",data);
        for(Product p: produkty){
            produktyLista.put(p.getId(),p.toJSON());
        }
        posilek.put("Produkty",produktyLista);
        request.put("Posilek",posilek);
        System.out.println(request);
        try{
            ServerConnection.bw.write(request.toString());
            ServerConnection.bw.newLine();
            ServerConnection.bw.flush();
            System.out.println(new JSONObject(ServerConnection.br.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        meal.setListaProduktow(produkty);
        User.getPosilki().add(meal);
        Stage stage = (Stage) nowyPosilekLabel.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void nowyprodukt(){
        int kalorie = Integer.parseInt(nowyProduktKalorie.getText());
        produkty.add(new Product(id,kalorie,nowyProduktNazwa.getText()));
        id++;
        nowyPosilekLabel.setText("Dodano Produkt!");
    }
}
