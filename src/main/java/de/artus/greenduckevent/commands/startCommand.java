package de.artus.greenduckevent.commands;

import de.artus.greenduckevent.ConfigManager;
import de.artus.greenduckevent.resetSystem.GameMap;
import de.artus.greenduckevent.resetSystem.LocalGameMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class startCommand implements CommandExecutor {

    GameMap map;
    public startCommand(GameMap gameMap){
        this.map = gameMap;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender.isOp()){


            for (Player player : Bukkit.getOnlinePlayers()){
                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Das Spiel wird geladen. Bitte warten...");
            }


            map.restoreFromSource(args[0]);
            for (Player player : Bukkit.getOnlinePlayers()){
                player.teleport(new Location(map.getWorld(), -351, 102, -154));
                player.getInventory().clear();
            }

        }

        return false;
    }
}
