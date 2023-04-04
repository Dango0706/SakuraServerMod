package me.tuanzi.sakura.enchantments.events;

import me.tuanzi.sakura.enchantments.EnchantmentReg;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

import static me.tuanzi.sakura.client.KeyboardInput.VEIN_MINE_KEY;
import static me.tuanzi.sakura.enchantments.EnchantmentReg.VEIN_MINE;

@Mod.EventBusSubscriber
public class ToolEvent {
    @SubscribeEvent
    public static void breakBlock(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHand = player.getMainHandItem();
        //连锁
        if (mainHand.getEnchantmentLevel(VEIN_MINE.get()) > 0) {
            if (mainHand.getItem() instanceof PickaxeItem || mainHand.getItem() instanceof AxeItem) {
                int level = mainHand.getEnchantmentLevel(VEIN_MINE.get());
                int damage = mainHand.getMaxDamage() - mainHand.getDamageValue();
                BlockState block = event.getState();
                BlockPos pos = event.getPos();
                if (VEIN_MINE_KEY.isDown()) {
                    if (player.getFoodData().getFoodLevel() > 0) {
                        des(player.level, pos, player, level * 5 + 2, damage, block.getBlock());
                    } else {
                        player.sendSystemMessage(Component.empty().append("你饿死了,吃点东西在连锁挖矿吧!"));
                    }

                }
            }
        }
        //钻石无处不在
        if (mainHand.getEnchantmentLevel(EnchantmentReg.DIAMONDS_EVERYWHERE.get()) > 0) {
            if (event.getState().getMaterial() == Material.STONE) {
                if (player.getRandom().nextDouble() < 0.05 * mainHand.getEnchantmentLevel(EnchantmentReg.DIAMONDS_EVERYWHERE.get())) {
                    ItemStack itemStack = new ItemStack(Items.DIAMOND);
                    ItemEntity itemEntity = new ItemEntity(player.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), itemStack);
                    itemEntity.spawnAtLocation(itemStack);
                }
            }
        }


    }


    public static void des(Level level, BlockPos pos, Player player, int maxMined, int maxUsed, Block block) {
        ItemStack itemStack = player.getMainHandItem();
        int count = 0;
        //防止损坏
        int maxCount = Math.min(maxMined, maxUsed) - 1;
        int damaged = maxCount;

        ArrayList<BlockPos> blockPos = rangeBlock(pos, 5);
        ArrayList<BlockPos> next = new ArrayList<>();

        for (BlockPos pos1 : blockPos) {
            if (maxCount > 0) {
                BlockState blockState = level.getBlockState(pos1);
                if (blockState.getBlock() == block) {
                    maxCount--;
                    count++;
                    level.destroyBlock(pos1, true);
                    next.add(pos1);
                }
            }
        }
        for (BlockPos pos1 : next) {
            BlockState blockState1 = level.getBlockState(pos1);
            if (blockState1.getBlock() == block) {
                ArrayList<BlockPos> pos2 = rangeBlock(pos1, 5);
                for (BlockPos pos3 : pos2) {
                    if (maxCount > 0) {
                        BlockState blockState = level.getBlockState(pos3);
                        if (blockState.getBlock() == block) {
                            maxCount--;
                            count++;
                            level.destroyBlock(pos3, true);
                        }
                    }
                }
            }
        }
        if (!player.isCreative()) {
            float exhaustion = 0;
            for (int i = 0; i < damaged - maxCount; i++) {
                //消耗耐久(自己计算耐久3等..
                itemStack.hurt(1, RandomSource.create(), (ServerPlayer) player);
                //增加疲劳度
                exhaustion += 0.5f;
            }
            //疲劳度+原有的>4了
            while (exhaustion + player.getFoodData().getExhaustionLevel() >= 4) {
                //减4
                exhaustion -= 4;
                //减饱食度或饱和
                if (player.getFoodData().getSaturationLevel() > 0) {
                    player.getFoodData().setSaturation(player.getFoodData().getSaturationLevel() - 1);
                } else {
                    player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - 1);
                }
            }
            //<4了那就加上去
            player.getFoodData().setExhaustion(exhaustion + player.getFoodData().getExhaustionLevel());
        }
        if (player instanceof ServerPlayer player1)
            player1.connection.send(new ClientboundSetActionBarTextPacket(Component.empty().append("本次连锁挖了:§b" + ++count + "§r个方块")));
    }

    private static ArrayList<BlockPos> rangeBlock(BlockPos pos, int range) {
        ArrayList<BlockPos> blockPos = new ArrayList<>();
        for (int i = 1; i < range + 1; i++) {
            //1
            blockPos.add(pos.offset(0, 0, i));
            blockPos.add(pos.offset(i, 0, 0));
            blockPos.add(pos.offset(-i, 0, 0));
            blockPos.add(pos.offset(0, 0, -i));
            blockPos.add(pos.offset(0, i, 0));
            blockPos.add(pos.offset(0, -i, 0));
            //2
            blockPos.add(pos.offset(i, 0, i));
            blockPos.add(pos.offset(-i, 0, i));
            blockPos.add(pos.offset(-i, 0, -i));
            blockPos.add(pos.offset(i, 0, -i));
            blockPos.add(pos.offset(0, i, i));
            blockPos.add(pos.offset(i, i, 0));
            blockPos.add(pos.offset(-i, i, 0));
            blockPos.add(pos.offset(0, i, -i));
            blockPos.add(pos.offset(0, -i, i));
            blockPos.add(pos.offset(i, -i, 0));
            blockPos.add(pos.offset(-i, -i, 0));
            blockPos.add(pos.offset(0, -i, -i));
            //3
            blockPos.add(pos.offset(i, i, i));
            blockPos.add(pos.offset(-i, i, i));
            blockPos.add(pos.offset(-i, i, -i));
            blockPos.add(pos.offset(i, i, -i));
            blockPos.add(pos.offset(i, -i, i));
            blockPos.add(pos.offset(-i, -i, i));
            blockPos.add(pos.offset(-i, -i, -i));
            blockPos.add(pos.offset(i, -i, -i));
        }
        return blockPos;
    }


}
