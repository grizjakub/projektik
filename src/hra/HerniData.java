package hra;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Trida HerniData slouzi k nacitani hernich dat ze souboru JSON.
 * Obsahuje metody pro inicializaci sveta, propojeni lokaci a rozmistenim predmetu a postav.
 */
public class HerniData {

    // Seznamy, do kterych Gson nacte data primo z JSONu
    private ArrayList<Lokace> lokace;
    private ArrayList<Predmet> predmety;
    private ArrayList<Postava> postavy;

    /**
     * Hlavni staticka metoda pro nacteni dat a vytvoreni herniho sveta.
     * Nacte JSON soubor, vytvori objekty a vzajemne je propoji.
     * @param resourcePath Cesta k souboru s daty (gamedata.json)
     * @return Inicializovany objekt HerniData obsahujici kompletni herni svet
     */
    public static HerniData nactiHerniDataZRes(String resourcePath) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(resourcePath)) {
            // Nacteni hrubych dat z JSONu
            HerniData data = gson.fromJson(reader, HerniData.class);

            // Rozrazeni predmetu do lokaci (podle ID lokace v predmetu)
            if (data.predmety != null) {
                data.inicializujPredmety();
            }

            // Rozrazeni postav do lokaci
            if (data.postavy != null) {
                data.inicializujPostavy();
            }

            // Propojeni jmen vychodu (aby hrac videl jmena mistnosti misto ID)
            if (data.lokace != null) {
                data.inicializujJmenavychodu();
            }

            return data;
        } catch (Exception e) {
            throw new RuntimeException("Chyba pri nacitani JSON: " + e.getMessage());
        }
    }

    /**
     * Projde vsechny nactene predmety a vlozi je do prislusnych lokaci.
     */
    private void inicializujPredmety() {
        for (Predmet p : predmety) {
            if (p.getLokaceId() != null) {
                Lokace l = najdiLokaciPodleId(p.getLokaceId());
                if (l != null) l.pridejPredmet(p);
            }
        }
    }

    /**
     * Projde vsechny nactene postavy a vlozi je do prislusnych lokaci.
     */
    private void inicializujPostavy() {
        for (Postava p : postavy) {
            if (p.getLokaceId() != null) {
                Lokace l = najdiLokaciPodleId(p.getLokaceId());
                if (l != null) l.pridejPostavu(p);
            }
        }
    }

    /**
     * Propoji sousedni lokace.
     * Nacte seznam ID sousedu a pro kazde ID najde objekt lokace.
     * Nasledne prida jmeno sousedni lokace do seznamu pro vypis.
     */
    private void inicializujJmenavychodu() {
        for (Lokace aktualni : lokace) {
            // Pokud ma lokace nejake sousedy (IDcka)
            if (aktualni.getOkolni() != null) {
                for (String idSouseda : aktualni.getOkolni()) {
                    // Najdeme sousedni lokaci podle ID
                    Lokace soused = najdiLokaciPodleId(idSouseda);

                    if (soused != null) {
                        // Vezmeme její JMENO a posleme ho do aktualni lokace
                        // To slouzi k tomu, aby hrac videl "Vychody: Les, Hrad" misto ID
                        aktualni.pridejJmenoSouseda(soused.getJmeno());
                    }
                }
            }
        }
    }

    /**
     * Najde objekt lokace podle jejiho ID.
     * @param id Identifikator lokace
     * @return Nalezena Lokace nebo null
     */
    public Lokace najdiLokaciPodleId(String id) {
        if (lokace == null) return null;
        for (Lokace l : lokace) {
            if (l.getId().equals(id)) return l;
        }
        return null;
    }

    /**
     * Zjisti, zda existuje lokace s danym jmenem.
     * Slouzi pro overeni pri zadavani prikazu pohybu.
     * @param jmeno Jmeno lokace
     * @return true pokud lokace existuje, jinak false
     */
    public boolean najdiLokaci(String jmeno) {
        if (lokace == null) return false;
        for (Lokace l : lokace) {
            if (l.getJmeno().equals(jmeno)) return true;
        }
        return false;
    }

    public ArrayList<Lokace> getLokace() { return lokace; }
}