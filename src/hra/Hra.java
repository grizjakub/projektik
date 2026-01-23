package hra;

import command.Command;
import command.Pohyb;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Supplier;

public class Hra {

    private HerniData svet;
    private Hrac hrac;
    private HashMap<String, Supplier<Command>> commands;
    private Scanner sc;
    private boolean opusit;

    /**
     * Metoda slouzici k nacteni herniho cyklu
     */
    public void inicializace(){
        this.opusit = false;
        this.sc = new Scanner(System.in);
        commands = new HashMap<>();
        svet = HerniData.nactiHerniDataZRes("res/gamedata.json");
        this.hrac = new Hrac(svet.getLokace().get(4));
        loadCommands();
    }

    public void loadCommands(){
        commands.put("pohyb", () ->{
            System.out.println("Napiš kam chceš jít: ");
            String vstup = sc.nextLine();
            return new Pohyb(hrac, vstup, svet);
        });
    }

    public void run() {
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

    public void start(){
        inicializace();
        run();
    }

}

