package me.tuanzi.sakura.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class Purification extends MobEffect {

    public Purification() {
        super(MobEffectCategory.BENEFICIAL, 0xffffff);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.removeEffect(MobEffects.WITHER);
    }
}
