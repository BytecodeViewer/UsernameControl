package me.bytecodeviewer.usernamecontrol.commands;

import me.bytecodeviewer.usernamecontrol.UsernameControl;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(UsernameControl.internalPluginPrefix + ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "(" + ChatColor.RESET + "Version: Build "
                    + UsernameControl.getInstance().getDescription().getVersion() + ChatColor.GRAY + ")" + ChatColor.DARK_GRAY + ", " + ChatColor.GRAY + "("
                    + ChatColor.RESET + "~BytecodeViewer" + ChatColor.GRAY + ")" + ChatColor.DARK_GRAY + "]");
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("UsernameControl.reload")) {
                    UsernameControl.getValues();
                    sender.sendMessage(UsernameControl.pluginPrefix + "Reload complete");
                } else {
                    sender.sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
                }
            }
        }

        return true;
    }
}
