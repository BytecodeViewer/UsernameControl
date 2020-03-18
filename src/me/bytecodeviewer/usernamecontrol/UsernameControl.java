package me.bytecodeviewer.usernamecontrol;

import me.bytecodeviewer.usernamecontrol.commands.MainCommand;
import me.bytecodeviewer.usernamecontrol.listeners.LoginListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class UsernameControl extends JavaPlugin {

    public static String internalPluginPrefix = ChatColor.GREEN + " UsernameControl " + ChatColor.RESET;
    public static String pluginPrefix;
    public static List<String> invalidCharacters = new ArrayList<>();
    public static String duplicateUserMessage;
    public static String invalidUsernameMessage;
    public static String usernameLengthMessage;
    public static boolean duplicateUser;
    public static boolean invalidUsername;
    public static boolean usernameLength;
    public static int maxUsernameLength;
    public static int minUsernameLength;

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
        final FileConfiguration configuration = UsernameControl.getInstance().getConfig();
        try {
            invalidCharacters.addAll(configuration.getStringList("invalid-characters"));
            pluginPrefix = ChatColor.translateAlternateColorCodes('&', configuration.getString("prefix"));
            duplicateUser = configuration.getBoolean("duplicate.enabled");
            duplicateUserMessage = ChatColor.translateAlternateColorCodes('&', configuration.getString("duplicate.message"));
            invalidUsername = configuration.getBoolean("invalid-names.enabled");
            invalidUsernameMessage = ChatColor.translateAlternateColorCodes('&', configuration.getString("invalid-names.message"));
            usernameLength = configuration.getBoolean("length.enabled");
            usernameLengthMessage = ChatColor.translateAlternateColorCodes('&', configuration.getString("length.message"));
            maxUsernameLength = configuration.getInt("length.max-length");
            minUsernameLength = configuration.getInt("length.min-length");
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
