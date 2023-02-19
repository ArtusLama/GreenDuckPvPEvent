package de.artus.greenduckevent.commands;

import de.artus.greenduckevent.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class setupLootCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender.isOp()){
            Player player = (Player) sender;
            ConfigManager.resetLoot(player.getWorld());
        }

        return false;
    }
}
