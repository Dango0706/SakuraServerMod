package me.tuanzi.sakura.enchantments.bow;

import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class QuickDraw extends SakuraEnchantment {
    public QuickDraw() {
        super(Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
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
     * Determines if this enchantment can be applied to a specific ItemStack.
     * 防止原版弓被附魔到还不兼容原版弓的附魔.
     * 兼容请复写此.
     *
     * @param pStack The ItemStack to test.
     */
    @Override
    public boolean canEnchant(ItemStack pStack) {
        return true;
    }
}
