package hra;

import java.util.ArrayList;

/**
 * Trida slouzisi k nastaveni zakladnich vlastnosti lokalit.
 */
public class Lokace {

    private String id;
    private String jmeno;
    private String popis;
    private ArrayList<String> okolni;
    private java.util.ArrayList<Predmet> predmety = new java.util.ArrayList<>();

    public void pridejPredmet(Predmet p) {
        predmety.add(p);
    }

    public Predmet vezmiPredmet(String nazev) {
        for (Predmet p : predmety) {
            if (p.getJmeno().equalsIgnoreCase(nazev) || p.getId().equalsIgnoreCase(nazev)) {
                predmety.remove(p);
                return p; // Vrátíme nalezený předmět
            }
        }
        return null; // Předmět tu není
    }

    // Uprav metodu toString(), aby vypisovala i předměty v místnosti
    @Override
    public String toString() {
        // Tady použijeme přímo proměnnou 'popis' (bez getPopis()), protože jsme uvnitř třídy
        String vypis = "Lokace: " + id + "\nPopis: " + popis + "\nOkolni lokace" + okolni;

        // Pokud máme předměty (tento list jsme vytvořili v předchozím kroku)
        if (!predmety.isEmpty()) {
            vypis += "\nVidíš zde předměty: " + predmety.toString();
        }

        // Výpis východů (sousedů)
        vypis += "\nVýchody: " + okolni.toString();

        return vypis;
    }


    public String getId() {
        return id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPopis() {
        return popis;
    }
    public ArrayList<String> getOkolni(){
        return okolni;
    }

}

