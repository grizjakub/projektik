package command;

import hra.HerniData;
import hra.Hrac;

/**
 * Trida slouzici k ukladani specifickych predmetu.
 */
public class Inventar extends Command{

    private String jmeno;

    public Inventar(Hrac hrac,String id, HerniData herniData) {
        super(hrac, herniData);
        this.jmeno = id;
    }
//TODO dokoncit metodu execute pro zobrazeni inventare
    @Override
    public String execute() {
        return "";
    }

    @Override
    public boolean odejit() {
        return false;
    }
}
