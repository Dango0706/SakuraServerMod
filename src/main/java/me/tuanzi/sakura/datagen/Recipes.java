package me.tuanzi.sakura.datagen;

import me.tuanzi.sakura.items.ItemReg;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;


public class Recipes extends RecipeProvider {

    public Recipes(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> p_251297_) {
        //ruby
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemReg.RUBY_PICKAXE.get())
                .pattern("aaa")
                .pattern(" b ")
                .pattern(" b ")
                .define('a', ItemReg.RUBY.get())
                .define('b', Items.STICK)
                .unlockedBy("ruby", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.RUBY.get(), Items.STICK))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemReg.RUBY_AXE.get())
                .pattern("aa ")
                .pattern("ab ")
                .pattern(" b ")
                .define('a', ItemReg.RUBY.get())
                .define('b', Items.STICK)
                .unlockedBy("ruby", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.RUBY.get(), Items.STICK))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemReg.RUBY_SHOVEL.get())
                .pattern(" a ")
                .pattern(" b ")
                .pattern(" b ")
                .define('a', ItemReg.RUBY.get())
                .define('b', Items.STICK)
                .unlockedBy("ruby", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.RUBY.get(), Items.STICK))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemReg.RUBY_HOE.get())
                .pattern("aa ")
                .pattern(" b ")
                .pattern(" b ")
                .define('a', ItemReg.RUBY.get())
                .define('b', Items.STICK)
                .unlockedBy("ruby", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.RUBY.get(), Items.STICK))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemReg.RUBY_SWORD.get())
                .pattern(" a ")
                .pattern(" a ")
                .pattern(" b ")
                .define('a', ItemReg.RUBY.get())
                .define('b', Items.STICK)
                .unlockedBy("ruby", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.RUBY.get(), Items.STICK))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.RUBY_CHESTPLATE.get())
                .pattern("a a")
                .pattern("aaa")
                .pattern("aaa")
                .define('a', ItemReg.RUBY.get())
                .unlockedBy("ruby", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.RUBY.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.RUBY_LEGGINGS.get())
                .pattern("aaa")
                .pattern("a a")
                .pattern("a a")
                .define('a', ItemReg.RUBY.get())
                .unlockedBy("ruby", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.RUBY.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.RUBY_HELMET.get())
                .pattern("aaa")
                .pattern("a a")
                .pattern("   ")
                .define('a', ItemReg.RUBY.get())
                .unlockedBy("ruby", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.RUBY.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.RUBY_BOOTS.get())
                .pattern("a a")
                .pattern("a a")
                .pattern("   ")
                .define('a', ItemReg.RUBY.get())
                .unlockedBy("ruby", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.RUBY.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.RUBY_BLOCK.get())
                .pattern("aaa")
                .pattern("aaa")
                .pattern("aaa")
                .define('a', ItemReg.RUBY.get())
                .unlockedBy("ruby", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.RUBY.get()))
                .save(p_251297_);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemReg.RUBY.get(), 9)
                .requires(ItemReg.RUBY_BLOCK.get())
                .unlockedBy("ruby_block", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.RUBY_BLOCK.get()))
                .save(p_251297_);
        //矿石
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemReg.RUBY_ORE.get()), RecipeCategory.MISC, ItemReg.RUBY.get(), 2.0f, 200)
                .unlockedBy("ruby_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.RUBY_ORE.get()))
                .save(p_251297_, "ruby_smelting");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemReg.RUBY_ORE.get()), RecipeCategory.MISC, ItemReg.RUBY.get(), 2.0f, 160)
                .unlockedBy("ruby_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.RUBY_ORE.get()))
                .save(p_251297_, "ruby_blast");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemReg.DEEP_RUBY_ORE.get()), RecipeCategory.MISC, ItemReg.RUBY.get(), 2.0f, 200)
                .unlockedBy("deepslate_ruby_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.DEEP_RUBY_ORE.get()))
                .save(p_251297_, "deepslate_ruby_smelting");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemReg.DEEP_RUBY_ORE.get()), RecipeCategory.MISC, ItemReg.RUBY.get(), 2.0f, 160)
                .unlockedBy("deepslate_ruby_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.DEEP_RUBY_ORE.get()))
                .save(p_251297_, "deepslate_ruby_blast");
        //翡翠
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemReg.JADEITE_PICKAXE.get())
                .pattern("aaa")
                .pattern(" b ")
                .pattern(" b ")
                .define('a', ItemReg.JADEITE.get())
                .define('b', Items.STICK)
                .unlockedBy("jadeite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.JADEITE.get(), Items.STICK))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemReg.JADEITE_AXE.get())
                .pattern("aa ")
                .pattern("ab ")
                .pattern(" b ")
                .define('a', ItemReg.JADEITE.get())
                .define('b', Items.STICK)
                .unlockedBy("jadeite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.JADEITE.get(), Items.STICK))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemReg.JADEITE_SHOVEL.get())
                .pattern(" a ")
                .pattern(" b ")
                .pattern(" b ")
                .define('a', ItemReg.JADEITE.get())
                .define('b', Items.STICK)
                .unlockedBy("jadeite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.JADEITE.get(), Items.STICK))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemReg.JADEITE_HOE.get())
                .pattern("aa ")
                .pattern(" b ")
                .pattern(" b ")
                .define('a', ItemReg.JADEITE.get())
                .define('b', Items.STICK)
                .unlockedBy("jadeite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.JADEITE.get(), Items.STICK))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ItemReg.JADEITE_SWORD.get())
                .pattern(" a ")
                .pattern(" a ")
                .pattern(" b ")
                .define('a', ItemReg.JADEITE.get())
                .define('b', Items.STICK)
                .unlockedBy("jadeite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.JADEITE.get(), Items.STICK))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.JADEITE_CHESTPLATE.get())
                .pattern("a a")
                .pattern("aaa")
                .pattern("aaa")
                .define('a', ItemReg.JADEITE.get())
                .unlockedBy("jadeite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.JADEITE.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.JADEITE_LEGGINGS.get())
                .pattern("aaa")
                .pattern("a a")
                .pattern("a a")
                .define('a', ItemReg.JADEITE.get())
                .unlockedBy("jadeite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.JADEITE.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.JADEITE_HELMET.get())
                .pattern("aaa")
                .pattern("a a")
                .pattern("   ")
                .define('a', ItemReg.JADEITE.get())
                .unlockedBy("jadeite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.JADEITE.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.JADEITE_BOOTS.get())
                .pattern("a a")
                .pattern("a a")
                .pattern("   ")
                .define('a', ItemReg.JADEITE.get())
                .unlockedBy("jadeite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.JADEITE.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.JADEITE_BLOCK.get())
                .pattern("aaa")
                .pattern("aaa")
                .pattern("aaa")
                .define('a', ItemReg.JADEITE.get())
                .unlockedBy("jadeite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.JADEITE.get()))
                .save(p_251297_);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemReg.JADEITE.get(), 9)
                .requires(ItemReg.JADEITE_BLOCK.get())
                .unlockedBy("ruby_block", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.JADEITE_BLOCK.get()))
                .save(p_251297_);
        //矿石
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemReg.DEEP_JADEITE_ORE.get()), RecipeCategory.MISC, ItemReg.JADEITE.get(), 2.0f, 200)
                .unlockedBy("deepslate_jadeite_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.DEEP_JADEITE_ORE.get()))
                .save(p_251297_, "deepslate_jadeite_smelting");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(ItemReg.DEEP_JADEITE_ORE.get()), RecipeCategory.MISC, ItemReg.JADEITE.get(), 2.0f, 160)
                .unlockedBy("deepslate_jadeite_ore", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.DEEP_JADEITE_ORE.get()))
                .save(p_251297_, "deepslate_jadeite_blast");
        //注魔石
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemReg.MAGIC_STONE.get())
                .pattern("ada")
                .pattern("bcb")
                .pattern("ada")
                .define('a', Items.DIAMOND_BLOCK)
                .define('b', Items.NETHERITE_INGOT)
                .define('c', Items.NETHER_STAR)
                .define('d', ItemReg.JADEITE.get())
                .unlockedBy("magic_stone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_STAR))
                .save(p_251297_);
        //心之容器
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemReg.THE_VESSEL_OF_THE_HEART.get())
                .pattern("aba")
                .pattern("bcb")
                .pattern("aba")
                .define('a', Items.ENCHANTED_GOLDEN_APPLE)
                .define('b', ItemReg.WARDEN_HEART.get())
                .define('c', Items.NETHER_STAR)
                .unlockedBy("the_vessel_of_the_heart", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHER_STAR))
                .save(p_251297_);
        //有毒的心之容器
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemReg.HEART_TOXIC.get())
                .pattern("aba")
                .pattern("bcb")
                .pattern("aba")
                .define('a', Items.POISONOUS_POTATO)
                .define('b', Items.PUFFERFISH)
                .define('c', Items.CAKE)
                .unlockedBy("heart_toxic", InventoryChangeTrigger.TriggerInstance.hasItems(Items.POISONOUS_POTATO))
                .save(p_251297_);
        //附魔桌
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemReg.MAGIC_TABLE.get())
                .pattern("aca")
                .pattern("bab")
                .pattern("aca")
                .define('a', ItemReg.MAGIC_STONE.get())
                .define('b', ItemReg.RUBY.get())
                .define('c', ItemReg.WARDEN_HEART.get())
                .unlockedBy("magic_table", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.WARDEN_HEART.get()))
                .save(p_251297_);
        //长杆
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.POLE.get())
                .pattern("ab ")
                .pattern("a  ")
                .pattern("ab ")
                .define('a', Items.STICK)
                .define('b', Items.STRING)
                .unlockedBy("pole", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(p_251297_);
        //匕首
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.WOODEN_DAGGER.get())
                .pattern("   ")
                .pattern("  a")
                .pattern(" b ")
                .define('a', Items.OAK_PLANKS)
                .define('b', Items.STICK)
                .unlockedBy("wooden_dagger", InventoryChangeTrigger.TriggerInstance.hasItems(Items.OAK_PLANKS))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.STONE_DAGGER.get())
                .pattern("   ")
                .pattern("  a")
                .pattern(" b ")
                .define('a', Items.STONE)
                .define('b', Items.STICK)
                .unlockedBy("stone_dagger", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STONE))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.IRON_DAGGER.get())
                .pattern("   ")
                .pattern("  a")
                .pattern(" b ")
                .define('a', Items.IRON_INGOT)
                .define('b', Items.STICK)
                .unlockedBy("iron_dagger", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.GOLD_DAGGER.get())
                .pattern("   ")
                .pattern("  a")
                .pattern(" b ")
                .define('a', Items.GOLD_INGOT)
                .define('b', Items.STICK)
                .unlockedBy("gold_dagger", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GOLD_INGOT))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.DIAMOND_DAGGER.get())
                .pattern("   ")
                .pattern("  a")
                .pattern(" b ")
                .define('a', Items.DIAMOND)
                .define('b', Items.STICK)
                .unlockedBy("diamond_dagger", InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.NETHERITE_DAGGER.get())
                .pattern("   ")
                .pattern("  a")
                .pattern(" b ")
                .define('a', Items.NETHERITE_INGOT)
                .define('b', Items.STICK)
                .unlockedBy("netherite_dagger", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.RUBY_DAGGER.get())
                .pattern("   ")
                .pattern("  a")
                .pattern(" b ")
                .define('a', ItemReg.RUBY_DAGGER.get())
                .define('b', Items.STICK)
                .unlockedBy("netherite_dagger", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.RUBY_DAGGER.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.JADEITE_DAGGER.get())
                .pattern("   ")
                .pattern("  a")
                .pattern(" b ")
                .define('a', ItemReg.JADEITE.get())
                .define('b', Items.STICK)
                .unlockedBy("netherite_dagger", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.JADEITE.get()))
                .save(p_251297_);
        //战戟
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.WOODEN_HALBERD.get())
                .pattern("aa ")
                .pattern("aa ")
                .pattern("ab ")
                .define('a', Items.OAK_PLANKS)
                .define('b', ItemReg.POLE.get())
                .unlockedBy("wooden_halberd", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.POLE.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.STONE_HALBERD.get())
                .pattern("aa ")
                .pattern("aa ")
                .pattern("ab ")
                .define('a', Items.COBBLESTONE)
                .define('b', ItemReg.POLE.get())
                .unlockedBy("stone_halberd", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.POLE.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.IRON_HALBERD.get())
                .pattern("aa ")
                .pattern("aa ")
                .pattern("ab ")
                .define('a', Items.IRON_INGOT)
                .define('b', ItemReg.POLE.get())
                .unlockedBy("iron_halberd", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.POLE.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.GOLD_HALBERD.get())
                .pattern("aa ")
                .pattern("aa ")
                .pattern("ab ")
                .define('a', Items.GOLD_INGOT)
                .define('b', ItemReg.POLE.get())
                .unlockedBy("gold_halberd", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.POLE.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.DIAMOND_HALBERD.get())
                .pattern("aa ")
                .pattern("aa ")
                .pattern("ab ")
                .define('a', Items.DIAMOND)
                .define('b', ItemReg.POLE.get())
                .unlockedBy("diamond_halberd", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.POLE.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.NETHERITE_HALBERD.get())
                .pattern("aa ")
                .pattern("aa ")
                .pattern("ab ")
                .define('a', Items.NETHERITE_INGOT)
                .define('b', ItemReg.POLE.get())
                .unlockedBy("netherite_halberd", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.POLE.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.RUBY_HALBERD.get())
                .pattern("aa ")
                .pattern("aa ")
                .pattern("ab ")
                .define('a', ItemReg.RUBY.get())
                .define('b', ItemReg.POLE.get())
                .unlockedBy("ruby_halberd", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.POLE.get()))
                .save(p_251297_);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ItemReg.JADEITE_HALBERD.get())
                .pattern("aa ")
                .pattern("aa ")
                .pattern("ab ")
                .define('a', ItemReg.JADEITE.get())
                .define('b', ItemReg.POLE.get())
                .unlockedBy("jadeite_halberd", InventoryChangeTrigger.TriggerInstance.hasItems(ItemReg.POLE.get()))
                .save(p_251297_);

        //经验书
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemReg.XP_BOOK.get())
                .pattern("aca")
                .pattern("dbd")
                .pattern("aca")
                .define('a', Items.EMERALD)
                .define('b', Items.BOOK)
                .define('c', Items.ENDER_PEARL)
                .define('d', Items.LAPIS_LAZULI)
                .unlockedBy("xp_book", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BOOK))
                .save(p_251297_);
        //电梯
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemReg.ELEVATOR.get())
                .pattern("aaa")
                .pattern("aba")
                .pattern("aaa")
                .define('a', Items.IRON_BLOCK)
                .define('b', Items.ENDER_PEARL)
                .unlockedBy("elevator", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ENDER_PEARL))
                .save(p_251297_);
    }


}
