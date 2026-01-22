package hra;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Reprezentuje data z nacteneho JSON souboru.
 * Tato trida slouzi jako sbirka vsech statickych hernich dat, jako predmety, postavy, lokoce, a ukoly.
 */
public class HerniData {
    private ArrayList<Lokace> lokace;


    /**
     * Nacte data z JSON souboru.
     * @param resourcePath cesta k JSON souboru
     * @return a hra.herniData objekt s vlastnostmi z nacteneho souboru
     */
    public static HerniData nactiHerniDataZRes(String resourcePath) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(resourcePath)) {
            return gson.fromJson(
                    reader,
                    HerniData.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Chyba při načítání JSON: " + e.getMessage());
        }
    }

    /**
     * Najde lokaci podle identifikatoru.
     * @param jmeno lokace se najde podle jmena
     * @return prislusna lokace
     */
    public Lokace najdiLokaci(String id) {
        for (Lokace l : lokace) {
            if (l.getId().equals(id)){
                return l;
            }
        }
        throw new IllegalArgumentException("Neexistuje lokace s id: " + id);
    }

    public ArrayList<Lokace> getLokace() {
        return lokace;
    }
}
