package com.matthewperiut.awesometutorial.block;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.template.block.TemplateStairsBlock;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class TutorialBlocks {
    @Entrypoint.Namespace
    private static final Namespace NAMESPACE = Null.get();

    public static Block HayBlock;
    public static Block HayBlockStairs;

    @EventListener
    public static void registerBlocks(BlockRegistryEvent event) {
        HayBlock = new HayBlock(NAMESPACE.id("hay_block"), Material.LEAVES)
                .setSoundGroup(Block.DIRT_SOUND_GROUP)
                .setTranslationKey(NAMESPACE.id("hay_block"))
                .setHardness(0.5F);
        HayBlockStairs = new TemplateStairsBlock(NAMESPACE.id("hay_block_stairs"), HayBlock)
                .setTranslationKey(NAMESPACE.id("hay_block_stairs"));
    }
}
