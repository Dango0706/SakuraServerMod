package me.tuanzi.sakura.effects;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

/**
 * The type Bleeding.
 */
public class Bleeding extends MobEffect {

    /**
     * Instantiates a new Bleeding.
     */
    public Bleeding() {
        super(MobEffectCategory.NEUTRAL, 0xff0000);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int level) {
        float damage = level + 1;
        boolean move = false;
        //不是客户端
        if (!entity.level.isClientSide()) {
            //检查X坐标
            if (entity.getPersistentData().contains("sakura:bleedingOldX")) {
                if (entity.getPersistentData().getDouble("sakura:bleedingOldX") - entity.getX() != 0) {
                    damage *= 2;
                    move = true;
                    entity.getPersistentData().putDouble("sakura:bleedingOldX", entity.getX());
                }
            } else {
                entity.getPersistentData().putDouble("sakura:bleedingOldX", entity.getX());
            }
            //若x没有移动,则检查Z坐标
            if (entity.getPersistentData().contains("sakura:bleedingOldZ") && !move) {
                if (entity.getPersistentData().getDouble("sakura:bleedingOldZ") - entity.getZ() != 0) {
                    damage *= 2;
                    entity.getPersistentData().putDouble("sakura:bleedingOldZ", entity.getZ());
                }
            } else {
                entity.getPersistentData().putDouble("sakura:bleedingOldZ", entity.getZ());
            }

            entity.hurt(DamageSource.MAGIC, damage);

            MobEffectInstance effectInstance = entity.getEffect(this);
            if (effectInstance != null && effectInstance.getDuration() <= 1) {
                entity.getPersistentData().remove("sakura:bleedingOldX");
                entity.getPersistentData().remove("sakura:bleedingOldZ");
            }

        }
    }

    @Override
    public boolean isDurationEffectTick(int time, int level) {
        if (time <= 1) {
            return true;
        }
        return time % (20 - (level + 1) * 2) == 0;

    }


}
