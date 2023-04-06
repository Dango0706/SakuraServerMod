package me.tuanzi.sakura.enchantments.sword;

import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BloodRage extends SakuraEnchantment {

    public BloodRage() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }


    @Override
    public SakuraRarity getSakuraRarity() {
        return SakuraRarity.UNCOMMON;
    }

    /**
     * Returns the maximum level that the enchantment can have.
     */
    @Override
    public int getMaxLevel() {
        return 4;
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     *
     * @param pLevel
     */
    @Override
    public int getMinCost(int pLevel) {
        return 1 + (pLevel- 1 ) *11;
    }

    @Override
    public int getMaxCost(int pLevel) {
        return this.getMinCost(pLevel) + 20;
    }
}
