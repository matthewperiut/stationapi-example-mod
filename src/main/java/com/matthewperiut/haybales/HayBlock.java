package com.matthewperiut.haybales;

import net.minecraft.block.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class HayBlock extends TemplateBlock {
    public HayBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public int getTexture(int side) {
        if (side == 0 || side == 1) {
            return TextureListener.hay_block_top;
        } else {
            return TextureListener.hay_block_side;
        }
    }
}
