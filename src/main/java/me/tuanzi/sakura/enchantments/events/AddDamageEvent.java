package me.tuanzi.sakura.enchantments.events;

import me.tuanzi.sakura.configs.Config;
import me.tuanzi.sakura.enchantments.EnchantmentReg;
import me.tuanzi.sakura.items.tiered_item.DaggerItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

import static me.tuanzi.sakura.SakuraMain.LOGGER;


@Mod.EventBusSubscriber()
public class AddDamageEvent {

    //魔法伤害
    public static float magicDamage = 0;
    //是否添加魔法伤害
    public static boolean isAddMagicDamage = false;

    //造成伤害之前的伤害.
    //最优先
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void addDamage(LivingHurtEvent event) {
        float baseDamage = event.getAmount();
        //总增加的伤害
        float damage = 0;
        //总减少伤害
        float reduceDamage = 0;
        //受伤者
        LivingEntity victim = event.getEntity();

        //(有伤害者)伤害计算
        if (event.getSource().getEntity() instanceof LivingEntity abuser && !abuser.level.isClientSide()) {
            ItemStack mainHand = abuser.getMainHandItem();
            //是匕首:(增加基础伤害)
            if (mainHand.getItem() instanceof DaggerItem daggerItem) {
                boolean addDamage = true;
                for (ItemStack itemStack : victim.getArmorSlots()) {
                    if (itemStack.getItem() instanceof ArmorItem item) {
                        //穿胸甲了
                        if (item.getSlot() == EquipmentSlot.CHEST) {
                            addDamage = false;
                        }
                    }
                }
                if (addDamage)
                    damage += daggerItem.getDamage();
            }

            //刺骨
            if (mainHand.getEnchantmentLevel(EnchantmentReg.PIERCING.get()) > 0) {
                //最大血量*1.5%*level
                //上限150.
                damage += Math.min(150, victim.getMaxHealth() * Config.PIERCING_MAX_HP.get() / 100 * abuser.getMainHandItem().getEnchantmentLevel(EnchantmentReg.PIERCING.get()));
            }
            //狂战士
            if (mainHand.getEnchantmentLevel(EnchantmentReg.BERSERKER.get()) > 0) {
                //增加 level*4-护甲 伤害
                damage += Math.max(4 * abuser.getMainHandItem().getEnchantmentLevel(EnchantmentReg.BERSERKER.get()) - abuser.getArmorValue(), 0);
            }
            //月光祝福/日光祝福
            if (mainHand.getEnchantmentLevel(EnchantmentReg.LUNAR_BLESSING.get()) > 0 || mainHand.getEnchantmentLevel(EnchantmentReg.APOLLO_BLESSING.get()) > 0) {
                float daytime = abuser.level.getTimeOfDay(1) * 24000;
                boolean isRain = abuser.level.isRaining();
                if (isRain) {
                    if (daytime > 12010 && daytime < 23992) {
                        //晚上加伤
                        damage += 2 * mainHand.getEnchantmentLevel(EnchantmentReg.LUNAR_BLESSING.get());
                    } else {
                        //晴天加伤
                        damage += 2 * mainHand.getEnchantmentLevel(EnchantmentReg.APOLLO_BLESSING.get());
                    }
                } else {
                    if (daytime > 12544 && daytime < 23460) {
                        //晚上加伤
                        damage += 2 * mainHand.getEnchantmentLevel(EnchantmentReg.LUNAR_BLESSING.get());
                    } else {
                        //晴天加伤
                        damage += 2 * mainHand.getEnchantmentLevel(EnchantmentReg.APOLLO_BLESSING.get());
                    }
                }
            }
            //追击
            if (mainHand.getEnchantmentLevel(EnchantmentReg.CHASE.get()) > 0) {
                //额外增加20%*level的已损生命值伤害
                damage += (victim.getMaxHealth() - victim.getHealth()) * 0.1 * mainHand.getEnchantmentLevel(EnchantmentReg.CHASE.get());
            }
            //重击
            if (mainHand.getEnchantmentLevel(EnchantmentReg.SMITE.get()) > 0) {
                int level = mainHand.getEnchantmentLevel(EnchantmentReg.SMITE.get());
                Random random = new Random();
                //有15%*level的几率暴击 额外造成30%*level的伤害
                if (random.nextInt(100) < level * 15) {
                    damage = (baseDamage + damage) * (1 + 0.3f * level);
                }
            }

            //最后结算攻速指数
            if (abuser instanceof ServerPlayer player) {
                //乘攻速百分比
                damage *= player.getAttackStrengthScale(1f);
            }
            //魔法易溶
            //然后分别计算魔法伤害与物理伤害
            if (mainHand.getEnchantmentLevel(EnchantmentReg.MAGIC_SOLUBLE.get()) > 0) {
                //增加为true
                isAddMagicDamage = true;
                magicDamage = (event.getAmount() + damage) * 0.2f * abuser.getMainHandItem().getEnchantmentLevel(EnchantmentReg.MAGIC_SOLUBLE.get());
            } else {
                magicDamage = 0;
                isAddMagicDamage = false;
            }


        }
        //(有/无伤害者)伤害计算
        if (!victim.level.isClientSide()) {
            //魔法保护
            for (ItemStack itemStack : victim.getArmorSlots()) {
                if (itemStack.getEnchantmentLevel(EnchantmentReg.MAGIC_PROTECTION.get()) > 0) {
                    if (magicDamage > 0) {
                        //削减魔法伤害
                        magicDamage -= event.getAmount() * (0.05f * itemStack.getEnchantmentLevel(EnchantmentReg.MAGIC_PROTECTION.get()));
                    }
                    if (event.getSource().isMagic()) {
                        reduceDamage += event.getAmount() * (0.05f * itemStack.getEnchantmentLevel(EnchantmentReg.MAGIC_PROTECTION.get()));
                    }
                }
            }

            //最后添加伤害
            event.setAmount(event.getAmount() + damage);
            //debug
            LOGGER.debug("计算前总物理伤害:" + (event.getAmount()));
            //减去魔法伤害
            event.setAmount(event.getAmount() - magicDamage);
            LOGGER.debug("计算前总伤害(-魔法伤害):" + (event.getAmount()));

            //最后减去伤害
            event.setAmount(event.getAmount() - reduceDamage);

        }

        //debug
        LOGGER.debug("计算前总伤害:" + (event.getAmount()));
    }
}
