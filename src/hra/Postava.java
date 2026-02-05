package hra;

public class Postava {
    private String id;
    private String jmeno;
    private String lokace;

    // Dialogy
    private String dialog_hrac;
    private String monolog;

    // Quest
    private String id_quest_predmetu;
    private String dialog_hrac_splneno;
    private String monolog_splneno;

    // Odměna
    private String id_odmena;
    private String nazev_odmeny;

    // Speciální vlastnost pro konec hry
    private boolean konec_hry = false;

    // Stav splnění
    private boolean questSplnen = false;

    public String getId() { return id; }
    public String getJmeno() { return jmeno; }
    public String getLokaceId() { return lokace; }
    public boolean isKonecHry() { return konec_hry; }
    public boolean isQuestSplnen() { return questSplnen; }

    public String mluvit(Hrac hrac) {
        StringBuilder sb = new StringBuilder();

        if (questSplnen) {
            sb.append(jmeno).append(": \"Už máme hotovo, ne?\"");
            return sb.toString();
        }

        if (id_quest_predmetu == null || id_quest_predmetu.isEmpty()) {
            sb.append("Jindra: ").append(dialog_hrac).append("\n");
            sb.append(jmeno).append(": ").append(monolog);
            return sb.toString();
        }

        String[] pozadovanePredmety = id_quest_predmetu.split(",");
        boolean maVsechno = true;

        for (String idPredmetu : pozadovanePredmety) {
            if (!hrac.maVInventari(idPredmetu.trim())) {
                maVsechno = false;
                break;
            }
        }

        if (maVsechno) {
            for (String idPredmetu : pozadovanePredmety) {
                hrac.odeberZInventarePodleId(idPredmetu.trim());
            }

            this.questSplnen = true;

            if (id_odmena != null && !id_odmena.isEmpty()) {
                Predmet odmena = new Predmet(id_odmena, nazev_odmeny, "");
                hrac.seberPredmet(odmena);
                sb.append("(Dostal jsi předmět: ").append(nazev_odmeny).append(")\n");
            }

            sb.append("Jindra: ").append(dialog_hrac_splneno).append("\n");
            sb.append(jmeno).append(": ").append(monolog_splneno);

            if (konec_hry) {
                sb.append("\n\n--- KONEC HRY ---");
            }

        } else {
            sb.append("Jindra: ").append(dialog_hrac).append("\n");
            sb.append(jmeno).append(": ").append(monolog);
        }

        return sb.toString();
    }

    @Override
    public String toString() { return jmeno; }
}