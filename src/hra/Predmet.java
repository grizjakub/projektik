package hra;

public class Predmet {
    private String id;
    private String jmeno;
    private String lokace; // <-- DŮLEŽITÉ: Gson sem uloží ID lokace (např. "tabor_s_h")
    private String postava;

    // Konstruktor není pro Gson nutný, ale pro tvé testy se hodí
    public Predmet(String id, String jmeno, String lokace) {
        this.id = id;
        this.jmeno = jmeno;
        this.lokace = lokace;
    }

    public String getId() { return id; }
    public String getJmeno() { return jmeno; }
    public String getLokaceId() { return lokace; } // Getter pro ID lokace

    @Override
    public String toString() { return jmeno; }
}