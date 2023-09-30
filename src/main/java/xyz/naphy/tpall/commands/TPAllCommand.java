package xyz.naphy.tpall.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.naphy.tpall.TPAll;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static xyz.naphy.tpall.TPAll.plugin;

public class TPAllCommand extends BukkitCommand {

    public TPAllCommand() {
        super("tpall");
        this.setPermission("tpall.admin");
    }

    @Override
    @SuppressWarnings("all")
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&4*&7] You don't have access to this command!"));
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage(TPAll.generateMessage(TPAll.plugin.getConfig().getString("No arg provided")));
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if (player == null) {
            sender.sendMessage(TPAll.generateMessage(TPAll.plugin.getConfig().getString("No player found").replace("%argument%", args[0])));
            return true;
        }
        Location loc = player.getLocation();
        List<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new TimerTask() {
            int i = 0;
            @Override
            public void run() {
                try {
                    if (i >= players.size()) {
                        service.shutdown();
                    }
                    else {
                        Player temp = players.get(i);
                        if (!temp.getName().equals(player.getName())) {
                            Bukkit.getScheduler().runTask(plugin, () -> temp.teleport(loc));
                            temp.sendMessage(TPAll.generateMessage(TPAll.plugin.getConfig().getString("TP Player").replace("%argument%", player.getName())));
                        }
                        i++;
                    }
                } catch (Exception e) {
                    System.err.println(e);
                }

            }
        }, 0, 200, TimeUnit.MILLISECONDS);
        sender.sendMessage(TPAll.generateMessage(TPAll.plugin.getConfig().getString("TP Admin").replace("%argument%", player.getName())));
        return true;
    }
}
