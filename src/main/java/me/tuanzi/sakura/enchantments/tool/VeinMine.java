package me.tuanzi.sakura.enchantments.tool;

import me.tuanzi.sakura.enchantments.EnchantmentReg;
import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class VeinMine extends SakuraEnchantment {
    public VeinMine() {
        super(Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public SakuraRarity getSakuraRarity() {
        return SakuraRarity.RARE;
    }

    /**
     * Determines if the enchantment passed can be applied together with this enchantment.
     *
     * @param pOther
     */
    @Override
    protected boolean checkCompatibility(Enchantment pOther) {
        return super.checkCompatibility(pOther) && pOther != EnchantmentReg.DIAMONDS_EVERYWHERE.get();
    }
}
