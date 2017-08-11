package fr.gwenzy.discord.electroid.events;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;



public class AddRessourceCommandListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        if(messageReceivedEvent.getMessage().getFormattedContent().startsWith("@TestBot ")){
            String[] args = messageReceivedEvent.getMessage().getFormattedContent().split(" ");

            if(args.length>1)
                if(args[1].equalsIgnoreCase("addressource") && messageReceivedEvent.getChannel().getLongID()==Long.parseLong("289498770507431936")){
                    if(args.length>5)
                        try{

                                if(args[2].equalsIgnoreCase("plug") || args[2].equalsIgnoreCase("board") || args[2].equalsIgnoreCase("ic") || args[2].equalsIgnoreCase("component") || args[2].equalsIgnoreCase("others")){
                                String type = args[2];
                                String name="";
                                int rank=0;
                                for(int i=3; i<args.length-1; i++) {
                                    if (args[i].equals(":")) {
                                        rank = i;
                                        break;
                                    } else {
                                        name += args[i];
                                        name += " ";
                                    }
                                }
                                name = name.substring(0, name.length()-1);

                                    String info = "";
                                    for(int i=rank+1; i<args.length-2; i++){
                                        info+=args[i];
                                        info+=" ";
                                    }
                                    info+=args[args.length-1];

                                String message = "**Ressource shared by " + messageReceivedEvent.getAuthor().mention() +" :**\n"
                                        + "Type: *" + type + "*\n"
                                        + "Name: *" + name + "*\n"
                                        + "```" + info + "```";
                                messageReceivedEvent.getChannel().sendMessage(message);
                                    messageReceivedEvent.getMessage().delete();

                                }
                            else{
                                messageReceivedEvent.getChannel().sendMessage("An error has occured, please use the correct command syntax :\n"
                                        +"```md\n"
                                        +"@Electroid#4749 addRessource <type> <name> : <infos>\n\n"
                                        +"<type> : can be \"plug\", \"board\", \"ic\", \"component\" or \"others\"```");
                            }

                        }

                        catch(Exception e){
                            e.printStackTrace();
                            messageReceivedEvent.getChannel().sendMessage("An error has occured, please use the correct command syntax :\n"
                                    +"**```md\n"
                                    +"@Electroid#4749 addRessource <type> <name> : <infos>```**"
                                    +"```md\n"
                                    +"<type> : can be \"plug\", \"board\", \"ic\", \"component\" or \"others\""
                                    +"<name> : the name of the plug/board/ic/component (ex: \"arduino UNO\", \"L293D\", ...)"
                                    +"<infos> : all the infos you have gathered about it (symbols, voltage ratings, pinouts ...)```");
                        }

                }
        }

    }
}