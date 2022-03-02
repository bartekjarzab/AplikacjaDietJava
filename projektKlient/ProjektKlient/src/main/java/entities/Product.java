package entities;

import org.json.JSONObject;

public class Product {
    private Integer id;
    private Integer kalorie;
    private String Nazwa;

    public Product(Integer id, Integer kalorie, String nazwa) {
        this.id = id;
        this.kalorie = kalorie;
        Nazwa = nazwa;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKalorie() {
        return kalorie;
    }

    public void setKalorie(Integer kalorie) {
        this.kalorie = kalorie;
    }

    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", kalorie=" + kalorie +
                ", Nazwa='" + Nazwa + '\'' +
                '}';
    }

    public JSONObject toJSON(){
        JSONObject produkt = new JSONObject();
        produkt.put("Nazwa",this.Nazwa);
        produkt.put("Kalorie",this.kalorie);
        produkt.put("id",this.id);
        return produkt;
    }
}
