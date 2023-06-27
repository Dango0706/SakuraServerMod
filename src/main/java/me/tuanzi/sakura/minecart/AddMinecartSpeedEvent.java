package me.tuanzi.sakura.minecart;


import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AddMinecartSpeedEvent {

    @SubscribeEvent
    public static void addMinecartSpeed(LivingEvent.LivingTickEvent event) {
        if (event.getEntity().getType() == EntityType.MINECART) {
            System.out.println(true);
        }

        if (event.getEntity() instanceof Player player) {
            if (player.isPassenger()) {
                if (player.getRootVehicle() instanceof Minecart minecart) {
                    Level level = player.level();
                    if (!level.isClientSide()) {
                        //如果脚下是加速方块
                        if (level.getBlockState(new BlockPos(minecart.getBlockX(), minecart.getBlockY() - 1, minecart.getBlockZ())).getBlock() == Blocks.REDSTONE_BLOCK) {
                            if (Math.abs(minecart.getDeltaMovement().x) > Math.abs(minecart.getDeltaMovement().z)) {
                                if (minecart.getDeltaMovement().x < 70 && minecart.getDeltaMovement().x > -70) {
//                                    System.out.println("pushX");
                                    if (minecart.getDeltaMovement().x > 0) {
//                                        minecart.setDeltaMovement(minecart.getDeltaMovement().add(1.5,0,0));
                                        minecart.move(MoverType.SELF, new Vec3(1, 0, 0));
//                                        minecart.tick();
                                    } else {
//                                        minecart.setDeltaMovement(minecart.getDeltaMovement().add(-1.5,0,0));
                                        minecart.move(MoverType.SELF, new Vec3(-1, 0, 0));
                                    }
//                                    minecart.tick();
                                }
                            } else {
                                if (minecart.getDeltaMovement().z < 70 && minecart.getDeltaMovement().z > -70) {
                                    if (minecart.getDeltaMovement().z > 0) {
                                        minecart.move(MoverType.SELF, new Vec3(0, 0, 1));
//                                        minecart.setDeltaMovement(minecart.getDeltaMovement().add(0,0,15));
                                    } else {
                                        minecart.move(MoverType.SELF, new Vec3(0, 0, -1));
//                                        minecart.setDeltaMovement(minecart.getDeltaMovement().add(0,0,-15));
                                    }
//                                    minecart.tick();
                                }
                            }

                        }
                    }
                }
            }
        }


    }

}
