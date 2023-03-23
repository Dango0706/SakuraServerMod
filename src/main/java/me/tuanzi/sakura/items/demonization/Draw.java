package me.tuanzi.sakura.items.demonization;

import me.tuanzi.sakura.enchantments.EnchantmentReg;
import me.tuanzi.sakura.enchantments.SakuraEnchantment;
import me.tuanzi.sakura.enchantments.SakuraRarity;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class Draw {

    public static int draw(int five, int three) {
        Random random = new Random();
        //五星保底
        if (five >= 90) {
            return 5;
        }
        //三星保底
        if (three >= 10) {
            double rand = random.nextDouble();
            //13%升级为4星
            if (rand <= 0.13) {
                return 4;
                //0.6%的概率升级为5星
            } else if (rand <= 0.136) {
                return 5;
                //未升级,3星
            } else {
                return 3;
            }
        }
        //否则
        double rand = random.nextDouble();
        if (rand <= 0.5) {
            return 1;
        } else if (rand <= 0.75) {
            return 2;
        } else if (rand <= 0.9) {
            //3星
            rand = random.nextDouble();
            //13%升级为4星
            if (rand <= 0.13) {
                return 4;
                //0.6%的概率升级为5星
            } else if (rand <= 0.136) {
                return 5;
                //未升级,3星
            } else {
                return 3;
            }
        } else if (rand <= 0.995) {
            rand = random.nextDouble();
            if (rand <= 0.006) {
                return 5;
            } else {
                return 4;
            }
        } else {
            return 5;
        }
    }

    public static ItemStack drawItem(int rarity) {

        if (rarity <= 0 || rarity >= 6) {
            rarity = 1;
        }

        //附魔孩子们
        ArrayList<ItemStack> commonItems = new ArrayList<>();
        ArrayList<ItemStack> uncommonItems = new ArrayList<>();
        ArrayList<ItemStack> rareItems = new ArrayList<>();
        ArrayList<ItemStack> epicItems = new ArrayList<>();
        ArrayList<ItemStack> legItems = new ArrayList<>();

        Random random = new Random();

        //添加附魔

        Class<?> enchantmentRegClass = EnchantmentReg.class;
        Field[] fields = enchantmentRegClass.getDeclaredFields();

        for (Field field : fields) {
            if (/*Modifier.isFinal(field.getModifiers()) && Modifier.isStatic(field.getModifiers()) &&*/ field.getType() == RegistryObject.class) {
                try {
                    RegistryObject<SakuraEnchantment> registryObject = (RegistryObject<SakuraEnchantment>) field.get(null);
                    if (registryObject.get().getSakuraRarity() == SakuraRarity.COMMON) {
                        if (registryObject.get().getMaxLevel() == 1) {
                            commonItems.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(registryObject.get(), 1)));
                        } else {
                            commonItems.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(registryObject.get(), random.nextInt(1, registryObject.get().getMaxLevel()))));
                        }
                    }
                    if (registryObject.get().getSakuraRarity() == SakuraRarity.UNCOMMON) {
                        if (registryObject.get().getMaxLevel() == 1) {
                            uncommonItems.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(registryObject.get(), 1)));
                        } else {
                            uncommonItems.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(registryObject.get(), random.nextInt(1, registryObject.get().getMaxLevel()))));
                        }
                    }
                    if (registryObject.get().getSakuraRarity() == SakuraRarity.RARE) {
                        if (registryObject.get().getMaxLevel() == 1) {
                            rareItems.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(registryObject.get(), 1)));
                        } else {
                            rareItems.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(registryObject.get(), random.nextInt(1, registryObject.get().getMaxLevel()))));
                        }

                    }
                    if (registryObject.get().getSakuraRarity() == SakuraRarity.EPIC) {
                        if (registryObject.get().getMaxLevel() == 1) {
                            epicItems.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(registryObject.get(), 1)));
                        } else {
                            epicItems.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(registryObject.get(), random.nextInt(1, registryObject.get().getMaxLevel()))));
                        }

                    }
                    if (registryObject.get().getSakuraRarity() == SakuraRarity.LEGENDARY) {
                        if (registryObject.get().getMaxLevel() == 1) {
                            legItems.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(registryObject.get(), 1)));
                        } else {
                            legItems.add(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(registryObject.get(), random.nextInt(1, registryObject.get().getMaxLevel()))));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }

        if (rarity == 1) {
            if (commonItems.size() == 1) {
                return commonItems.get(0);
            }
            return commonItems.get(random.nextInt(0, commonItems.size()));
        } else if (rarity == 2) {
            if (uncommonItems.size() == 1) {
                return uncommonItems.get(0);
            }
            return uncommonItems.get(random.nextInt(0, uncommonItems.size()));
        } else if (rarity == 3) {
            if (rareItems.size() == 1) {
                return rareItems.get(0);
            }
            return rareItems.get(random.nextInt(0, rareItems.size()));
        } else if (rarity == 4) {
            if (epicItems.size() == 1) {
                return epicItems.get(0);
            }
            return epicItems.get(random.nextInt(0, epicItems.size()));
        } else {
            if (legItems.size() == 1) {
                return legItems.get(0);
            }
            return legItems.get(random.nextInt(0, legItems.size()));
        }


    }


}
