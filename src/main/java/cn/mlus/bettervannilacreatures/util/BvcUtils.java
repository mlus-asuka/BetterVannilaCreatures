package cn.mlus.bettervannilacreatures.util;


import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;

public class BvcUtils {

    public static boolean isMoving(Mob mob) {
        return mob.getX() != mob.xOld || mob.getZ() != mob.zOld;
    }

    public static float rotlerp(float pSourceAngle, float pTargetAngle, float pMaximumChange) {
        float f = Mth.wrapDegrees(pTargetAngle - pSourceAngle);
        if (f > pMaximumChange) {
            f = pMaximumChange;
        }

        if (f < -pMaximumChange) {
            f = -pMaximumChange;
        }

        float f1 = pSourceAngle + f;
        if (f1 < 0.0F) {
            f1 += 360.0F;
        } else if (f1 > 360.0F) {
            f1 -= 360.0F;
        }

        return f1;
    }

    public static float getDirectionAngle(float x, float z) {
        // 从正前方为0度，顺时针旋转计算角度
        double angle = (90 - Math.toDegrees(Math.atan2(z, x))) % 360;
        // 确保角度在0-360度范围内
        if (angle < 0) angle += 360;
        return (float)angle;
    }
}