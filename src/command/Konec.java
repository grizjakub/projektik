package command;

import hra.HerniData;
import hra.Hrac;

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
