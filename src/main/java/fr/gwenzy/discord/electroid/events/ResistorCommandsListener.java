package fr.gwenzy.discord.electroid.events;

import fr.gwenzy.discord.electroid.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IEmoji;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;


public class ResistorCommandsListener implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        if(messageReceivedEvent.getMessage().getFormattedContent().startsWith("@Electroid ")){
                String[] args = messageReceivedEvent.getMessage().getFormattedContent().split(" ");


            if(args.length>1) {

                if(args[1].equalsIgnoreCase("color")){
                    if(args.length>3){
                        //String to int value
                        System.out.println("Value Command");
                        boolean ok = true;


                        long value=0;
                        double percent=0.0;
                        try{
                            value = Long.parseLong(args[2]);
                        }
                        catch(Exception e){
                            try{
                                double val = Double.parseDouble(args[2].substring(0, args[2].length()-1));
                                switch(args[2].toUpperCase().charAt(args[2].length()-1)){
                                    case 'K':
                                        value=(long) (val*1000);
                                        break;
                                    case 'M':
                                        value=(long) (val*1000000);
                                        break;
                                    case 'G':
                                        value=(long) (val*1000000000);
                                        break;

                                }
                            }
                            catch(Exception ex){
                                messageReceivedEvent.getChannel().sendMessage("Value error");
                                ok = false;
                            }
                        }

                        try{
                            if(args[3].endsWith("%"))
                                percent = Double.parseDouble(args[3].substring(0, args[3].length()-1));
                            else{
                                messageReceivedEvent.getChannel().sendMessage("Percentage error");
                                ok = false;
                            }
                        }
                        catch(Exception e){
                            ok= false;
                            messageReceivedEvent.getChannel().sendMessage("Percentage error");
                        }

                        if(ok){
                            System.out.println("Calculating with "+value+"...");
                            String r1, r2, r3, r4;
                            String[] colors={"black", "brown", "red", "orange", "yellow","green", "blue", "purple", "grey", "white", "silver", "gold"};
                            IEmoji tolerance = null;
                            System.out.println(args.length+":"+args[3]);
                            r1 = colors[Integer.parseInt(String.valueOf(String.valueOf(value).charAt(0)))];
                            r2 = colors[Integer.parseInt(String.valueOf(String.valueOf(value).charAt(1)))];
                            r3 = (args.length>4 && args[4].equalsIgnoreCase("5"))?colors[Integer.parseInt(String.valueOf(String.valueOf(value).charAt(2)))]:"";
                            r4 = (args.length>4 && args[4].equalsIgnoreCase("5"))?colors[String.valueOf(value).length()-3]:colors[String.valueOf(value).length()-2];
                            String s = String.valueOf(percent);
                            if (s.equals("0.05")) {
                                tolerance = messageReceivedEvent.getGuild().getEmojiByName("grey");

                            } else if (s.equals("0.10")) {
                                tolerance = messageReceivedEvent.getGuild().getEmojiByName("purple");

                            } else if (s.equals("0.1")) {
                                tolerance = messageReceivedEvent.getGuild().getEmojiByName("purple");

                            } else if (s.equals("0.25")) {
                                tolerance = messageReceivedEvent.getGuild().getEmojiByName("blue");

                            } else if (s.equals("0.5")) {
                                tolerance = messageReceivedEvent.getGuild().getEmojiByName("green");

                            } else if (s.equals("1.0")) {
                                tolerance = messageReceivedEvent.getGuild().getEmojiByName("brown");

                            } else if (s.equals("2.0")) {
                                tolerance = messageReceivedEvent.getGuild().getEmojiByName("red");

                            } else if (s.equals("5.0")) {
                                tolerance = messageReceivedEvent.getGuild().getEmojiByName("gold");

                            } else if (s.equals("10.0")) {
                                tolerance = messageReceivedEvent.getGuild().getEmojiByName("silver");

                            }


                            EmbedBuilder eb = new EmbedBuilder();
                            eb.withColor(Color.GREEN);
                            eb.withDesc("Resistor color code request");
                            eb.appendField("Value", args[2]+"Ω", true);
                            eb.appendField("Tolerance", percent+"%", true);
                            eb.appendField("Color code", ""+messageReceivedEvent.getGuild().getEmojiByName("resist1")+messageReceivedEvent.getGuild().getEmojiByName(r1)+messageReceivedEvent.getGuild().getEmojiByName(r2)+(!r3.equals("")?messageReceivedEvent.getGuild().getEmojiByName(r3):"")+messageReceivedEvent.getGuild().getEmojiByName(r4)+tolerance+messageReceivedEvent.getGuild().getEmojiByName("resist2"), true);

                            messageReceivedEvent.getChannel().sendMessage(eb.build());
                        }



                    }
                    else{
                        messageReceivedEvent.getChannel().sendMessage("Please use the correct syntax");
                    }


                }
                else if(args[1].equalsIgnoreCase("value"))
                {
                    String message = messageReceivedEvent.getMessage().getFormattedContent();
                    if(args.length>1){
                        try{
                            String colorCode;
                            colorCode = message.replaceAll(" ", "").substring(15).replaceAll("><", "-").replace(":", "").replaceAll("resist1", "").replaceAll("resist2", "");
                            colorCode = colorCode.replaceAll("[^A-Za-z \\-]", "");
                            if(colorCode.startsWith("-")){
                                colorCode = colorCode.substring(1, colorCode.length()-1);
                            }
                            System.out.println("color code : "+colorCode);
                            String[] colors = colorCode.toLowerCase().split("-");
                            System.out.println("Working with "+message.replaceAll(" ", "").substring(15).replaceAll("::", "-").replace(":", "").replaceAll("resist1", "").replaceAll("resist2", "")+" : "+colors[0]+", "+colors[1]+", "+colors[2]+", "+colors[3]);
                            String emValue=""+ messageReceivedEvent.getGuild().getEmojiByName("resist1");
                            String valueStr = "0";
                            int value = 0;
                            int multiplier = 0;
                            Double tolerance = 0.0;
                            int i = 0;

                            if(colors.length==4){
                                for(String color : colors){
                                    emValue+=messageReceivedEvent.getGuild().getEmojiByName(color);
                                    System.out.println("New color for i="+i+": "+color);
                                    if(i<=1){
                                        valueStr+=Main.resistorValues.get(color);
                                    }
                                    else if(i==2){
                                        multiplier = (int) Math.pow(10, Main.resistorValues.get(color));
                                    }
                                    else if(i==3){
                                        tolerance = Main.resistorTolerances.get(color);

                                    }
                                    i++;
                                }
                            }
                            else{
                                for(String color : colors){
                                    emValue+=messageReceivedEvent.getGuild().getEmojiByName(color);

                                    System.out.println("New color for i="+i+": "+color);
                                    if(i<=2){
                                        valueStr+=Main.resistorValues.get(color);
                                    }
                                    else if(i==3){
                                        multiplier = (int) Math.pow(10, Main.resistorValues.get(color));
                                    }
                                    else if(i==4){
                                        tolerance = Main.resistorTolerances.get(color);
                                    }
                                    i++;
                                }
                            }

                            emValue+=messageReceivedEvent.getGuild().getEmojiByName("resist2");
                            value = Integer.parseInt(valueStr)*multiplier;
                            String valueDisp = "";
                            if(value>=1000){
                                if(value>=1000000){
                                    valueDisp = String.valueOf(Double.valueOf(value)/1000000)+"M";
                                }
                                else{
                                    valueDisp = String.valueOf(Double.valueOf(value)/1000)+"k";
                                }
                            }
                            else
                                valueDisp = String.valueOf(value);
                            //ggme.getChannel().sendMessage("Resistance value is : "+valueDisp.replace(".0", "")+"Ω - "+tolerance+"%").complete();

                            EmbedBuilder eb = new EmbedBuilder();
                            eb.withColor(Color.GREEN);
                            eb.withDesc("Resistor value request");
                            eb.appendField("Color code", emValue+"       ",true);
                            eb.appendField("Value", valueDisp.replace(".0", "")+"Ω",true);
                            eb.appendField("Tolerance", tolerance+"%" ,true);

                            messageReceivedEvent.getChannel().sendMessage(eb.build());

                        }catch(Exception e){
                            e.printStackTrace();
                            messageReceivedEvent.getChannel().sendMessage("An error has occured, please check your color code syntax");
                        }
                    }

            }

        }
    }
    }
}
