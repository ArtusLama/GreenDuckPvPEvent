package de.artus.greenduckevent;

import de.artus.greenduckevent.chestloot.lootGen;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    public static File ConfigFile = new File("plugins/event", "config.yml");
    public static FileConfiguration Config = YamlConfiguration.loadConfiguration(ConfigFile);

    public static void save() throws IOException {
        Config.save(ConfigFile);
    }
    private static final lootGen lootGenerator = new lootGen(Config);

    public static void resetLoot(World world){
        lootGenerator.resetLoot(world);
    }
    public static void spawnLoot(){
        lootGenerator.genLootForChests();
    }


}
