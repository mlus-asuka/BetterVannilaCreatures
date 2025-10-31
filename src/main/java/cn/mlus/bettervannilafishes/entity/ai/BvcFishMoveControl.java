package cn.mlus.bettervannilafishes.entity.ai;

import cn.mlus.bettervannilafishes.entity.BvcAbstractFish;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

public class BvcFishMoveControl extends MoveControl {
    private final int maxTurnX;
    private final int maxTurnY;
    private final float inWaterSpeedModifier;
    private final float outsideWaterSpeedModifier;
    private final boolean applyGravity;

    private final BvcAbstractFish fish;

    public BvcFishMoveControl(BvcAbstractFish pMob) {
        super(pMob);
        this.maxTurnX = 85;
        this.maxTurnY = 10;
        this.inWaterSpeedModifier = 0.02F;
        this.outsideWaterSpeedModifier = 0.1F;
        this.applyGravity = true;
        this.fish = pMob;
    }

    public void tick() {
        if (this.applyGravity && this.mob.isInWater()) {
            this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0.0, 0.005, 0.0));
        }

        if (this.operation == Operation.MOVE_TO && !this.mob.getNavigation().isDone()) {
            double $$0 = this.wantedX - this.mob.getX();
            double $$1 = this.wantedY - this.mob.getY();
            double $$2 = this.wantedZ - this.mob.getZ();
            double $$3 = $$0 * $$0 + $$1 * $$1 + $$2 * $$2;
            if ($$3 < 2.500000277905201E-7) {
                this.mob.setZza(0.0F);
            } else {
                float $$4 = (float)(Mth.atan2($$2, $$0) * 57.2957763671875) - 90.0F;

                this.mob.setYRot(this.rotlerp(this.mob.getYRot(), $$4, (float)this.maxTurnY));
                this.mob.yHeadRot = this.mob.yBodyRot;
                float $$5 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
                if (this.mob.isInWater()) {

//                    if(Mth.abs(Mth.degreesDifference(this.fish.getYRot(),this.fish.yBodyRot)) < 1)
                        this.mob.setSpeed($$5 * this.inWaterSpeedModifier);

                    double $$6 = Math.sqrt($$0 * $$0 + $$2 * $$2);
                    float $$8;
                    if (Math.abs($$1) > 9.999999747378752E-6 || Math.abs($$6) > 9.999999747378752E-6) {
                        $$8 = -((float)(Mth.atan2($$1, $$6) * 57.2957763671875));
                        $$8 = Mth.clamp(Mth.wrapDegrees($$8), (float)(-this.maxTurnX), (float)this.maxTurnX);
                        this.mob.setXRot(this.rotlerp(this.mob.getXRot(), $$8, 5.0F));
                    }

                    $$8 = Mth.cos(this.mob.getXRot() * 0.017453292F);
                    float $$9 = Mth.sin(this.mob.getXRot() * 0.017453292F);
                    this.mob.zza = $$8 * $$5;
                    this.mob.yya = -$$9 * $$5;
                } else {
                    float $$10 = Math.abs(Mth.wrapDegrees(this.mob.getYRot() - $$4));
                    float $$11 = getTurningSpeedFactor($$10);
                    this.mob.setSpeed($$5 * this.outsideWaterSpeedModifier * $$11);
                }

            }
        } else {
            if(this.fish.randomSwimmingGoal != null && this.fish.getTarget() == null && this.fish.isInWater())
                this.fish.randomSwimmingGoal.trigger();
        }
    }

    private static float getTurningSpeedFactor(float p_249853_) {
        return 1.0F - Mth.clamp((p_249853_ - 10.0F) / 50.0F, 0.0F, 1.0F);
    }
}
