package me.tuanzi.sakura.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SoulBound extends SakuraEnchantment {
    public SoulBound() {
        super(Rarity.VERY_RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public SakuraRarity getSakuraRarity() {
        return SakuraRarity.LEGENDARY;
    }
}
