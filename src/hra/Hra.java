package hra;

import command.Command;
import command.Pohyb;

import java.util.HashMap;

public class Hra {

    private HerniData svet;
    private Hrac hrac;
    private HashMap<String, Command> commands;


    public void inicializace(){
        commands = new HashMap<>();
        svet = HerniData.nactiHerniDataZRes("/gamedata.json");
        //TODO pridat commands
        commands.put("pohyb", new Pohyb(hrac));

    }

    public void start(){
        inicializace();
    }

}
