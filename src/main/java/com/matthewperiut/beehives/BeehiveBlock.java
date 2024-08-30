package com.matthewperiut.beehives;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.DirectionProperty;
import net.modificationstation.stationapi.api.state.property.IntProperty;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Direction;

import static java.lang.Math.abs;

public class BeehiveBlock extends TemplateBlock {
    public static final DirectionProperty FACING;
    public static final IntProperty HONEY_LEVEL;

    static {
        FACING = Properties.FACING;
        HONEY_LEVEL = IntProperty.of("honey_level", 0, 5);
    }

    public BeehiveBlock(Identifier identifier, Material material) {
        super(identifier, material);
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(HONEY_LEVEL, 0));
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, HONEY_LEVEL);
    }


    public static Direction getDirectionFromPlacer(int x, int z, LivingEntity placer) {
        double xDiff = placer.x - x;
        double zDiff = placer.z - z;
        if (abs(xDiff) > abs(zDiff)) {
            if (xDiff > 0) {
                return Direction.EAST;
            } else {
                return Direction.WEST;
            }
        } else {
            if (zDiff > 0) {
                return Direction.SOUTH;
            } else {
                return Direction.NORTH;
            }
        }
    }

    @Override
    public void onPlaced(World world, int x, int y, int z, LivingEntity placer) {
        world.setBlockState(x, y, z, getDefaultState().with(FACING, getDirectionFromPlacer(x, z, placer)));
        super.onPlaced(world, x, y, z, placer);
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        if (world.isRemote)
            return true;

        BlockState current = world.getBlockState(x, y, z);
        int honey = current.get(HONEY_LEVEL);
        if (honey == 5) {
            boolean gotBottle = false;
            if (player.inventory.getSelectedItem() != null) {
                if (player.inventory.getSelectedItem().getItem().id == ItemListener.GlassBottle.id) {
                    player.inventory.main[player.inventory.selectedSlot].count--;
                    ItemStack stack = new ItemStack(ItemListener.HoneyBottle);
                    ItemEntity entity = new ItemEntity(world, x + 0.5f, y + 1, z + 0.5f, stack);
                    world.spawnEntity(entity);
                    gotBottle = true;
                }
            }
            if (!gotBottle) {
                ItemStack stack = new ItemStack(ItemListener.Honeycomb);
                ItemEntity var24 = new ItemEntity(world, x + 0.5f, y + 1, z + 0.5f, stack);
                world.spawnEntity(var24);
            }

            honey = 0;
        }
        world.setBlockStateWithNotify(x, y, z, current.with(HONEY_LEVEL, honey));
        return true;
    }
}
