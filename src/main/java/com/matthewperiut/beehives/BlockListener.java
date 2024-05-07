package com.matthewperiut.beehives;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class BlockListener {
    @Entrypoint.Namespace
    private static final Namespace NAMESPACE = Null.get();

    public static Block Beehive;

    @EventListener
    public static void registerBlocks(BlockRegistryEvent event) {
        Beehive = new BeehiveBlock(NAMESPACE.id("beehive"), Material.WOOD)
                .setTranslationKey(NAMESPACE.id("beehive"))
                .setSoundGroup(Block.WOOD_SOUND_GROUP)
                .setHardness(2.0F)
                .setResistance(5.F);
    }
}
