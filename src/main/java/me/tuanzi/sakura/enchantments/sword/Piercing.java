package me.tuanzi.sakura.enchantments.sword;

import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import static me.tuanzi.sakura.configs.Config.PIERCING_ENABLE;
import static me.tuanzi.sakura.enchantments.EnchantmentReg.CHASE;

public class Piercing extends SakuraEnchantment {
    public Piercing() {
        super(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public SakuraRarity getSakuraRarity() {
        return SakuraRarity.EPIC;
    }

    @Override
    public int getMinCost(int level) {
        return 20 * level;
    }

    @Override
    public int getMaxCost(int level) {
        return 30 * level;
    }

    @Override
    public boolean isTreasureOnly() {
        return !PIERCING_ENABLE.get();
    }

    /**
     * Determines if the enchantment passed can be applied together with this enchantment.
     *
     * @param pOther
     */
    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return pOther != CHASE.get();
    }


    /*    @Override
    public boolean isEnable() {
        return Config.PIERCING_ENABLE.get();
    }*/
}
