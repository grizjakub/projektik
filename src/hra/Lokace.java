package hra;

import java.util.ArrayList;

public class Lokace {
    private String id;
    private String jmeno;
    private String popis;
    private ArrayList<String> okolni; // ID sousedů z JSONu

    // Inicializace seznamů
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

    public void pridejPostavu(Postava p) {
        if (postavy == null) postavy = new ArrayList<>();
        postavy.add(p);
    }

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

    @Override
    public String toString() {
        String vypis = "Lokace: " + jmeno + "\nPopis: " + popis;

        if (predmety != null && !predmety.isEmpty()) {
            vypis += "\nVidíš zde: " + predmety.toString();
        }

        if (postavy != null && !postavy.isEmpty()) {
            vypis += "\nPostavy: " + postavy.toString();
        }

        if (jmenaSousedu != null && !jmenaSousedu.isEmpty()) {
            vypis += "\nVýchody: " + jmenaSousedu.toString();
        } else if (okolni != null) {
            vypis += "\nVýchody (ID): " + okolni.toString();
        }

        return vypis;
    }
}