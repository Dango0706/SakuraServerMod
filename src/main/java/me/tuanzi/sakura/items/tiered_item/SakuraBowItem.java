package me.tuanzi.sakura.items.tiered_item;

import me.tuanzi.sakura.enchantments.EnchantmentReg;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import static me.tuanzi.sakura.enchantments.bow.MultipleShoot.spawnArrow;

public class SakuraBowItem extends BowItem {

    private final double level;

    public SakuraBowItem(Properties pProperties, double level) {
        super(pProperties);
        this.level = level;

    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, pStack) > 0;
            ItemStack itemstack = player.getProjectile(pStack);

            int i = this.getUseDuration(pStack) - pTimeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(pStack, pLevel, player, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = getPowerForTime(i);
                if (!((double) f < 0.1D)) {
                    boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem) itemstack.getItem()).isInfinite(itemstack, pStack, player));
                    if (!pLevel.isClientSide) {
                        ArrowItem arrowitem = (ArrowItem) (itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrow abstractarrow = arrowitem.createArrow(pLevel, itemstack, player);
                        abstractarrow = customArrow(abstractarrow);
                        abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);
                        if (f == 1.0F) {
                            abstractarrow.setCritArrow(true);
                        }
                        //添加伤害
                        abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + level * 2);

                        int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, pStack);
                        if (j > 0) {
                            abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (double) j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, pStack);
                        if (k > 0) {
                            abstractarrow.setKnockback(k);
                        }

                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, pStack) > 0) {
                            abstractarrow.setSecondsOnFire(100);
                        }

                        pStack.hurtAndBreak(1, player, (p_253596_) -> {
                            p_253596_.broadcastBreakEvent(player.getUsedItemHand());
                        });
                        if (flag1 || player.getAbilities().instabuild && (itemstack.is(Items.SPECTRAL_ARROW) || itemstack.is(Items.TIPPED_ARROW))) {
                            abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }
                        //多重射击
                        Vec3 playerDirection = pEntityLiving.getLookAngle();
                        for (int im = 0; im < pStack.getEnchantmentLevel(EnchantmentReg.MULTIPLE_SHOOT.get()); im++) {
                            float x;
                            float y;
                            float z;
                            float fm = 6 - pStack.getEnchantmentLevel(EnchantmentReg.MULTIPLE_SHOOT.get());
                            x = pEntityLiving.getRandom().nextFloat() * fm;
                            y = pEntityLiving.getRandom().nextFloat() * fm;
                            z = pEntityLiving.getRandom().nextFloat() * fm;
                            if (pEntityLiving.getRandom().nextBoolean()) {
                                x *= -1;
                            }
                            if (pEntityLiving.getRandom().nextBoolean()) {
                                y *= -1;
                            }
                            if (pEntityLiving.getRandom().nextBoolean()) {
                                z *= -1;
                            }
                            Vec3 newVec3 = playerDirection.add(x, y, z);
                            spawnArrow(abstractarrow, arrowitem, pLevel, newVec3);
                        }
                        //最终射出的箭
                        pLevel.addFreshEntity(abstractarrow);
                    }

                    pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.getInventory().removeItem(itemstack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    public int getDefaultProjectileRange() {
        return (int) Math.round(15 + 5 * level);
    }




    /**
     * How long it takes to use or consume an item
     *
     * @param pStack
     */
/*    @Override
    public int getUseDuration(ItemStack pStack) {
        int enchantmentLevel = pStack.getEnchantmentLevel(EnchantmentReg.QUICK_DRAW.get());
        return enchantmentLevel > 0 ? Math.max(72000 - 12000 * enchantmentLevel, 12000) : 72000;
    }*/




}
