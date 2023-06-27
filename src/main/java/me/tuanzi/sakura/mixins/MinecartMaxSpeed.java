package me.tuanzi.sakura.mixins;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AbstractMinecart.class)
public class MinecartMaxSpeed {

    @ModifyConstant(method = "getMaxSpeed", constant = @Constant(doubleValue = 8.0D))
    private double mixinLimitInt(double i) {
        return 100.0D;
    }

}
