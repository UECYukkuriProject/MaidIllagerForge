package net.yokohama_miyazawa.maidillager.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@OnlyIn(Dist.CLIENT)
@Mixin(IllagerModel.class)
public class MixinIllagerModel {

    private ModelPart chignonB;
    private ModelPart tail;
    private ModelPart hair;
    private ModelPart forelock;
    private ModelPart blinkEyeR;
    private ModelPart blinkEyeL;
    private ModelPart hurtEyeR;
    private ModelPart hurtEyeL;
    private ModelPart mouth;
    private ModelPart Skirt;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(ModelPart root, CallbackInfo cir) {
        ModelPart head = ((IllagerModel)(Object)this).getHead();
        this.chignonB = head.getChild("chignonB");
        this.tail = head.getChild("tail");
        this.hair = head.getChild("hair");
        this.forelock = head.getChild("forelock");
        this.blinkEyeR = head.getChild("blinkEyeR");
        this.blinkEyeL = head.getChild("blinkEyeL");
        this.hurtEyeR = head.getChild("hurtEyeR");
        this.hurtEyeL = head.getChild("hurtEyeL");
        this.mouth = head.getChild("mouth");
        this.Skirt = root.getChild("Skirt");

        this.chignonB.visible = false;
        this.tail.visible = false;
        this.forelock.visible = false;
        this.blinkEyeR.visible = false;
        this.blinkEyeL.visible = false;
        this.hurtEyeR.visible = false;
        this.hurtEyeL.visible = false;
        this.mouth.visible = false;

        ModelPart hat = ((IllagerModel)(Object)this).getHat();
        hat.visible = false;

        ModelPart arms = ((IllagerModelAccessor) (Object)this).getArms();
        arms.visible = false;
    }

