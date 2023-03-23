package me.tuanzi.sakura.enchantments.bow;

import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MultipleShoot extends SakuraEnchantment {
    public MultipleShoot() {
        super(Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    public static void spawnArrow(Level worldIn, Player player, ItemStack stackBow, int charge, Vec3 offsetVector) {
        ArrowItem arrowitem = (ArrowItem) Items.ARROW;
        AbstractArrow abstractarrowentity = arrowitem.createArrow(worldIn, stackBow, player);
        abstractarrowentity.pickup = AbstractArrow.Pickup.DISALLOWED;
        abstractarrowentity.setPos(abstractarrowentity.getX() + offsetVector.x(), abstractarrowentity.getY(), abstractarrowentity.getZ() + offsetVector.z());
        float f = BowItem.getPowerForTime(charge);
        abstractarrowentity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);
        if (f == 1.0F) {
            abstractarrowentity.setCritArrow(true);
        }
        int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, stackBow);
        if (j > 0) {
            abstractarrowentity.setBaseDamage(abstractarrowentity.getBaseDamage() + j * 0.5D + 0.5D);
        }
        int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, stackBow);
        if (k > 0) {
            abstractarrowentity.setKnockback(k);
        }
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, stackBow) > 0) {
            abstractarrowentity.setSecondsOnFire(100);
        }
        worldIn.addFreshEntity(abstractarrowentity);
    }

    @Override
    public SakuraRarity getSakuraRarity() {
        return SakuraRarity.RARE;
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public int getMinCost(int p_44679_) {
        return 5 * p_44679_;
    }

    @Override
    public int getMaxCost(int p_44691_) {
        return 10 * p_44691_;
    }


}
