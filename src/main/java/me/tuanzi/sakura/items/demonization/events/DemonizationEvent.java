package me.tuanzi.sakura.items.demonization.events;

import me.tuanzi.sakura.items.ItemReg;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DemonizationEvent {
    @SubscribeEvent
    public static void wardenDeathEvent(LivingDeathEvent event) {
        if (event.getEntity() instanceof Warden warden && event.getSource().getEntity() instanceof Player player && !player.level.isClientSide()) {
            //15%*抢夺等级+1的概率掉落
            if (player.getRandom().nextDouble() < 0.15 * (player.getMainHandItem().getEnchantmentLevel(Enchantments.MOB_LOOTING) + 1)) {
                ItemStack itemStack = new ItemStack(ItemReg.WARDEN_HEART.get());
                ItemEntity itemEntity = new ItemEntity(warden.getLevel(), warden.getX(), warden.getY(), warden.getZ(), itemStack);
                itemEntity.spawnAtLocation(itemStack);
            }
        }
    }

    @SubscribeEvent
    public static void playerCopy(PlayerEvent.Clone event) {
        //old
        Player old = event.getOriginal();
        //new
        Player New = event.getEntity();
        //避免保底消失
        if (old != null && New != null && !New.level.isClientSide()) {
            if (old.getPersistentData().contains("sakura:draw_total_count")) {
                New.getPersistentData().putInt("sakura:draw_total_count", old.getPersistentData().getInt("sakura:draw_total_count"));
            }
            if (old.getPersistentData().contains("sakura:draw_leg_count")) {
                New.getPersistentData().putInt("sakura:draw_leg_count", old.getPersistentData().getInt("sakura:draw_leg_count"));
            }
            if (old.getPersistentData().contains("sakura:draw_rare_count")) {
                New.getPersistentData().putInt("sakura:draw_rare_count", old.getPersistentData().getInt("sakura:draw_rare_count"));
            }
        }
    }

}
