package fr.gwenzy.discord.electroid.events;

import fr.gwenzy.discord.electroid.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;


public class AdminCommandsListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        if(messageReceivedEvent.getMessage().getFormattedContent().startsWith("@Electroid ")){
                String[] args = messageReceivedEvent.getMessage().getFormattedContent().split(" ");



                if(args.length>1)
                    if(args[1].equalsIgnoreCase("shutdown")){
                        Main.commands++;

                        long totalSeconds = (System.currentTimeMillis()-Main.launchTimestamp)/1000;
                        long seconds = totalSeconds % 60;
                        long totalMinutes = totalSeconds / 60;
                        long minutes = totalMinutes % 60;
                        long hours = totalMinutes / 60;

                        String time = hours+"h "+minutes+"m "+seconds+"s";

                        EmbedBuilder eb = new EmbedBuilder();
                        eb.withColor(Color.GREEN);
                        eb.appendField("Excecution time", time, true);
                        eb.appendField("Commands used", String.valueOf(Main.commands), true);
                        eb.withDesc("Bot stats during this session");
                        messageReceivedEvent.getChannel().sendMessage(eb.build());
                        if(Main.electroid.isLoggedIn())Main.electroid.logout();


                    }






        }
    }
}
