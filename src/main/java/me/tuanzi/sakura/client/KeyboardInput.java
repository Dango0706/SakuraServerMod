package me.tuanzi.sakura.client;

import com.mojang.blaze3d.platform.InputConstants;
import me.tuanzi.sakura.SakuraMain;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = SakuraMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyboardInput {

    private static final String CATEGORY = "key.sakura";

        public static final KeyMapping VEIN_MINE_KEY = new KeyMapping(
                "key.sakura.vein_mine",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_GRAVE_ACCENT,
                CATEGORY
        );

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(VEIN_MINE_KEY);
    }



}
