package me.tuanzi.sakura.enchantments.events;

import me.tuanzi.sakura.enchantments.EnchantmentReg;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Iterator;
import java.util.ListIterator;

@Mod.EventBusSubscriber
public class SoulBound {
    @SubscribeEvent
    public static void soul_bound(PlayerEvent.Clone event) {
        if (event.getOriginal() instanceof ServerPlayer player) {
            if(player.deathTime>0){
                Player nPlayer = event.getEntity();
                ListIterator<ItemStack> iterator =  player.getInventory().items.listIterator();
                for (ListIterator<ItemStack> it = iterator; it.hasNext(); ) {
                    //重生时获取遍历物品栏,有灵魂绑定则保留.
                    ItemStack itemStack = it.next();
                    if(itemStack.getEnchantmentLevel(EnchantmentReg.SOUL_BOUND.get())>0){
                        nPlayer.addItem(itemStack.copy());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void test (LivingDropsEvent event){
        if(event.getEntity().getLevel().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY))
            return;

        if(event.getEntity() instanceof Player player){
            if (player instanceof FakePlayer || event.isCanceled()) {
                return;
            }

            Iterator<ItemEntity> iterator = event.getDrops().iterator();
            while (iterator.hasNext()){
                ItemEntity item = iterator.next();
                ItemStack itemStack = item.getItem();
                if(itemStack.getEnchantmentLevel(EnchantmentReg.SOUL_BOUND.get())>0){
                    //清除掉落物,并且返还给死亡者
                    iterator.remove();
                    player.addItem(itemStack.copy());
                }
            }

        }
    }

}
