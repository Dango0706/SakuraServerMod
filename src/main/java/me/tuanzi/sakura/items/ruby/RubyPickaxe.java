package me.tuanzi.sakura.items.ruby;

import me.tuanzi.sakura.items.ItemTiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;

public class RubyPickaxe extends PickaxeItem {
    public RubyPickaxe() {
        super(ItemTiers.RUBY, 1, -2.8F, (new Item.Properties()));
    }
}
