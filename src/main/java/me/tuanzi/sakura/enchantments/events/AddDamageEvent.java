package me.tuanzi.sakura.enchantments.events;

import me.tuanzi.sakura.configs.Config;
import me.tuanzi.sakura.enchantments.EnchantmentReg;
import me.tuanzi.sakura.items.tiered_item.DaggerItem;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
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
        //原本的伤害
        float baseDamage = event.getAmount();
        //总增加的伤害
        float damage = 0;
        //总减少伤害
        float reduceDamage = 0;
        //受伤者
        LivingEntity victim = event.getEntity();

        //(有伤害者)伤害计算
        if (event.getSource().getEntity() instanceof LivingEntity abuser && !abuser.level().isClientSide()) {
            ItemStack mainHand = abuser.getMainHandItem();
            //是匕首:(增加基础伤害)
            if (mainHand.getItem() instanceof DaggerItem daggerItem) {
                boolean addDamage = true;
                for (ItemStack itemStack : victim.getArmorSlots()) {
                    if (itemStack.getItem() instanceof ArmorItem item) {
                        //穿胸甲了
                        if (item.getEquipmentSlot() == EquipmentSlot.CHEST) {
                            addDamage = false;
                        }
                    }
                }
                if (addDamage)
                    damage += daggerItem.getDamage();
            }
            //增加基础伤害.
            //英勇
            if (mainHand.getEnchantmentLevel(EnchantmentReg.HEROIC.get()) > 0) {
                //基础伤害 * (0.3 * 等级 + 已消耗耐久的百分比 * 2),不小于1
                damage += event.getAmount() * Math.max((0.3 * mainHand.getEnchantmentLevel(EnchantmentReg.HEROIC.get()) + ((float) mainHand.getDamageValue() / (float) mainHand.getMaxDamage()) * 2), 1) - baseDamage;
            }
            //血怒
            if (mainHand.getEnchantmentLevel(EnchantmentReg.BLOOD_RAGE.get()) > 0) {
                //基础伤害 * (0.3 * 等级 + 已消耗耐久的百分比 * 2),不小于1
                damage += (abuser.getMaxHealth()  - abuser.getHealth())  / abuser.getMaxHealth() * 2 * mainHand.getEnchantmentLevel(EnchantmentReg.BLOOD_RAGE.get()) ;
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
                float daytime = abuser.level().getTimeOfDay(1) * 24000;
                boolean isRain = abuser.level().isRaining();
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
            //增加乘积伤害.
            //即基础伤害之上添加伤害.
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
                victim.getPersistentData().putBoolean("isAddMagicDamage", true);
                victim.getPersistentData().putFloat("magicDamage", (event.getAmount() + damage) * 0.2f * abuser.getMainHandItem().getEnchantmentLevel(EnchantmentReg.MAGIC_SOLUBLE.get()));
            } else {
                victim.getPersistentData().remove("isAddMagicDamage");
                victim.getPersistentData().remove("magicDamage");
            }
        }
        //(有/无伤害者)伤害计算
        if (!victim.level().isClientSide()) {
            for (ItemStack itemStack : victim.getArmorSlots()) {
                //魔法保护
                if (itemStack.getEnchantmentLevel(EnchantmentReg.MAGIC_PROTECTION.get()) > 0) {
                    if (magicDamage > 0) {
                        //削减魔法伤害
                        victim.getPersistentData().putFloat("magicDamage", victim.getPersistentData().getFloat("magicDamage") * (0.05f * itemStack.getEnchantmentLevel(EnchantmentReg.MAGIC_PROTECTION.get())));
                    }
                    if (event.getSource().is(DamageTypes.MAGIC)) {
                        reduceDamage += event.getAmount() * (0.05f * itemStack.getEnchantmentLevel(EnchantmentReg.MAGIC_PROTECTION.get()));
                    }
                }
                //闪避
                if (itemStack.getEnchantmentLevel(EnchantmentReg.DODGE.get()) > 0) {
                    //1% * 等级
                    if (victim.getRandom().nextDouble() < 0.01 * itemStack.getEnchantmentLevel(EnchantmentReg.DODGE.get())) {
                        event.setAmount(0);
                        damage = 0;
                        magicDamage = 0;
                        isAddMagicDamage = false;
                        //消耗3点耐久值.
                        itemStack.hurt(3, victim.getRandom(), null);
                        //播放音效
                        if (victim instanceof ServerPlayer serverPlayer)
                            serverPlayer.connection.send(new ClientboundSoundPacket(Holder.direct(SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("minecraft:entity.arrow.shoot"))), SoundSource.HOSTILE, victim.getX(), victim.getY(), victim.getZ(), 1, 1.7f, 1));
                        if (event.getSource().getEntity() instanceof ServerPlayer serverPlayer)
                            serverPlayer.connection.send(new ClientboundSoundPacket(Holder.direct(SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("minecraft:entity.arrow.shoot"))), SoundSource.HOSTILE, victim.getX(), victim.getY(), victim.getZ(), 1, 1.7f, 1));

                    }
                }
            }

            //最后添加伤害
            event.setAmount(event.getAmount() + damage);
            //debug
            LOGGER.debug("计算前总物理伤害:" + (event.getAmount()));
            //减去魔法伤害
            event.setAmount(event.getAmount() - victim.getPersistentData().getFloat("magicDamage"));
            LOGGER.debug("计算前总伤害(-魔法伤害):" + (event.getAmount()));

            //最后减去伤害
            event.setAmount(event.getAmount() - reduceDamage);

        }
        //debug
        LOGGER.debug("计算前总伤害:" + (event.getAmount()));
    }
}
