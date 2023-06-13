package me.tuanzi.sakura.blocks.demonization;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.Collections;
import java.util.List;

public class MagicTable extends Block {
    public MagicTable() {
        super(Properties.of().strength(5).mapColor(MapColor.STONE));
    }

    @Override
    public List<ItemStack> getDrops(BlockState state,  LootParams.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }

}
