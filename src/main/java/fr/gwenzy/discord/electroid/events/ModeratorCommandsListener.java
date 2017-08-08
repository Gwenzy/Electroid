package fr.gwenzy.discord.electroid.events;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;


public class ModeratorCommandsListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        if(messageReceivedEvent.getMessage().getFormattedContent().startsWith("@Electroid ")){
                String[] args = messageReceivedEvent.getMessage().getFormattedContent().split(" ");




        }
    }
}
