package me.bytecodeviewer.usernamecontrol;

import me.bytecodeviewer.usernamecontrol.commands.MainCommand;
import me.bytecodeviewer.usernamecontrol.listeners.LoginListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class UsernameControl extends JavaPlugin {
    public static boolean duplicateUser;
    public static String duplicateUserMessage;
    public static boolean invalidUsername;
    public static String invalidUsernameMessage;
    public static boolean usernameLength;
    public static String usernameLengthMessage;
    public static int maxUsernameLength;
    public static int minUsernameLength;
    public static String internalPluginPrefix = ChatColor.GREEN + " UsernameControl " + ChatColor.RESET;
    public static String pluginPrefix;
    public static List<String> invalidCharacters = new ArrayList<>();
    private static UsernameControl instance;
    private PluginManager pluginManager;

    public static UsernameControl getInstance() {
        return instance;
    }

    /**
     * Get values from config
     *
     * @since Build 1
     */

    public static void getValues() {
        UsernameControl.getInstance().reloadConfig();
        try {
            for (String characters : UsernameControl.getInstance().getConfig().getStringList("invalid-characters")) {
                invalidCharacters.add(String.valueOf(characters));
            }
            pluginPrefix = ChatColor.translateAlternateColorCodes('&', UsernameControl.getInstance().getConfig().getString("prefix"));
            duplicateUser = UsernameControl.getInstance().getConfig().getBoolean("duplicate.enabled");
            duplicateUserMessage = ChatColor.translateAlternateColorCodes('&', UsernameControl.getInstance().getConfig().getString("duplicate.message"));
            invalidUsername = UsernameControl.getInstance().getConfig().getBoolean("invalid-names.enabled");
            invalidUsernameMessage = ChatColor.translateAlternateColorCodes('&', UsernameControl.getInstance().getConfig().getString("invalid-names.message"));
            usernameLength = UsernameControl.getInstance().getConfig().getBoolean("length.enabled");
            usernameLengthMessage = ChatColor.translateAlternateColorCodes('&', UsernameControl.getInstance().getConfig().getString("length.message"));
            maxUsernameLength = UsernameControl.getInstance().getConfig().getInt("length.max-length");
            minUsernameLength = UsernameControl.getInstance().getConfig().getInt("length.min-length");
        } catch (Exception e) {
            UsernameControl.getInstance().getLogger().severe("Something is wrong with your config file.");
            UsernameControl.getInstance().pluginManager.disablePlugin(UsernameControl.getInstance());
        }
    }

    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getValues();
        pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new LoginListener(), this);
        getCommand("UsernameControl").setExecutor(new MainCommand());
    }
}
