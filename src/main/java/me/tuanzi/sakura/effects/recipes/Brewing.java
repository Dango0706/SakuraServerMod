package me.tuanzi.sakura.effects.recipes;

import me.tuanzi.sakura.effects.PotionReg;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.crafting.StrictNBTIngredient;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;

public class Brewing {
    public Brewing() {
        //钢铁身躯药水
        ItemStack potion = new ItemStack(Items.POTION);
        ItemStack input = new ItemStack(Items.DIAMOND);
        PotionUtils.setPotion(potion, Potions.AWKWARD);
        addRecipe(potion, input, "STEEL_BODY");
        input = new ItemStack(Items.WITHER_SKELETON_SKULL);
        addRecipe(potion, input, "PURIFICATION");
        input = new ItemStack(Items.TOTEM_OF_UNDYING);
        addRecipe(potion, input, "UNDYING");


    }

    private void addRecipe(ItemStack potion, ItemStack input, String potionName) {

        ItemStack result = new ItemStack(Items.POTION);
        ItemStack r_II = new ItemStack(Items.POTION);
        ItemStack r_TIME = new ItemStack(Items.POTION);
        ItemStack splashResult = new ItemStack(Items.SPLASH_POTION);
        ItemStack splashResult_II = new ItemStack(Items.SPLASH_POTION);
        ItemStack splashResult_TIME = new ItemStack(Items.SPLASH_POTION);
        ItemStack lingeringResult = new ItemStack(Items.LINGERING_POTION);
        ItemStack lingeringResult_TIME = new ItemStack(Items.LINGERING_POTION);
        ItemStack lingeringResult_II = new ItemStack(Items.LINGERING_POTION);

        boolean not_II = false;
        boolean not_Time = false;

        Field field;
        RegistryObject<Potion> registryObject = null;
        try {
            field = PotionReg.class.getDeclaredField(potionName);
            field.setAccessible(true);
            registryObject = (RegistryObject<Potion>) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        PotionUtils.setPotion(result, registryObject.get());
        PotionUtils.setPotion(splashResult, registryObject.get());
        PotionUtils.setPotion(lingeringResult, registryObject.get());
        BrewingRecipeRegistry.addRecipe(StrictNBTIngredient.of(potion), Ingredient.of(input), result);

        try {
            field = PotionReg.class.getDeclaredField(potionName + "_II");
            field.setAccessible(true);
            registryObject = (RegistryObject<Potion>) field.get(null);
        } catch (Exception e) {
            not_II = true;
        }
        if (!not_II) {
            PotionUtils.setPotion(r_II, registryObject.get());
            PotionUtils.setPotion(splashResult_II, registryObject.get());
            PotionUtils.setPotion(lingeringResult_II, registryObject.get());
            BrewingRecipeRegistry.addRecipe(StrictNBTIngredient.of(result), Ingredient.of(new ItemStack(Items.GLOWSTONE_DUST)), r_II);
            BrewingRecipeRegistry.addRecipe(StrictNBTIngredient.of(splashResult), Ingredient.of(new ItemStack(Items.GLOWSTONE_DUST)), splashResult_II);
            BrewingRecipeRegistry.addRecipe(StrictNBTIngredient.of(lingeringResult), Ingredient.of(new ItemStack(Items.GLOWSTONE_DUST)), lingeringResult_TIME);
        }

        try {
            field = PotionReg.class.getDeclaredField(potionName + "_TIME");
            field.setAccessible(true);
            registryObject = (RegistryObject<Potion>) field.get(null);
        } catch (Exception e) {
            not_Time = true;
        }
        if (!not_Time) {
            PotionUtils.setPotion(r_TIME, registryObject.get());
            PotionUtils.setPotion(splashResult_TIME, registryObject.get());
            PotionUtils.setPotion(lingeringResult_TIME, registryObject.get());
            BrewingRecipeRegistry.addRecipe(StrictNBTIngredient.of(result), Ingredient.of(new ItemStack(Items.REDSTONE)), r_TIME);
            BrewingRecipeRegistry.addRecipe(StrictNBTIngredient.of(splashResult), Ingredient.of(new ItemStack(Items.REDSTONE)), splashResult_TIME);
            BrewingRecipeRegistry.addRecipe(StrictNBTIngredient.of(lingeringResult), Ingredient.of(new ItemStack(Items.REDSTONE)), lingeringResult_TIME);
        }
        //瓶装->投掷
        BrewingRecipeRegistry.addRecipe(StrictNBTIngredient.of(result), Ingredient.of(new ItemStack(Items.GUNPOWDER)), splashResult);
        BrewingRecipeRegistry.addRecipe(StrictNBTIngredient.of(r_II), Ingredient.of(new ItemStack(Items.GUNPOWDER)), splashResult_II);
        BrewingRecipeRegistry.addRecipe(StrictNBTIngredient.of(r_TIME), Ingredient.of(new ItemStack(Items.GUNPOWDER)), splashResult_TIME);
        //投掷->滞留
        BrewingRecipeRegistry.addRecipe(StrictNBTIngredient.of(splashResult), Ingredient.of(new ItemStack(Items.DRAGON_BREATH)), lingeringResult);
        BrewingRecipeRegistry.addRecipe(StrictNBTIngredient.of(splashResult_II), Ingredient.of(new ItemStack(Items.DRAGON_BREATH)), lingeringResult_II);
        BrewingRecipeRegistry.addRecipe(StrictNBTIngredient.of(splashResult_TIME), Ingredient.of(new ItemStack(Items.DRAGON_BREATH)), lingeringResult_TIME);


    }


}
