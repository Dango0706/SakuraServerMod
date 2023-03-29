package me.tuanzi.sakura.client;

import me.tuanzi.sakura.effects.recipes.Brewing;
import me.tuanzi.sakura.items.tiered_item.SakuraBowItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static me.tuanzi.sakura.items.ItemReg.ITEMS;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    @SubscribeEvent
    public static void regEvent(FMLCommonSetupEvent event) {

        event.enqueueWork(() ->
        {
            //弓模型
            ArrayList<Item> items = new ArrayList<>();
            items.addAll(ITEMS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet()));
            for(Item item  : items){
                if(item instanceof SakuraBowItem){
                    ItemProperties.register(item, new ResourceLocation("pull"), (pStack, pLevel, pEntity, pSeed) -> {
                        if (pEntity == null) {
                            return 0.0F;
                        } else {
                            return !(pEntity.getUseItem().getItem() instanceof SakuraBowItem) ? 0.0F : (float) (pStack.getUseDuration() - pEntity.getUseItemRemainingTicks()) / 20.0F;
                        }
                    });
                    ItemProperties.register(item, new ResourceLocation("pulling"), (pStack, pLevel, pEntity, pSeed) -> pEntity != null && pEntity.isUsingItem() && pEntity.getUseItem() == pStack ? 1.0F : 0.0F);
                }
            }
            //酿造台配方
            new Brewing();

        });
    }
}
