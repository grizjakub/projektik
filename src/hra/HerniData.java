package hra;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

public class HerniData {
    private ArrayList<Lokace> lokace;
    private ArrayList<Predmet> predmety;
    private ArrayList<Postava> postavy;

    public static HerniData nactiHerniDataZRes(String resourcePath) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(resourcePath)) {
            HerniData data = gson.fromJson(reader, HerniData.class);

            if (data.predmety != null) data.inicializujPredmety();
            if (data.postavy != null) data.inicializujPostavy();
            if (data.lokace != null) data.inicializujJmenavychodu();

            return data;
        } catch (Exception e) {
            // Tady se vypisoval ten Error, teď by to mělo projít
            throw new RuntimeException("Chyba při načítání JSON: " + e.getMessage());
        }
    }

    private void inicializujJmenavychodu() {
        for (Lokace aktualni : lokace) {
            if (aktualni.getOkolni() != null) {
                for (String idSouseda : aktualni.getOkolni()) {
                    Lokace soused = najdiLokaciPodleId(idSouseda);
                    if (soused != null) {
                        // Voláme metodu, kterou jsme přidali do Lokace.java
                        aktualni.pridejJmenoSouseda(soused.getJmeno());
                    }
                }
            }
        }
    }

    private void inicializujPredmety() {
        for (Predmet p : predmety) {
            if (p.getLokaceId() != null) {
                Lokace l = najdiLokaciPodleId(p.getLokaceId());
                if (l != null) l.pridejPredmet(p);
            }
        }
    }

    private void inicializujPostavy() {
        for (Postava p : postavy) {
            if (p.getLokaceId() != null) {
                Lokace l = najdiLokaciPodleId(p.getLokaceId());
                if (l != null) l.pridejPostavu(p);
            }
        }
    }

    public Lokace najdiLokaciPodleId(String id) {
        if (lokace == null) return null;
        for (Lokace l : lokace) {
            if (l.getId().equals(id)) return l;
        }
        return null;
    }

    public boolean najdiLokaci(String jmeno) {
        if (lokace == null) return false;
        for (Lokace l : lokace) {
            if (l.getJmeno().equals(jmeno)) return true;
        }
        return false;
    }

    public ArrayList<Lokace> getLokace() { return lokace; }
}