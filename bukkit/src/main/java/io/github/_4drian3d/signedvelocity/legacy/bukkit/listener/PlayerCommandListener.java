package io.github._4drian3d.signedvelocity.legacy.bukkit.listener;

import io.github._4drian3d.signedvelocity.legacy.bukkit.SignedVelocity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandListener implements Listener {
  private final SignedVelocity plugin;

  public PlayerCommandListener(SignedVelocity plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onComanD(PlayerCommandPreprocessEvent event) {
    Player player = event.getPlayer();
    plugin.getCommandQueue().dataFrom(player.getUniqueId())
            .nextResult()
            .thenAccept(result -> {
              if (result.cancelled()) {
                event.setCancelled(true);
              } else {
                final String modified = result.toModify();
                if (modified != null) {
                  event.setMessage(modified);
                }
              }
            }).join();
  }
}
