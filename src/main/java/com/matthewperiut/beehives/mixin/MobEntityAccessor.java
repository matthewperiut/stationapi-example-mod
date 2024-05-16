package com.matthewperiut.beehives.mixin;

import net.minecraft.class_61;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MobEntity.class)
public interface MobEntityAccessor {
    @Accessor("field_661")
    class_61 getField_661();
    @Accessor("field_661")
    void setField_661(class_61 thing);
}
