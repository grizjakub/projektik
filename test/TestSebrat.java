
import command.Sebrat;
import hra.Hrac;
import hra.Lokace;
import hra.Predmet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSebrat {

    private Hrac hrac;
    private Lokace louka;

    @Before
    public void setUp() {
        louka = new Lokace("louka", "Louka", "Popis");
        hrac = new Hrac(louka);
    }

    @Test
    public void testSebratObycejnyPredmet() {
        Predmet kamen = new Predmet("kamen", "Kamen", "louka");
        louka.pridejPredmet(kamen);

        Sebrat prikaz = new Sebrat(hrac, "kamen", null);
        String vysledek = prikaz.execute();

        assertTrue(vysledek.contains("Sebral jsi"));
        assertTrue(hrac.maVInventari("kamen"));

        assertNull(louka.najdiPredmet("kamen"));
    }

    @Test
    public void testSebratKytkyBezKosiku() {
        Predmet kytky = new Predmet("kytky", "Lecive byliny", "louka");
        louka.pridejPredmet(kytky);

        Sebrat prikaz = new Sebrat(hrac, "kytky", null);
        String vysledek = prikaz.execute();

        assertTrue(vysledek.contains("Nemas je do ceho dat") || vysledek.contains("Potrebujes kosik"));
        assertFalse(hrac.maVInventari("kytky"));

        assertNotNull(louka.najdiPredmet("kytky"));
    }

    @Test
    public void testSebratKytkySKosikem() {
        Predmet kytky = new Predmet("kytky", "Lecive byliny", "louka");
        louka.pridejPredmet(kytky);

        Predmet kosik = new Predmet("kosik", "Prazdny kosik", "");
        hrac.seberPredmet(kosik);

        Sebrat prikaz = new Sebrat(hrac, "kytky", null);
        String vysledek = prikaz.execute();

        assertTrue(vysledek.contains("natrhal byliny") || vysledek.contains("Kosik plny bylin"));
        assertFalse(hrac.maVInventari("kosik"));
        assertTrue(hrac.maVInventari("kosik_bylin"));

        assertNull(louka.najdiPredmet("kytky"));
    }
}