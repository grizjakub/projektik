package command;

import hra.Hrac;
import hra.HerniData;
import hra.Postava;

public class Mluvit extends Command {
    private String sKymMluvit;

    public Mluvit(Hrac hrac, String sKymMluvit, HerniData herniData) {
        super(hrac, herniData);
        this.sKymMluvit = sKymMluvit;
    }

    @Override
    public String execute() {
        // Zkusíme najít postavu v aktuální místnosti
        Postava postava = hrac.getAktualniLokace().najdiPostavu(sKymMluvit);

        if (postava != null) {
            // Pokud tam je, vrátíme její text
            return postava.getJmeno() + ": \"" + postava.getMonolog() + "\"";
        } else {
            return "Postava '" + sKymMluvit + "' tu není.";
        }
    }

    @Override
    public boolean odejit() {
        return false;
    }
}
