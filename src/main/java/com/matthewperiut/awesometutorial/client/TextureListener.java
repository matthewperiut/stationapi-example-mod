package com.matthewperiut.awesometutorial.client;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.client.texture.atlas.ExpandableAtlas;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class TextureListener {
    @Entrypoint.Namespace
    private static final Namespace NAMESPACE = Null.get();

    public static int hay_block_side;
    public static int hay_block_top;

    @EventListener
    public static void registerTextures(TextureRegisterEvent event) {
        ExpandableAtlas terrain = Atlases.getTerrain();
        hay_block_side = terrain.addTexture(NAMESPACE.id("block/hay_block_side")).index;
        hay_block_top = terrain.addTexture(NAMESPACE.id("block/hay_block_top")).index;
    }
}
