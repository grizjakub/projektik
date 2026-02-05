package command;

import hra.Hrac;
import hra.HerniData;
import hra.Postava;

public class Mluvit extends Command {
    private String sKymMluvit;
    private boolean hraKonci = false; // Nový přepínač

    public Mluvit(Hrac hrac, String sKymMluvit, HerniData herniData) {
        super(hrac, herniData);
        this.sKymMluvit = sKymMluvit;
    }

    @Override
    public String execute() {
        Postava postava = hrac.getAktualniLokace().najdiPostavu(sKymMluvit);

        if (postava != null) {
            String odpoved = postava.mluvit(hrac);
            // Kontrola, jestli tento rozhovor ukončil hru
            if (postava.isQuestSplnen() && postava.isKonecHry()) {
                this.hraKonci = true;
            }

            return odpoved;
        } else {
            return "Postava '" + sKymMluvit + "' tu není.";
        }
    }

    @Override
    public boolean odejit() {
        return hraKonci;
    }
}