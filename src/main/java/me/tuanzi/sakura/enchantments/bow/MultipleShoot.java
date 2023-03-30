package me.tuanzi.sakura.enchantments.bow;

import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
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

import java.util.UUID;

public class MultipleShoot extends SakuraEnchantment {
    public MultipleShoot() {
        super(Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    //射出不同的箭矢
    public static void spawnArrow(Level worldIn, Player player, ItemStack stackBow, int charge, Vec3 offsetVector) {
        ArrowItem arrowitem = (ArrowItem) Items.ARROW;
        AbstractArrow abstractarrowentity = arrowitem.createArrow(worldIn, stackBow, player);
        abstractarrowentity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
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

    //射出相同的箭矢
    public static void spawnArrow(AbstractArrow arrow, ArrowItem arrowItem, Level level, Vec3 offsetVector) {
        AbstractArrow abstractArrow = arrowItem.createArrow(level, new ItemStack(Items.BOW), (LivingEntity) arrow.getOwner());
        abstractArrow.deserializeNBT(arrow.serializeNBT().copy());
        abstractArrow.setUUID(UUID.randomUUID());
        abstractArrow.setPos(arrow.getX() + offsetVector.x(), arrow.getY(), arrow.getZ() + offsetVector.z());
        level.addFreshEntity(abstractArrow);
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
