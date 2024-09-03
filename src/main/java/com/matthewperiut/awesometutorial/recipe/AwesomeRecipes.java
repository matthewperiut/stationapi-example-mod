package com.matthewperiut.awesometutorial.recipe;

import com.matthewperiut.awesometutorial.block.TutorialBlocks;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

public class AwesomeRecipes {
    @EventListener
    public static void registerRecipes(RecipeRegisterEvent event) {
        if (event.recipeId == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type()) {
            CraftingRegistry.addShapedRecipe(new ItemStack(TutorialBlocks.HayBlock, 1),
                    "WWW", "WWW", "WWW", 'W', Item.WHEAT);
            CraftingRegistry.addShapedRecipe(new ItemStack(TutorialBlocks.HayBlockStairs, 6),
                    "H  ", "HH ", "HHH", 'H', TutorialBlocks.HayBlock);
            CraftingRegistry.addShapedRecipe(new ItemStack(TutorialBlocks.HayBlockStairs, 6),
                    "  H", " HH", "HHH", 'H', TutorialBlocks.HayBlock);
        }
        if (event.recipeId == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type()) {
            CraftingRegistry.addShapedRecipe(new ItemStack(Item.WHEAT, 9),
                    "H", 'H', TutorialBlocks.HayBlock);
        }
    }
}
