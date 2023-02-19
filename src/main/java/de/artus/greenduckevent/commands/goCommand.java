package de.artus.greenduckevent.commands;

import de.artus.greenduckevent.ConfigManager;
import de.artus.greenduckevent.GreenduckEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class goCommand implements CommandExecutor, Listener {


    private BukkitTask task;
    private int count;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender.isOp()){
            Player player = (Player) sender;
            ConfigManager.resetLoot(player.getWorld());

            for (Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "3 ... 2 ... 1 ... GOOO!!!");
                p.setGameMode(GameMode.SURVIVAL);
                p.setHealth(20);
            }
            count = 30;
            task = Bukkit.getScheduler().runTaskTimer(GreenduckEvent.getInstance(), ()-> {


                if (count == 15) {
                    for (Player p : Bukkit.getOnlinePlayers()){
                        p.sendMessage(ChatColor.GREEN + "Chests are now filled!");
                    }
                    ConfigManager.spawnLoot();
                }
                if (count <= 0) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendMessage(ChatColor.RED + "Die Schutzzeit ist jetzt vorbei!!!");
                    }
                    task.cancel();
                }
                count--;
            }, 20, 20);

            for (Player p : Bukkit.getOnlinePlayers()) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * 30, 255, true));
            }
        }


        return false;
    }
}
