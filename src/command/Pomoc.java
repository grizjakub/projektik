package command;

import hra.Hrac;
import hra.HerniData;

/**
 * Trida slouzi jako prikaz pomoc
 */
public class Pomoc extends Command {

    public Pomoc(Hrac hrac, HerniData herniData) {
        super(hrac, herniData);
    }

    /**
     * Metoda slouzici k vypsani moznych prikazu.
     * @return
     */
    @Override
    public String execute() {
        return "---------------- NÁPOVĚDA ----------------\n" +
                "Tvým úkolem je splnit úkoly místních obyvatel.\n\n" +
                "Dostupné příkazy:\n" +
                "  > pohyb [lokace]   -> Přesune tě do sousední místnosti (např. 'pohyb les')\n" +
                "  > sebrat [věc]     -> Sebere předmět ze země (např. 'sebrat kosik')\n" +
                "  > mluvit [postava] -> Promluví s postavou (např. 'mluvit kvetuse')\n" +
                "  > inventar         -> Zobrazí, co máš v batohu\n" +
                "  > pomoc            -> Zobrazí tuto nápovědu\n" +
                "  > konec            -> Ukončí hru\n" +
                "------------------------------------------";
    }

    @Override
    public boolean odejit() {
        return false;
    }
}