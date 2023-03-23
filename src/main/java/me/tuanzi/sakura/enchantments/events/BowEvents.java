package me.tuanzi.sakura.enchantments.events;

import me.tuanzi.sakura.enchantments.EnchantmentReg;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static me.tuanzi.sakura.enchantments.bow.MultipleShoot.spawnArrow;

@Mod.EventBusSubscriber
public class BowEvents {
    @SubscribeEvent
    public static void useBow(LivingEntityUseItemEvent event) {

    }

    //用完弓
    @SubscribeEvent
    public static void useBowShoot(ArrowLooseEvent event) {
        ItemStack itemStack = event.getBow();
        if (itemStack.getEnchantmentLevel(EnchantmentReg.MULTIPLE_SHOOT.get()) > 0) {
            Vec3 playerDirection = event.getEntity().getLookAngle();
            for (int i = 0; i < itemStack.getEnchantmentLevel(EnchantmentReg.MULTIPLE_SHOOT.get()); i++) {
                float x;
                float y;
                float z;
                float f = 6 - itemStack.getEnchantmentLevel(EnchantmentReg.MULTIPLE_SHOOT.get());
                x = event.getEntity().getRandom().nextFloat() * f;
                y = event.getEntity().getRandom().nextFloat() * f;
                z = event.getEntity().getRandom().nextFloat() * f;
                if (event.getEntity().getRandom().nextBoolean()) {
                    x *= -1;
                }
                if (event.getEntity().getRandom().nextBoolean()) {
                    y *= -1;
                }
                if (event.getEntity().getRandom().nextBoolean()) {
                    z *= -1;
                }
                Vec3 newVec3 = playerDirection.add(x, y, z);
                spawnArrow(event.getLevel(), event.getEntity(), itemStack, event.getCharge(), newVec3.normalize());
            }

        }
    }

    //刚开始用弓
    @SubscribeEvent
    public static void beforeUseBow(ArrowNockEvent event) {

    }
}
