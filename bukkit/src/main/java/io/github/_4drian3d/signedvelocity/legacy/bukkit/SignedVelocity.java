package io.github._4drian3d.signedvelocity.legacy.bukkit;

import io.github._4drian3d.signedvelocity.legacy.bukkit.listener.PlayerChatListener;
import io.github._4drian3d.signedvelocity.legacy.bukkit.listener.PlayerCommandListener;
import io.github._4drian3d.signedvelocity.legacy.bukkit.listener.PlayerQuitListener;
import io.github._4drian3d.signedvelocity.legacy.bukkit.listener.PluginMessageListener;
import io.github._4drian3d.signedvelocity.legacy.common.SignedQueue;
import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SignedVelocity extends JavaPlugin {

  public static final String CHANNEL = "signedvelocity:main";
  private final SignedQueue chatQueue = new SignedQueue();

  private final SignedQueue commandQueue = new SignedQueue();


  @Override
  public void onEnable() {
    final Server server = getServer();
    server.getMessenger().registerIncomingPluginChannel(this, CHANNEL, new PluginMessageListener(this));

    final PluginManager pluginManager = server.getPluginManager();
    final Listener[] listeners = {
            new PlayerChatListener(this),
            new PlayerCommandListener(this),
            new PlayerQuitListener(this)
    };
    for (final Listener listener : listeners) {
      pluginManager.registerEvents(listener, this);
    }
  }

  public SignedQueue getChatQueue() {
    return chatQueue;
  }

  public SignedQueue getCommandQueue() {
    return commandQueue;
  }
}
