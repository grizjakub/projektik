package command;

import hra.HerniData;
import hra.Hrac;

public abstract class Command {
    protected Hrac hrac;
    protected HerniData herniData;

    public Command(Hrac hrac, HerniData herniData) {
        this.hrac = hrac;
        this.herniData = herniData;
    }

        // Metoda na otevreni nabidkzy cesty.
    public abstract String execute();
    public abstract boolean exit();

}
