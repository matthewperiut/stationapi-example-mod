package com.matthewperiut.beehives;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class ItemListener {
    @Entrypoint.Namespace
    private static final Namespace NAMESPACE = Null.get();

    public static Item GlassBottle;
    public static Item HoneyBottle;
    public static Item Honeycomb;

    @EventListener
    public static void registerItems(ItemRegistryEvent event) {
        GlassBottle = new TemplateItem(NAMESPACE.id("glass_bottle"))
                    .setTranslationKey(NAMESPACE.id("glass_bottle"));
        HoneyBottle = new TemplateItem(NAMESPACE.id("honey_bottle"))
                    .setTranslationKey(NAMESPACE.id("honey_bottle"))
                    .setMaxCount(1);
        Honeycomb = new TemplateItem(NAMESPACE.id("honeycomb"))
                    .setTranslationKey(NAMESPACE.id("honeycomb"));
    }
}
