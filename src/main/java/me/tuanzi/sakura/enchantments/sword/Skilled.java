package me.tuanzi.sakura.enchantments.sword;

import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import static me.tuanzi.sakura.configs.Config.SKILLED_ENABLE;

public class Skilled extends SakuraEnchantment {
    public Skilled() {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public SakuraRarity getSakuraRarity() {
        return SakuraRarity.RARE;
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
        return !SKILLED_ENABLE.get();
    }
}
