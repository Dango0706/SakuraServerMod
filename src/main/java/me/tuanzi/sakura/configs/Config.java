package me.tuanzi.sakura.configs;

import net.minecraftforge.common.ForgeConfigSpec;


public class Config {

    public static ForgeConfigSpec CONFIG;
    public static ForgeConfigSpec.DoubleValue PIERCING_MAX_HP;
    public static ForgeConfigSpec.DoubleValue SKILLED_ATTACK_SPEED;
    public static ForgeConfigSpec.BooleanValue SKILLED_ENABLE;
    public static ForgeConfigSpec.BooleanValue PIERCING_ENABLE;
    public static ForgeConfigSpec.BooleanValue BLEEDING_ENABLE;
    public static ForgeConfigSpec.BooleanValue BOW_BLEEDING_ENABLE;
    public static ForgeConfigSpec.BooleanValue MAGIC_SOLUBLE_ENABLE;
    public static ForgeConfigSpec.DoubleValue BLEEDING_DAMAGE;

    static {
        ForgeConfigSpec.Builder commonBuilder = new ForgeConfigSpec.Builder();
        commonBuilder.comment("附魔设定").push("enchant");
        commonBuilder.comment("所有是否通过附魔台附魔均在制作中..");
        commonBuilder.comment("附魔[刺骨]").push("piercing");
        PIERCING_ENABLE = commonBuilder.comment("是否可以通过附魔台附魔?").comment("默认:false").define("enable", false);
        PIERCING_MAX_HP = commonBuilder.comment("每级造成敌方最大血量百分之几的伤害 单位是百分比").comment("默认:1.5").defineInRange("value", 1.5, 0, 100);
        commonBuilder.pop();
        commonBuilder.comment("附魔[熟练]").push("skilled");
        SKILLED_ENABLE = commonBuilder.comment("是否可以通过附魔台附魔?").comment("默认:false").define("enable", false);
        SKILLED_ATTACK_SPEED = commonBuilder.comment("熟练每级提供的攻速加成 单位:%").comment("默认:15").defineInRange("value", 15.0, 0, 2000);
        commonBuilder.pop();
        commonBuilder.comment("附魔[流血]").push("bleeding");
        BLEEDING_ENABLE = commonBuilder.comment("是否可以通过附魔台附魔?").comment("默认:false").define("enable", false);
        BOW_BLEEDING_ENABLE = commonBuilder.comment("弓箭的[流血]是否可以通过附魔台附魔?").comment("默认:false").define("enable", false);
        BLEEDING_DAMAGE = commonBuilder.comment("每级流血造成的伤害(移动时双倍)[WIP]").comment("默认:1.0").defineInRange("value", 1.0, 0, 2000);
        commonBuilder.pop();
        commonBuilder.comment("附魔[流血]").push("magic_soluble_enable");
        MAGIC_SOLUBLE_ENABLE = commonBuilder.comment("是否可以通过附魔台附魔?").comment("默认:false").define("enable", false);
        commonBuilder.pop();
        commonBuilder.pop();
        CONFIG = commonBuilder.build();

    }


}
