package me.tuanzi.sakura.items;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.function.Supplier;

/**
 * The enum My armor material.
 */
public enum MyArmorMaterial implements ArmorMaterial {
    /**
     * 红宝石
     */
    //护甲int:[头,腿,胸,脚]
    RUBY("ruby", 16,
            Util.make(new EnumMap<>(ArmorItem.Type.class),(ruby_type)->{
                ruby_type.put(ArmorItem.Type.BOOTS, 2);
                ruby_type.put(ArmorItem.Type.LEGGINGS, 5);
                ruby_type.put(ArmorItem.Type.CHESTPLATE, 7);
                ruby_type.put(ArmorItem.Type.HELMET, 2);
            }),
            12, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> Ingredient.of(ItemReg.RUBY.get())),
    JADEITE("jadeite", 23,Util.make(new EnumMap<>(ArmorItem.Type.class),(ruby_type)->{
        ruby_type.put(ArmorItem.Type.BOOTS, 4);
        ruby_type.put(ArmorItem.Type.LEGGINGS, 7);
        ruby_type.put(ArmorItem.Type.CHESTPLATE, 8);
        ruby_type.put(ArmorItem.Type.HELMET, 4);
    }), 25, SoundEvents.ARMOR_EQUIP_IRON, 3.5F, 0.0F, () -> Ingredient.of(ItemReg.JADEITE.get()));

    private final String name;
    private final int durabilityMultiplier;
    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;


    MyArmorMaterial(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> slotProtections, int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionFunctionForType = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type p_266807_) {
        return HEALTH_FUNCTION_FOR_TYPE.get(p_266807_) * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type p_267168_) {
        return this.protectionFunctionForType.get(p_267168_);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }


}
