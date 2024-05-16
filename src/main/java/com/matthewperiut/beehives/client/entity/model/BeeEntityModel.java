package com.matthewperiut.beehives.client.entity.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class BeeEntityModel extends EntityModel {
    private final ModelPart bone;
    private final ModelPart body;
    private final ModelPart rightWing;
    private final ModelPart leftWing;
    private final ModelPart frontLegs;
    private final ModelPart middleLegs;
    private final ModelPart backLegs;
    private final ModelPart stinger;
    private final ModelPart leftAntenna;
    private final ModelPart rightAntenna;
    private float bodyPitch;

    public BeeEntityModel() {
        float y_pivot_diff = -16.f;
//        this.bone = new ModelPart(su,sv); // start u and v
//        bone.addCuboid(x,y,z,sx,sy,sz); // 3d start point and 3d size
//        bone.setPivot(x, y, z); // pivot points
        bone = new ModelPart(0, 0); // start u and v
        //bone.addCuboid(x,y,z,sx,sy,sz); // 3d start point and 3d size
        bone.setPivot(0.0F, 0.0F - y_pivot_diff, 0.0F); // pivot points

        body = new ModelPart(0, 0); // start u and v
        body.addCuboid(-3.5F, -4.0F, -5.0F, 7, 7, 10); // 3d start point and 3d size
        body.setPivot(bone.pivotX, bone.pivotY, bone.pivotZ); // pivot points

        stinger = new ModelPart(26, 7); // start u and v
        stinger.addCuboid(0.0F, -1.0F, 5.0F, 0, 1, 2); // 3d start point and 3d size
        stinger.setPivot(bone.pivotX, bone.pivotY, bone.pivotZ); // pivot points
        // stinger = new ModelPart(su,sv); // start u and v

        leftAntenna = new ModelPart(2, 0); // start u and v
        leftAntenna.addCuboid(1.5F, -2.0F, -3.0F, 1, 2, 3); // 3d start point and 3d size
        leftAntenna.setPivot(0.0F, -2.0F - y_pivot_diff, -5.0F); // pivot points

        rightAntenna = new ModelPart(2, 3); // start u and v
        rightAntenna.addCuboid(-2.5F, -2.0F, -3.0F, 1, 2, 3); // 3d start point and 3d size
        rightAntenna.setPivot(0.0F, -2.0F - y_pivot_diff, -5.0F); // pivot points

        float dilation = 0.001F;
        rightWing = new ModelPart(0, 18); // start u and v
        rightWing.addCuboid(-9.0F, 0.0F, 0.0F, 9, 0, 6, dilation); // 3d start point and 3d size
        rightWing.setPivot(-1.5F, -4.0F - y_pivot_diff, -3.0F); // pivot points
        rightWing.pitch = 0.0f;
        rightWing.yaw = -0.2618f;
        rightWing.roll = 0.0f;

        leftWing = new ModelPart(9, 24); // start u and v
        leftWing.addCuboid(0.0F, 0.0F, 0.0F, 9, 0, 6, dilation); // 3d start point and 3d size
        leftWing.setPivot(1.5F, -4.0F - y_pivot_diff, -3.0F); // pivot points
        leftWing.mirror = true;
        leftWing.pitch = 0.0f;
        leftWing.yaw = 0.2618f;
        leftWing.roll = 0.0f;

        frontLegs = new ModelPart(26, 1); // start u and v
        frontLegs.addCuboid(-5.0F, 0.0F, 0.0F, 7, 2, 0); // 3d start point and 3d size
        frontLegs.setPivot(1.5F, 3.0F - y_pivot_diff, -2.0F); // pivot points

        middleLegs = new ModelPart(26, 3); // start u and v
        middleLegs.addCuboid(-5.0F, 0.0F, 0.0F, 7, 2, 0); // 3d start point and 3d size
        middleLegs.setPivot(1.5F, 3.0F - y_pivot_diff, 0.0F); // pivot points

        backLegs = new ModelPart(26, 5); // start u and v
        backLegs.addCuboid(-5.0F, 0.0F, 0.0F, 7, 2, 0); // 3d start point and 3d size
        backLegs.setPivot(1.5F, 3.0F - y_pivot_diff, 2.0F); // pivot points
    }

    public void render(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        super.render(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);
        this.setAngles(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);
        body.render(scale);
        rightWing.render(scale);
        leftWing.render(scale);
        frontLegs.render(scale);
        middleLegs.render(scale);
        backLegs.render(scale);
        stinger.render(scale);
        leftAntenna.render(scale);
        rightAntenna.render(scale);
    }

    private float time = 0.f;
    public void animateModel(LivingEntity entity, float limbAngle, float limbDistance, float tickDelta) {
        time += tickDelta;
        this.bodyPitch = (float) (Math.sin(time / 10) + 1) / 2;
        //leftWing.roll = (float) (Math.sin(time / 10) + 1) / 2;
        //rightWing.roll = (float) (Math.sin(time / 10) + 1) / 2;
    }

    public void setAngles(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        this.rightWing.pitch = 0.0F;
        this.leftAntenna.pitch = 0.0F;
        this.rightAntenna.pitch = 0.0F;
        this.bone.pitch = 0.0F;
        float k;
        k = animationProgress * 120.32113F * 0.017453292F;
        this.rightWing.yaw = 0.0F;
        this.rightWing.roll = MathHelper.cos(k) * 3.1415927F * 0.15F;
        this.leftWing.pitch = this.rightWing.pitch;
        this.leftWing.yaw = this.rightWing.yaw;
        this.leftWing.roll = -this.rightWing.roll;
        this.frontLegs.pitch = 0.7853982F;
        this.middleLegs.pitch = 0.7853982F;
        this.backLegs.pitch = 0.7853982F;
        this.bone.pitch = 0.0F;
        this.bone.yaw = 0.0F;
        this.bone.roll = 0.0F;

        if (this.bodyPitch > 0.0F) {
            this.bone.pitch = interpolateAngle(this.bone.pitch, 3.0915928F, this.bodyPitch);
        }
    }

    public static float interpolateAngle(float angle1, float angle2, float progress) {
        float f;
        for(f = angle2 - angle1; f < -3.1415927F; f += 6.2831855F) {
        }

        while(f >= 3.1415927F) {
            f -= 6.2831855F;
        }

        return angle1 + progress * f;
    }
}
