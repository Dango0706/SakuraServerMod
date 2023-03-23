package me.tuanzi.sakura.enchantments.sword;

import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import static me.tuanzi.sakura.configs.Config.MAGIC_SOLUBLE_ENABLE;

public class MagicSoluble extends SakuraEnchantment {

    public MagicSoluble() {
        super(Rarity.VERY_RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public SakuraRarity getSakuraRarity() {
        return SakuraRarity.EPIC;
    }

    @Override
    public int getMinCost(int level) {
        return 30 * level;
    }

    @Override
    public int getMaxCost(int level) {
        return 50 * level;
    }

    @Override
    public boolean isTreasureOnly() {
        return !MAGIC_SOLUBLE_ENABLE.get();
    }
}
