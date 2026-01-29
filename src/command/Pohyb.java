package command;

import hra.HerniData;
import hra.Hrac;

/**
 * Trida Pohyb, ktera dedi tridu command a urcuje mechaniku pohybu.
 */

public class Pohyb extends Command {
    private String jmeno;

    public Pohyb(Hrac hrac, String id, HerniData herniData) {
        super(hrac, herniData);
        this.jmeno = id;
    }

    /**
     * Metoda na urceni urceni nabidky cestovani a informovani o aktualni lokalite.
     * @return aktualni mistnost, jeji popis a sousedni lokace,
     * v pripade, ze lokalita neni v dosahu nebo neexistuje tak o tom informuje.
     */
    @Override
    public String execute() {
        if (herniData.najdiLokaci(jmeno)) {
            for (int i = 0; i < herniData.getLokace().size(); i++) {
                if (herniData.getLokace().get(i).getJmeno().equalsIgnoreCase(jmeno)) {
                    if (hrac.getAktualniLokace().getOkolni().contains(herniData.getLokace().get(i).getId())) {
                        hrac.setAktualniLokace(herniData.getLokace().get(i));
                        return "Aktualni mistnost " + jmeno;
                    }
                }
            }
            return "Lokace: " + jmeno + " není v dosahu";
        }else {
            return "Lokace: " + jmeno + " neexistuje";
        }
    }

    @Override
    public boolean odejit() {
        return false;
    }
}
