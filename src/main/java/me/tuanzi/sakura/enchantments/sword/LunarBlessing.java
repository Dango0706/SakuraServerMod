package me.tuanzi.sakura.enchantments.sword;

import me.tuanzi.sakura.enchantments.EnchantmentReg;
import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class LunarBlessing extends SakuraEnchantment {
    public LunarBlessing() {
        super(Rarity.COMMON, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public SakuraRarity getSakuraRarity() {
        return SakuraRarity.COMMON;
    }

    @Override
    protected boolean checkCompatibility(Enchantment pEnch) {
        return super.checkCompatibility(pEnch) && pEnch != EnchantmentReg.APOLLO_BLESSING.get();    }

    @Override
    public boolean isTreasureOnly() {
        return false;
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     *
     * @param pLevel
     */
    @Override
    public int getMinCost(int pLevel) {
        return 5 + (pLevel - 1) * 8;
    }

    @Override
    public int getMaxCost(int pLevel) {
        return this.getMinCost(pLevel) + 20;
    }
}
