package command;

import hra.HerniData;
import hra.Hrac;

/**
 * Trida slouzici k ukladani specifickych predmetu.
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
