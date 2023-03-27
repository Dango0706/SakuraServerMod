package me.tuanzi.sakura.items.tiered_item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static me.tuanzi.sakura.utils.Utils.*;
import static net.minecraftforge.common.ForgeMod.ATTACK_RANGE;

@Mod.EventBusSubscriber
public class HalberdItem extends TieredItem implements Vanishable {

    private final float attackDamage;
    /**
     * Modifiers applied when the item is in the mainhand of a user.
     */
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public HalberdItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pProperties);
        this.attackDamage = pAttackDamageModifier + pTier.getAttackDamageBonus();
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", pAttackSpeedModifier, AttributeModifier.Operation.ADDITION));
        builder.put(ATTACK_RANGE.get(), new AttributeModifier(DAGGER_ITEM_BASE_ATTACK_RANGE_UUID, "Weapon modifier", 0.5, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    public float getDamage() {
        return this.attackDamage;
    }

    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.hurtAndBreak(1, pAttacker, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }

    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

    /**
     * Can this Item disable a shield
     *
     * @param stack    The ItemStack
     * @param shield   The shield in question
     * @param entity   The LivingEntity holding the shield
     * @param attacker The LivingEntity holding the ItemStack
     * @return True if this ItemStack can disable the shield in question.
     */
    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }

    /**
     * Allows items to add custom lines of information to the mouseover description.
     *
     * @param pStack
     * @param pLevel
     * @param pTooltipComponents
     * @param pIsAdvanced
     */
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.empty().append("双手武器").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.empty().append("-副手持物品会使武器攻速变得更慢,伤害更低!").withStyle(ChatFormatting.GRAY));
        }
        pTooltipComponents.add(Component.empty().append("穿刺").withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.BOLD));
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.empty().append("-使敌方盾牌短暂失效").withStyle(ChatFormatting.GRAY));
        }
        if (!Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.empty().append("按shift查看详细").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
        }
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.category == EnchantmentCategory.WEAPON;
    }
}
