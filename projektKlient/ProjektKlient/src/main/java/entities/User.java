package entities;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private static Integer id;
    private static String login;
    private static Integer zapotrzebowanie_kaloryczne;
    private static String haslo;
    private static Integer rola;
    private static List<Meal> posilki;
    private static List<Activity> aktywnosci;

    public User() {
    }

    public static int mealMaxID(){
        int suma = 0;
        for(Meal m: posilki){
            if(suma < m.getId()){
                suma = m.getId();
            }
        }
        return suma;
    }

    public static int maxID(){
        int id = 0;
        for(Activity a: aktywnosci){
            if(a.getId() > id){
                id = a.getId();
            }
        }
        return id;
    }

    public User(Integer id, String login, Integer zapotrzebowanie_kaloryczne, String haslo, Integer rola) {
        User.id = id;
        User.login = login;
        User.zapotrzebowanie_kaloryczne = zapotrzebowanie_kaloryczne;
        User.haslo = haslo;
        User.rola = rola;
    }

    public static int zjedzoneKalorie(Date data){
        int suma = 0;
        List<Meal> posilki = User.posilki.stream().filter(e->{return e.getData().equals(data);}).collect(Collectors.toList());
        for(Meal m : posilki){
            suma += m.Kalorie();
        }
        return suma;
    }

    public static int spaloneKalorie(Date data){
        int suma = 0;
        List<Activity> aktywnosciData = aktywnosci.stream().filter(e->{return e.getData().equals(data);}).collect(Collectors.toList());
        System.out.println(data);
        System.out.println(aktywnosciData);
        for(Activity a : aktywnosciData){
            suma += a.getKalorie();
        }
        return suma;
    }

    public static Integer getRola() {
        return rola;
    }

    public static void setRola(Integer rola) {
        User.rola = rola;
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        User.id = id;
    }

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        User.login = login;
    }

    public static Integer getZapotrzebowanie_kaloryczne() {
        return zapotrzebowanie_kaloryczne;
    }

    public static void setZapotrzebowanie_kaloryczne(Integer zapotrzebowanie_kaloryczne) {
        User.zapotrzebowanie_kaloryczne = zapotrzebowanie_kaloryczne;
    }

    public static String getHaslo() {
        return haslo;
    }

    public static void setHaslo(String haslo) {
        User.haslo = haslo;
    }

    public static List<Meal> getPosilki() {
        return posilki;
    }

    public static void setPosilki(List<Meal> posilki) {
        User.posilki = posilki;
    }

    public static List<Activity> getAktywnosci() {
        return aktywnosci;
    }

    public static void setAktywnosci(List<Activity> aktywnosci) {
        User.aktywnosci = aktywnosci;
    }
}
