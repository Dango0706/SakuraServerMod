package me.tuanzi.sakura.items.events;

import me.tuanzi.sakura.items.ItemReg;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FunctionalItemEvents {

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

    @SubscribeEvent
    public static void livingDeathEntity(LivingDeathEvent event) {
        if (event.getEntity() instanceof WitherSkeleton witherSkeleton && event.getSource().getEntity() instanceof Player player && !player.level.isClientSide()) {
            //15%*抢夺等级+1的概率掉落
            if (player.getRandom().nextDouble() < 0.25 * (player.getMainHandItem().getEnchantmentLevel(Enchantments.MOB_LOOTING) + 1)) {
                ItemStack itemStack = new ItemStack(ItemReg.WITHER_SKELETON_SKULL_DEBRIS.get());
                ItemEntity itemEntity = new ItemEntity(witherSkeleton.getLevel(), witherSkeleton.getX(), witherSkeleton.getY(), witherSkeleton.getZ(), itemStack);
                itemEntity.spawnAtLocation(itemStack);
            }
        }
    }



}
