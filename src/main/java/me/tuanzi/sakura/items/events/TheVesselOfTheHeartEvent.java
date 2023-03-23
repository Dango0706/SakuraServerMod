package me.tuanzi.sakura.items.events;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TheVesselOfTheHeartEvent {

    @SubscribeEvent
    public static void clone(PlayerEvent.Clone event) {
        //old
        Player old = event.getOriginal();
        //new
        Player New = event.getEntity();
        if (old != null && New != null) {
            //心之容器
            New.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(old.getAttributeBaseValue(Attributes.MAX_HEALTH));
        }


    }


}
