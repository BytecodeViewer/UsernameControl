package me.bytecodeviewer.usernamecontrol.listeners;

import me.bytecodeviewer.usernamecontrol.UsernameControl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        if (UsernameControl.duplicateUser)
            if (Bukkit.getServer().getPlayer(player.getName()) != null)
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, UsernameControl.pluginPrefix + UsernameControl.duplicateUserMessage);

        if (UsernameControl.invalidUsername) {
            for (String entry : UsernameControl.invalidCharacters) {
                if (player.getName().contains(entry))
                    event.disallow(PlayerLoginEvent.Result.KICK_OTHER, UsernameControl.pluginPrefix + UsernameControl.invalidUsernameMessage);
            }
        }

        if (UsernameControl.usernameLength)
            if (player.getName().length() > UsernameControl.maxUsernameLength || player.getName().length() < UsernameControl.minUsernameLength)
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, UsernameControl.pluginPrefix + UsernameControl.usernameLengthMessage);
    }
}
