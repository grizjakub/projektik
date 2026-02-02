package hra;

public class Postava {
    private String id;
    private String jmeno;
    private String lokace; // ID lokace pro Gson
    private String monolog; // Co postava řekne

    public Postava(String id, String jmeno, String lokace, String monolog) {
        this.id = id;
        this.jmeno = jmeno;
        this.lokace = lokace;
        this.monolog = monolog;
    }

    public String getId() { return id; }
    public String getJmeno() { return jmeno; }
    public String getLokaceId() { return lokace; }
    public String getMonolog() { return monolog; }

    @Override
    public String toString() { return jmeno; }
}