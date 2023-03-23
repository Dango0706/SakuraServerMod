package me.tuanzi.sakura.enchantments.sword;

import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import static me.tuanzi.sakura.enchantments.EnchantmentReg.PIERCING;

public class Chase extends SakuraEnchantment {
    public Chase() {
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
    protected boolean checkCompatibility(Enchantment p_44690_) {
        return p_44690_ != PIERCING.get();
    }
}
