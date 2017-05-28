package fr.gwenzy.discord.electroid.events;

import fr.gwenzy.discord.electroid.Main;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;


public class ReadyListener implements IListener<ReadyEvent>{
    public void handle(ReadyEvent readyEvent) {
        Main.electroid.changePlayingText("@Electroid help");
    }
}
