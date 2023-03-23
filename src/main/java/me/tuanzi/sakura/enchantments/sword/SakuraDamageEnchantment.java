package me.tuanzi.sakura.enchantments.sword;

import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SakuraDamageEnchantment extends SakuraEnchantment {

    public static final int ALL = 0;
    public static final int UNDEAD = 1;
    public static final int ARTHROPODS = 2;
    private static final String[] NAMES = new String[]{"all", "undead", "arthropods"};
    private static final int[] MIN_COST = new int[]{1, 5, 5};
    private static final int[] LEVEL_COST = new int[]{11, 8, 8};
    private static final int[] LEVEL_COST_SPAN = new int[]{20, 20, 20};
    private final int type;

    private final boolean aBoolean;

    public SakuraDamageEnchantment(int type, boolean aBoolean) {
        super(aBoolean ? Rarity.UNCOMMON : Rarity.COMMON, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        this.type = type;
        this.aBoolean = aBoolean;
    }

    /**
     * Returns the minimal value of enchantability needed on the enchantment level passed.
     */
    public int getMinCost(int pEnchantmentLevel) {
        if (aBoolean) {
            return (MIN_COST[this.type] + (pEnchantmentLevel - 1) * LEVEL_COST[this.type]) * 2;
        } else {
            return (MIN_COST[this.type] + (pEnchantmentLevel - 1) * LEVEL_COST[this.type]) / 2;
        }
    }

    public int getMaxCost(int pEnchantmentLevel) {
        if (aBoolean) {
            return (this.getMinCost(pEnchantmentLevel) + LEVEL_COST_SPAN[this.type]) * 2;
        } else {
            return (this.getMinCost(pEnchantmentLevel) + LEVEL_COST_SPAN[this.type]) / 2;

        }

    }

    @Override
    public SakuraRarity getSakuraRarity() {
        if (aBoolean) {
            return SakuraRarity.UNCOMMON;
        } else {
            return SakuraRarity.COMMON;
        }
    }

    public int getMaxLevel() {
        return 5;
    }

    /**
     * Calculates the additional damage that will be dealt by an item with this enchantment. This alternative to
     * calcModifierDamage is sensitive to the targets EnumCreatureAttribute.
     *
     * @param pLevel The level of the enchantment being used.
     */
    public float getDamageBonus(int pLevel, MobType pCreatureType) {
        if (aBoolean) {
            if (this.type == 0) {
                return 1.8F * pLevel;
            } else if (this.type == 1 && pCreatureType == MobType.UNDEAD) {
                return (float) pLevel * 3.2F;
            } else {
                return this.type == 2 && pCreatureType == MobType.ARTHROPOD ? (float) pLevel * 3.2F : 0.0F;
            }
        } else {
            if (this.type == 0) {
                return 0.35F * pLevel;
            } else if (this.type == 1 && pCreatureType == MobType.UNDEAD) {
                return (float) pLevel * 1.65F;
            } else {
                return this.type == 2 && pCreatureType == MobType.ARTHROPOD ? (float) pLevel * 1.65F : 0.0F;
            }
        }

    }

    /**
     * Determines if the enchantment passed can be applied together with this enchantment.
     *
     * @param pEnch The other enchantment to test compatibility with.
     */
    public boolean checkCompatibility(Enchantment pEnch) {
        return !(pEnch instanceof DamageEnchantment || pEnch instanceof SakuraDamageEnchantment);
    }

    /**
     * Determines if this enchantment can be applied to a specific ItemStack.
     *
     * @param pStack The ItemStack to test.
     */
    public boolean canEnchant(ItemStack pStack) {
        return pStack.getItem() instanceof AxeItem || super.canEnchant(pStack);
    }

    /**
     * Called whenever a mob is damaged with an item that has this enchantment on it.
     *
     * @param pUser   The user of the enchantment.
     * @param pTarget The entity being attacked.
     * @param pLevel  The level of the enchantment.
     */
    public void doPostAttack(LivingEntity pUser, Entity pTarget, int pLevel) {
        if (pTarget instanceof LivingEntity livingentity) {
            if (aBoolean) {
                if (this.type == 2 && pLevel > 0 && livingentity.getMobType() == MobType.ARTHROPOD) {
                    int i = (20 + pUser.getRandom().nextInt(10 * pLevel)) * 2;
                    livingentity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, i, 5));
                }
            } else {
                if (this.type == 2 && pLevel > 0 && livingentity.getMobType() == MobType.ARTHROPOD) {
                    int i = (int) ((20 + pUser.getRandom().nextInt(10 * pLevel)) * 0.75);
                    livingentity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, i, 1));
                }
            }

        }

    }

    @Override
    public boolean isTreasureOnly() {
        return false;
    }
}
