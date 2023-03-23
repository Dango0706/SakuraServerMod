package me.tuanzi.sakura.capability;

import net.minecraft.nbt.CompoundTag;

public class MaxHealthCapability implements IMaxHealthCapability {
    private double maxHealth;


    public MaxHealthCapability(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public double getMaxHealth() {
        return maxHealth;
    }

    @Override
    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundNBT = new CompoundTag();
        compoundNBT.putDouble("maxHealth", this.maxHealth);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.maxHealth = nbt.getDouble("maxHealth");
    }
}
