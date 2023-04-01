package me.tuanzi.sakura.enchantments.events;

import me.tuanzi.sakura.enchantments.EnchantmentReg;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class HealthEvent {

    @SubscribeEvent
    public static void healEvent(LivingHealEvent event) {

        if (event.getEntity().getSlot(102).get().getEnchantmentLevel(EnchantmentReg.BATHING_REJUVENATE.get()) > 0) {
            //沐浴回春
            event.setAmount(event.getAmount() * (1 + 0.2f * event.getEntity().getSlot(102).get().getEnchantmentLevel(EnchantmentReg.BATHING_REJUVENATE.get())));
        }

    }

}
