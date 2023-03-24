package me.tuanzi.sakura.items.functional;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class XpBook extends Item {

    public XpBook() {
        super(new Properties().defaultDurability(1395));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getMainHandItem();
        if (pUsedHand == InteractionHand.MAIN_HAND) {
            //蹲下存
            if (pPlayer.getPose() == Pose.CROUCHING) {
                int needXp = Math.min(pPlayer.totalExperience, itemStack.getDamageValue());
                System.out.println("我有的:" + pPlayer.totalExperience);
                pPlayer.giveExperiencePoints(-needXp);
                itemStack.setDamageValue(itemStack.getDamageValue() - needXp);
                //站起来取
            } else if (pPlayer.getPose() == Pose.STANDING) {
                int inExp = itemStack.getMaxDamage() - itemStack.getDamageValue();
                System.out.println("在书里的:" + inExp);
                pPlayer.giveExperiencePoints(inExp);
                itemStack.setDamageValue(itemStack.getMaxDamage());

            }
        }
        return InteractionResultHolder.success(itemStack);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }


}
