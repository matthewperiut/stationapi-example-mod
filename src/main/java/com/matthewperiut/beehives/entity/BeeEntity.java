package com.matthewperiut.beehives.entity;

import com.matthewperiut.beehives.BlockListener;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.math.Vec3d;

import java.util.ArrayList;

import static com.matthewperiut.beehives.BeehiveBlock.HONEY_LEVEL;
import static com.matthewperiut.beehives.entity.EntityListener.NAMESPACE;

public class BeeEntity extends AnimalEntity implements MobSpawnDataProvider {
    public BeeEntity(World arg) {
        super(arg);
        this.setBoundingBoxSpacing(0.8F, 0.8F);
        this.texture = "assets/beehives/stationapi/textures/entity/bee/bee.png";
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return NAMESPACE.id("Bee");
    }

    @Override
    protected String getHurtSound() {
        return "beehives:mobs.bee.beehurt";
    }

    @Override
    protected String getDeathSound() {
        return "beehives:mobs.bee.beedeath";
    }

    public float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        } else {
            return value;
        }
    }

    public void hover(float distanceOffGround) {
        // Constants for hovering and gravity compensation
        double amplitude = 0.003; // Amplitude of the sine wave for hovering
        double frequency = 0.1; // Frequency of the sine wave for hovering
        double gravity = 0.0784; // Gravity compensation

        // Attraction strength and damping, adjust as needed for game feel
        double attractionStrength = 0.005; // Reduced strength of the attraction towards the targetY
        double dampingFactor = 0.95; // Damping to smooth out the attraction and prevent overshooting
        double attractionDistanceThreshold = 0.5; // Only apply attraction if the distance is more than this value

        // Compensate for gravity
        this.velocityY += gravity;

        // Apply hovering behavior
        this.velocityY += amplitude * Math.sin(frequency * this.age);

        // Calculate targetY based on nearest non-air block below
        double targetY = y - 1;
        for (int i = 0; i < 3; i++) {
            int blockId = world.getBlockId((int) x, (int) (y - i), (int) z);

            if (blockId != 0 && blockId != Block.SNOW.id) {

                targetY = y - i + distanceOffGround;
                break;
            }
        }

        // Attraction towards targetY, only if the entity is far enough from the target
        double distanceToTargetY = Math.abs(targetY - this.y);
        if (distanceToTargetY > attractionDistanceThreshold) {
            double attraction = (targetY - this.y) * attractionStrength;
            this.velocityY += attraction;
        }

        // Apply damping to the velocity to prevent oscillation or excessive speed
        this.velocityY *= dampingFactor;

        // Fall Distance
        fallDistance = 0;
    }

    Vec3d target;
    public void goToBlock(Vec3d pos) {
        target = pos;
        path = this.world.method_189(this, (int) pos.x, (int) pos.y, (int) pos.z, 16.0F);
    }

    public void stopMoving() {
        target = null;
        path = null;
    }

    BlockPos homeBlock;
    Vec3d getHome() {
        int range = 10;
        for (int i = -range; i < range; i++) {
            for (int j = -range; j < range; j++) {
                for (int k = -range; k < range; k++) {
                    if (world.getBlockId((int) (x + i), (int) (y + j), (int) (z + k)) == BlockListener.Beehive.id) {
                        homeBlock = new BlockPos((int) (x + i), (int) (y + j), (int) (z + k));
                        return new Vec3d(i + 0.5 + (int)x, j + 0.5 + (int)y, k + 0.5 + (int)z);
                    }
                }
            }
        }
        return null;
    }

    Vec3d getFlower() {
        int range = 10;
        ArrayList<Vec3d> flowers = new ArrayList<>();
        for (int i = -range; i < range; i++) {
            for (int j = -range; j < range; j++) {
                for (int k = -range; k < range; k++) {
                    int blockId = world.getBlockId((int) (x + i), (int) (y + j), (int) (z + k));
                    if (blockId == Block.DANDELION.id || blockId == Block.ROSE.id) {
                        flowers.add(new Vec3d((int) (x + i + 0.5), (int) (y + j + 0.5), (int) (z + k + 0.5)));
                    }
                }
            }
        }

        if (flowers.size() > 0) {
            return flowers.get(random.nextInt(flowers.size()));
        }

        return null;
    }

    void gravitate() {
        Vec3d appliedForce = target.normalize().multiply(0.01f);
        velocityX += appliedForce.getX();
        velocityZ += appliedForce.getZ();
    }

    private Vec3d home = null;
    private Vec3d flower = null;
    // progress counter will count to -20 if at home
    // and will count to 20 if at flower
    // -1 is "go to home"
    // 1 is "go to flower"
    private int progressCounter = -1;
    private static final int bound = 50;
    @Override
    public void tick() {
        float distance = 1.5f;

        super.tick();
        Vec3d pos = new Vec3d(x,y,z);

        if (age % 5 == 0) {
            if (home == null) {
                home = getHome();
            } else {
                if (progressCounter < 0) {
                    // go to home
                    goToBlock(home);
                    if (pos.distanceTo(home) < 2) {
                        progressCounter--;
                        distance = 0.5f;
                    }
                    if (progressCounter < -bound) {
                        progressCounter = 1;
                        BlockState current = world.getBlockState(homeBlock);
                        int honey = current.get(HONEY_LEVEL);
                        honey++;
                        if (honey < 6)
                            world.setBlockStateWithNotify(homeBlock, current.with(HONEY_LEVEL, honey));
                    }
                }
            }
            if (flower == null) {
                flower = getFlower();
            } else {
                if (progressCounter > 0) {
                    // go to flower
                    goToBlock(flower);
                    if (pos.distanceTo(flower) < 2) {
                        progressCounter++;
                        distance = 0.5f;
                    }

                    if (progressCounter > bound) {
                        progressCounter = -1;
                    }
                }
            }
        }

        if (progressCounter < -1 && progressCounter > -bound) {
            gravitate();
        }
        if (progressCounter > 1 && progressCounter < bound) {
            gravitate();
        }

        hover(distance);
    }

    @Override
    protected void tickLiving() {
        super.tickLiving();
        jumping = false;
    }
}
