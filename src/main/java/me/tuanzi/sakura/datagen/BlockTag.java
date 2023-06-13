package me.tuanzi.sakura.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class BlockTag extends BlockTagsProvider {
    DeferredRegister<? extends Block> deferredRegister;

    public BlockTag(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, DeferredRegister<? extends Block> deferredRegister, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, modId, existingFileHelper);
        this.deferredRegister = deferredRegister;
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        addPickaxe(getBlocks());
    }

    protected Set<? extends Block> getBlocks() {
        return deferredRegister.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
    }

    protected final void addPickaxe(Set<? extends Block> blocks) {
        for (Block block : blocks) {
            if (block.defaultMapColor() == MapColor.STONE || block.defaultMapColor() == MapColor.METAL) {
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
            }
        }
    }

    protected final void addAxe(Block... blocks) {
        tag(BlockTags.MINEABLE_WITH_AXE).add(blocks);
    }

    protected final void addShovel(Block... blocks) {
        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(blocks);
    }

    protected final void addHoe(Block... blocks) {
        tag(BlockTags.MINEABLE_WITH_HOE).add(blocks);
    }

    protected final void addStoneTool(Block... blocks) {
        tag(BlockTags.NEEDS_STONE_TOOL).add(blocks);
    }

    protected final void addIronTool(Block... blocks) {
        tag(BlockTags.NEEDS_IRON_TOOL).add(blocks);
    }

    protected final void addDiamondTool(Block... blocks) {
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(blocks);
    }
}
