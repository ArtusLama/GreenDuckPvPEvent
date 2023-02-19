package de.artus.greenduckevent.chestloot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class lootItem {


    private final Material material;
    private final Map<Enchantment, Integer> enchantments = new HashMap<>();
    private final double chance;

    private final int minAmount;
    private final int maxAmount;
    public lootItem(ConfigurationSection section){
        Material material;

        try {
            material = Material.valueOf(section.getString("material"));
        } catch (Exception e){
            material = Material.AIR;
        }
        this.material = material;

        ConfigurationSection enchantmentSection = section.getConfigurationSection("enchantments");
        if (enchantmentSection != null){
            for (String key : enchantmentSection.getKeys(false)){
                Bukkit.getLogger().severe( "[" + section.getName() + "] " + material + " -> " + key);
                Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(key.toLowerCase()));

                if (enchantment != null){
                    int level = enchantmentSection.getInt(key);
                    enchantments.put(enchantment, level);
                }
            }
        }

        chance = section.getDouble("chance");
        minAmount = section.getInt("minAmount");
        maxAmount = section.getInt("maxAmount");
    }

    public boolean shouldFill(Random random){
        return random.nextDouble() < chance;
    }

    public ItemStack make(ThreadLocalRandom random){
        int amount = random.nextInt(minAmount, maxAmount + 1);
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (!enchantments.isEmpty()){
            for (Map.Entry<Enchantment, Integer> enchantmentEntry : enchantments.entrySet()){
                itemMeta.addEnchant(enchantmentEntry.getKey(), enchantmentEntry.getValue(), true);
            }
        }

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
