package io.github._4drian3d.signedvelocity.legacy.bukkit.listener;

import io.github._4drian3d.signedvelocity.legacy.bukkit.SignedVelocity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitListener implements Listener {
  private final SignedVelocity plugin;

  public PlayerQuitListener(SignedVelocity plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onquit(PlayerQuitEvent event) {
    final UUID playerUUID = event.getPlayer().getUniqueId();
    plugin.getChatQueue().removeData(playerUUID);
    plugin.getCommandQueue().removeData(playerUUID);
  }
}
