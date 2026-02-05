package command;

import hra.Hrac;
import hra.HerniData;
import hra.Predmet;
import hra.Lokace;

public class Sebrat extends Command {
    private String coChciSebrat;

    public Sebrat(Hrac hrac, String coChciSebrat, HerniData herniData) {
        super(hrac, herniData);
        this.coChciSebrat = coChciSebrat;
    }

    @Override
    public String execute() {
        Lokace aktualniLokace = hrac.getAktualniLokace();

        Predmet sebranyPredmet = aktualniLokace.vezmiPredmet(coChciSebrat);

        if (sebranyPredmet == null) {
            return "Predmet '" + coChciSebrat + "' tu nikde nevidim.";
        }
        if (sebranyPredmet.getId().equals("kytky") || sebranyPredmet.getJmeno().equalsIgnoreCase("Lecive byliny")) {
            if (hrac.maVInventari("kosik")) {
                hrac.odeberZInventarePodleId("kosik");
                Predmet plnyKosik = new Predmet("kosik_bylin", "Kosik plny bylin", "");
                hrac.seberPredmet(plnyKosik);
                return "Opatrne jsi natrhal byliny do kosiku. Nyni mas 'Kosik plny bylin'.";
            } else {
                aktualniLokace.pridejPredmet(sebranyPredmet);

                return "Nemas je do ceho dat. Potrebujes kosik.";
            }
        }
        hrac.seberPredmet(sebranyPredmet);
        return "Sebral jsi: " + sebranyPredmet.getJmeno();
    }

    @Override
    public boolean odejit() {
        return false;
    }
}