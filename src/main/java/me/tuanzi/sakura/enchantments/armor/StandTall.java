package me.tuanzi.sakura.enchantments.armor;

import me.tuanzi.sakura.enchantments.EnchantmentReg;
import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class StandTall extends SakuraEnchantment {

    public StandTall() {
        super(Rarity.VERY_RARE, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void deathEvent(LivingDamageEvent event) {
        if (event.getEntity() instanceof Player player && event.getAmount() > player.getHealth()) {
            ItemStack itemStack = player.getItemBySlot(EquipmentSlot.CHEST);
            if (itemStack.getEnchantmentLevel(EnchantmentReg.STAND_TALL.get()) > 0) {
                int needXp = (int) (event.getAmount() - 2 * itemStack.getEnchantmentLevel(EnchantmentReg.STAND_TALL.get()));
                if (player.totalExperience > needXp) {
                    event.setAmount(0);
                    player.giveExperiencePoints(-needXp);
                }
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public SakuraRarity getSakuraRarity() {
        return SakuraRarity.LEGENDARY;
    }


}
