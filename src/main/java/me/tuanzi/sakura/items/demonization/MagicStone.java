package me.tuanzi.sakura.items.demonization;

import me.tuanzi.sakura.blocks.BlockReg;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import static me.tuanzi.sakura.blocks.BlockReg.MAGIC_TABLE;
import static me.tuanzi.sakura.items.demonization.Draw.draw;
import static me.tuanzi.sakura.items.demonization.Draw.drawItem;
import static me.tuanzi.sakura.utils.Utils.COLOR;

public class MagicStone extends Item {
    public MagicStone() {
        super(new Properties());
    }

    protected void resultDraw(UseOnContext use){

    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext use) {
        if (!use.getLevel().isClientSide() && use.getPlayer() != null) {
            //是抽奖方块
            if (use.getLevel().getBlockState(use.getClickedPos()).getBlock() == MAGIC_TABLE.get()) {
                //检查方块.
                BlockPos pos = use.getClickedPos();
                boolean blockPlaced = false;
                if (use.getLevel().getBlockState(pos.offset(0, -1, 0)).getBlock() == BlockReg.JADEITE_BLOCK.get())
                    if (use.getLevel().getBlockState(pos.offset(0, 3, 0)).getBlock() == MAGIC_TABLE.get())
                        if (use.getLevel().getBlockState(pos.offset(1, -1, 1)).getBlock() == BlockReg.JADEITE_BLOCK.get())
                            if (use.getLevel().getBlockState(pos.offset(-1, -1, 1)).getBlock() == BlockReg.JADEITE_BLOCK.get())
                                if (use.getLevel().getBlockState(pos.offset(-1, -1, -1)).getBlock() == BlockReg.JADEITE_BLOCK.get())
                                    if (use.getLevel().getBlockState(pos.offset(-1, -1, 1)).getBlock() == BlockReg.JADEITE_BLOCK.get())
                                        if (use.getLevel().getBlockState(pos.offset(1, -1, 0)).getBlock() == Blocks.DIAMOND_BLOCK)
                                            if (use.getLevel().getBlockState(pos.offset(0, -1, -1)).getBlock() == Blocks.NETHERITE_BLOCK)
                                                if (use.getLevel().getBlockState(pos.offset(0, -1, 1)).getBlock() == Blocks.EMERALD_BLOCK)
                                                    if (use.getLevel().getBlockState(pos.offset(-1, -1, 0)).getBlock() == Blocks.GOLD_BLOCK)
                                                        blockPlaced = true;
                //通过.
                if (blockPlaced) {
                    Player player = use.getPlayer();
                    ItemStack itemStack = use.getItemInHand();
                    //总抽卡数
                    String allCount = "sakura:draw_total_count";
                    //距离五星保底
                    String legCount = "sakura:draw_leg_count";
                    //距离三星保底
                    String rareCount = "sakura:draw_rare_count";
                    int count = 0;
                    int leg_count = 0;
                    int rare_count = 0;
                    //检测玩家nbt
                    if (player.getPersistentData().contains(allCount)) {
                        count = player.getPersistentData().getInt(allCount);
                    } else {
                        player.getPersistentData().putInt(allCount, 1);
                    }
                    if (player.getPersistentData().contains(legCount)) {
                        leg_count = player.getPersistentData().getInt(legCount);
                    } else {
                        player.getPersistentData().putInt(legCount, 1);
                    }
                    if (player.getPersistentData().contains(rareCount)) {
                        rare_count = player.getPersistentData().getInt(rareCount);
                    } else {
                        player.getPersistentData().putInt(rareCount, 1);
                    }
                    //站立
                    if (player.getPose() == Pose.STANDING) {
                        int needXP = 30;
                        if (player.experienceLevel < needXP && !player.isCreative()) {
                            ServerPlayer serverPlayer = (ServerPlayer) player;
                            serverPlayer.connection.send(new ClientboundSetActionBarTextPacket(Component.empty().append("§c古老的注魔桌没有回应你!好像是经验等级不足...")));
                            //todo:弹开效果
//                        serverPlayer.moveTo(serverPlayer.getX() - 2, serverPlayer.getY() + 3, serverPlayer.getZ() - 2);
//                        serverPlayer.move(MoverType.PLAYER,new Vec3(-2,3,-2));
//                        serverPlayer.addDeltaMovement(new Vec3(-2,3,-2));
                            return InteractionResult.SUCCESS;
                        } else {
                            if (!player.isCreative())
                                player.experienceLevel -= needXP;
                        }
                        Level level = use.getLevel();
                        //抽奖...
                        //抽奖券-1
                        if (!player.isCreative()) {
                            itemStack.setCount(itemStack.getCount() - 1);
                        }
                        //抽奖次数+1
                        count += 1;
                        leg_count += 1;
                        rare_count += 1;
                        //获得结果
                        int result = draw(leg_count, rare_count);
                        //三四五星 重置保底
                        if (result == 3 || result == 4) {
                            rare_count = 0;
                        }
                        if (result == 5) {
                            leg_count = 0;
                        }
                        //告知结果player.sendSystemMessage(Component.empty().append("抽奖结果:" + result));
                        //获取方块坐标
                        int x = use.getClickedPos().getX();
                        int y = use.getClickedPos().getY();
                        int z = use.getClickedPos().getZ();
                        //获取烟花颜色
                        int[] color = {COLOR[result - 1]};
                        //创建烟花火箭
                        ItemStack resultRocket = new ItemStack(Items.FIREWORK_ROCKET);
                        CompoundTag allTag = new CompoundTag();
                        CompoundTag fireworksTag = new CompoundTag();
                        ListTag explosionsTag = new ListTag();
                        CompoundTag boom1Tag = new CompoundTag();
                        boom1Tag.putByte("Trail", (byte) 1);
                        boom1Tag.putByte("Flicker", (byte) 1);
                        boom1Tag.putInt("Type", 0);
                        boom1Tag.putIntArray("Colors", color);
                        CompoundTag boom2Tag = boom1Tag.copy();
                        boom2Tag.putInt("Type", 2);
                        explosionsTag.add(boom1Tag);
                        explosionsTag.add(boom2Tag);
                        fireworksTag.put("Explosions", explosionsTag);
                        fireworksTag.putInt("Flight", 0);
                        allTag.put("Fireworks", fireworksTag);
                        resultRocket.addTagElement("Fireworks", fireworksTag);
                        //创建烟花火箭entity
                        FireworkRocketEntity rocket = new FireworkRocketEntity(use.getLevel(), x + 0.5, y + 1, z + 0.5, resultRocket);
                        //发射烟花火箭
                        level.addFreshEntity(rocket);
                        ExperienceOrb experienceOrb = new ExperienceOrb(level, x, y + 0.5, z, 1);
                        level.addFreshEntity(experienceOrb);
                        //获得结果的物品
                        ItemStack resultItem = drawItem(result);
                        ItemEntity itemEntity = new ItemEntity(use.getLevel(), x + 0.5, y + 1, z + 0.5, resultItem);
                        //发光
                        itemEntity.setGlowingTag(true);
                        //延迟捡起
                        itemEntity.setPickUpDelay(30);
                        //发射物品
                        level.addFreshEntity(itemEntity);
                        //添加nbt
                        player.getPersistentData().putInt(allCount, count);
                        player.getPersistentData().putInt(legCount, leg_count);
                        player.getPersistentData().putInt(rareCount, rare_count);
                        //增加冷却时间
                        player.getCooldowns().addCooldown(this, 30);
                        //潜行
                    } else if (player.getPose() == Pose.CROUCHING) {
                        //发送抽奖信息
                        player.sendSystemMessage(Component.empty().append("注魔信息..."));
                        //潜行告诉保底
                        player.sendSystemMessage(Component.empty().append("距离4星保底:" + (10 - rare_count)));
                        player.sendSystemMessage(Component.empty().append("距离5星保底:" + (90 - leg_count)));
                    }
                } else {
                    ServerPlayer player = (ServerPlayer) use.getPlayer();
                    player.connection.send(new ClientboundSetActionBarTextPacket(Component.empty().append("§c古老的注魔桌没有回应你!好像是阵法没有摆对...")));
                }


            }
        }
        return InteractionResult.SUCCESS;
    }

}
