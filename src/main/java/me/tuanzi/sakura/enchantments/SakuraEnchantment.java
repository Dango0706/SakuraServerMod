package me.tuanzi.sakura.enchantments;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

/**
 * @author tuanzi
 */
public class SakuraEnchantment extends Enchantment {


//    private final boolean enable = true;

    public SakuraEnchantment(Rarity p_44676_, EnchantmentCategory p_44677_, EquipmentSlot[] p_44678_) {
        super(p_44676_, p_44677_, p_44678_);
    }

    public SakuraRarity getSakuraRarity() {
        return SakuraRarity.COMMON;
    }

/*    public boolean isEnable() {
        return enable;
    }*/

    @Override
    public Component getFullname(int p_44701_) {


        MutableComponent mutablecomponent = Component.translatable("enchantment.sakura.slot." + this.category.name().toLowerCase()).append(Component.translatable(this.getDescriptionId()));

        if (getSakuraRarity() == SakuraRarity.COMMON) {
            mutablecomponent.withStyle(ChatFormatting.GRAY);
        } else if (getSakuraRarity() == SakuraRarity.UNCOMMON) {
            mutablecomponent.withStyle(ChatFormatting.GREEN);
        } else if (getSakuraRarity() == SakuraRarity.RARE) {
            mutablecomponent.withStyle(ChatFormatting.AQUA);
        } else if (getSakuraRarity() == SakuraRarity.EPIC) {
            mutablecomponent.withStyle(ChatFormatting.LIGHT_PURPLE);
        } else {
            mutablecomponent.withStyle(ChatFormatting.GOLD);
        }
        if (p_44701_ != 1 || this.getMaxLevel() != 1) {
            mutablecomponent.append(" ").append(Component.translatable("enchantment.level." + p_44701_));
        }
/*
        if(!enable)
            mutablecomponent.append("§c[已禁用]");*/
        return mutablecomponent;
    }


    //<=2级的可以被附魔台附魔
    @Override
    public boolean isTreasureOnly() {
        return this.getSakuraRarity() != SakuraRarity.COMMON && this.getSakuraRarity() != SakuraRarity.UNCOMMON;
    }

    //<=2级不可在战利品表出现
    @Override
    public boolean isDiscoverable() {
        return this.getSakuraRarity() == SakuraRarity.COMMON || this.getSakuraRarity() == SakuraRarity.UNCOMMON;
    }

    //<=2级的可以被交易
    @Override
    public boolean isTradeable() {
        return this.getSakuraRarity() == SakuraRarity.COMMON || this.getSakuraRarity() == SakuraRarity.UNCOMMON;
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
        return this.category != EnchantmentCategory.BOW || pStack.getItem() != Items.BOW;
    }
}
