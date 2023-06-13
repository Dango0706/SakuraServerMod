package me.tuanzi.sakura.enchantments.bow;

import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class Wither extends SakuraEnchantment {
    public Wither() {
        super(Rarity.UNCOMMON, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }


    @Override
    public SakuraRarity getSakuraRarity() {
        return SakuraRarity.UNCOMMON;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getMinCost(int p_44679_) {
        return 5 * p_44679_;
    }

    @Override
    public int getMaxCost(int p_44691_) {
        return 10 * p_44691_;
    }

    @Override
    public void doPostAttack(LivingEntity livingEntity, Entity entity, int level) {
        if (!livingEntity.level().isClientSide()) {
            if (entity instanceof LivingEntity livingEntity1) {
                livingEntity1.addEffect(new MobEffectInstance(MobEffects.WITHER, 40 * level, level, false, false));
            }
        }
    }
}
