package me.tuanzi.sakura.enchantments.events;

import me.tuanzi.sakura.enchantments.EnchantmentReg;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.HashMap;

@Mod.EventBusSubscriber
public class SoulBound {
    /*暂时只能通过别人捡到使其失效*/
    static HashMap<String, ArrayList<ItemStack>> hashMap = new HashMap<>();

    @SubscribeEvent
    public static void soul(LivingDeathEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && !player.level.isClientSide) {
            NonNullList<ItemStack> itemStacks = player.getInventory().items;
            ArrayList<ItemStack> itemStacks1 = new ArrayList<>();
            for (ItemStack itemStack : itemStacks) {
                if (itemStack.getEnchantmentLevel(EnchantmentReg.SOUL_BOUND.get()) > 0) {
                    itemStacks1.add(itemStack);
                    //添加标签
                    CompoundTag tag = new CompoundTag();
                    tag.putBoolean("enable", true);
                    itemStack.addTagElement("SoulBound", tag);
                }
            }
            hashMap.put(player.getName().getString(), itemStacks1);
        }
    }


    @SubscribeEvent
    public static void soul_bound(PlayerEvent.Clone event) {
        if (event.getOriginal() instanceof ServerPlayer player) {
            if (player.deathTime > 0 && hashMap.containsKey(player.getName().getString())) {
                for (ItemStack itemStack : hashMap.get(player.getName().getString())) {
                    event.getEntity().addItem(itemStack);
                }
                hashMap.remove(player.getName().getString());
            }
        }
    }

    @SubscribeEvent
    public static void sou(TickEvent.LevelTickEvent event) {
        //todo:fix暂时只能通过别人捡到使其失效
        if (!event.level.isClientSide()) {

        }
    }

}
