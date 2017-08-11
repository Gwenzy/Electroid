package fr.gwenzy.discord.electroid;

import fr.gwenzy.discord.electroid.events.*;
import fr.gwenzy.discord.electroid.methods.ResistorsMethods;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Main {
    public static IDiscordClient electroid;

    public static final long launchTimestamp = System.currentTimeMillis();
    public static final List<String> operatorsID = Arrays.asList("205809466514472960", "224940744362819584");
    public static int commands;

    public static HashMap<String, Integer> resistorValues = new HashMap();
    public static HashMap<String, Double> resistorTolerances = new HashMap();



    public static IDiscordClient createClient(String token, boolean login) { // Returns a new instance of the Discord client
        ClientBuilder clientBuilder = new ClientBuilder(); // Creates the ClientBuilder instance
        clientBuilder.withToken(token); // Adds the login info to the builder
        try {
            /*clientBuilder.registerListener(new AdminCommandsListener());
            clientBuilder.registerListener(new ModeratorCommandsListener());
            clientBuilder.registerListener(new MemberCommandsListener());
            clientBuilder.registerListener(new ResistorCommandsListener());
            clientBuilder.registerListener(new ReadyListener());*/
            clientBuilder.registerListener(new AddRessourceCommandListener());

            if (login) {
                return clientBuilder.login(); // Creates the client instance and logs the client in
            } else {
                return clientBuilder.build(); // Creates the client instance but it doesn't log the client in yet, you would have to call client.login() yourself
            }
        } catch (DiscordException e) { // This is thrown if there was a problem building the client
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args){
        commands=0;
        ResistorsMethods.initResistors();

        electroid = createClient(Tokens.getTokenElectroid(), true);
    }
}
