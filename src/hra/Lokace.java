package hra;

import java.util.ArrayList;

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

