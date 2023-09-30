package xyz.naphy.tpall.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;
import xyz.naphy.tpall.TPAll;

public class TPAllReload extends BukkitCommand {

    public TPAllReload() {
        super("tpallreload");
        this.setPermission("tpall.admin");
    }
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        TPAll.plugin.saveDefaultConfig();
        TPAll.plugin.reloadConfig();
        sender.sendMessage(TPAll.generateMessage("&7[&bTPAll&7] The plugin has been reloaded &asuccessfully&7!"));
        return true;
    }
}
