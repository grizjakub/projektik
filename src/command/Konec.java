package command;

import hra.HerniData;
import hra.Hrac;

/**
 * Trida slouzici jako command pro ukonceni hry.
 */

public class Konec extends Command {

    public Konec(Hrac hrac, HerniData herniData) {
        super(hrac, herniData);
    }

    @Override
    public String execute() {
        return "Pěkný zbytek večera přeji";
    }

    @Override
    public boolean odejit() {
        return true;
    }
}
