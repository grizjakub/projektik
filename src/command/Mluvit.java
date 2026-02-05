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
        // 1. Najdeme postavu v aktuální místnosti
        Postava postava = hrac.getAktualniLokace().najdiPostavu(sKymMluvit);

        if (postava != null) {
            // 2. Zavoláme chytrou metodu mluvit(), která vrátí celý dialog
            return postava.mluvit(hrac);
        } else {
            return "Postava '" + sKymMluvit + "' tu není.";
        }
    }

    @Override
    public boolean odejit() {
        return false;
    }
}