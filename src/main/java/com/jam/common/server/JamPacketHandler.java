package com.jam.common.server;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class JamPacketHandler {
    public static final SimpleNetworkWrapper NetworkInstance = NetworkRegistry.INSTANCE.newSimpleChannel("justamagnet");

}
