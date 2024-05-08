package com.matthewperiut.beehives;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

public class RecipeListener {
    @EventListener
    public static void registerRecipes(RecipeRegisterEvent event) {
        if (event.recipeId == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type()) {
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.Beehive),
            "PPP", "PHP", "PPP",
                    'P', Block.PLANKS,
                    'H', ItemListener.Honeycomb);
            CraftingRegistry.addShapedRecipe(new ItemStack(ItemListener.GlassBottle, 3),
            "G G", " G ",
                    'G', Block.GLASS);
        }
        if (event.recipeId == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type()) {
            CraftingRegistry.addShapelessRecipe(new ItemStack(Item.DYE, 2, 11),
                    ItemListener.Honeycomb);
        }
    }
}
