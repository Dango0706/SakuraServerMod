package me.tuanzi.sakura.blocks.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static me.tuanzi.sakura.SakuraMain.MODID;
import static me.tuanzi.sakura.blocks.features.TestBiomeModifier.TEST_BIOME_MODIFIER_NAME;

public class FeatureReg {

    public static final DeferredRegister<PlacementModifierType<?>> PLACEMENT_MODIFIERS = DeferredRegister.create(Registries.PLACEMENT_MODIFIER_TYPE, MODID);
    public static final RegistryObject<PlacementModifierType<?>> FILTER_OVERWORLD = PLACEMENT_MODIFIERS.register("filter_overworld",
            () -> (PlacementModifierType<PlacementModifier>) () -> DimensionBiomeFilter.CODEC_OVERWORLD);
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MODID);
    public static final RegistryObject<Codec<? extends BiomeModifier>> TEST_BIOME_MODIFIER = BIOME_MODIFIERS.register(TEST_BIOME_MODIFIER_NAME, TestBiomeModifier::makeCodec);


}
