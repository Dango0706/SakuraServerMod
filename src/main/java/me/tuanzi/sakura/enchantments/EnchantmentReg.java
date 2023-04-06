package me.tuanzi.sakura.enchantments;

import me.tuanzi.sakura.SakuraMain;
import me.tuanzi.sakura.enchantments.armor.*;
import me.tuanzi.sakura.enchantments.bow.MultipleShoot;
import me.tuanzi.sakura.enchantments.bow.QuickDraw;
import me.tuanzi.sakura.enchantments.bow.SkyShot;
import me.tuanzi.sakura.enchantments.bow.Wither;
import me.tuanzi.sakura.enchantments.sword.*;
import me.tuanzi.sakura.enchantments.tool.DiamondsEverywhere;
import me.tuanzi.sakura.enchantments.tool.VeinMine;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentReg {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, SakuraMain.MODID);
    public static final RegistryObject<Enchantment> PIERCING = ENCHANTMENTS.register("piercing", Piercing::new);
    public static final RegistryObject<Enchantment> IDENTIFICATION = ENCHANTMENTS.register("identification", Identification::new);
    public static final RegistryObject<Enchantment> BERSERKER = ENCHANTMENTS.register("berserker", Berserker::new);
    public static final RegistryObject<Enchantment> BLEEDING = ENCHANTMENTS.register("bleeding", Bleeding::new);
    public static final RegistryObject<Enchantment> BOW_BLEEDING = ENCHANTMENTS.register("bow_bleeding", me.tuanzi.sakura.enchantments.bow.Bleeding::new);
    public static final RegistryObject<Enchantment> MAGIC_PROTECTION = ENCHANTMENTS.register("magic_protection", MagicProtection::new);
    public static final RegistryObject<Enchantment> MAGIC_SOLUBLE = ENCHANTMENTS.register("magic_soluble", MagicSoluble::new);
    public static final RegistryObject<Enchantment> SKILLED = ENCHANTMENTS.register("skilled", Skilled::new);
    public static final RegistryObject<Enchantment> SMITE = ENCHANTMENTS.register("smite", Smite::new);
    public static final RegistryObject<Enchantment> LUNAR_BLESSING = ENCHANTMENTS.register("lunar_blessing", LunarBlessing::new);
    public static final RegistryObject<Enchantment> APOLLO_BLESSING = ENCHANTMENTS.register("apollo_blessing", ApolloBlessing::new);
    public static final RegistryObject<Enchantment> EDUCATION = ENCHANTMENTS.register("education", Education::new);
    public static final RegistryObject<Enchantment> LIFE_STEAL = ENCHANTMENTS.register("life_steal", LifeSteal::new);
    public static final RegistryObject<Enchantment> CHASE = ENCHANTMENTS.register("chase", Chase::new);
    public static final RegistryObject<Enchantment> ADVANCED_SHARPNESS = ENCHANTMENTS.register("advanced_sharpness", () -> new SakuraDamageEnchantment(0, true));
    public static final RegistryObject<Enchantment> ADVANCED_SMITE = ENCHANTMENTS.register("advanced_smite", () -> new SakuraDamageEnchantment(1, true));
    public static final RegistryObject<Enchantment> ADVANCED_BANE_OF_ARTHROPODS = ENCHANTMENTS.register("advanced_bane_of_arthropods", () -> new SakuraDamageEnchantment(2, true));
    public static final RegistryObject<Enchantment> LESSER_SHARPNESS = ENCHANTMENTS.register("lesser_sharpness", () -> new SakuraDamageEnchantment(0, false));
    public static final RegistryObject<Enchantment> LESSER_SMITE = ENCHANTMENTS.register("lesser_smite", () -> new SakuraDamageEnchantment(1, false));
    public static final RegistryObject<Enchantment> LESSER_BANE_OF_ARTHROPODS = ENCHANTMENTS.register("lesser_bane_of_arthropods", () -> new SakuraDamageEnchantment(2, false));
    public static final RegistryObject<Enchantment> EXTENSIONS = ENCHANTMENTS.register("extensions", Extensions::new);
    public static final RegistryObject<Enchantment> ATTACK_EXTENSIONS = ENCHANTMENTS.register("attack_extensions", AttackExtensions::new);
    public static final RegistryObject<Enchantment> SOUL_BOUND = ENCHANTMENTS.register("soul_bound", SoulBound::new);
    public static final RegistryObject<Enchantment> VITALITY = ENCHANTMENTS.register("vitality", Vitality::new);
    public static final RegistryObject<Enchantment> WITHER = ENCHANTMENTS.register("wither", Wither::new);
    public static final RegistryObject<Enchantment> MULTIPLE_SHOOT = ENCHANTMENTS.register("multiple_shoot", MultipleShoot::new);
    public static final RegistryObject<Enchantment> VEIN_MINE = ENCHANTMENTS.register("vein_mine", VeinMine::new);
    public static final RegistryObject<Enchantment> DIAMONDS_EVERYWHERE = ENCHANTMENTS.register("diamonds_everywhere", DiamondsEverywhere::new);
    public static final RegistryObject<Enchantment> QUICK_DRAW = ENCHANTMENTS.register("quick_draw", QuickDraw::new);
    public static final RegistryObject<Enchantment> BATHING_REJUVENATE = ENCHANTMENTS.register("bathing_rejuvenate", me.tuanzi.sakura.enchantments.armor.BathingRejuvenate::new);
    public static final RegistryObject<Enchantment> DODGE = ENCHANTMENTS.register("dodge", Dodge::new);
    public static final RegistryObject<Enchantment> HEROIC = ENCHANTMENTS.register("heroic", Heroic::new);
    public static final RegistryObject<Enchantment> BLOOD_RAGE = ENCHANTMENTS.register("blood_rage", BloodRage::new);
    public static final RegistryObject<Enchantment> SKY_SHOT = ENCHANTMENTS.register("sky_shot", SkyShot::new);

}
