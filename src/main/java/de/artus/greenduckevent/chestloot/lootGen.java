package de.artus.greenduckevent.chestloot;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class lootGen {

    private final Set<Location> chestLocations = new HashSet<>();

    private final List<lootItem> lootItems = new ArrayList<>();


    public lootGen(FileConfiguration config){
        ConfigurationSection itemsSection = config.getConfigurationSection("lootItems");

        if (itemsSection == null){
            Bukkit.getLogger().severe("Config has no lootItems section!");
        }

        for (String key : itemsSection.getKeys(false)){
            ConfigurationSection section = itemsSection.getConfigurationSection(key);
            lootItems.add(new lootItem(section));
        }

    }

    public void resetLoot(World world){
        chestLocations.clear();
        chestLocations.add(new Location(world ,-319, 100, -188));
        chestLocations.add(new Location(world ,-336, 99, -172));
        chestLocations.add(new Location(world ,-324, 101, -168));
        chestLocations.add(new Location(world ,-302, 99, -162));
        chestLocations.add(new Location(world ,-292, 100, -200));
        chestLocations.add(new Location(world ,-312, 100, -186));
        chestLocations.add(new Location(world ,-278, 102, -179));
        chestLocations.add(new Location(world ,-261, 101, -166));
        chestLocations.add(new Location(world ,-251, 101, -165));
        chestLocations.add(new Location(world ,-256, 100, -151));
        chestLocations.add(new Location(world ,-238, 107, -145));
        chestLocations.add(new Location(world ,-237, 101, -123));
        chestLocations.add(new Location(world ,-231, 101, -120));
        chestLocations.add(new Location(world ,-256, 101, -130));
        chestLocations.add(new Location(world ,-271, 101, -122));
        chestLocations.add(new Location(world ,-275, 101, -134));
        chestLocations.add(new Location(world ,-279, 105, -144));
        chestLocations.add(new Location(world ,-281, 105, -150));
        chestLocations.add(new Location(world ,-279, 102, -162));
        chestLocations.add(new Location(world ,-289, 103, -138));
        chestLocations.add(new Location(world ,-275, 103, -99));
        chestLocations.add(new Location(world ,-261, 101, -95));
        chestLocations.add(new Location(world ,-286, 101, -88));
        chestLocations.add(new Location(world ,-286, 101, -108));
        chestLocations.add(new Location(world ,-288, 98, -120));
        chestLocations.add(new Location(world ,-286, 98, -127));
        chestLocations.add(new Location(world ,-260, 101, -106));
        chestLocations.add(new Location(world ,-307, 99, -88));
        chestLocations.add(new Location(world ,-304, 99, -109));
        chestLocations.add(new Location(world ,-320, 102, -121));
        chestLocations.add(new Location(world ,-309, 101, -124));
        chestLocations.add(new Location(world ,-318, 101, -132));
        chestLocations.add(new Location(world ,-329, 102, -124));
        chestLocations.add(new Location(world ,-336, 101, -141));
        chestLocations.add(new Location(world ,-223, 113, -130));
        chestLocations.add(new Location(world ,-265, 100, -196));

        for (Location location : chestLocations){
            Block block = location.getBlock();
            if (block.getState() instanceof Chest) {
                Chest chest = (Chest) block.getState();
                chest.getInventory().clear();
            }
        }
    }
    public void genLootForChests(){


        for (Location location : chestLocations){
            Block block = location.getBlock();
            if (block.getState() instanceof Chest) {
                Chest chest = (Chest) block.getState();
                fill(chest.getInventory());
            }
        }
    }


    public void fill(Inventory inventory){
        inventory.clear();

        ThreadLocalRandom random = ThreadLocalRandom.current();
        Set<lootItem> used = new HashSet<>();

        for (int slotIndex = 0; slotIndex < inventory.getSize(); slotIndex++){
            lootItem randomItem = lootItems.get(random.nextInt(lootItems.size()));
            if (used.contains(randomItem)) continue;
            used.add(randomItem);

            if (randomItem.shouldFill(random)) {
                ItemStack itemStack = randomItem.make(random);
                inventory.setItem(slotIndex, itemStack);
            }
        }
    }
}
