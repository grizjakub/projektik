package hra;

import hra.Predmet;

/**
 * Trida Postava predstavuje NPC (non-player character) ve hre.
 * Postavy mohou vest dialog s hracem, zadavat ukoly a rozdavat odmeny.
 * Nektere postavy mohou po splneni ukolu ukoncit hru (vitezstvi).
 */
public class Postava {

    private String id;
    private String jmeno;
    private String lokace;

    private String dialog_hrac;
    private String monolog;

    private String id_quest_predmetu;
    private String dialog_hrac_splneno;
    private String monolog_splneno;

    private String id_odmena;
    private String nazev_odmeny;

    private boolean konec_hry = false;
    private boolean questSplnen = false;

    public String getId() { return id; }
    public String getJmeno() { return jmeno; }
    public String getLokaceId() { return lokace; }
    public boolean isKonecHry() { return konec_hry; }
    public boolean isQuestSplnen() { return questSplnen; }

    /**
     * Metoda pro vedeni dialogu s hracem.
     * Resi celou logiku questu: kontroluje, zda ma hrac pozadovane predmety,
     * odebirá je, predava odmeny a nastavuje stav splneno.
     * @param hrac Reference na hrace (pro pristup k inventari)
     * @return Text dialogu
     */
    public String mluvit(Hrac hrac) {
        StringBuilder sb = new StringBuilder();

        // 1. Pokud je ukol jiz splnen, vypiseme jen kratkou zpravu
        if (questSplnen) {
            sb.append(jmeno).append(": \"Uz mame hotovo, ne?\"");
            return sb.toString();
        }

        // 2. Pokud postava nema zadny ukol (obycejny pokec)
        if (id_quest_predmetu == null || id_quest_predmetu.isEmpty()) {
            sb.append("Jindra: ").append(dialog_hrac).append("\n");
            sb.append(jmeno).append(": ").append(monolog);
            return sb.toString();
        }

        // 3. Kontrola, zda ma hrac v inventari vsechny pozadovane predmety
        // (Podporuje vice predmetu oddelenych carkou, napr. "pivo,klobasa")
        String[] pozadovanePredmety = id_quest_predmetu.split(",");
        boolean maVsechno = true;

        for (String idPredmetu : pozadovanePredmety) {
            if (!hrac.maVInventari(idPredmetu.trim())) {
                maVsechno = false;
                break;
            }
        }

        if (maVsechno) {

            // Odebereme vsechny pozadovane predmety z inventare
            for (String idPredmetu : pozadovanePredmety) {
                hrac.odeberZInventarePodleId(idPredmetu.trim());
            }

            // Oznacime ukol jako splneny
            this.questSplnen = true;

            // Pokud je nastavena odmena, vytvorime ji a dame hraci
            if (id_odmena != null && !id_odmena.isEmpty()) {
                Predmet odmena = new Predmet(id_odmena, nazev_odmeny, "");
                hrac.seberPredmet(odmena);
                sb.append("(Dostal jsi predmet: ").append(nazev_odmeny).append(")\n");
            }

            sb.append("Jindra: ").append(dialog_hrac_splneno).append("\n");
            sb.append(jmeno).append(": ").append(monolog_splneno);

            // Pokud tato postava ukoncuje hru, pridame informaci
            if (konec_hry) {
                sb.append("\n\n--- KONEC HRY ---");
            }

        } else {
            // Hrac nema vsechny veci
            sb.append("Jindra: ").append(dialog_hrac).append("\n");
            sb.append(jmeno).append(": ").append(monolog);
        }

        return sb.toString();
    }

    @Override
    public String toString() { return jmeno; }
}