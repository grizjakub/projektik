package command;

import hra.Hrac;
import hra.HerniData;
import hra.Predmet;

public class Sebrat extends Command {
    private String coChciSebrat;

    public Sebrat(Hrac hrac, String coChciSebrat, HerniData herniData) {
        super(hrac, herniData);
        this.coChciSebrat = coChciSebrat;
    }

    @Override
    public String execute() {
        // 1. Zeptáme se aktuální lokace, jestli nám dá předmět
        // Metoda 'vezmiPredmet' ho rovnou odstraní z lokace, pokud tam je.
        Predmet predmet = hrac.getAktualniLokace().vezmiPredmet(coChciSebrat);

        if (predmet != null) {
            // 2. Pokud jsme ho dostali, vložíme ho hráči do inventáře
            hrac.seberPredmet(predmet);
            return "Sebral jsi: " + predmet.getJmeno();
        } else {
            // 3. Pokud v lokaci nebyl
            return "Předmět '" + coChciSebrat + "' tu nikde nevidím.";
        }
    }

    @Override
    public boolean odejit() {
        return false; // Hra po sebrání nekončí
    }
}
