package com.matthewperiut.haybales;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.template.block.TemplateStairsBlock;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class BlockListener {
    @Entrypoint.Namespace
    private static final Namespace MOD_ID = Null.get();

    public static Block HayBlock;
    public static Block HayBlockStairs;

    @EventListener
    public static void registerBlocks(BlockRegistryEvent event) {
        HayBlock = new HayBlock(MOD_ID.id("hay_block"), Material.LEAVES)
                .setTranslationKey(MOD_ID.id("hay_block"))
                .setSoundGroup(Block.DIRT_SOUND_GROUP)
                .setHardness(0.6F)
                .setResistance(1.F);
        HayBlockStairs = new TemplateStairsBlock(MOD_ID.id("hay_block_stairs"), HayBlock)
                .setTranslationKey(MOD_ID.id("hay_block_stairs"));
    }
}
