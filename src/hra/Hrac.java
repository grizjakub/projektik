package hra;

import java.util.ArrayList;

/**
 * Trida Hrac predstavuje uzivatele ve hre.
 * Uchovava informace o aktualni poloze a predmetech, ktere ma hrac u sebe.
 */
public class Hrac {

    private ArrayList<Predmet> inventar = new ArrayList<>();
    private Lokace aktualniLokace;

    public Hrac(Lokace aktualniLokace) {
        this.aktualniLokace = aktualniLokace;
    }

    /**
     * Prida predmet do inventare hrace.
     * @param p Predmet, ktery se ma pridat
     */
    public void seberPredmet(Predmet p) {
        inventar.add(p);
    }

    /**
     * Vrati obsah inventare.
     * @return Seznam predmetu nebo zprava o prazdnem inventari
     */
    public String getObsahInventare() {
        if (inventar.isEmpty()) {
            return "Inventar je prazdny.";
        }
        return "V inventari mas: " + inventar.toString();
    }

    /**
     * Zjisti, zda ma hrac dany predmet v inventari.
     * Hleda se podle ID i podle jmena.
     * @param idNeboJmenoPredmetu Nazev nebo ID hledaneho predmetu
     * @return true pokud predmet v inventari je, jinak false
     */
    public boolean maVInventari(String idNeboJmenoPredmetu) {
        for (Predmet p : inventar) {
            if (p.getId().equalsIgnoreCase(idNeboJmenoPredmetu) ||
                    p.getJmeno().equalsIgnoreCase(idNeboJmenoPredmetu)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Odebere predmet z inventare na zaklade jeho ID.
     * Pouziva metodu removeIf pro efektivni odstraneni.
     * @param id Identifikator predmetu k odebrani
     */
    public void odeberZInventarePodleId(String id) {
        inventar.removeIf(p -> p.getId().equalsIgnoreCase(id));
    }

    public Lokace getAktualniLokace() { return aktualniLokace; }

    public void setAktualniLokace(Lokace aktualniLokace) { this.aktualniLokace = aktualniLokace; }

    @Override
    public String toString() {
        return "Hrac{" +
                "aktualniLokace=" + aktualniLokace +
                '}';
    }
}