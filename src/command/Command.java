package command;

import hra.HerniData;
import hra.Hrac;

/**
 * Abstraktni trida command slouzi k zapsani vsech abstraktnich comandu/prikazu v metodach,
 * ktere jsou dale specifikovany v jinych tridach.
 */

public abstract class Command {
    protected Hrac hrac;
    protected HerniData herniData;

    public Command(Hrac hrac, HerniData herniData) {
        this.hrac = hrac;
        this.herniData = herniData;
    }

        // Metoda na otevreni nabidky cesty.
    public abstract String execute();
    public abstract boolean odejit();

}
