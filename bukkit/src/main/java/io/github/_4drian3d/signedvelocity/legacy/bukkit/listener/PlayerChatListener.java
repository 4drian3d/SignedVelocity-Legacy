package io.github._4drian3d.signedvelocity.legacy.bukkit.listener;

import io.github._4drian3d.signedvelocity.legacy.bukkit.SignedVelocity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {
  private final SignedVelocity plugin;

  public PlayerChatListener(SignedVelocity plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onChat(AsyncPlayerChatEvent event) {
   Player player = event.getPlayer();
    plugin.getChatQueue().dataFrom(player.getUniqueId())
            .nextResult()
            .thenAccept(result -> {
              if (result.cancelled()) {
                event.setCancelled(true);
              } else {
                final String modifiedChat = result.toModify();
                if (modifiedChat != null) {
                  event.setMessage(modifiedChat);
                }
              }
            }).join();
  }
}
