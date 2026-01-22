package hra;

public class Hrac {

    private Lokace aktualniLokace;

    public Hrac(Lokace aktualniLokace) {
        this.aktualniLokace = aktualniLokace;
    }

    public Lokace getAktualniLokace() {
        return aktualniLokace;
    }

    public void setAktualniLokace(Lokace aktualniLokace) {
        this.aktualniLokace = aktualniLokace;
    }

    @Override
    public String toString() {
        return "Hrac{" +
                "aktualniLokace=" + aktualniLokace +
                '}';
    }
}


