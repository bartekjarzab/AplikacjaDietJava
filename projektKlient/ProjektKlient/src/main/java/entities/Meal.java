package entities;

import javafx.scene.control.ProgressBar;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.util.List;

public class Meal {
    private Integer id;
    private String nazwa;
    private Date data;
    private List<Product> listaProduktow;

    public Meal(Integer id, String nazwa, Date data) {
        this.id = id;
        this.nazwa = nazwa;
        this.data = data;
    }

    public Meal() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Product> getListaProduktow() {
        return listaProduktow;
    }

    public void setListaProduktow(List<Product> listaProduktow) {
        this.listaProduktow = listaProduktow;
    }

    public int Kalorie(){
        int suma = 0;
        for(Product p: listaProduktow){
            suma += p.getKalorie();
        }
        return suma;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", data=" + data +
                ", listaProduktow=" + listaProduktow +
                '}';
    }

    public JSONObject toJson(){
        JSONObject posilek = new JSONObject();
        JSONArray product = new JSONArray();
        posilek.put("Nazwa",this.nazwa);
        posilek.put("Data",this.data);
        posilek.put("id",this.id);
        for(Product p: listaProduktow){
            product.put(p.getId(),p.toJSON());
        }
        posilek.put("Skladniki",product);
        return posilek;
    }
}
