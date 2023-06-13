package me.tuanzi.sakura.items;

import me.tuanzi.sakura.blocks.BlockReg;
import me.tuanzi.sakura.items.demonization.MagicStone;
import me.tuanzi.sakura.items.demonization.WardenHeart;
import me.tuanzi.sakura.items.functional.HeartToxic;
import me.tuanzi.sakura.items.functional.TheVesselOfTheHeart;
import me.tuanzi.sakura.items.functional.XpBook;
import me.tuanzi.sakura.items.jadeite.*;
import me.tuanzi.sakura.items.ruby.*;
import me.tuanzi.sakura.items.tiered_item.DaggerItem;
import me.tuanzi.sakura.items.tiered_item.GreatswordItem;
import me.tuanzi.sakura.items.tiered_item.HalberdItem;
import me.tuanzi.sakura.items.tiered_item.SakuraBowItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static me.tuanzi.sakura.SakuraMain.MODID;

/**
 * @author tuanzi
 * <p>
 * 注册物品
 */
public class ItemReg {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    //红宝石
    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", Ruby::new);
    public static final RegistryObject<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", RubyPickaxe::new);
    public static final RegistryObject<Item> RUBY_AXE = ITEMS.register("ruby_axe", RubyAxe::new);
    public static final RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword", RubySword::new);
    public static final RegistryObject<Item> RUBY_HOE = ITEMS.register("ruby_hoe", RubyHoe::new);
    public static final RegistryObject<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel", RubyShovel::new);

