package com.matthewperiut.beehives.client.entity;

import com.matthewperiut.beehives.client.entity.renderer.BeeEntityRenderer;
import com.matthewperiut.beehives.entity.BeeEntity;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;

public class EntityRendererListener {
    @EventListener
    public void registerEntityRenderers(EntityRendererRegisterEvent event) {
        event.renderers.put(BeeEntity.class, new BeeEntityRenderer());
    }
}
