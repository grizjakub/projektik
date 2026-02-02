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

    // Seznam postav
    private java.util.ArrayList<Postava> postavy = new java.util.ArrayList<>();

    // Metoda pro přidání postavy (pro načítání hry)
    public void pridejPostavu(Postava p) {
        postavy.add(p);
    }

    // Metoda pro nalezení postavy podle jména (pro příkaz mluvit)
    public Postava najdiPostavu(String jmeno) {
        for (Postava p : postavy) {
            if (p.getJmeno().equalsIgnoreCase(jmeno) || p.getId().equalsIgnoreCase(jmeno)) {
                return p;
            }
        }
        return null;
    }

    // Uprav metodu toString(), aby vypisovala i předměty v místnosti
    @Override
    public String toString() {
        String vypis = "Lokace: " + id + "\nPopis: " + popis;

        // Výpis předmětů
        if (!predmety.isEmpty()) vypis += "\nPředměty: " + predmety;

        // Výpis postav
        if (!postavy.isEmpty()) vypis += "\nPostavy: " + postavy;

        vypis += "\nVýchody: " + okolni;
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

