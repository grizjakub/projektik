package hra;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

public class HerniData {
    // Gson sem automaticky načte seznam lokací
    private ArrayList<Lokace> lokace;
    // Gson sem automaticky načte seznam předmětů (nově přidáno)
    private ArrayList<Predmet> predmety;

    /**
     * Načte data, vytvoří objekty a ROZMÍSTÍ PŘEDMĚTY DO LOKACÍ.
     */
    public static HerniData nactiHerniDataZRes(String resourcePath) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(resourcePath)) {

            // 1. Nejdříve načteme "surová" data z JSONu
            HerniData data = gson.fromJson(reader, HerniData.class);

            // 2. Teď musíme předměty fyzicky vložit do lokací
            if (data.predmety != null && data.lokace != null) {
                data.inicializujPredmety();
            }

            return data;

        } catch (Exception e) {
            throw new RuntimeException("Chyba při načítání JSON: " + e.getMessage());
        }
    }

    // Pomocná metoda, která projde předměty a dá je tam, kam patří
    private void inicializujPredmety() {
        for (Predmet p : predmety) {
            String kamPatriId = p.getLokaceId(); // Získáme ID lokace (např. "tabor_s_h")

            // Pokud má předmět určenou lokaci (není null)
            if (kamPatriId != null && !kamPatriId.isEmpty()) {
                Lokace lokaceKamPatri = najdiLokaciPodleId(kamPatriId);

                if (lokaceKamPatri != null) {
                    lokaceKamPatri.pridejPredmet(p); // <-- TADY SE PŘEDMĚT VLOŽÍ DO MÍSTNOSTI
                }
            }
        }
    }

    // Pomocná metoda pro hledání lokace uvnitř tohoto objektu
    private Lokace najdiLokaciPodleId(String id) {
        for (Lokace l : lokace) {
            if (l.getId().equals(id)) {
                return l;
            }
        }
        return null;
    }

    // Tvoje stávající metody...
    public boolean najdiLokaci(String jmeno) {
        for (Lokace l : lokace) {
            if (l.getJmeno().equals(jmeno)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Lokace> getLokace() { return lokace; }
}