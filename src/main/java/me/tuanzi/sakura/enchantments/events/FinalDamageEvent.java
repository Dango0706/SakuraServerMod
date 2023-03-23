package me.tuanzi.sakura.enchantments.events;

import me.tuanzi.sakura.enchantments.EnchantmentReg;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static me.tuanzi.sakura.SakuraMain.LOGGER;
import static me.tuanzi.sakura.effects.EffectReg.SURRENDER;
import static me.tuanzi.sakura.enchantments.events.AddDamageEvent.isAddMagicDamage;
import static me.tuanzi.sakura.enchantments.events.AddDamageEvent.magicDamage;


//经过护甲,药水等计算后造成的伤害量
@Mod.EventBusSubscriber
public class FinalDamageEvent {
    @SubscribeEvent
    public static void Indent(LivingDamageEvent event) {
        //受害者
        System.out.println(event.getEntity());
        //施暴者 来源于谁(射箭找是谁射的箭)
        System.out.println(event.getSource().getEntity());
        //谁造成的伤害(射箭则为箭
        System.out.println(event.getSource().getDirectEntity());
        //被害者
        LivingEntity victim = event.getEntity();
        //吸血值(不计算魔法伤害)
        float heal = event.getAmount();
        //如果有抗性下降,则额外吸血
        if ((victim.getEffect(SURRENDER.get()) != null && victim.getEffect(SURRENDER.get()).getDuration() > 0)) {
            heal *= (1 + 0.1f * (victim.getEffect(SURRENDER.get()).getAmplifier() + 1));
        }
        //魔法易溶
        //最终添加magicDamage
        if (isAddMagicDamage) {
            event.setAmount(event.getAmount() + magicDamage);
            isAddMagicDamage = false;
            magicDamage = 0;
        }
        //抗性下降(每级增加10%最终受到的伤害)
        if ((victim.getEffect(SURRENDER.get()) != null && victim.getEffect(SURRENDER.get()).getDuration() > 0)) {
            event.setAmount(event.getAmount() * (1 + 0.1f * (victim.getEffect(SURRENDER.get()).getAmplifier() + 1)));
        }
        if (event.getSource().getEntity() instanceof LivingEntity abuser && !abuser.level.isClientSide()) {
            //主手物品
            ItemStack mainHand = abuser.getMainHandItem();
            //是玩家
            if (abuser instanceof ServerPlayer player && !player.level.isClientSide()) {
                //显血(鉴定) 获取最终伤害
                if (mainHand.getEnchantmentLevel(EnchantmentReg.IDENTIFICATION.get()) > 0) {
                    //todo:更多选择,不仅是ActionBar
                    player.connection.send(new ClientboundSetActionBarTextPacket(Component.empty().append("HP:" + Math.max(victim.getHealth() - (event.getAmount()), 0) + "/" + victim.getMaxHealth() + " §c-" + Math.min((event.getAmount()), victim.getHealth()))));
                }
            }
            //吸血
            if (mainHand.getEnchantmentLevel(EnchantmentReg.LIFE_STEAL.get()) > 0) {
                abuser.heal(heal * 0.1f * mainHand.getEnchantmentLevel(EnchantmentReg.LIFE_STEAL.get()));
            }
        }
        //debug
        LOGGER.info("计算后总伤害:" + (event.getAmount()));
    }
}
