package me.tuanzi.sakura.enchantments.events;

import me.tuanzi.sakura.enchantments.EnchantmentReg;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DropXPEvent {
    @SubscribeEvent
    public static void dropXp(LivingExperienceDropEvent event) {
        Player player = event.getAttackingPlayer();
        if (player != null && !player.getLevel().isClientSide()) {
            if (player.getMainHandItem().getEnchantmentLevel(EnchantmentReg.EDUCATION.get()) > 0) {
                event.setDroppedExperience((int) (event.getDroppedExperience() * (1 + 0.5 * player.getMainHandItem().getEnchantmentLevel(EnchantmentReg.EDUCATION.get()))));
            }
        }
    }
}
