package fr.gwenzy.discord.electroid.methods;

import fr.gwenzy.discord.electroid.Main;

import java.util.HashMap;

/**
 * Created by gwend on 26/05/2017.
 */
public class ResistorsMethods {

    public static void initResistors(){
        Main.resistorValues.put("black", 0);
        Main.resistorValues.put("brown", 1);
        Main.resistorValues.put("red", 2);
        Main.resistorTolerances.put("brown", 1.0);
        Main.resistorTolerances.put("red", 2.0);
        Main.resistorValues.put("orange", 3);
        Main.resistorValues.put("yellow", 4);
        Main.resistorValues.put("green", 5);
        Main.resistorValues.put("blue", 6);
        Main.resistorValues.put("purple", 7);
        Main.resistorValues.put("grey", 8);
        Main.resistorTolerances.put("green", 0.5);
        Main.resistorTolerances.put("blue", 0.25);
        Main.resistorTolerances.put("purple", 0.1);
        Main.resistorTolerances.put("grey", 0.05);
        Main.resistorValues.put("white", 9);
        Main.resistorTolerances.put("gold", 5.0);
        Main.resistorTolerances.put("silver", 10.0);
    }
}
