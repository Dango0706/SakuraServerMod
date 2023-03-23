package me.tuanzi.sakura.items;

import me.tuanzi.sakura.blocks.BlockReg;
import me.tuanzi.sakura.items.demonization.MagicStone;
import me.tuanzi.sakura.items.demonization.WardenHeart;
import me.tuanzi.sakura.items.jadeite.*;
import me.tuanzi.sakura.items.ruby.*;
import me.tuanzi.sakura.items.tiered_item.DaggerItem;
import net.minecraft.world.entity.EquipmentSlot;
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

    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", Ruby::new);
    public static final RegistryObject<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", RubyPickaxe::new);
    public static final RegistryObject<Item> RUBY_AXE = ITEMS.register("ruby_axe", RubyAxe::new);
    public static final RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword", RubySword::new);
    public static final RegistryObject<Item> RUBY_HOE = ITEMS.register("ruby_hoe", RubyHoe::new);
    public static final RegistryObject<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel", RubyShovel::new);

    public static final RegistryObject<Item> RUBY_HELMET = ITEMS.register("ruby_helmet", () -> new ArmorItem(MyArmorMaterial.RUBY, EquipmentSlot.HEAD, (new Item.Properties())));
    public static final RegistryObject<Item> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () -> new ArmorItem(MyArmorMaterial.RUBY, EquipmentSlot.CHEST, (new Item.Properties())));
    public static final RegistryObject<Item> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () -> new ArmorItem(MyArmorMaterial.RUBY, EquipmentSlot.LEGS, (new Item.Properties())));
    public static final RegistryObject<Item> RUBY_BOOTS = ITEMS.register("ruby_boots", () -> new ArmorItem(MyArmorMaterial.RUBY, EquipmentSlot.FEET, (new Item.Properties())));
//    public static final TagKey<Item> RUBY_ORE_TAG = TagKey.create(Registries.ITEM, new ResourceLocation(MODID, "ruby_ore"));

    public static final RegistryObject<Item> RUBY_ORE = ITEMS.register("ruby_ore", () -> new BlockItem(BlockReg.RUBY_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEEP_RUBY_ORE = ITEMS.register("deepslate_ruby_ore", () -> new BlockItem(BlockReg.DEEP_RUBY_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> RUBY_BLOCK = ITEMS.register("ruby_block", () -> new BlockItem(BlockReg.RUBY_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_STONE = ITEMS.register("magic_stone", MagicStone::new);
    public static final RegistryObject<Item> MAGIC_TABLE = ITEMS.register("magic_table", () -> new BlockItem(BlockReg.MAGIC_TABLE.get(), new Item.Properties()));
    public static final RegistryObject<Item> WARDEN_HEART = ITEMS.register("warden_heart", WardenHeart::new);
    public static final RegistryObject<Item> THE_VESSEL_OF_THE_HEART = ITEMS.register("the_vessel_of_the_heart", TheVesselOfTheHeart::new);
    public static final RegistryObject<Item> HEART_TOXIC = ITEMS.register("heart_toxic", HeartToxic::new);
    public static final RegistryObject<Item> JADEITE = ITEMS.register("jadeite_gem", Jadeite::new);
    public static final RegistryObject<Item> JADEITE_PICKAXE = ITEMS.register("jadeite_pickaxe", JadeitePickaxe::new);
    public static final RegistryObject<Item> JADEITE_AXE = ITEMS.register("jadeite_axe", JadeiteAxe::new);
    public static final RegistryObject<Item> JADEITE_SWORD = ITEMS.register("jadeite_sword", JadeiteSword::new);
    public static final RegistryObject<Item> JADEITE_HOE = ITEMS.register("jadeite_hoe", JadeiteHoe::new);
    public static final RegistryObject<Item> JADEITE_SHOVEL = ITEMS.register("jadeite_shovel", JadeiteShovel::new);
    public static final RegistryObject<Item> DEEP_JADEITE_ORE = ITEMS.register("deepslate_jadeite_ore", () -> new BlockItem(BlockReg.DEEP_JADEITE_ORE.get(), new Item.Properties()));
    public static final RegistryObject<Item> JADEITE_BLOCK = ITEMS.register("jadeite_block", () -> new BlockItem(BlockReg.JADEITE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> JADEITE_HELMET = ITEMS.register("jadeite_helmet", () -> new ArmorItem(MyArmorMaterial.JADEITE, EquipmentSlot.HEAD, (new Item.Properties())));
    public static final RegistryObject<Item> JADEITE_CHESTPLATE = ITEMS.register("jadeite_chestplate", () -> new ArmorItem(MyArmorMaterial.JADEITE, EquipmentSlot.CHEST, (new Item.Properties())));
    public static final RegistryObject<Item> JADEITE_LEGGINGS = ITEMS.register("jadeite_leggings", () -> new ArmorItem(MyArmorMaterial.JADEITE, EquipmentSlot.LEGS, (new Item.Properties())));
    public static final RegistryObject<Item> JADEITE_BOOTS = ITEMS.register("jadeite_boots", () -> new ArmorItem(MyArmorMaterial.JADEITE, EquipmentSlot.FEET, (new Item.Properties())));

    //dagger
    public static final RegistryObject<Item> WOODEN_DAGGER = ITEMS.register("wooden_dagger", () -> new DaggerItem(Tiers.WOOD, 1.8f, -2, new Item.Properties()));
    public static final RegistryObject<Item> STONE_DAGGER = ITEMS.register("stone_dagger", () -> new DaggerItem(Tiers.STONE, 1.8f, -2, new Item.Properties()));
    public static final RegistryObject<Item> IRON_DAGGER = ITEMS.register("iron_dagger", () -> new DaggerItem(Tiers.IRON, 1.8f, -2, new Item.Properties()));
    public static final RegistryObject<Item> GOLD_DAGGER = ITEMS.register("gold_dagger", () -> new DaggerItem(Tiers.GOLD, 1.8f, -2, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_DAGGER = ITEMS.register("diamond_dagger", () -> new DaggerItem(Tiers.DIAMOND, 1.8f, -2, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_DAGGER = ITEMS.register("netherite_dagger", () -> new DaggerItem(Tiers.NETHERITE, 1.8f, -2, new Item.Properties()));
    public static final RegistryObject<Item> RUBY_DAGGER = ITEMS.register("ruby_dagger", () -> new DaggerItem(ItemTiers.RUBY, 1.8f, -2, new Item.Properties()));
    public static final RegistryObject<Item> JADEITE_DAGGER = ITEMS.register("jadeite_dagger", () -> new DaggerItem(ItemTiers.JADEITE, 1.8f, -2, new Item.Properties()));


}
