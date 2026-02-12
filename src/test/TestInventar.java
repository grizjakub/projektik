package test;

import hra.Hrac;
import hra.Lokace;
import hra.Predmet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestInventar {

    private Hrac hrac;
    private Lokace lokace;

    @Before
    public void setUp() {
        lokace = new Lokace("test_lokace", "Testovací místnost", "Popis");
        hrac = new Hrac(lokace);
    }

    @Test
    public void testVlozeniDoInventare() {
        Predmet p = new Predmet("mec", "Zelezny mec", "");
        hrac.seberPredmet(p);

        assertTrue("Hráč by měl mít předmět v inventáři", hrac.maVInventari("mec"));
        assertTrue("Hráč by měl mít předmět i podle jména", hrac.maVInventari("Zelezny mec"));
    }

    @Test
    public void testOdebraniZInventare() {
        Predmet p = new Predmet("jablko", "Cervene jablko", "");
        hrac.seberPredmet(p);

        assertTrue(hrac.maVInventari("jablko"));

        hrac.odeberZInventarePodleId("jablko");

        assertFalse("Hráč by už neměl mít jablko", hrac.maVInventari("jablko"));
    }
}