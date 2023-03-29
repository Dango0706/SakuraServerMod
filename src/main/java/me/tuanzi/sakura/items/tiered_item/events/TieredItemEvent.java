package me.tuanzi.sakura.items.tiered_item.events;

import me.tuanzi.sakura.items.tiered_item.GreatswordItem;
import me.tuanzi.sakura.items.tiered_item.HalberdItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TieredItemEvent {

    @SubscribeEvent
    public static void attack(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Item mainHand = player.getMainHandItem().getItem();
        //双手武器拿着减攻速,减攻击力
        if (mainHand instanceof HalberdItem ||
                mainHand instanceof GreatswordItem) {
            if (!(player.getOffhandItem().getItem() instanceof AirItem)) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20, 3));
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 0));
            }
        }
    }


}
