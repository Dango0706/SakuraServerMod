package me.tuanzi.sakura.blocks.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.particles.SculkChargeParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

import static me.tuanzi.sakura.SakuraMain.MODID;

public record TestBiomeModifier(HolderSet<Biome> biomes) implements BiomeModifier {

    public static final String TEST_BIOME_MODIFIER_NAME = "test_biome_modifier";
    public static final ResourceLocation TEST_BIOME_MODIFIER = new ResourceLocation(MODID, TEST_BIOME_MODIFIER_NAME);

    public static Codec<TestBiomeModifier> makeCodec() {
        return RecordCodecBuilder.create(builder -> builder.group(
                Biome.LIST_CODEC.fieldOf("biomes").forGetter(TestBiomeModifier::biomes)
        ).apply(builder, TestBiomeModifier::new));
    }

    private static DataResult<GenerationStep.Decoration> generationStageFromString(String name) {
        try {
            return DataResult.success(GenerationStep.Decoration.valueOf(name));
        } catch (Exception e) {
            return DataResult.error(() -> "Not a decoration stage: " + name);
        }
    }

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && this.biomes.contains(biome)) {
            builder.getSpecialEffects().ambientParticle(new AmbientParticleSettings(new SculkChargeParticleOptions(1.0f), 1.0f));
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return FeatureReg.TEST_BIOME_MODIFIER.get();
    }
}


