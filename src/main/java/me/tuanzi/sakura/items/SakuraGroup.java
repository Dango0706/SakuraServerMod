/*
package me.tuanzi.sakura.items;

import me.tuanzi.sakura.effects.PotionReg;
import me.tuanzi.sakura.enchantments.EnchantmentReg;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static me.tuanzi.sakura.SakuraMain.MODID;
import static me.tuanzi.sakura.items.ItemReg.RUBY;

*/
/**
 * @author tuanzi
 * <p>
 * 添加一个创造模式物品栏
 *//*

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SakuraGroup {


    static ArrayList<Enchantment> enchantments = new ArrayList<>();
    static ArrayList<Item> items = new ArrayList<>();
    static ArrayList<Potion> potions = new ArrayList<>();

    //添加附魔
    static Class<?> enchantmentRegClass = EnchantmentReg.class;
    static Field[] enchantmentRegClassDeclaredFields = enchantmentRegClass.getDeclaredFields();
    static Class<?> itemRegClass = ItemReg.class;
    static Field[] itemRegClassDeclaredFields = itemRegClass.getDeclaredFields();
    static Class<?> potionRegClass = PotionReg.class;
    static Field[] potionRegClassDeclaredFields = potionRegClass.getDeclaredFields();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void buildContents(CreativeModeTabEvent.Register event) {

        for (Field field : enchantmentRegClassDeclaredFields) {
            if (field.getType() == RegistryObject.class) {
                try {
                    RegistryObject<Enchantment> registryObject = (RegistryObject<Enchantment>) field.get(null);
                    enchantments.add(registryObject.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for (Field field : itemRegClassDeclaredFields) {
            if (field.getType() == RegistryObject.class) {
                try {
                    RegistryObject<Item> registryObject = (RegistryObject<Item>) field.get(null);
                    items.add(registryObject.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for (Field field : potionRegClassDeclaredFields) {
            if (field.getType() == RegistryObject.class) {
                try {
                    RegistryObject<Potion> registryObject = (RegistryObject<Potion>) field.get(null);
                    potions.add(registryObject.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        event.registerCreativeModeTab(new ResourceLocation(MODID, "sakura"), builder ->
                // 设置名字
                builder.title(Component.translatable("item_group." + MODID))
                        // 添加图标
                        .icon(() -> new ItemStack(RUBY.get()))
                        // 添加物品
                        .displayItems((enabledFlags, populator, hasPermissions) -> {
                            for (Item item : items) {
                                populator.accept(item);
                            }
                            for (Enchantment enchantment : enchantments) {
                                populator.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, enchantment.getMaxLevel())));
                            }
                            for(Potion potion : potions){
                                ItemStack itemStack = new ItemStack(Items.POTION);
                                PotionUtils.setPotion(itemStack,potion);
                                populator.accept(itemStack);
                            }

                           */
/* populator.accept(RUBY.get());
                            populator.accept(RUBY_ORE.get());
                            populator.accept(RUBY_AXE.get());
                            populator.accept(RUBY_HOE.get());
                            populator.accept(RUBY_PICKAXE.get());
                            populator.accept(RUBY_SHOVEL.get());
                            populator.accept(RUBY_SWORD.get());
                            populator.accept(RUBY_HELMET.get());
                            populator.accept(RUBY_CHESTPLATE.get());
                            populator.accept(RUBY_LEGGINGS.get());
                            populator.accept(RUBY_BOOTS.get());
                            populator.accept(MAGIC_STONE.get());
                            populator.accept(WARDEN_HEART.get());
                            populator.accept(THE_VESSEL_OF_THE_HEART.get());
                            populator.accept(HEART_TOXIC.get());
                            populator.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(PIERCING.get(), PIERCING.get().getMaxLevel())));
                            populator.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(IDENTIFICATION.get(), IDENTIFICATION.get().getMaxLevel())));
                            populator.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(BLEEDING.get(), BLEEDING.get().getMaxLevel())));
                            populator.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(BOW_BLEEDING.get(), BOW_BLEEDING.get().getMaxLevel())));
                            populator.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(BERSERKER.get(), BERSERKER.get().getMaxLevel())));
                            populator.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(MAGIC_PROTECTION.get(), MAGIC_PROTECTION.get().getMaxLevel())));
                            populator.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(MAGIC_SOLUBLE.get(), MAGIC_SOLUBLE.get().getMaxLevel())));
                            populator.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(SKILLED.get(), SKILLED.get().getMaxLevel())));
                            populator.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(SMITE.get(), SMITE.get().getMaxLevel())));
                            populator.accept(EnchantedBookItem.createForEnchantment(new EnchantmentInstance(LUNAR_BLESSING.get(), LUNAR_BLESSING.get().getMaxLevel())));
*//*

                        }));
    }
}
*/
