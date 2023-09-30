package xyz.naphy.tpall;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.naphy.tpall.commands.TPAllCommand;
import xyz.naphy.tpall.commands.TPAllReload;

public final class TPAll extends JavaPlugin {
    public static TPAll plugin;

    @Override
    public void onEnable() {
        plugin = this;
        saveDefaultConfig();
        getServer().getCommandMap().register(new TPAllCommand().getName(), new TPAllCommand());
        getServer().getCommandMap().register(new TPAllReload().getName(), new TPAllReload());
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[" + this.getName() + "] Plugin is now enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[" + this.getName() + "] Plugin is now disabled!");
    }

    public static Component generateMessage(String string) {
        String text = string.replace("&0", "<reset><#000000>")
                .replace("&1", "<reset><#0000AA>")
                .replace("&2", "<reset><#00AA00>")
                .replace("&3", "<reset><#00AAAA>")
                .replace("&4", "<reset><#AA0000>")
                .replace("&5", "<reset><#AA00AA>")
                .replace("&6", "<reset><#FFAA00>")
                .replace("&7", "<reset><#AAAAAA>")
                .replace("&8", "<reset><#555555>")
                .replace("&9", "<reset><#5555FF>")
                .replace("&a", "<reset><#55FF55>")
                .replace("&b", "<reset><#55FFFF>")
                .replace("&c", "<reset><#FF5555>")
                .replace("&d", "<reset><#FF55FF>")
                .replace("&e", "<reset><#FFFF55>")
                .replace("&f", "<reset><#FFFFFF>")
                .replace("&l", "<b>")
                .replace("&o", "<i>")
                .replace("&n", "<u>")
                .replace("&m", "<st>")
                .replace("&k", "<obf>")
                .replace("&r", "<reset>");

        return MiniMessage.miniMessage().deserialize(text);
    }
}