    @Inject(method = "createBodyLayer", at = @At("HEAD"), cancellable = true)
    private static void onCreateBodyLayer(CallbackInfoReturnable<LayerDefinition> cir) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // オリジナルとの身長差分、体全体を下にずらす
        float heightOffset = 8.0F;

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F+heightOffset, 0.0F));
        head.addOrReplaceChild("chignonB", CubeListBuilder.create().texOffs(52, 10).addBox(-2.0F, -7.2F, 4.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(46, 20).addBox(-1.5F, -7.8F, 4.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("forelock", CubeListBuilder.create().texOffs(44, 33).addBox(-4.0F, -8.0F, -4.5F, 8.0F, 4.0F, 2.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("blinkEyeR", CubeListBuilder.create().texOffs(4, 0).addBox(-3.0F, -4.0F, -4.01F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("blinkEyeL", CubeListBuilder.create().texOffs(4, 0).mirror().addBox(1.0F, -4.0F, -4.01F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("hurtEyeR", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -4.01F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("hurtEyeL", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(1.0F, -4.0F, -4.01F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -1.0F, -4.01F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 8).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F+heightOffset, 0.0F));
        PartDefinition rightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 1.0F+heightOffset, 0.0F));
        PartDefinition leftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(56, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 1.0F+heightOffset, 0.0F));
        PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 19).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 7.0F+heightOffset, 0.0F));
        PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 19).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, 7.0F+heightOffset, 0.0F));
        PartDefinition Skirt = partdefinition.addOrReplaceChild("Skirt", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F+heightOffset, 0.0F));

        // ダミーの帽子と腕
        head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(32, 8).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F+heightOffset, 0.0F));

        cir.setReturnValue(LayerDefinition.create(meshdefinition, 64, 64));
    }

    @Inject(method = "setupAnim", at = @At("HEAD"), cancellable = true)
    private <T extends AbstractIllager> void onSetupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo cir) {
        if (entity instanceof Illusioner) {
            this.Skirt.visible = false;
            ModelPart hat = ((IllagerModel)(Object)this).getHat();
            hat.visible = false;
        }
        if (entity instanceof Pillager || entity instanceof Illusioner) {
            this.chignonB.visible = true;
            this.tail.visible = true;
        }
        if (entity instanceof Evoker || entity instanceof Pillager) {
            this.forelock.visible = true;
        }

        ModelPart head = ((IllagerModel)(Object)this).getHead();
        head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        head.xRot = headPitch * ((float)Math.PI / 180F);

        ModelPart rightArm = ((IllagerModelAccessor) (Object)this).getRightArm();
        ModelPart leftArm  = ((IllagerModelAccessor) (Object)this).getLeftArm();
        ModelPart rightLeg = ((IllagerModelAccessor) (Object)this).getRightLeg();
        ModelPart leftLeg  = ((IllagerModelAccessor) (Object)this).getLeftLeg();
        // TODO: ラヴェジャーに乗っている時に下半身がめり込むのを解消する
        if (((IllagerModel)(Object)this).riding) {  // ラヴェジャーに乗っている時
            this.setAngle(rightArm, -0.6283185F, 0.0F, 0.0F);
            this.setAngle(leftArm, -0.6283185F, 0.0F, 0.0F);
            this.setAngle(rightLeg, -1.256637F, 0.3141593F, 0.0F);
            this.setAngle(leftLeg, -1.256637F, -0.3141593F, 0.0F);
        } else {
            float xAngle = (float)Math.cos(limbSwing * 0.6662) * 2.0F * limbSwingAmount * 0.5F;
            float defaultZAngle = (float)Math.PI / 5.0F;
            float zSwing = ((float)Math.PI / 40.0F) * (float)Math.sin(3.0F * ageInTicks * ((float)Math.PI / 180F));
            float zAngle = defaultZAngle + zSwing;
            this.setAngle(rightArm, -xAngle, 0.0F, zAngle);
            this.setAngle(leftArm, xAngle, 0.0F, -zAngle);
            this.setAngle(rightLeg, (float)Math.cos(limbSwing * 0.6662) * 1.4F * limbSwingAmount * 0.5F, 0.0F, 0.0F);
            this.setAngle(leftLeg, (float)Math.cos(limbSwing * 0.6662 + Math.PI) * 1.4F * limbSwingAmount * 0.5F, 0.0F, 0.0F);
        }

        AbstractIllager.IllagerArmPose armpose = entity.getArmPose();
        switch(armpose){
            case SPELLCASTING -> {
                this.setAngle(rightArm, 0.0F, 0.0F, (float)Math.PI * (2.0F / 3.0F));
                this.setAngle(leftArm, 0.0F, 0.0F, -(float)Math.PI * (2.0F / 3.0F));
            }
            case BOW_AND_ARROW, CROSSBOW_HOLD, CROSSBOW_CHARGE -> {
                this.setAngle(rightArm, -(float)Math.PI / 2.0F + head.xRot, -(float)Math.PI / 15.0F, 0.0F);
                this.setAngle(leftArm, -(float)Math.PI / 2.0F + head.xRot, (float)Math.PI / 15.0F, 0.0F);
            }
            case CELEBRATING -> {
                this.setAngle(rightArm, 0.0F, 0.0F, (float)Math.PI * (5.0F / 6.0F));
                this.setAngle(leftArm, 0.0F, 0.0F, -(float)Math.PI * (5.0F / 6.0F));
            }
        }

        if (!(entity instanceof Illusioner)) {  // イリュージョナー以外の表情変化
            if (this.isHurt(entity) || entity.isDeadOrDying()) {  // ダメージを受けたもしくは死ぬ時
                this.blinkEyeR.visible = false;
                this.blinkEyeL.visible = false;
                if (entity instanceof Pillager && !entity.isDeadOrDying()) {  // ピリジャーがダメージを受けた時は利き腕とは逆の目だけ閉じる
                    ModelPart closeEye = entity.getMainArm() == HumanoidArm.LEFT ? this.hurtEyeR : this.hurtEyeL;
                    closeEye.visible = true;
                } else {
                    this.hurtEyeR.visible = true;
                    this.hurtEyeL.visible = true;
                }
                if (entity instanceof Pillager || entity instanceof Vindicator || entity.getId() % 2 == 0) {
                    this.mouth.visible = true;
                }
            } else if (this.shouldBlink(entity, ageInTicks)) {  // 瞬き
                this.hurtEyeR.visible = false;
                this.hurtEyeL.visible = false;
                this.mouth.visible = false;
                this.blinkEyeR.visible = true;
                this.blinkEyeL.visible = true;
            } else {
                this.hurtEyeR.visible = false;
                this.hurtEyeL.visible = false;
                this.mouth.visible = false;
                this.blinkEyeR.visible = false;
                this.blinkEyeL.visible = false;
            }
        }

        cir.cancel();
    }

    private <T extends AbstractIllager> boolean isHurt(T entity) {
        return entity.hurtTime > 0;
    }

    private <T extends AbstractIllager> boolean shouldBlink(T entity, float tick) {
        int entityId = entity.getId();
        float blinkTheta = tick +(float)entityId;
        return 0 > (float) Math.sin(blinkTheta * 0.05F) + (float) Math.sin(blinkTheta * 0.13F) + (float) Math.sin(blinkTheta * 0.7F) + 2.55F;
    }

    private void setAngle(ModelPart part, float xAngle, float yAngle, float zAngle) {
        part.xRot = xAngle;
        part.yRot = yAngle;
        part.zRot = zAngle;
    }

    // 腕と武器の位置関係を調整
    @Inject(method = "translateToHand", at = @At("TAIL"), cancellable = true)
    private void onTranslateToHand(HumanoidArm arm, PoseStack poseStack, CallbackInfo cir) {
        double dx = (arm == HumanoidArm.LEFT) ? -0.05 : 0.05;
        double dy = -0.1;
        double dz = 0.04;
        poseStack.translate(dx, dy, dz);
    }
}
