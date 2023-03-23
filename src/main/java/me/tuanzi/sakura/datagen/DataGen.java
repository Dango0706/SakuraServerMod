package me.tuanzi.sakura.datagen;

import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static me.tuanzi.sakura.SakuraMain.MODID;
import static me.tuanzi.sakura.blocks.BlockReg.BLOCKS;
import static me.tuanzi.sakura.items.ItemReg.ITEMS;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        if (event.includeClient()) {
            //block/item models, blockstates, language files...
            event.getGenerator().addProvider(true, new ItemModel(event.getGenerator(), MODID, event.getExistingFileHelper(), ITEMS));
        }
        if (event.includeServer()) {
            //recipes,advancements,tags...
            event.getGenerator().addProvider(true, new Recipes(event.getGenerator().getPackOutput()));
            event.getGenerator().addProvider(true, new BlockTag(event.getGenerator().getPackOutput(), event.getLookupProvider(), MODID, BLOCKS, event.getExistingFileHelper()));

        }
        if (event.includeReports()) {
            //world
        }

    }
}
