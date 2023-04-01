package me.tuanzi.sakura.random_events;

import net.minecraft.core.Holder;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

import static me.tuanzi.sakura.utils.Utils.COLOR_CODE;

@Mod.EventBusSubscriber
public class EliteMobEvent {
    //是否是精英怪
    static final String isElite = "elite";
    //精英怪等级
    static final String eliteLevel = "eliteLevel";

    //受伤害
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void mobHurtEvent(LivingDamageEvent event) {
        LivingEntity livingEntity = event.getEntity();
        LivingEntity abuser = (LivingEntity) event.getSource().getEntity();
        if (event.getEntity().getPersistentData().getBoolean(isElite) && abuser != null) {
            //缴械
            if (livingEntity.getPersistentData().getBoolean(EliteSkills.DISARM.getName())) {
                String skill = EliteSkills.DISARM.getName();
                //冷却到了
                if (livingEntity.getPersistentData().getInt(skill + "冷却") <= 0) {
                    //使目标扔出主手武器
                    if (abuser instanceof Player player) {
                        //手中拿东西了
                        if (player.getMainHandItem().getItem() != Items.AIR) {
                            //扔掉物品
                            player.drop(player.getMainHandItem().copy(), true);
                            //设置为0
                            player.getMainHandItem().setCount(0);
                            //冷却10s
                            livingEntity.getPersistentData().putInt(skill + "冷却", 20 * 20);
                        }
                    }
                }
            }
            //重生
            if (livingEntity.getPersistentData().getBoolean(EliteSkills.RELIFE.getName())) {
                if (event.getAmount() > livingEntity.getHealth()) {
                    //播放音效
                    if (abuser instanceof ServerPlayer serverPlayer)
                        serverPlayer.connection.send(new ClientboundSoundPacket(Holder.direct(SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("minecraft:entity.player.levelup"))), SoundSource.HOSTILE, abuser.getX(), abuser.getY(), abuser.getZ(), 1, 1.25f, 1));
                    //设置最终伤害为0
                    event.setAmount(0);
                    //恢复满血
                    livingEntity.setHealth(livingEntity.getMaxHealth());
                    //设置为禁用
                    livingEntity.getPersistentData().putBoolean(EliteSkills.RELIFE.getName(), false);
                }
            }


        }
    }

    //生成
    @SubscribeEvent
    public static void mobSpawn(LivingSpawnEvent.SpecialSpawn event) {
        LevelAccessor level = event.getLevel();
        if (event.getEntity() instanceof Monster mob) {
            //设置基础
            double base;
            if (level.getDifficulty() == Difficulty.PEACEFUL) {
                base = 0;
            } else if (level.getDifficulty() == Difficulty.EASY) {
                base = 2;
            } else if (level.getDifficulty() == Difficulty.NORMAL) {
                base = 4;
            } else {
                base = 8;
            }
            /*设置概率*/
            if (mob.getRandom().nextDouble() < /*1*/ 0.015 * base) {
                double random = mob.getRandom().nextDouble();
                //后期计算base
                if (random < 0.4) {
                    //精英
                    //生成精英怪物,添加标签
                    mob.getPersistentData().putBoolean(isElite, true);
                    mob.getPersistentData().putInt(eliteLevel, 1);
                    //修改名字
                    mob.setCustomName(Component.empty().append("§a[精英]").append(mob.getName()));
                    mob.setCustomNameVisible(true);
                    //添加attribute
                    mob.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(mob.getMaxHealth() * base / 2);
                    mob.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(mob.getAttributeBaseValue(Attributes.ATTACK_DAMAGE) * base / 2);
                } else if (random < 0.65) {
                    //大师
                    //生成精英怪物,添加标签
                    mob.getPersistentData().putBoolean(isElite, true);
                    mob.getPersistentData().putInt(eliteLevel, 2);
                    //修改名字
                    mob.setCustomName(Component.empty().append("§b[大师]").append(mob.getName()));
                    mob.setCustomNameVisible(true);
                    //添加attribute
                    mob.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(mob.getMaxHealth() * base);
                    mob.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(mob.getAttributeBaseValue(Attributes.ATTACK_DAMAGE) * base);

                } else if (random < 0.8) {
                    //超级大师
                    //生成精英怪物,添加标签
                    mob.getPersistentData().putBoolean(isElite, true);
                    mob.getPersistentData().putInt(eliteLevel, 3);
                    //修改名字
                    mob.setCustomName(Component.empty().append("§d[超级大师]").append(mob.getName()));
                    mob.setCustomNameVisible(true);
                    //添加attribute
                    mob.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(mob.getMaxHealth() * base * 2);
                    mob.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(mob.getAttributeBaseValue(Attributes.ATTACK_DAMAGE) * base * 2);

                } else if (random < 0.95) {
                    //究极大师
                    //生成精英怪物,添加标签
                    mob.getPersistentData().putBoolean(isElite, true);
                    mob.getPersistentData().putInt(eliteLevel, 4);
                    //修改名字
                    mob.setCustomName(Component.empty().append("§e[究极大师]").append(mob.getName()));
                    mob.setCustomNameVisible(true);
                    //添加attribute
                    mob.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(mob.getMaxHealth() * base * 4);
                    mob.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(mob.getAttributeBaseValue(Attributes.ATTACK_DAMAGE) * base * 4);

                } else {
                    //无人能挡
                    //生成精英怪物,添加标签
                    mob.getPersistentData().putBoolean(isElite, true);
                    mob.getPersistentData().putInt(eliteLevel, 5);
                    //修改名字
                    mob.setCustomName(Component.empty().append("§c[无人能挡]").append(mob.getName()));
                    mob.setCustomNameVisible(true);
                    //添加attribute
                    mob.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(mob.getMaxHealth() * base * 8);
                    mob.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(mob.getAttributeBaseValue(Attributes.ATTACK_DAMAGE) * base * 8);
                    mob.getAttributes().getInstance(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(1);

                }
                //公共添加
                mob.getAttributes().getInstance(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(1);
                mob.getAttributes().getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(mob.getAttributeBaseValue(Attributes.MOVEMENT_SPEED) * (1 + (float) (mob.getPersistentData().getInt(eliteLevel) * mob.getPersistentData().getInt(eliteLevel)) / 10));
                double baseHealth = mob.getMaxHealth();
                //添加技能池
                for (int i = 0; i < (base + mob.getPersistentData().getInt(eliteLevel) * 4) / 2; i++) {
                    if (mob.getRandom().nextDouble() < (base / 10)) {
                        ArrayList<String> skillList = new ArrayList<>();
                        for (EliteSkills skill : EliteSkills.values()) {
                            skillList.add(skill.getName());
                        }
                        int max = skillList.size();
                        String skill = skillList.get(mob.getRandom().nextInt(max));
                        if (mob.getPersistentData().contains(skill)) {
                            //有这个技能了,那就加20%的最大生命值吧
                            if (mob.getMaxHealth() < baseHealth * 3)
                                mob.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(mob.getMaxHealth() * 1.2);
                            else
                                mob.getAttributes().getInstance(Attributes.ARMOR).setBaseValue(mob.getArmorValue() * 1.05);

                        } else {
                            //没有这个skill
                            mob.getPersistentData().putBoolean(skill, true);
                            mob.getPersistentData().putInt(skill + "冷却", 0);
                            //添加在名字上
                            mob.setCustomName(mob.getCustomName().copy().append(" §" + COLOR_CODE[mob.getRandom().nextInt(COLOR_CODE.length)] + skill));
                        }
                    }
                }
                //设置最终血量
                mob.setHealth(mob.getMaxHealth());
            }
        }
    }

    //升空技能
    @SubscribeEvent
    public static void PlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.getPersistentData().getBoolean("被升空")) {
//            event.player.push(0, 2, 0);
            event.player.lerpMotion(0, 1.2, 0);
            event.player.getPersistentData().remove("被升空");
        }
    }

    //技能
    @SubscribeEvent
    public static void Livingtick(TickEvent.LevelTickEvent event) {
        Level level = event.level;
        Iterable<Entity> entityIterator = ((ServerLevel) level).getAllEntities();
        for (Entity entity : entityIterator) {
            if (entity instanceof Mob mob) {
                //是精英怪
                if (mob.getPersistentData().getInt(eliteLevel) > 0) {
                    //攻击目标不是玩家
                    if (mob.getTarget() != null && mob.getTarget().getType() != EntityType.PLAYER) {
                        double x = mob.getX();
                        double y = mob.getY();
                        double z = mob.getZ();
                        ArrayList<Entity> entities = (ArrayList<Entity>) level.getEntities(mob, AABB.of(BoundingBox.fromCorners(new Vec3i(x - 40, y - 40, z - 40), new Vec3i(x + 40, y + 40, z + 40))));
                        for (Entity entity1 : entities) {
                            //设置攻击目标为玩家
                            //todo:设置为最近的玩家
                            if (entity1 instanceof Player player) {
                                mob.setTarget(player);
                            }
                        }
                    }
                    //可被激怒生物设置为玩家.
                    if (mob instanceof NeutralMob mob1) {
                        double x = mob.getX();
                        double y = mob.getY();
                        double z = mob.getZ();
                        ArrayList<Entity> entities = (ArrayList<Entity>) level.getEntities(mob, AABB.of(BoundingBox.fromCorners(new Vec3i(x - 40, y - 40, z - 40), new Vec3i(x + 40, y + 40, z + 40))));
                        for (Entity entity1 : entities) {
                            //设置攻击目标为玩家
                            //todo:设置为最近的玩家
                            if (entity1 instanceof Player player) {
                                mob1.setLastHurtByMob(player);
                                mob1.setPersistentAngerTarget(player.getUUID());
                                mob1.setTarget(player);
                                mob1.setRemainingPersistentAngerTime(999999999);
                            }
                        }

                    }
                    //减冷却
                    if (!level.isClientSide()) {
                        for (EliteSkills skills : EliteSkills.values()) {
                            String s = skills.getName();
                            if (mob.getPersistentData().getBoolean(s)) {
                                mob.getPersistentData().putInt(s + "冷却", Math.max(0, mob.getPersistentData().getInt(s + "冷却") - 1));
                            }
                        }
                    }
                }
            }
        }
    }

    //修改逻辑:为攻击这个生物的人受到技能影响.
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void livingDamage(LivingDamageEvent event) {
        //生物被精英怪打
        if (event.getSource().getEntity() instanceof Mob mob) {
            LivingEntity target = event.getEntity();
            //重击
            if (mob.getPersistentData().getBoolean(EliteSkills.PUMMEL.getName())) {
                event.setAmount(event.getAmount() * 1.2f);
            }
            //击退
            if (mob.getPersistentData().getBoolean(EliteSkills.KNOCKBACK.getName())) {
                target.knockback(10/*附魔:击退 的等级*/ * 0.5F, Mth.sin(mob.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(mob.getYRot() * ((float) Math.PI / 180F)));
            }

        }
        //精英怪受伤
        if (event.getEntity() instanceof Mob mob) {
            if (event.getSource().getEntity() instanceof LivingEntity target) {
                double x = target.getX();
                double y = target.getY();
                double z = target.getZ();
                Vec3 targetVec3 = new Vec3(x, y, z);
                Vec3 mobVec3 = new Vec3(mob.getX(), mob.getY(), mob.getZ());
                //小于7格范围内
                if (targetVec3.distanceTo(mobVec3) < 7) {
                    //升空
                    if (mob.getPersistentData().getBoolean(EliteSkills.LEVITATION.getName())) {
                        String skill = EliteSkills.LEVITATION.getName();
                        //冷却到了
                        if (mob.getPersistentData().getInt(skill + "冷却") <= 0) {
                            //冷却10s
                            mob.getPersistentData().putInt(skill + "冷却", 200);
                            //使目标升空
                            if (target instanceof ServerPlayer p) {
                                p.getPersistentData().putBoolean("被升空", true);
                                p.hurt(DamageSource.mobAttack(mob), 0.05f);
                            } else {
                                target.push(0, 1.2, 0);
                            }
                        }
                    }
                    //中毒
                    if (mob.getPersistentData().getBoolean(EliteSkills.POTION.getName())) {
                        String skill = EliteSkills.POTION.getName();
                        if (mob.getPersistentData().getInt(skill + "冷却") <= 0) {
                            //冷却5s
                            mob.getPersistentData().putInt(skill + "冷却", 20 * 5);
                            //使目标中毒
                            target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));

                        }
                    }
                    //饥饿
                    if (mob.getPersistentData().getBoolean(EliteSkills.HUNGER.getName())) {
                        String skill = EliteSkills.HUNGER.getName();
                        if (mob.getPersistentData().getInt(skill + "冷却") <= 0) {
                            //冷却5s
                            mob.getPersistentData().putInt(skill + "冷却", 20 * 5);
                            //使目标饥饿
                            target.addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 0));
                        }

                    }
                    //失明
                    if (mob.getPersistentData().getBoolean(EliteSkills.BLINDNESS.getName())) {
                        String skill = EliteSkills.BLINDNESS.getName();
                        if (mob.getPersistentData().getInt(skill + "冷却") <= 0) {
                            //冷却10s
                            mob.getPersistentData().putInt(skill + "冷却", 20 * 10);
                            //使目标饥饿
                            target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));

                        }
                    }//虚弱
                    if (mob.getPersistentData().getBoolean(EliteSkills.WEAKNESS.getName())) {
                        String skill = EliteSkills.WEAKNESS.getName();
                        if (mob.getPersistentData().getInt(skill + "冷却") <= 0) {
                            //冷却5s
                            mob.getPersistentData().putInt(skill + "冷却", 20 * 5);
                            //使目标饥饿
                            target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));

                        }
                    }//凋零
                    if (mob.getPersistentData().getBoolean(EliteSkills.WITHER.getName())) {
                        String skill = EliteSkills.WITHER.getName();
                        if (mob.getPersistentData().getInt(skill + "冷却") <= 0) {
                            //冷却5s
                            mob.getPersistentData().putInt(skill + "冷却", 20 * 5);
                            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0));

                        }
                    }//忍者
                    if (mob.getPersistentData().getBoolean(EliteSkills.NINJA.getName())) {
                        String skill = EliteSkills.NINJA.getName();
                        if (mob.getPersistentData().getInt(skill + "冷却") <= 0) {
                            //冷却5s
                            mob.getPersistentData().putInt(skill + "冷却", 20 * 5);
                            mob.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 100, 0));
                        }
                    }//灼热
                    if (mob.getPersistentData().getBoolean(EliteSkills.SCORCHING.getName())) {
                        String skill = EliteSkills.SCORCHING.getName();
                        if (mob.getPersistentData().getInt(skill + "冷却") <= 0) {
                            //冷却5s
                            mob.getPersistentData().putInt(skill + "冷却", 20 * 5);
                            target.setRemainingFireTicks(200);
                        }
                    }
                }
            }//绝对防御
            if (mob.getPersistentData().getBoolean(EliteSkills.DEFENSE.getName())) {
                event.setAmount(event.getAmount() * 0.85f);
            }
        }


    }


    //死亡掉落更多经验
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void dropXp(LivingExperienceDropEvent event) {
        //是精英怪
        if (event.getEntity().getPersistentData().getBoolean(isElite)) {
            //精英怪等级
            int level = event.getEntity().getPersistentData().getInt(eliteLevel);
            Level worldLevel = event.getEntity().getLevel();
            int base;
            if (worldLevel.getDifficulty() == Difficulty.PEACEFUL) {
                base = 0;
            } else if (worldLevel.getDifficulty() == Difficulty.EASY) {
                base = 2;
            } else if (worldLevel.getDifficulty() == Difficulty.NORMAL) {
                base = 4;
            } else {
                base = 8;
            }
            //等级 * 基数
            level *= level;
            level *= base;
            //最终为原始 * 等级的平方 * 基数 / 2.
            event.setDroppedExperience(event.getDroppedExperience() * level / 2);


        }
    }


}


