package fr.gwenzy.discord.electroid.events;

import fr.gwenzy.discord.electroid.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;


public class MemberCommandsListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        if(messageReceivedEvent.getMessage().getFormattedContent().startsWith("@Electroid ")){
                String[] args = messageReceivedEvent.getMessage().getFormattedContent().split(" ");


            if(args.length>1)
                if(args[1].equalsIgnoreCase("help")){
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.withColor(Color.YELLOW);
                    eb.withDesc("Here is a help !");
                    eb.appendField("Convert resistor value to color code: ",  messageReceivedEvent.getGuild().getUserByID(Long.parseLong("274888981030633472")).mention()+" color <Resistor Value> <Tolerancce> [Bands Number(optionnal)]", false);
                    eb.appendField("Convert color code to resistor value: ", messageReceivedEvent.getGuild().getUserByID(Long.parseLong("274888981030633472")).mention()+" value <Color Code>", false);
                    eb.appendField("Examples: ", "@Electroid#4749 color 3.5k 10% 5\n"
                            + "@Electroid#4749 color 10M 2%\n"
                            + "@Electroid#4749 value "+messageReceivedEvent.getGuild().getEmojiByName("resist1")+messageReceivedEvent.getGuild().getEmojiByName("orange")+messageReceivedEvent.getGuild().getEmojiByName("orange")+messageReceivedEvent.getGuild().getEmojiByName("red")+messageReceivedEvent.getGuild().getEmojiByName("gold")+messageReceivedEvent.getGuild().getEmojiByName("resist2")+"\n"
                            + "@Electroid#4749 value orange-orange-red-gold", false);
                    messageReceivedEvent.getChannel().sendMessage(eb.build());
                }



        }
    }
}
