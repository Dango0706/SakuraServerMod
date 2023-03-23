package me.tuanzi.sakura.random_events;

import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

import static me.tuanzi.sakura.utils.Utils.COLOR_CODE;

@Mod.EventBusSubscriber
public class SpawnMobEvent {

    @SubscribeEvent
    public static void mobHurtEvent(LivingDamageEvent event) {
        if (event.getEntity().getPersistentData().getBoolean("elite")) {
            LivingEntity livingEntity = event.getEntity();
            LivingEntity abuser = (LivingEntity) event.getSource().getEntity();
            //缴械
            if (livingEntity.getPersistentData().getBoolean(EliteSkills.disarm.getName())) {
                String skill = EliteSkills.disarm.getName();
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

        }
    }

    //生成成功
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
            if (mob.getRandom().nextDouble() < 1 /*0.015 * base*/) {
                double random = mob.getRandom().nextDouble();
                //后期计算base
                if (random < 0.4) {
                    //精英
                    //生成精英怪物,添加标签
                    mob.getPersistentData().putBoolean("elite", true);
                    mob.getPersistentData().putInt("eliteLevel", 1);
                    //修改名字
                    mob.setCustomName(Component.empty().append("§a[精英]").append(mob.getName()));
                    mob.setCustomNameVisible(true);
                    //添加attribute
                    mob.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(mob.getMaxHealth() * base / 2);
                    mob.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(mob.getAttributeBaseValue(Attributes.ATTACK_DAMAGE) * base / 2);
                } else if (random < 0.65) {
                    //大师
                    //生成精英怪物,添加标签
                    mob.getPersistentData().putBoolean("elite", true);
                    mob.getPersistentData().putInt("eliteLevel", 2);
                    //修改名字
                    mob.setCustomName(Component.empty().append("§b[大师]").append(mob.getName()));
                    mob.setCustomNameVisible(true);
                    //添加attribute
                    mob.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(mob.getMaxHealth() * base);
                    mob.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(mob.getAttributeBaseValue(Attributes.ATTACK_DAMAGE) * base);

                } else if (random < 0.8) {
                    //超级大师
                    //生成精英怪物,添加标签
                    mob.getPersistentData().putBoolean("elite", true);
                    mob.getPersistentData().putInt("eliteLevel", 3);
                    //修改名字
                    mob.setCustomName(Component.empty().append("§d[超级大师]").append(mob.getName()));
                    mob.setCustomNameVisible(true);
                    //添加attribute
                    mob.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(mob.getMaxHealth() * base * 2);
                    mob.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(mob.getAttributeBaseValue(Attributes.ATTACK_DAMAGE) * base * 2);

                } else if (random < 0.95) {
                    //究极大师
                    //生成精英怪物,添加标签
                    mob.getPersistentData().putBoolean("elite", true);
                    mob.getPersistentData().putInt("eliteLevel", 4);
                    //修改名字
                    mob.setCustomName(Component.empty().append("§e[究极大师]").append(mob.getName()));
                    mob.setCustomNameVisible(true);
                    //添加attribute
                    mob.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(mob.getMaxHealth() * base * 4);
                    mob.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(mob.getAttributeBaseValue(Attributes.ATTACK_DAMAGE) * base * 4);

                } else {
                    //无人能挡
                    //生成精英怪物,添加标签
                    mob.getPersistentData().putBoolean("elite", true);
                    mob.getPersistentData().putInt("eliteLevel", 5);
                    //修改名字
                    mob.setCustomName(Component.empty().append("§c[无人能挡]").append(mob.getName()));
                    mob.setCustomNameVisible(true);
                    //添加attribute
                    mob.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(mob.getMaxHealth() * base * 8);
                    mob.getAttributes().getInstance(Attributes.ATTACK_DAMAGE).setBaseValue(mob.getAttributeBaseValue(Attributes.ATTACK_DAMAGE) * base * 8);
                    mob.getAttributes().getInstance(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(1);

                }
                //公共添加
                mob.getAttributes().getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(mob.getAttributeBaseValue(Attributes.MOVEMENT_SPEED) * Math.min(base, 4));
                mob.getAttributes().getInstance(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(1);
                //添加技能池
                for (int i = 0; i < (base + mob.getPersistentData().getInt("eliteLevel") * 4) / 2; i++) {
                    if (mob.getRandom().nextDouble() < (base / 10)) {
                        ArrayList<String> skillList = new ArrayList<>();
                        for (EliteSkills skill : EliteSkills.values()) {
                            skillList.add(skill.getName());
                        }
                        int max = skillList.size();
                        String skill = skillList.get(mob.getRandom().nextInt(max));
                        if (mob.getPersistentData().contains(skill)) {
                            //有这个技能了,那就加20%的最大生命值吧
                            mob.getAttributes().getInstance(Attributes.MAX_HEALTH).setBaseValue(mob.getMaxHealth() * 1.2);
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

    @SubscribeEvent
    public static void PlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.getPersistentData().getBoolean("被升空")) {
//            event.player.push(0, 2, 0);
            event.player.lerpMotion(0, 1.2, 0);
            event.player.getPersistentData().remove("被升空");
        }
    }


    @SubscribeEvent
    public static void Livingtick(TickEvent.LevelTickEvent event) {
        Level level = event.level;
        Iterable<Entity> entityIterator = ((ServerLevel) level).getAllEntities();
        for (Entity entity : entityIterator) {
            if (entity instanceof Mob mob) {
                //是精英怪
                if (mob.getPersistentData().getInt("eliteLevel") > 0) {
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

                    //技能
                    if (mob.getTarget() != null) {
                        LivingEntity target = mob.getTarget();
                        double x = target.getX();
                        double y = target.getY();
                        double z = target.getZ();
                        Vec3 targetVec3 = new Vec3(x, y, z);
                        Vec3 mobVec3 = new Vec3(mob.getX(), mob.getY(), mob.getZ());
                        if (targetVec3.distanceTo(mobVec3) < 7) {
                            //升空
                            if (mob.getPersistentData().getBoolean(EliteSkills.levitation.getName())) {
                                String skill = EliteSkills.levitation.getName();
                                //冷却到了
                                if (mob.getPersistentData().getInt(skill + "冷却") <= 0) {
                                    //冷却10s
                                    mob.getPersistentData().putInt(skill + "冷却", 200);
                                    //使目标升空
                                    if (target instanceof ServerPlayer p) {
                                        p.getPersistentData().putBoolean("被升空", true);
                                        p.hurt(DamageSource.mobAttack(mob), 0.05f);
                                    }
                                }
                            }
                            //中毒
                            if (mob.getPersistentData().getBoolean(EliteSkills.potion.getName())) {
                                String skill = EliteSkills.potion.getName();
                                if (mob.getPersistentData().getInt(skill + "冷却") <= 0) {

                                    //冷却5s
                                    mob.getPersistentData().putInt(skill + "冷却", 20 * 5);
                                    //使目标中毒
                                    target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));

                                }
                            }
                            //饥饿
                            if (mob.getPersistentData().getBoolean(EliteSkills.hunger.getName())) {
                                String skill = EliteSkills.hunger.getName();
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
                                    //小于7格
                                    //冷却10s
                                    mob.getPersistentData().putInt(skill + "冷却", 20 * 10);
                                    //使目标饥饿
                                    target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0));

                                }
                            }//虚弱
                            if (mob.getPersistentData().getBoolean(EliteSkills.WEAKNESS.getName())) {
                                String skill = EliteSkills.WEAKNESS.getName();
                                if (mob.getPersistentData().getInt(skill + "冷却") <= 0) {
                                    //小于7格
                                    //冷却5s
                                    mob.getPersistentData().putInt(skill + "冷却", 20 * 5);
                                    //使目标饥饿
                                    target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


