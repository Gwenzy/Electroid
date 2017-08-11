package fr.gwenzy.discord.electroid.events;

import fr.gwenzy.discord.electroid.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.util.Image;


public class ReadyListener implements IListener<ReadyEvent>{
    public void handle(ReadyEvent readyEvent) {
        Main.electroid.changePlayingText("@Electroid help");
        Main.electroid.changeAvatar(Image.forUrl("png", "https://cdn.discordapp.com/attachments/273584318876745728/345611838261821441/Image5.png"));


    }
}
