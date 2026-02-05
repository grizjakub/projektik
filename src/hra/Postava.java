package hra;

public class Postava {
    private String id;
    private String jmeno;
    private String lokace;
    private String monolog;
    private String id_quest_predmetu;
    private String monolog_splneno;

    public String getId() { return id; }
    public String getJmeno() { return jmeno; }
    public String getLokaceId() { return lokace; }

    /**
     * Tato metoda rozhodne, co postava řekne, na základě toho, co má hráč v batohu.
     */
    public String mluvit(Hrac hrac) {
        // 1. Pokud postava nic nechce (nemá quest), řekne jen základní text.
        if (id_quest_predmetu == null || id_quest_predmetu.isEmpty()) {
            return monolog;
        }

        boolean maPredmet = hrac.maVInventari(id_quest_predmetu);

        if (maPredmet) {
            return monolog_splneno;
        } else {
            return monolog;
        }
    }

    @Override
    public String toString() { return jmeno; }
}