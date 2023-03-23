package me.tuanzi.sakura.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IMaxHealthCapability extends INBTSerializable<CompoundTag> {

    double getMaxHealth();

    void setMaxHealth(double maxHealth);

}
