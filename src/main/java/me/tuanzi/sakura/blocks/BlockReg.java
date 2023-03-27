package me.tuanzi.sakura.blocks;

import me.tuanzi.sakura.blocks.demonization.MagicTable;
import me.tuanzi.sakura.blocks.functional.Elevator;
import me.tuanzi.sakura.blocks.jadeite.DeepJadeiteOre;
import me.tuanzi.sakura.blocks.jadeite.JadeiteBlock;
import me.tuanzi.sakura.blocks.ruby.DeepRubyOre;
import me.tuanzi.sakura.blocks.ruby.RubyBlock;
import me.tuanzi.sakura.blocks.ruby.RubyOre;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static me.tuanzi.sakura.SakuraMain.MODID;

public class BlockReg {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final RegistryObject<Block> RUBY_ORE = BLOCKS.register("ruby_ore", RubyOre::new);
    public static final RegistryObject<Block> DEEP_RUBY_ORE = BLOCKS.register("deepslate_ruby_ore", DeepRubyOre::new);
    public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", RubyBlock::new);
    public static final RegistryObject<Block> DEEP_JADEITE_ORE = BLOCKS.register("deepslate_jadeite_ore", DeepJadeiteOre::new);
    public static final RegistryObject<Block> JADEITE_BLOCK = BLOCKS.register("jadeite_block", JadeiteBlock::new);
    public static final RegistryObject<Block> MAGIC_TABLE = BLOCKS.register("magic_table", MagicTable::new);
    public static final RegistryObject<Block> ELEVATOR = BLOCKS.register("elevator", Elevator::new);

}
