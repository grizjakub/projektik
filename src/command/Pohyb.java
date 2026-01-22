package command;

import hra.Hrac;

public class Pohyb extends Command {

    private Hrac hrac;

    public Pohyb(Hrac hrac) {
        this.hrac = hrac;
    }

    @Override
    String jdi(String command) {
        return "";
    }
}
