package me.tuanzi.sakura.items.tiered_item.events;

import me.tuanzi.sakura.items.tiered_item.HalberdItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AirItem;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Events {

    @SubscribeEvent
    public static void attack(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player.getMainHandItem().getItem() instanceof HalberdItem) {
            if (!(player.getOffhandItem().getItem() instanceof AirItem)) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 20, 3));
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 0));
            }
        }
    }

    @SubscribeEvent
    public static void aVoid(ItemTooltipEvent event){

    }


}
