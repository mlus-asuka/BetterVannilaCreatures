package cn.mlus.bettervannilacreatures.entity;

import cn.mlus.bettervannilacreatures.util.BvcUtils;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.BodyRotationControl;

public class GeneralBodyControl extends BodyRotationControl {
    Mob mob;
    int maxBodyRot;
    public GeneralBodyControl(Mob pMob) {
        this(pMob, 5);
    }

    public GeneralBodyControl(Mob pMob, int maxRotationStep) {
        super(pMob);
        this.mob = pMob;
        this.maxBodyRot = maxRotationStep;
    }

    @Override
    public void clientTick() {
        if (BvcUtils.isMoving(mob)) {
            this.mob.yBodyRot = Mth.approachDegrees(this.mob.yBodyRot,this.mob.getYRot(),maxBodyRot);
            this.mob.yHeadRot = Mth.rotateIfNecessary(this.mob.yHeadRot, this.mob.yBodyRot, (float)this.mob.getMaxHeadYRot());
        } else {
           super.clientTick();
        }
    }
}
