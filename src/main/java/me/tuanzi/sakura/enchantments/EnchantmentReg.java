package me.tuanzi.sakura.enchantments;

import me.tuanzi.sakura.SakuraMain;
import me.tuanzi.sakura.enchantments.armor.AttackExtensions;
import me.tuanzi.sakura.enchantments.armor.Extensions;
import me.tuanzi.sakura.enchantments.armor.MagicProtection;
import me.tuanzi.sakura.enchantments.armor.Vitality;
import me.tuanzi.sakura.enchantments.bow.MultipleShoot;
import me.tuanzi.sakura.enchantments.bow.Wither;
import me.tuanzi.sakura.enchantments.sword.*;
import me.tuanzi.sakura.enchantments.tool.VeinMine;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentReg {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, SakuraMain.MODID);
    public static final RegistryObject<SakuraEnchantment> PIERCING = ENCHANTMENTS.register("piercing", Piercing::new);
    public static final RegistryObject<SakuraEnchantment> IDENTIFICATION = ENCHANTMENTS.register("identification", Identification::new);
    public static final RegistryObject<SakuraEnchantment> BERSERKER = ENCHANTMENTS.register("berserker", Berserker::new);
    public static final RegistryObject<SakuraEnchantment> BLEEDING = ENCHANTMENTS.register("bleeding", Bleeding::new);
    public static final RegistryObject<SakuraEnchantment> BOW_BLEEDING = ENCHANTMENTS.register("bow_bleeding", me.tuanzi.sakura.enchantments.bow.Bleeding::new);
    public static final RegistryObject<SakuraEnchantment> MAGIC_PROTECTION = ENCHANTMENTS.register("magic_protection", MagicProtection::new);
    public static final RegistryObject<SakuraEnchantment> MAGIC_SOLUBLE = ENCHANTMENTS.register("magic_soluble", MagicSoluble::new);
    public static final RegistryObject<SakuraEnchantment> SKILLED = ENCHANTMENTS.register("skilled", Skilled::new);
    public static final RegistryObject<SakuraEnchantment> SMITE = ENCHANTMENTS.register("smite", Smite::new);
    public static final RegistryObject<SakuraEnchantment> LUNAR_BLESSING = ENCHANTMENTS.register("lunar_blessing", LunarBlessing::new);
    public static final RegistryObject<SakuraEnchantment> APOLLO_BLESSING = ENCHANTMENTS.register("apollo_blessing", ApolloBlessing::new);
    public static final RegistryObject<SakuraEnchantment> EDUCATION = ENCHANTMENTS.register("education", Education::new);
    public static final RegistryObject<SakuraEnchantment> LIFE_STEAL = ENCHANTMENTS.register("life_steal", LifeSteal::new);
    public static final RegistryObject<SakuraEnchantment> CHASE = ENCHANTMENTS.register("chase", Chase::new);
    public static final RegistryObject<SakuraEnchantment> ADVANCED_SHARPNESS = ENCHANTMENTS.register("advanced_sharpness", () -> new SakuraDamageEnchantment(0, true));
    public static final RegistryObject<SakuraEnchantment> ADVANCED_SMITE = ENCHANTMENTS.register("advanced_smite", () -> new SakuraDamageEnchantment(1, true));
    public static final RegistryObject<SakuraEnchantment> ADVANCED_BANE_OF_ARTHROPODS = ENCHANTMENTS.register("advanced_bane_of_arthropods", () -> new SakuraDamageEnchantment(2, true));
    public static final RegistryObject<SakuraEnchantment> LESSER_SHARPNESS = ENCHANTMENTS.register("lesser_sharpness", () -> new SakuraDamageEnchantment(0, false));
    public static final RegistryObject<SakuraEnchantment> LESSER_SMITE = ENCHANTMENTS.register("lesser_smite", () -> new SakuraDamageEnchantment(1, false));
    public static final RegistryObject<SakuraEnchantment> LESSER_BANE_OF_ARTHROPODS = ENCHANTMENTS.register("lesser_bane_of_arthropods", () -> new SakuraDamageEnchantment(2, false));
    public static final RegistryObject<SakuraEnchantment> EXTENSIONS = ENCHANTMENTS.register("extensions", Extensions::new);
    public static final RegistryObject<SakuraEnchantment> ATTACK_EXTENSIONS = ENCHANTMENTS.register("attack_extensions", AttackExtensions::new);
    public static final RegistryObject<SakuraEnchantment> SOUL_BOUND = ENCHANTMENTS.register("soul_bound", SoulBound::new);
    public static final RegistryObject<SakuraEnchantment> VITALITY = ENCHANTMENTS.register("vitality", Vitality::new);
    public static final RegistryObject<SakuraEnchantment> WITHER = ENCHANTMENTS.register("wither", Wither::new);
    public static final RegistryObject<SakuraEnchantment> MULTIPLE_SHOOT = ENCHANTMENTS.register("multiple_shoot", MultipleShoot::new);
    public static final RegistryObject<SakuraEnchantment> VEIN_MINE = ENCHANTMENTS.register("vein_mine", VeinMine::new);

}
