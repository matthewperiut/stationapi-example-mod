package com.matthewperiut.beehives.client.entity.renderer;

import com.matthewperiut.beehives.client.entity.model.BeeEntityModel;
import net.minecraft.client.render.entity.*;
import net.minecraft.entity.LivingEntity;

public class BeeEntityRenderer extends LivingEntityRenderer {
    private static final String ANGRY_TEXTURE = "textures/entity/bee/bee_angry.png";
    private static final String ANGRY_NECTAR_TEXTURE = "textures/entity/bee/bee_angry_nectar.png";
    private static final String PASSIVE_TEXTURE = "assets/beehives/stationapi/textures/entity/bee/bee.png";
    private static final String NECTAR_TEXTURE = "textures/entity/bee/bee_nectar.png";

    public BeeEntityRenderer() {
        super(new BeeEntityModel(), 0.4F);
    }

    @Override
    public void render(LivingEntity arg, double d, double e, double f, float g, float h) {
        super.render(arg, d, e, f, g, h);
    }
}
