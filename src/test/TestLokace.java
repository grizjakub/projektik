package test;

import hra.Lokace;
import hra.Predmet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestLokace {

    private Lokace lokace;

    @Before
    public void setUp() {
        lokace = new Lokace("les", "Temny les", "Vsude jsou stromy.");
    }

    @Test
    public void testVkladaniPredmetu() {
        Predmet p = new Predmet("kaminek", "Maly kaminek", "les");
        lokace.pridejPredmet(p);

        assertNotNull("Předmět by měl být v lokaci", lokace.najdiPredmet("kaminek"));
    }

    @Test
    public void testOdebraniPredmetu() {
        Predmet p = new Predmet("kaminek", "Maly kaminek", "les");
        lokace.pridejPredmet(p);

        Predmet sebrany = lokace.vezmiPredmet("kaminek");

        assertNotNull("Měli bychom dostat objekt předmětu", sebrany);

        assertNull("Předmět už by v lokaci neměl být", lokace.najdiPredmet("kaminek"));
    }

    @Test
    public void testSousedniLokace() {
        lokace.pridejJmenoSouseda("Hrad");
        lokace.pridejJmenoSouseda("Jeskyne");

        String vypis = lokace.toString();
        assertTrue(vypis.contains("Hrad"));
        assertTrue(vypis.contains("Jeskyne"));
    }
}