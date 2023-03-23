package me.tuanzi.sakura.enchantments.sword;

import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class Education extends SakuraEnchantment {
    public Education() {
        super(Rarity.RARE, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public SakuraRarity getSakuraRarity() {
        return SakuraRarity.EPIC;
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
