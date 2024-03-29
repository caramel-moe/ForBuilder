package moe.caramel.forbuilder;

import moe.caramel.forbuilder.command.MainCommand;
import moe.caramel.forbuilder.command.SpeedCommand;
import moe.caramel.forbuilder.command.WorldCommand;
import moe.caramel.forbuilder.event.EventListener;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static final String DEBUG = "debug";
    public static final String WORLD_LIST = "worlds";

    @Override
    public void onLoad() {
        // Register Command
        this.registerCommand("forbuilder", new MainCommand(this));
        this.registerCommand("speed", new SpeedCommand());
        this.registerCommand("world", new WorldCommand(this));
    }

    @Override
    public void onEnable() {
        // Load Worlds
        for (final var name : this.getConfig().getStringList(WORLD_LIST)) {
            new WorldCreator(name).createWorld();
        }

        // Register Event
        this.getServer().getPluginManager().registerEvents(new EventListener(this), this);
    }
}