    public static final RegistryObject<Item> RUBY_HELMET = ITEMS.register("ruby_helmet", () -> new ArmorItem(MyArmorMaterial.RUBY, ArmorItem.Type.HELMET, (new Item.Properties())));
    public static final RegistryObject<Item> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () -> new ArmorItem(MyArmorMaterial.RUBY, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
    public static final RegistryObject<Item> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () -> new ArmorItem(MyArmorMaterial.RUBY, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
    public static final RegistryObject<Item> RUBY_BOOTS = ITEMS.register("ruby_boots", () -> new ArmorItem(MyArmorMaterial.RUBY, ArmorItem.Type.BOOTS, (new Item.Properties())));
    public static final RegistryObject<Item> RUBY_ORE = ITEMS.register("ruby_ore", () -> new BlockItem(BlockReg.RUBY_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEP_RUBY_ORE = ITEMS.register("deepslate_ruby_ore", () -> new BlockItem(BlockReg.DEEP_RUBY_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> RUBY_BLOCK = ITEMS.register("ruby_block", () -> new BlockItem(BlockReg.RUBY_BLOCK.get(), new Item.Properties()));
    //翡翠
    public static final RegistryObject<Item> JADEITE = ITEMS.register("jadeite_gem", Jadeite::new);
    public static final RegistryObject<Item> JADEITE_PICKAXE = ITEMS.register("jadeite_pickaxe", JadeitePickaxe::new);
    public static final RegistryObject<Item> JADEITE_AXE = ITEMS.register("jadeite_axe", JadeiteAxe::new);
    public static final RegistryObject<Item> JADEITE_SWORD = ITEMS.register("jadeite_sword", JadeiteSword::new);
    public static final RegistryObject<Item> JADEITE_HOE = ITEMS.register("jadeite_hoe", JadeiteHoe::new);
    public static final RegistryObject<Item> JADEITE_SHOVEL = ITEMS.register("jadeite_shovel", JadeiteShovel::new);
    public static final RegistryObject<Item> DEEP_JADEITE_ORE = ITEMS.register("deepslate_jadeite_ore", () -> new BlockItem(BlockReg.DEEP_JADEITE_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> JADEITE_BLOCK = ITEMS.register("jadeite_block", () -> new BlockItem(BlockReg.JADEITE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> JADEITE_HELMET = ITEMS.register("jadeite_helmet", () -> new ArmorItem(MyArmorMaterial.JADEITE, ArmorItem.Type.HELMET, (new Item.Properties().defaultDurability(500))));
    public static final RegistryObject<Item> JADEITE_CHESTPLATE = ITEMS.register("jadeite_chestplate", () -> new ArmorItem(MyArmorMaterial.JADEITE, ArmorItem.Type.CHESTPLATE, (new Item.Properties().defaultDurability(1000))));
    public static final RegistryObject<Item> JADEITE_LEGGINGS = ITEMS.register("jadeite_leggings", () -> new ArmorItem(MyArmorMaterial.JADEITE, ArmorItem.Type.LEGGINGS, (new Item.Properties().defaultDurability(1000))));
    public static final RegistryObject<Item> JADEITE_BOOTS = ITEMS.register("jadeite_boots", () -> new ArmorItem(MyArmorMaterial.JADEITE, ArmorItem.Type.BOOTS, (new Item.Properties().defaultDurability(500))));

    //tiered_item
    public static final RegistryObject<Item> POLE = ITEMS.register("pole", () -> new Item(new Item.Properties()));
    //dagger
    public static final RegistryObject<Item> WOODEN_DAGGER = ITEMS.register("wooden_dagger", () -> new DaggerItem(Tiers.WOOD, 1.8f, -2, new Item.Properties()));
    public static final RegistryObject<Item> STONE_DAGGER = ITEMS.register("stone_dagger", () -> new DaggerItem(Tiers.STONE, 1.8f, -2, new Item.Properties()));
    public static final RegistryObject<Item> IRON_DAGGER = ITEMS.register("iron_dagger", () -> new DaggerItem(Tiers.IRON, 1.8f, -2, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_DAGGER = ITEMS.register("gold_dagger", () -> new DaggerItem(Tiers.GOLD, 1.8f, -2, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_DAGGER = ITEMS.register("diamond_dagger", () -> new DaggerItem(Tiers.DIAMOND, 1.8f, -2, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_DAGGER = ITEMS.register("netherite_dagger", () -> new DaggerItem(Tiers.NETHERITE, 1.8f, -2, new Item.Properties()));
    public static final RegistryObject<Item> RUBY_DAGGER = ITEMS.register("ruby_dagger", () -> new DaggerItem(ItemTiers.RUBY, 1.8f, -2, new Item.Properties()));
    public static final RegistryObject<Item> JADEITE_DAGGER = ITEMS.register("jadeite_dagger", () -> new DaggerItem(ItemTiers.JADEITE, 1.8f, -2, new Item.Properties()));
    //halberd
    public static final RegistryObject<Item> WOODEN_HALBERD = ITEMS.register("wooden_halberd", () -> new HalberdItem(Tiers.WOOD, 5f, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> STONE_HALBERD = ITEMS.register("stone_halberd", () -> new HalberdItem(Tiers.STONE, 5f, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> IRON_HALBERD = ITEMS.register("iron_halberd", () -> new HalberdItem(Tiers.IRON, 5f, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_HALBERD = ITEMS.register("gold_halberd", () -> new HalberdItem(Tiers.GOLD, 5f, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_HALBERD = ITEMS.register("diamond_halberd", () -> new HalberdItem(Tiers.DIAMOND, 5f, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_HALBERD = ITEMS.register("netherite_halberd", () -> new HalberdItem(Tiers.NETHERITE, 5f, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> RUBY_HALBERD = ITEMS.register("ruby_halberd", () -> new HalberdItem(ItemTiers.RUBY, 5f, -2.8f, new Item.Properties()));
    public static final RegistryObject<Item> JADEITE_HALBERD = ITEMS.register("jadeite_halberd", () -> new HalberdItem(ItemTiers.JADEITE, 5f, -2.8f, new Item.Properties()));
    //弓
    public static final RegistryObject<Item> STONE_BOW = ITEMS.register("stone_bow", () -> new SakuraBowItem(new Item.Properties().defaultDurability(250), 2));
    public static final RegistryObject<Item> IRON_BOW = ITEMS.register("iron_bow", () -> new SakuraBowItem(new Item.Properties().defaultDurability(386), 2.5));
    public static final RegistryObject<Item> GOLD_BOW = ITEMS.register("gold_bow", () -> new SakuraBowItem(new Item.Properties().defaultDurability(128), 2));
    public static final RegistryObject<Item> DIAMOND_BOW = ITEMS.register("diamond_bow", () -> new SakuraBowItem(new Item.Properties().defaultDurability(1536), 3));
    public static final RegistryObject<Item> NETHERITE_BOW = ITEMS.register("netherite_bow", () -> new SakuraBowItem(new Item.Properties().defaultDurability(2048), 3.5));
    public static final RegistryObject<Item> RUBY_BOW = ITEMS.register("ruby_bow", () -> new SakuraBowItem(new Item.Properties().defaultDurability(128), 2.75));
    public static final RegistryObject<Item> JADEITE_BOW = ITEMS.register("jadeite_bow", () -> new SakuraBowItem(new Item.Properties().defaultDurability(2536), 3));

    //大剑
    public static final RegistryObject<Item> WOODEN_GREATSWORD = ITEMS.register("wooden_greatsword", () -> new GreatswordItem(Tiers.WOOD, 3.25f, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> STONE_GREATSWORD = ITEMS.register("stone_greatsword", () -> new GreatswordItem(Tiers.STONE, 3.25f, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> IRON_GREATSWORD = ITEMS.register("iron_greatsword", () -> new GreatswordItem(Tiers.IRON, 3.25f, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_GREATSWORD = ITEMS.register("gold_greatsword", () -> new GreatswordItem(Tiers.GOLD, 3.25f, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_GREATSWORD = ITEMS.register("diamond_greatsword", () -> new GreatswordItem(Tiers.DIAMOND, 3.25f, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_GREATSWORD = ITEMS.register("netherite_greatsword", () -> new GreatswordItem(Tiers.NETHERITE, 3.25f, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> RUBY_GREATSWORD = ITEMS.register("ruby_greatsword", () -> new GreatswordItem(ItemTiers.RUBY, 3.25f, -2.6f, new Item.Properties()));
    public static final RegistryObject<Item> JADEITE_GREATSWORD = ITEMS.register("jadeite_greatsword", () -> new GreatswordItem(ItemTiers.JADEITE, 3.25f, -2.6f, new Item.Properties()));



    //原材料物品
    public static final RegistryObject<Item> WARDEN_HEART = ITEMS.register("warden_heart", WardenHeart::new);
    public static final RegistryObject<Item> MAGIC_STONE = ITEMS.register("magic_stone", MagicStone::new);
    public static final RegistryObject<Item> MAGIC_TABLE = ITEMS.register("magic_table", () -> new BlockItem(BlockReg.MAGIC_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> THE_VESSEL_OF_THE_HEART = ITEMS.register("the_vessel_of_the_heart", TheVesselOfTheHeart::new);
    public static final RegistryObject<Item> HEART_TOXIC = ITEMS.register("heart_toxic", HeartToxic::new);
    public static final RegistryObject<Item> XP_BOOK = ITEMS.register("xp_book", XpBook::new);
    public static final RegistryObject<Item> ELEVATOR = ITEMS.register("elevator", () -> new BlockItem(BlockReg.ELEVATOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> WITHER_SKELETON_SKULL_DEBRIS = ITEMS.register("wither_skeleton_skull_debris", () -> new Item(new Item.Properties()));


}
