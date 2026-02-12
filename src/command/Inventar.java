package command;

import hra.HerniData;
import hra.Hrac;

/**
 * Trida slouzici pouze jako command pro zobrazeni inventare
 */
public class Inventar extends Command{


    public Inventar(Hrac hrac, HerniData herniData) {
        super(hrac, herniData);
    }

    @Override
    public String execute() {
        return  hrac.getObsahInventare();
    }

    @Override
    public boolean odejit() {
        return false;
    }
}
