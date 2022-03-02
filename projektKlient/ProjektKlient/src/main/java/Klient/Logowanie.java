package Klient;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

import entities.Activity;
import entities.Meal;
import entities.Product;
import entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;
import server.ServerConnection;

public class Logowanie {

    @FXML
    private Button logujButton;
    @FXML
    private Button rejestracjaButton;
    @FXML
    private TextField login;
    @FXML
    private PasswordField haslo;
    @FXML
    private Label logowanieText;


    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("rejestracja");
    }

    @FXML
    private void zaloguj(){
        if(login.getText().isEmpty()){
            System.out.println("Brak Loginu");
            login.setStyle("-fx-background-color: red");
        }else if(haslo.getText().isEmpty()){
            System.out.println("Brak Hasla");
            haslo.setStyle("-fx-background-color: red");
        }
        else{
            JSONObject uzytkownik = new JSONObject();
            JSONObject uzytkownik_dane = new JSONObject();
            JSONObject response;
            JSONObject data;
            uzytkownik_dane.put("username",login.getText());
            uzytkownik_dane.put("password",haslo.getText());
            uzytkownik.put("request_code",1);
            uzytkownik.put("User",uzytkownik_dane);
            try {
                ServerConnection.bw.write(uzytkownik.toString());
                ServerConnection.bw.newLine();
                ServerConnection.bw.flush();
                response = new JSONObject(ServerConnection.br.readLine());
                if(response.getInt("response_code") != 200){
                    logowanieText.setText(response.getString("response_message"));
                }else{
                    data = response.getJSONObject("Data");
                    User.setLogin(data.getString("username"));
                    User.setId(data.getInt("userID"));
                    User.setZapotrzebowanie_kaloryczne(data.getInt("zapotrzebowanie_kaloryczne"));
                    JSONArray lista = data.getJSONArray("aktywnosci");
                    JSONArray posilki = data.getJSONArray("posilki");
                    /* Zapisywanie Aktywnosci do Listy Aktywnosci */
                    if(lista != null) {
                        List<Activity> aktywnosci = new ArrayList<>();
                        for(int i = 0; i < lista.length(); i++){
                            if(lista.optJSONObject(i) != null){
                                JSONObject object = lista.optJSONObject(i);
                                int id = object.getInt("id");
                                Date dataSQL = Date.valueOf(object.getString("Data"));
                                String nazwa = object.getString("Nazwa");
                                int kalorie = object.getInt("Spalone_Kalorie");
                                aktywnosci.add(new Activity(id,dataSQL,nazwa,kalorie));
                            }
                        }
                        User.setAktywnosci(aktywnosci);
                    }


                    /* Zapisywanie Posilkow Do Listy Posilkow */
                    if(posilki != null){
                        List<Meal> posilkiLista = new ArrayList<>();
                        JSONArray Skladniki;
                        System.out.println(posilki.length());
                        for(int i = 0; i < posilki.length(); i++){
                            if(posilki.optJSONObject(i) != null){
                                Meal meal;
                                JSONObject object = posilki.optJSONObject(i);
                                int id = object.getInt("id");
                                Date dataPosilku = Date.valueOf(object.getString("Data"));
                                String nazwa = object.getString("Nazwa");
                                meal = new Meal(id,nazwa,dataPosilku);
                                Skladniki = object.getJSONArray("Skladniki");
                                if(!Skladniki.isEmpty()){
                                    List<Product> listaProduktow = new ArrayList<>();
                                    for(int j = 0; j < Skladniki.length(); j++){
                                        if(Skladniki.optJSONObject(j) != null){
                                            JSONObject obiekt = Skladniki.optJSONObject(j);
                                            int idProduktu = obiekt.getInt("id");
                                            int Kalorie = obiekt.getInt("Kalorie");
                                            String Nazwa = obiekt.getString("Nazwa");
                                            listaProduktow.add(new Product(idProduktu,Kalorie,Nazwa));
                                        }
                                    }
                                    meal.setListaProduktow(listaProduktow);
                                    posilkiLista.add(meal);
                                }else {
                                    meal.setListaProduktow(Collections.emptyList());
                                    posilkiLista.add(meal);
                                }

                            }
                        }
                        User.setPosilki(posilkiLista);
                    }
                    App.setRoot("MainWindow");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
