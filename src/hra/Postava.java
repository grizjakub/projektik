package hra;

public class Postava {
    private String id;
    private String jmeno;
    private String lokace;

    // Dialogy - zadání úkolu
    private String dialog_hrac; // Co říká Jindra
    private String monolog;     // Co odpoví NPC

    // Quest a splnění
    private String id_quest_predmetu;
    private String dialog_hrac_splneno; // Co říká Jindra při odevzdání
    private String monolog_splneno;     // Co odpoví NPC při odevzdání

    // Gettery
    public String getId() { return id; }
    public String getJmeno() { return jmeno; }
    public String getLokaceId() { return lokace; }

    /**
     * Hlavní metoda pro dialog.
     * Vrátí formátovaný text:
     * Jindra: [Otázka]
     * [Jméno NPC]: [Odpověď]
     */
    public String mluvit(Hrac hrac) {
        StringBuilder sb = new StringBuilder();

        // 1. Kontrola, zda postava vůbec něco chce (má quest?)
        if (id_quest_predmetu == null || id_quest_predmetu.isEmpty()) {
            // Nemá quest -> jen pokec
            sb.append("Jindra: ").append(dialog_hrac).append("\n");
            sb.append(jmeno).append(": ").append(monolog);
            return sb.toString();
        }

        // 2. Kontrola, zda má hráč předmět v batohu
        boolean maPredmet = hrac.maVInventari(id_quest_predmetu);

        if (maPredmet) {
            // MÁ PŘEDMĚT -> SPLNĚNO
            // Můžeme předmět odebrat, aby úkol nešel dělat donekonečna
            hrac.odeberZInventarePodleId(id_quest_predmetu);

            sb.append("Jindra: ").append(dialog_hrac_splneno).append("\n");
            sb.append(jmeno).append(": ").append(monolog_splneno);
        } else {
            // NEMÁ PŘEDMĚT -> ZADÁNÍ ÚKOLU
            sb.append("Jindra: ").append(dialog_hrac).append("\n");
            sb.append(jmeno).append(": ").append(monolog);
        }

        return sb.toString();
    }

    @Override
    public String toString() { return jmeno; }
}