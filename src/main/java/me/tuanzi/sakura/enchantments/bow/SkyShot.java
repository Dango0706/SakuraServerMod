package me.tuanzi.sakura.enchantments.bow;

import me.tuanzi.sakura.enchantments.EnchantmentReg;
import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SkyShot extends SakuraEnchantment {
    public SkyShot() {
        super(Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @SubscribeEvent
    public static void arrowSpawn(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof AbstractArrow)) return;
        Entity shooter = ((AbstractArrow) entity).getOwner();
        if (!(shooter instanceof Player player)) return;
        if (player.getMainHandItem().getEnchantmentLevel(EnchantmentReg.SKY_SHOT.get()) <= 0) return;
        entity.setNoGravity(true);
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
        return 1;
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     *
     * @param pLevel
     */
    @Override
    public int getMinCost(int pLevel) {
        return 30;
    }


}
