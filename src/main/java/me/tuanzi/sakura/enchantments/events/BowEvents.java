package me.tuanzi.sakura.enchantments.events;

import me.tuanzi.sakura.enchantments.EnchantmentReg;
import me.tuanzi.sakura.items.tiered_item.SakuraBowItem;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Method;

import static me.tuanzi.sakura.enchantments.bow.MultipleShoot.spawnArrow;
import static net.minecraft.world.InteractionHand.MAIN_HAND;
import static net.minecraft.world.InteractionHand.OFF_HAND;

@Mod.EventBusSubscriber
public class BowEvents {
    @SubscribeEvent
    public static void useBow(LivingEntityUseItemEvent event) {

    }

    //用完弓
    @SubscribeEvent
    public static void useBowShoot(ArrowLooseEvent event) {
        ItemStack itemStack = event.getBow();
        if (!(itemStack.getItem() instanceof SakuraBowItem)) {
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
    }

    //刚开始用弓
    @SubscribeEvent
    public static void beforeUseBow(ArrowNockEvent event) {
        if(event.getBow().getEnchantmentLevel(Enchantments.INFINITY_ARROWS)>0){
            event.getEntity().startUsingItem(event.getHand());
            event.setAction(InteractionResultHolder.success(event.getBow()));
        }
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        //快速拉弓
        Player player = event.player;
        ItemStack heldItem = player.getItemInHand(MAIN_HAND);
        if (!(heldItem.getItem() instanceof BowItem)) heldItem = player.getItemInHand(OFF_HAND);
        if (!(heldItem.getItem() instanceof BowItem) || heldItem.getEnchantmentLevel(EnchantmentReg.QUICK_DRAW.get()) <= 0)
            return;
        if (player.isUsingItem()) {
            for (int i = 0; i < EnchantmentHelper.getEnchantmentLevel(EnchantmentReg.QUICK_DRAW.get(), player); i++) {
                player.getPersistentData().putInt("QuickDrawCd", player.getPersistentData().getInt("QuickDrawCd") + 1);
            }
            if (player.getPersistentData().getInt("QuickDrawCd") > 10) {
                tickHeldBow(player);
                player.getPersistentData().putInt("QuickDrawCd", 0);
            }

        }
    }
    //快速拉弓(反射)
    private static void tickHeldBow(Player player) {
        try {
            Method m = ObfuscationReflectionHelper.findMethod(LivingEntity.class, "updatingUsingItem");
            m.invoke(player);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
