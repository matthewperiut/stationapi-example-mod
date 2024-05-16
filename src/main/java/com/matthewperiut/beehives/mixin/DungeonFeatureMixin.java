package com.matthewperiut.beehives.mixin;

import com.matthewperiut.beehives.ItemListener;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.DungeonFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(DungeonFeature.class)
public class DungeonFeatureMixin {
    @Inject(method = "getRandomChestItem", at = @At("HEAD"), cancellable = true)
    void addOurRandomItem(Random random, CallbackInfoReturnable<ItemStack> cir) {
        if (0 == random.nextInt(20)) {
            cir.setReturnValue(new ItemStack(ItemListener.Honeycomb, random.nextInt(1, 8)));
        }
    }

    // TEMPORARY //
    @Inject(method = "generate", at = @At("RETURN"))
    void location(World world, Random random, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ() == true) {
            System.out.println("dungeon: " + x + " " + (y + 4) + " " + z);
        }
    }
}
