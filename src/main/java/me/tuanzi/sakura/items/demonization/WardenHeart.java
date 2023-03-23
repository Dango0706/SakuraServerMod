package me.tuanzi.sakura.items.demonization;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class WardenHeart extends Item {
    public WardenHeart() {
        super(new Item.Properties());
    }

    @Override
    public Component getName(ItemStack p_41458_) {
        return super.getName(p_41458_).copy().withStyle(ChatFormatting.LIGHT_PURPLE);
    }
}
