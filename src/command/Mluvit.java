package command;

import hra.Hrac;
import hra.HerniData;
import hra.Postava;

/**
 * Trida Mluvit predstavuje prikaz pro interakci s postavami.
 * Umoznuje hracovi vest dialog, plnit ukoly a pripadne ukoncit hru uspesnym splnenim cile.
 */
public class Mluvit extends Command {

    private String sKymMluvit;
    private boolean hraKonci = false;

    public Mluvit(Hrac hrac, String sKymMluvit, HerniData herniData) {
        super(hrac, herniData);
        this.sKymMluvit = sKymMluvit;
    }

    /**
     * Provede logiku rozhovoru.
     * Najde postavu v aktualni lokaci, ziska od ni text dialogu
     * a zkontroluje, zda tento rozhovor neukoncuje celou hru.
     * @return Text odpovedi postavy nebo upozorneni na chybejici postavu.
     */
    @Override
    public String execute() {
        // Zkusime najit postavu v aktualni lokaci podle jmena
        Postava postava = hrac.getAktualniLokace().najdiPostavu(sKymMluvit);

        if (postava != null) {
            // Ziskame text dialogu (metoda mluvit v tride Postava resi i predavani predmetu)
            String odpoved = postava.mluvit(hrac);

            // Kontrola vitezstvi: pokud je quest splnen A postava je oznacena jako ta, ktera ukoncuje hru
            if (postava.isQuestSplnen() && postava.isKonecHry()) {
                this.hraKonci = true;
            }

            return odpoved;
        } else {
            return "Postava '" + sKymMluvit + "' tu neni.";
        }
    }

    /**
     * Vraci informaci, zda ma hra po provedeni tohoto prikazu skoncit.
     * @return true pokud hrac splnil hlavni cil a mluvil s finalni postavou, jinak false
     */
    @Override
    public boolean odejit() {
        return hraKonci;
    }
}