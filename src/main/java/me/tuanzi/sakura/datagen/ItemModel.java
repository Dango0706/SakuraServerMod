package me.tuanzi.sakura.datagen;

import me.tuanzi.sakura.items.tiered_item.HalberdItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ItemModel extends ItemModelProvider {

    public static final ResourceLocation GENERATED = new ResourceLocation("item/generated");
    public static final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");
    protected final DeferredRegister<? extends Item> deferredRegister;
    protected Set<Item> skipItems = new HashSet<>();

    //这里的DeferredRegister只是为了方便遍历物品 你也可以选择使用net.minecraft.core.Registry#ITEM来进行遍历
    public ItemModel(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper, DeferredRegister<? extends Item> deferredRegister) {
        super(generator.getPackOutput(), modid, existingFileHelper);
        this.deferredRegister = deferredRegister;
    }

    private static String name(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    @Override
    protected void registerModels() {
        Set<Item> items = getItems();
        //跳过战戟
        skipItems.addAll(items.stream().filter(item -> item instanceof HalberdItem).toList());

        items.removeAll(skipItems);
        registerItemBlock(items.stream()
                .filter(item -> item instanceof BlockItem)
                .map(item -> (BlockItem) item)
                .collect(Collectors.toSet()));

        items.removeAll(skipItems);
        registerItem(items);
    }

    //为了方便你们直接继承以后，可以跳过你们想跳过的物品的模型生成
    protected void skipItems(Item... items) {
        Collections.addAll(skipItems, items);
    }

    protected void skipItems(Collection<? extends Item> items) {
        skipItems.addAll(items);
    }

    protected Set<Item> getItems() {
        return deferredRegister.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
    }

    //模型生成主逻辑，在注册后，会设置已注册的方块物品为跳过状态。防止覆盖生成。
    protected void registerItemBlock(@NotNull Set<BlockItem> blockItems) {
        blockItems.forEach(blockItem -> withExistingParent(name(blockItem),
                modLoc("block/" + name(blockItem))));
        skipItems(blockItems);
    }

    protected void registerItem(Set<Item> items) {
        items.forEach(this::generatedItem);
        items.stream()
                //如果属于工具类型 就使用工具模型
                .filter(item -> item instanceof TieredItem)
                .forEach(this::handheldItem);
    }

    protected final ItemModelBuilder generatedItem(String name) {
        return withExistingParent(name, GENERATED)
                .texture("layer0", modLoc("item/" + name));
    }

    protected final ItemModelBuilder generatedItem(Item item) {
        return generatedItem(name(item));
    }

    protected final ItemModelBuilder handheldItem(String name) {
        return withExistingParent(name, HANDHELD)
                .texture("layer0", modLoc("item/" + name));
    }

    protected final ItemModelBuilder handheldItem(Item item) {
        return handheldItem(name(item));
    }

}
