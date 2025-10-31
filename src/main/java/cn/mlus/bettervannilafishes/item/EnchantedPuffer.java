package cn.mlus.bettervannilafishes.item;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import javax.annotation.Nullable;

public class EnchantedPuffer extends BowItem {
    public EnchantedPuffer(Properties pProperties) {
        super(pProperties.defaultDurability(50));
    }

    private int clientSideAttackTime;
    @Nullable
    private LivingEntity clientSideCachedAttackTarget;

    public boolean isFoil(@NotNull ItemStack pStack) {
        return true;
    }

    public int getAttackDuration() {
        return 80;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);

        double range = 10.0;
        LivingEntity closestTarget = null;
        double closestDistance = Double.MAX_VALUE;
        for (LivingEntity entity : pPlayer.level().getEntitiesOfClass(LivingEntity.class, pPlayer.getBoundingBox().inflate(range))) {
            if (entity != pPlayer && (entity.getMobType().equals(MobType.UNDEAD) || entity instanceof Guardian)) {
                double distance = pPlayer.distanceToSqr(entity);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestTarget = entity;
                }
            }
        }
        if (closestTarget != null) {
            this.clientSideAttackTime = 0;
            this.clientSideCachedAttackTarget = closestTarget;
        }

        if(clientSideCachedAttackTarget == null || !clientSideCachedAttackTarget.isAlive())
            return InteractionResultHolder.pass(itemstack);

        pPlayer.startUsingItem(pHand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public void onUseTick(@NotNull Level pLevel, @NotNull LivingEntity livingEntity, @NotNull ItemStack pStack, int pRemainingUseDuration) {
        if (this.clientSideAttackTime < this.getAttackDuration()) {
            ++this.clientSideAttackTime;
        }

        LivingEntity target = clientSideCachedAttackTarget;
        if (target != null) {
            float yawRad = livingEntity.getYRot() * ((float)Math.PI / 180F);

            double rightHandOffsetX = -Math.sin(yawRad) * 0.4;
            double rightHandOffsetZ = Math.cos(yawRad) * 0.4;
            double rightHandOffsetY = -0.25;

            double rightHandX = livingEntity.getX() + rightHandOffsetX;
            double rightHandY = livingEntity.getEyeY() + rightHandOffsetY;
            double rightHandZ = livingEntity.getZ() + rightHandOffsetZ;

            double dx = target.getX() - rightHandX;
            double dy = target.getY(0.5) - rightHandY;
            double dz = target.getZ() - rightHandZ;
            double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

            dx /= distance;
            dy /= distance;
            dz /= distance;

            for (int i = 0; i < 5; i++) {
                double offsetX = (pLevel.random.nextDouble() - 0.5) * 0.3;
                double offsetY = (pLevel.random.nextDouble() - 0.5) * 0.3;
                double offsetZ = (pLevel.random.nextDouble() - 0.5) * 0.3;

                double beamProgress = pLevel.random.nextDouble() * distance;

                pLevel.addParticle(
                        new DustParticleOptions(new Vector3f(0.2f, 1f, 0.4f), 1.0f),
                        rightHandX + dx * beamProgress + offsetX,
                        rightHandY + dy * beamProgress + offsetY,
                        rightHandZ + dz * beamProgress + offsetZ,
                        0, 0, 0
                );
            }

            pLevel.playSound(null, rightHandX, rightHandY, rightHandZ,
                    SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 0.7F, 1.5F + pLevel.random.nextFloat() * 0.3F);

            if (pLevel.random.nextFloat() < 0.2f) {
                pLevel.playSound(null, rightHandX, rightHandY, rightHandZ,
                        SoundEvents.BEACON_AMBIENT, SoundSource.PLAYERS, 0.3F, 2.0F);
            }
        }
    }

    public void releaseUsing(@NotNull ItemStack pStack, @NotNull Level pLevel, @NotNull LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            int i = this.getUseDuration(pStack) - pTimeLeft;
            i = ForgeEventFactory.onArrowLoose(pStack, pLevel, player, i, true);
            if (i < 0) {
                return;
            }

            float f = getPowerForTime(i);
            if (!((double)f < 0.1)) {
                if (!pLevel.isClientSide) {
                    pStack.hurtAndBreak(1, player, (p_289501_) -> p_289501_.broadcastBreakEvent(player.getUsedItemHand()));
                    if(clientSideCachedAttackTarget != null)
                        clientSideCachedAttackTarget.hurt(pLevel.damageSources().magic(), 20.0F * f);
                }

                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }

    }
}
