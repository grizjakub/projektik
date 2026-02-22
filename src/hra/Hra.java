package hra;

import command.*;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Klicova trida na ktere se vytvori hracske prostredi a spousti se pres ni zakladni metody.
 */
public class Hra {

    private HerniData svet;
    private Hrac hrac;
    private HashMap<String, Supplier<Command>> commands;
    private Scanner sc;
    private boolean opusit;

    /**
     * Metoda slouzici k nacteni herniho cyklu.
     */
    public void inicializace(){
        this.opusit = false;
        vypisUvitani();
        this.sc = new Scanner(System.in);
        commands = new HashMap<>();
        svet = HerniData.nactiHerniDataZRes("res/gamedata.json");
        this.hrac = new Hrac(svet.getLokace().get(4));
        nactiCommand();
    }

    /**
     * Metoda slouzici k nacteni commandu pouzitych uzivatelem.
     * @return new Pohyb s prislusnymi vlastnostmi.
     */
    public void nactiCommand(){
        commands.put("pohyb", () ->{
            System.out.println("Napiš kam chceš jít: ");
            String vstup = sc.nextLine();
            return new Pohyb(hrac, vstup, svet);
        });
        commands.put("sebrat", () -> {
            System.out.println("Co chceš sebrat? ");
            String vstup = sc.nextLine();
            return new Sebrat(hrac, vstup, svet);
        });
        commands.put("inventar", () -> {
           return new Inventar(hrac, svet);
        });
        commands.put("mluvit", () -> {
            System.out.println("S kým chceš mluvit?");
            String vstup = sc.nextLine();
            return new Mluvit(hrac, vstup, svet);
        });
        commands.put("pomoc", () -> new Pomoc(hrac, svet));
        commands.put("konec", () -> new Konec(hrac, svet));

    }

    /**
     * Metoda slouzici k precteni a rozpoznani prikazu.
     */
    public void bez() {
        while(!opusit){
            System.out.println(hrac.toString());
            System.out.print(">> ");
            String prikaz = sc.nextLine();
            if (commands.containsKey(prikaz)) {
                Command cmd = commands.get(prikaz).get();
                System.out.println(cmd.execute());
                this.opusit = cmd.odejit();
            }else
                System.out.println("Příkaz " + prikaz + " neexistuje");
        }
    }

    /**
     * Vypise uvodni text a pribeh hry.
     */
    private void vypisUvitani() {
        System.out.println("=========================================================");
        System.out.println("                       NA LOVU");
        System.out.println("=========================================================");
        System.out.println("Vitej ve hre Na Lovu!");
        System.out.println("Jsi Jindrich, ochrance slechtice pana Ptacka, ztraceny v hlubokem lese. Tvym jedinym");
        System.out.println("cilem je dostat se zpet do civilizace.");
        System.out.println();
        System.out.println("Prozkoumej okoli, pomoz mistnim obyvatelum a ziskej to,");
        System.out.println("co potrebujes k ceste domu.");
        System.out.println();
        System.out.println("Napis 'pomoc' pro zobrazeni seznamu prikazu.");
        System.out.println("=========================================================\n");
    }

    /**
     * Metoda slouzici k spusteni herniho cyklu.
     */
    public void start(){
        inicializace();
        bez();
    }

}

