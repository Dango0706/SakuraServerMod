package me.tuanzi.sakura.enchantments.events;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import me.tuanzi.sakura.enchantments.EnchantmentReg;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static me.tuanzi.sakura.configs.Config.SKILLED_ATTACK_SPEED;
import static me.tuanzi.sakura.utils.Utils.*;
import static net.minecraftforge.common.ForgeMod.ATTACK_RANGE;
import static net.minecraftforge.common.ForgeMod.REACH_DISTANCE;

@Mod.EventBusSubscriber
public class AttributeEvent {
    @SubscribeEvent
    public static void attributeModifier(TickEvent.PlayerTickEvent event) {
//        if(event.player.getMainHandItem().getItem() instanceof  PickaxeItem item){
//            System.out.println("是镐子");
//            if (item.getTier().getLevel() >= 2){
//                System.out.println("等级>=2");
//            }
//        }


        if (!event.player.level.isClientSide()) {
            Player player = event.player;
            ItemStack itemStack = player.getMainHandItem();
            ItemStack chestplateStack = player.getItemBySlot(EquipmentSlot.CHEST);
            Multimap<Attribute, AttributeModifier> modifierMultimap = ArrayListMultimap.create();
            //加攻速
            modifierMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(SKILLED_UUID, "附魔[熟练]加攻速", SKILLED_ATTACK_SPEED.get() / 100 * itemStack.getEnchantmentLevel(EnchantmentReg.SKILLED.get()), AttributeModifier.Operation.MULTIPLY_BASE));
            if (itemStack.getEnchantmentLevel(EnchantmentReg.SKILLED.get()) > 0) {
                if (!player.getAttributes().hasModifier(Attributes.ATTACK_SPEED, SKILLED_UUID)) {
                    player.getAttributes().addTransientAttributeModifiers(modifierMultimap);
                }
            } else {
                player.getAttributes().removeAttributeModifiers(modifierMultimap);
            }
            //清除map
            modifierMultimap.clear();
            //加触及半径
            modifierMultimap.put(REACH_DISTANCE.get(), new AttributeModifier(ADD_EXTENSIONS_UUID, "附魔[触及范围扩展]加触及半径", chestplateStack.getEnchantmentLevel(EnchantmentReg.EXTENSIONS.get()), AttributeModifier.Operation.ADDITION));
            if (chestplateStack.getEnchantmentLevel(EnchantmentReg.EXTENSIONS.get()) > 0) {
                if (!player.getAttributes().hasModifier(REACH_DISTANCE.get(), ADD_EXTENSIONS_UUID)) {
                    player.getAttributes().addTransientAttributeModifiers(modifierMultimap);
                }
            } else {
                player.getAttributes().removeAttributeModifiers(modifierMultimap);
            }
            //清除map
            modifierMultimap.clear();
            //加攻击半径
            modifierMultimap.put(ATTACK_RANGE.get(), new AttributeModifier(ATTACK_EXTENSIONS_UUID, "附魔[攻击范围扩展]加攻击半径", 0.5 * chestplateStack.getEnchantmentLevel(EnchantmentReg.ATTACK_EXTENSIONS.get()), AttributeModifier.Operation.ADDITION));
            if (chestplateStack.getEnchantmentLevel(EnchantmentReg.ATTACK_EXTENSIONS.get()) > 0) {
                if (!player.getAttributes().hasModifier(ATTACK_RANGE.get(), ATTACK_EXTENSIONS_UUID)) {
                    player.getAttributes().addTransientAttributeModifiers(modifierMultimap);
                }
            } else {
                player.getAttributes().removeAttributeModifiers(modifierMultimap);
            }
            //清除map
            modifierMultimap.clear();
            //加最大血量
            modifierMultimap.put(Attributes.MAX_HEALTH, new AttributeModifier(ADD_MAX_HEALTH_UUID, "附魔[活力]加攻击半径", 2 * chestplateStack.getEnchantmentLevel(EnchantmentReg.VITALITY.get()), AttributeModifier.Operation.ADDITION));
            if (chestplateStack.getEnchantmentLevel(EnchantmentReg.VITALITY.get()) > 0) {
                if (!player.getAttributes().hasModifier(Attributes.MAX_HEALTH, ADD_MAX_HEALTH_UUID)) {
                    player.getAttributes().addTransientAttributeModifiers(modifierMultimap);
                }
            } else {
                player.getAttributes().removeAttributeModifiers(modifierMultimap);
            }

        }
    }
}
