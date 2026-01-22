package command;

import hra.Hrac;

public class Pohyb extends Command {

    private Hrac hrac;

    public Pohyb(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    public String potvrdit(String command) {
        //TODO dodelat pohyb

        return "";
    }

    @Override
    public boolean odejit() {
        return false;
    }
}
