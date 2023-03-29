package me.tuanzi.sakura.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static me.tuanzi.sakura.SakuraMain.MODID;
import static me.tuanzi.sakura.utils.Utils.ADD_ARMOR_UUID;
import static me.tuanzi.sakura.utils.Utils.DELETE_ARMOR_UUID;

public class EffectReg {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);
    public static final RegistryObject<MobEffect> BLEEDING = EFFECTS.register("bleeding", Bleeding::new);
    public static final RegistryObject<MobEffect> SHATTERED_ARMOR = EFFECTS.register("shattered_armor", () -> new ShatteredArmor().addAttributeModifier(Attributes.ARMOR, DELETE_ARMOR_UUID.toString(), -2, AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<MobEffect> STEEL_BODY = EFFECTS.register("steel_body", () -> new SteelBody().addAttributeModifier(Attributes.ARMOR, ADD_ARMOR_UUID.toString(), 2, AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<MobEffect> SURRENDER = EFFECTS.register("surrender", Surrender::new);
    public static final RegistryObject<MobEffect> FASTEN = EFFECTS.register("fasten", Fasten::new);
    public static final RegistryObject<MobEffect> PURIFICATION = EFFECTS.register("purification", Purification::new);
    public static final RegistryObject<MobEffect> UNDYING = EFFECTS.register("undying", ()-> new Undying(MobEffectCategory.BENEFICIAL, 0xffffff));

}
