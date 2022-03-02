package entities;
import java.sql.Date;


public class Activity {
    private int id;
    private Date data;
    private String nazwa;
    private int kalorie;

    public Activity(Integer id, Date data, String nazwa, Integer kalorie)  {
            this.id = id;
            this.data = data;
            this.nazwa = nazwa;
            this.kalorie = kalorie;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getKalorie() {
        return kalorie;
    }

    public void setKalorie(int kalorie) {
        this.kalorie = kalorie;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", data=" + data+
                ", nazwa='" + nazwa+ '\'' +
                ", Kalorie=" + kalorie +
                '}';
    }
}
