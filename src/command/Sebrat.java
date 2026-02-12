package command;

import hra.Hrac;
import hra.HerniData;
import hra.Predmet;
import hra.Lokace;

/**
 * Trida Sebrat predstavuje prikaz pro sebrani predmetu z aktualni lokace.
 * Obsahuje take specialni logiku pro kombinovani predmetu.
 */
public class Sebrat extends Command {

    private String coChciSebrat;

    public Sebrat(Hrac hrac, String coChciSebrat, HerniData herniData) {
        super(hrac, herniData);
        this.coChciSebrat = coChciSebrat;
    }

    /**
     * Provede pokus o sebrani predmetu.
     * Nejprve zjisti, zda predmet v lokaci existuje.
     * Pote zkontroluje specialni podminky (napr. nutnost mit kosik na byliny).
     * Pokud vse klapne, predmet se presune z lokace do inventare hrace.
     * @return Zprava pro hrace o vysledku akce
     */
    @Override
    public String execute() {
        Lokace aktualniLokace = hrac.getAktualniLokace();

        // Zkusime predmet vzit z lokace
        Predmet sebranyPredmet = aktualniLokace.vezmiPredmet(coChciSebrat);

        if (sebranyPredmet == null) {
            return "Predmet '" + coChciSebrat + "' tu nikde nevidim.";
        }

        // Specialni logika pro kytky
        if (sebranyPredmet.getId().equals("kytky") || sebranyPredmet.getJmeno().equalsIgnoreCase("Lecive byliny")) {

            // Pro sebrani bylin musi mit hrac v inventari kosik
            if (hrac.maVInventari("kosik")) {

                // Smazeme prazdny kosik z inventare
                hrac.odeberZInventarePodleId("kosik");

                // Vytvorime novy predmet (plny kosik)
                Predmet plnyKosik = new Predmet("kosik_bylin", "Kosik plny bylin", "");

                // Vlozime plny kosik hraci do inventare
                hrac.seberPredmet(plnyKosik);

                return "Opatrne jsi natrhal byliny do kosiku. Nyni mas 'Kosik plny bylin'.";

            } else {
                // Musime kytky vratit zpet na zem, protoze je hrac nemohl vzit do kosiku
                aktualniLokace.pridejPredmet(sebranyPredmet);

                return "Nemas je do ceho dat. Potrebujes kosik.";
            }
        }

        // Sebrani
        hrac.seberPredmet(sebranyPredmet);
        return "Sebral jsi: " + sebranyPredmet.getJmeno();
    }

    @Override
    public boolean odejit() {
        return false;
    }
}