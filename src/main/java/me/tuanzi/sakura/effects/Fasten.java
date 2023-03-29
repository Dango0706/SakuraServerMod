package me.tuanzi.sakura.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class Fasten extends MobEffect {
    public Fasten() {
        super(MobEffectCategory.NEUTRAL, 0);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level.isClientSide()) {
            //todo:fix缓慢移动,且可以飞天

//            pLivingEntity.move(MoverType.PLAYER,pLivingEntity.getPosition(1));
//            pLivingEntity.moveTo(pLivingEntity.getPosition(1));
//            System.out.println(pLivingEntity.getPosition(0));
            pLivingEntity.teleportTo(pLivingEntity.xOld, pLivingEntity.yOld, pLivingEntity.zOld);
//            pLivingEntity.moveTo(pLivingEntity.getPosition(0));

        }
    }

    /**
     * Checks whether the effect is ready to be applied this tick.
     *
     * @param pDuration
     * @param pAmplifier
     */
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
