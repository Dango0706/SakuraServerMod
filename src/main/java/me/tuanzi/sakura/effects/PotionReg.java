package me.tuanzi.sakura.effects;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static me.tuanzi.sakura.SakuraMain.MODID;

public class PotionReg {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MODID);

    public static final RegistryObject<Potion> BLEEDING = POTIONS.register("bleeding", () -> new Potion(new MobEffectInstance(EffectReg.BLEEDING.get(), 20 * 60 * 3, 0)));
    public static final RegistryObject<Potion> BLEEDING_TIME = POTIONS.register("bleeding_time", () -> new Potion(new MobEffectInstance(EffectReg.BLEEDING.get(), 20 * 60 * 8, 0)));
    public static final RegistryObject<Potion> BLEEDING_II = POTIONS.register("bleeding_ii", () -> new Potion(new MobEffectInstance(EffectReg.BLEEDING.get(), 20 * 30 * 3, 1)));

    public static final RegistryObject<Potion> SHATTERED_ARMOR = POTIONS.register("shattered_armor", () -> new Potion(new MobEffectInstance(EffectReg.SHATTERED_ARMOR.get(), 20 * 60 * 3, 0)));
    public static final RegistryObject<Potion> SHATTERED_ARMOR_TIME = POTIONS.register("shattered_armor_time", () -> new Potion(new MobEffectInstance(EffectReg.SHATTERED_ARMOR.get(), 20 * 60 * 8, 0)));
    public static final RegistryObject<Potion> SHATTERED_ARMOR_II = POTIONS.register("shattered_armor_ii", () -> new Potion(new MobEffectInstance(EffectReg.SHATTERED_ARMOR.get(), 20 * 30 * 3, 1)));

    public static final RegistryObject<Potion> STEEL_BODY = POTIONS.register("steel_body", () -> new Potion(new MobEffectInstance(EffectReg.STEEL_BODY.get(), 20 * 60 * 3, 0)));
    public static final RegistryObject<Potion> STEEL_BODY_TIME = POTIONS.register("steel_body_time", () -> new Potion(new MobEffectInstance(EffectReg.STEEL_BODY.get(), 20 * 60 * 8, 0)));
    public static final RegistryObject<Potion> STEEL_BODY_II = POTIONS.register("steel_body_ii", () -> new Potion(new MobEffectInstance(EffectReg.STEEL_BODY.get(), 20 * 30 * 3, 1)));

    public static final RegistryObject<Potion> SURRENDER = POTIONS.register("surrender", () -> new Potion(new MobEffectInstance(EffectReg.SURRENDER.get(), 20 * 60 * 3, 0)));
    public static final RegistryObject<Potion> SURRENDER_TIME = POTIONS.register("surrender_time", () -> new Potion(new MobEffectInstance(EffectReg.SURRENDER.get(), 20 * 60 * 8, 0)));
    public static final RegistryObject<Potion> SURRENDER_II = POTIONS.register("surrender_ii", () -> new Potion(new MobEffectInstance(EffectReg.SURRENDER.get(), 20 * 30 * 3, 1)));

    public static final RegistryObject<Potion> FASTEN = POTIONS.register("fasten", () -> new Potion(new MobEffectInstance(EffectReg.FASTEN.get(), 20 * 60 * 3, 0)));
    public static final RegistryObject<Potion> FASTEN_TIME = POTIONS.register("fasten_time", () -> new Potion(new MobEffectInstance(EffectReg.FASTEN.get(), 20 * 60 * 8, 0)));

    public static final RegistryObject<Potion> PURIFICATION = POTIONS.register("purification", () -> new Potion(new MobEffectInstance(EffectReg.PURIFICATION.get(), 20 * 60 * 3, 0)));
    public static final RegistryObject<Potion> PURIFICATION_TIME = POTIONS.register("purification_time", () -> new Potion(new MobEffectInstance(EffectReg.PURIFICATION.get(), 20 * 60 * 8, 0)));

    public static final RegistryObject<Potion> UNDYING = POTIONS.register("undying", () -> new Potion(new MobEffectInstance(EffectReg.UNDYING.get(), 20 * 30 * 3, 0)));
    public static final RegistryObject<Potion> UNDYING_TIME = POTIONS.register("undying_time", () -> new Potion(new MobEffectInstance(EffectReg.UNDYING.get(), 20 * 30 * 8, 0)));


}
