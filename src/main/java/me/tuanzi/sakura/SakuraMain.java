package me.tuanzi.sakura;

import com.mojang.logging.LogUtils;
import me.tuanzi.sakura.blocks.BlockReg;
import me.tuanzi.sakura.blocks.features.FeatureReg;
import me.tuanzi.sakura.configs.Config;
import me.tuanzi.sakura.effects.EffectReg;
import me.tuanzi.sakura.effects.PotionReg;
import me.tuanzi.sakura.enchantments.EnchantmentReg;
import me.tuanzi.sakura.items.ItemReg;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.lang.reflect.Field;

/**
 * @author tuanzi
 */

@Mod(SakuraMain.MODID)
public class SakuraMain {
    /*
     * todo:
     *  修改注魔桌贴图
     *  增加附魔:https://www.mcmod.cn/class/2025.html
     *  https://www.mcmod.cn/item/list/834-5.html
     *  https://www.mcmod.cn/item/list/61-5.html
     *  https://www.mcmod.cn/item/list/1708-5.html
     *  写几个饰品:https://www.mcmod.cn/class/2029.html
     *  可考虑添加动画:https://www.mcmod.cn/class/3232.html
     *  添加configAPI:https://www.mcmod.cn/class/2346.html
     *  可考虑:https://www.mcmod.cn/class/5074.html
     *  写一本helpbook:https://www.mcmod.cn/class/1388.html finish:3.21
     *  抽卡需求经验值 finish:3.21
     *  连锁挖矿附魔 finish:3.22
     * */
    public static final String MODID = "sakura";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SakuraMain() {

        //设置一些属性的上限
        try {
            Field maximumValueField = RangedAttribute.class.getDeclaredField("maxValue");
            maximumValueField.setAccessible(true);
            maximumValueField.set(Attributes.MAX_HEALTH, Double.MAX_VALUE);
            maximumValueField.set(Attributes.ATTACK_DAMAGE, Double.MAX_VALUE);
            maximumValueField.set(Attributes.ATTACK_KNOCKBACK, Double.MAX_VALUE);
            maximumValueField.set(Attributes.ARMOR, Double.MAX_VALUE);
            maximumValueField.set(Attributes.ARMOR_TOUGHNESS, Double.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }


        BlockReg.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.CONFIG);
        EnchantmentReg.ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EffectReg.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        PotionReg.POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FeatureReg.PLACEMENT_MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FeatureReg.BIOME_MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemReg.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        LOGGER.info("加载!");
    }


}
