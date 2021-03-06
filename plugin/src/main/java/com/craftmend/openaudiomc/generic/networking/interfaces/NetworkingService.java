package com.craftmend.openaudiomc.generic.networking.interfaces;

import com.craftmend.openaudiomc.generic.networking.abstracts.AbstractPacket;
import com.craftmend.openaudiomc.generic.networking.client.objects.player.ClientConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Consumer;

public abstract class NetworkingService {

    protected Map<UUID, Consumer<ClientConnection>> createdConnectionSubscribers = new HashMap<>();
    protected Map<UUID, Consumer<ClientConnection>> removedConnectionSubscribers = new HashMap<>();

    public abstract void connectIfDown();
    public abstract void send(Authenticatable client, AbstractPacket packet);
    public abstract void triggerPacket(AbstractPacket abstractPacket);
    public abstract void remove(UUID player);
    public abstract void stop();
    public abstract void addEventHandler(INetworkingEvents events);
    public abstract ClientConnection register(Player player);
    public abstract ClientConnection register(ProxiedPlayer player);
    public abstract ClientConnection register(com.velocitypowered.api.proxy.Player player);
    public abstract Set<INetworkingEvents> getEvents();
    public abstract ClientConnection getClient(UUID uuid);
    public abstract Collection<ClientConnection> getClients();
    public abstract int getThroughputPerSecond();

    public UUID subscribeToConnections(Consumer<ClientConnection> handler) {
        UUID id = UUID.randomUUID();
        createdConnectionSubscribers.put(id, handler);
        return id;
    }

    public UUID subscribeToDisconnections(Consumer<ClientConnection> handler) {
        UUID id = UUID.randomUUID();
        removedConnectionSubscribers.put(id, handler);
        return id;
    }

    public void unsubscribeClientEventHandler(UUID uuid) {
        removedConnectionSubscribers.remove(uuid);
        createdConnectionSubscribers.remove(uuid);
    }

}
