package me.tuanzi.sakura.enchantments.tool;

import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class DiamondsEverywhere extends SakuraEnchantment {

    public DiamondsEverywhere() {
        super(Rarity.UNCOMMON, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public SakuraRarity getSakuraRarity() {
        return SakuraRarity.UNCOMMON;
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     *
     * @param pLevel
     */
    @Override
    public int getMinCost(int pLevel) {
        return 15 + (pLevel - 1) * 11;
    }

    @Override
    public int getMaxCost(int pLevel) {
        return this.getMinCost(pLevel) + 5;
    }


    @Override
    public boolean checkCompatibility(Enchantment pEnch) {
        return super.checkCompatibility(pEnch) && pEnch != Enchantments.SILK_TOUCH;
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
        return pStack.getItem() instanceof PickaxeItem;
    }
}
