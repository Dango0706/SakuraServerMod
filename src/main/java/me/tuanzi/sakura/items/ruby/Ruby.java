package me.tuanzi.sakura.items.ruby;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

/**
 * @author tuanzi
 * <p>
 * 红宝石
 */
public class Ruby extends Item {
    public Ruby() {
        super(new Item.Properties());
    }

    /**
     * Called when this item is used when targeting a Block
     *
     * @param pContext
     */
    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        Player p = pContext.getPlayer();
/*        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(new TranslatableText("title.examplemod.config"));*/

        return InteractionResult.SUCCESS;
    }
}
