package de.artus.greenduckevent;

import de.artus.greenduckevent.events.onDeath;
import de.artus.greenduckevent.events.onJoin;
import de.artus.greenduckevent.resetSystem.GameMap;
import de.artus.greenduckevent.resetSystem.LocalGameMap;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import de.artus.greenduckevent.commands.*;

import java.io.File;

public final class GreenduckEvent extends JavaPlugin {


    private GameMap map;
    private static Plugin plugin;

    public static Plugin getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {

        plugin = this;

        getDataFolder().mkdirs();
        File gameMapsFolder = new File(getDataFolder(), "gameMaps");
        if (!gameMapsFolder.exists()){
            gameMapsFolder.mkdirs();
        }

        map = new LocalGameMap(gameMapsFolder, "pvpworld", false);

        getCommand("startevent").setExecutor(new startCommand(map));
        getCommand("stopevent").setExecutor(new stopCommand(map));
        getCommand("setuploot").setExecutor(new setupLootCommand());
        getCommand("go").setExecutor(new goCommand());
        getCommand("warp").setExecutor(new warpCommand());
        getCommand("spawn").setExecutor(new spawnCommand());

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new onDeath(), this);
        manager.registerEvents(new onJoin(), this);




    }

    @Override
    public void onDisable() {

    }
}
