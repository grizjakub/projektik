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


    public String getId() {
        return id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public ArrayList<String> getOkolni(){
        return okolni;
    }

    @Override
    public String toString() {
        return "game.Location{" +
                "id='" + id + '\'' +
                ", name='" + jmeno + '\'' +
                ", description='" + popis + '\'' +
                ", neighbors=" + okolni +
                '}';
    }
}

