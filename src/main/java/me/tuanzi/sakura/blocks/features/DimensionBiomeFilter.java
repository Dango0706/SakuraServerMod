package me.tuanzi.sakura.blocks.features;


import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.Set;

/**
 * A biome filter that also checks if the dimension is right
 */
public class DimensionBiomeFilter extends PlacementFilter {

    private static final DimensionBiomeFilter INSTANCE_OVERWORLD = new DimensionBiomeFilter(Set.of(Level.OVERWORLD));
    public static Codec<PlacementModifier> CODEC_OVERWORLD = Codec.unit(() -> INSTANCE_OVERWORLD);
    private final Set<ResourceKey<Level>> levelTest;


    public DimensionBiomeFilter(Set<ResourceKey<Level>> levelTest) {
        this.levelTest = levelTest;
    }

    @Override
    protected boolean shouldPlace(PlacementContext context, RandomSource random, BlockPos pos) {
        if (levelTest.contains(context.getLevel().getLevel().dimension())) {
            PlacedFeature placedfeature = context.topFeature().orElseThrow(() -> new IllegalStateException("Tried to biome check an unregistered feature"));
            Holder<Biome> biome = context.getLevel().getBiome(pos);
            return biome.value().getGenerationSettings().hasFeature(placedfeature);
        } else {
            return false;
        }
    }

    @Override
    public PlacementModifierType<?> type() {
        return PlacementModifierType.BIOME_FILTER;
    }
}
