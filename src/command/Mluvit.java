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
        Postava postava = hrac.getAktualniLokace().najdiPostavu(sKymMluvit);

        if (postava != null) {
            String odpoved = postava.mluvit(hrac);

            return postava.getJmeno() + ": \"" + odpoved + "\"";
        } else {
            return "Postava '" + sKymMluvit + "' tu není.";
        }
    }

    @Override
    public boolean odejit() {
        return false;
    }
}