package me.tuanzi.sakura.blocks.events;

import me.tuanzi.sakura.blocks.BlockReg;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FuncionalBlockEvent {
    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        Level level = event.player.getLevel();
        Player player = event.player;
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        //加时间
        player.getPersistentData().putInt("ELEVATOR", player.getPersistentData().getInt("ELEVATOR") + 1);
        //电梯
        if (level.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == BlockReg.ELEVATOR.get()) {
            if (player instanceof LocalPlayer player1) {
                if (player.getPersistentData().getInt("ELEVATOR") > 20) {
                    if (player1.input.jumping) {
                        for (int i = 2; i < 100; i++) {
                            if (level.getBlockState(new BlockPos(x, y + i, z)).getBlock() == BlockReg.ELEVATOR.get()) {
                                player.absMoveTo(x, y + 1 + i - 0.42, z);
                                player1.playSound(SoundEvents.ENDERMAN_TELEPORT, 1, 1);
                                player.getPersistentData().putInt("ELEVATOR",0);
                                return;
                            }
                        }
                    }
                    if (player1.input.shiftKeyDown) {
                        for (int i = 2; i < 100; i++) {
                            if (level.getBlockState(new BlockPos(x, y - i, z)).getBlock() == BlockReg.ELEVATOR.get()) {
                                player.absMoveTo(x, y + 1 - i, z);
                                player1.playSound(SoundEvents.ENDERMAN_TELEPORT, 1, 1);
                                player.getPersistentData().putInt("ELEVATOR",0);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
