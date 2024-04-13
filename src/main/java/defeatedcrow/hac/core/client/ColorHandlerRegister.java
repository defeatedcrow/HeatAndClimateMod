package defeatedcrow.hac.core.client;

import defeatedcrow.hac.core.material.BuildInit;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;

public class ColorHandlerRegister {

	public static void registerBlockColorHandler(RegisterColorHandlersEvent.Block event) {

		event.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getAverageGrassColor(world, pos) : GrassColor.get(0.5D, 1.0D),
			BuildInit.SLAB_GRASS.get());
	}

	public static void registerItemColorHandler(RegisterColorHandlersEvent.Item event) {

		event.register((stack, tintIndex) -> {
			BlockState state = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
			return event.getBlockColors().getColor(state, null, null, tintIndex);
		}, BuildInit.SLAB_GRASS.get());
	}

}
