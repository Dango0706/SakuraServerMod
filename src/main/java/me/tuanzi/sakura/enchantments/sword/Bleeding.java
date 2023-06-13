package me.tuanzi.sakura.enchantments.sword;

import me.tuanzi.sakura.effects.EffectReg;
import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import static me.tuanzi.sakura.configs.Config.BLEEDING_ENABLE;

public class Bleeding extends SakuraEnchantment {
    public Bleeding() {
        super(Rarity.UNCOMMON, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public SakuraRarity getSakuraRarity() {
        return SakuraRarity.UNCOMMON;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isTreasureOnly() {
        return !BLEEDING_ENABLE.get();
    }

    @Override
    public int getMinCost(int p_44679_) {
        return 10 * p_44679_;
    }

    @Override
    public int getMaxCost(int p_44691_) {
        return 20 * p_44691_;
    }

    @Override
    public void doPostAttack(LivingEntity livingEntity, Entity entity, int level) {
        if (!livingEntity.level().isClientSide()) {
            if (entity instanceof LivingEntity livingEntity1) {
                livingEntity1.addEffect(new MobEffectInstance(EffectReg.BLEEDING.get(), 40 * level, level, false, false));
            }
        }
    }
}
