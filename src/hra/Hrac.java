package hra;

import java.util.ArrayList;

/**
 * Trida slouzici k pridani vlastnosti hracovy.
 */
public class Hrac {

    private Lokace aktualniLokace;
    private ArrayList<Predmet> inventar = new ArrayList<>();

    public Hrac(Lokace aktualniLokace) {
            this.aktualniLokace = aktualniLokace;
        }

    public void seberPredmet(Predmet p) {
        inventar.add(p);
    }

    public String getObsahInventare() {
        if (inventar.isEmpty()) {
            return "Inventář je prázdný.";
        }
        return "V inventáři máš: " + inventar.toString();
    }

    public Lokace getAktualniLokace() {
        return aktualniLokace;
    }

    public void setAktualniLokace(Lokace aktualniLokace) {
        this.aktualniLokace = aktualniLokace;
    }

    @Override
    public String toString() {
        return "Hrac{" +
                "aktualniLokace=" + aktualniLokace +
                '}';
    }
}


