package me.tuanzi.sakura.effects.events;

import me.tuanzi.sakura.effects.EffectReg;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PotionEffectEvent {

    @SubscribeEvent
    public static void dieEvent(LivingDeathEvent event) {
        LivingEntity livingEntity = event.getEntity();
        DamageSource damageSource = event.getSource();
        if (livingEntity.hasEffect(EffectReg.UNDYING.get())) {
            if (damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY))
                return;

            event.setCanceled(true);
            if (livingEntity instanceof ServerPlayer serverplayer) {
                serverplayer.awardStat(Stats.ITEM_USED.get(Items.TOTEM_OF_UNDYING), 1);
                CriteriaTriggers.USED_TOTEM.trigger(serverplayer, new ItemStack(Items.TOTEM_OF_UNDYING));
            }
            livingEntity.setHealth(1.0F);
            livingEntity.removeAllEffects();
            livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
            livingEntity.level().broadcastEntityEvent(livingEntity, (byte) 35);

        }
    }

}
