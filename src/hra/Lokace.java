package hra;

import java.util.ArrayList;

/**
 * Trida Lokace predstavuje jednu mistnost (nebo oblast) v hernim svete.
 * Uchovava informace o predmetech, postavach a sousednich lokacich.
 */
public class Lokace {

    private String id;
    private String jmeno;
    private String popis;
    private ArrayList<String> okolni;

    private ArrayList<Predmet> predmety = new ArrayList<>();
    private ArrayList<Postava> postavy = new ArrayList<>();
    private ArrayList<String> jmenaSousedu = new ArrayList<>();

    public Lokace() {
        this.predmety = new ArrayList<>();
        this.postavy = new ArrayList<>();
        this.jmenaSousedu = new ArrayList<>();
        this.okolni = new ArrayList<>();
    }

    public Lokace(String id, String jmeno, String popis) {
        this();
        this.id = id;
        this.jmeno = jmeno;
        this.popis = popis;
    }

    public String getId() { return id; }
    public String getJmeno() { return jmeno; }
    public String getPopis() { return popis; }

    public ArrayList<String> getOkolni() {
        if (okolni == null) return new ArrayList<>();
        return okolni;
    }

    public void pridejPredmet(Predmet p) {
        if (predmety == null) predmety = new ArrayList<>();
        predmety.add(p);
    }

    /**
     * Pokusi se najit predmet podle nazvu a odebrat ho z lokace.
     * Pouziva se, kdyz hrac sbira predmet.
     * @param nazev Nazev nebo ID hledaneho predmetu
     * @return Nalezeny Predmet, nebo null pokud v lokaci neni
     */
    public Predmet vezmiPredmet(String nazev) {
        if (predmety == null) return null;
        for (Predmet p : predmety) {
            if (p.getJmeno().equalsIgnoreCase(nazev) || p.getId().equalsIgnoreCase(nazev)) {
                predmety.remove(p);
                return p;
            }
        }
        return null;
    }

    /**
     * Najde predmet v lokaci a vrati ho.
     * Slouzi pro testovani nebo pro kontrolu, co v mistnosti je.
     * @param nazev Nazev nebo ID predmetu
     * @return Nalezeny Predmet nebo null
     */
    public Predmet najdiPredmet(String nazev) {
        if (predmety == null) return null;
        for (Predmet p : predmety) {
            if (p.getJmeno().equalsIgnoreCase(nazev) || p.getId().equalsIgnoreCase(nazev)) {
                return p;
            }
        }
        return null;
    }

    public void pridejPostavu(Postava p) {
        if (postavy == null) postavy = new ArrayList<>();
        postavy.add(p);
    }

    /**
     * Najde postavu v lokaci podle jmena nebo ID.
     * @param jmeno Jmeno nebo ID postavy
     * @return Nalezena Postava nebo null
     */
    public Postava najdiPostavu(String jmeno) {
        if (postavy == null) return null;
        for (Postava p : postavy) {
            if (p.getJmeno().equalsIgnoreCase(jmeno) || p.getId().equalsIgnoreCase(jmeno)) {
                return p;
            }
        }
        return null;
    }

    public void pridejJmenoSouseda(String jmenoSouseda) {
        if (jmenaSousedu == null) jmenaSousedu = new ArrayList<>();
        jmenaSousedu.add(jmenoSouseda);
    }

    /**
     * Vrati textovy popis lokace vcetne obsahu a vychodu.
     * @return Zformatovany retezec s informacemi
     */
    @Override
    public String toString() {
        String vypis = "Lokace: " + jmeno + "\nPopis: " + popis;

        if (predmety != null && !predmety.isEmpty()) {
            vypis += "\nVidis zde: " + predmety.toString();
        }

        if (postavy != null && !postavy.isEmpty()) {
            vypis += "\nPostavy: " + postavy.toString();
        }

        if (jmenaSousedu != null && !jmenaSousedu.isEmpty()) {
            vypis += "\nVychody: " + jmenaSousedu.toString();
        } else if (okolni != null) {
            vypis += "\nVychody (ID): " + okolni.toString();
        }

        return vypis;
    }
}