package Klient;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.JSONObject;
import server.ServerConnection;

public class Rejestracja {
    @FXML
    private TextField login;
    @FXML
    private TextField zapotrzebowanie;
    @FXML
    private PasswordField haslo;
    @FXML
    private Label rejestracjaLabel;
    @FXML
    private Button anuluj;

    @FXML
    private void anuluj() throws IOException {
        App.setRoot("logowanie");
    }

    @FXML
    private void rejestruj(){
        JSONObject request = new JSONObject();
        JSONObject user = new JSONObject();
        request.put("request_code",3);
        if(login.getText().isEmpty()){
            login.setStyle("-fx-background-color: red");
        }else if(zapotrzebowanie.getText().isEmpty()){
            zapotrzebowanie.setStyle("-fx-background-color: red");
        }else if(haslo.getText().isEmpty()){
            haslo.setStyle("-fx-background-color: red");
        }else{
            int zapotrzebowanieKaloryczne = Integer.parseInt(zapotrzebowanie.getText());
            user.put("username",login.getText());
            user.put("password",haslo.getText());
            user.put("calories_needed",zapotrzebowanieKaloryczne);
            request.put("User",user);
            try {
                ServerConnection.bw.write(request.toString());
                ServerConnection.bw.newLine();
                ServerConnection.bw.flush();
                JSONObject response = new JSONObject(ServerConnection.br.readLine());
                System.out.println(response);
                if(response.getInt("response_code") != 200){
                    rejestracjaLabel.setText(response.getString("response_message"));
                }else{
                    rejestracjaLabel.setText(response.getString("response_message"));
                    anuluj.setText("Cofnij");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}