package me.tuanzi.sakura.items.functional;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class HeartToxic extends Item {
    public HeartToxic() {
        super(new Properties());
    }

    @Override
    public Component getName(ItemStack p_41458_) {
        return super.getName(p_41458_).copy().withStyle(ChatFormatting.GREEN);
    }

    @Override
    public InteractionResult useOn(UseOnContext event) {
        if (event.getPlayer() != null && !event.getPlayer().level().isClientSide()) {
            Player player = event.getPlayer();
            player.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(player.getMaxHealth() - 2);
            player.setHealth(player.getHealth() - 2);
            if (!player.isCreative()) {
                event.getItemInHand().setCount(event.getItemInHand().getCount() - 1);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
