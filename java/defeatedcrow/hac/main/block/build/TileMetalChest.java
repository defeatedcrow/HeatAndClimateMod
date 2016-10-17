package defeatedcrow.hac.main.block.build;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;

public class TileMetalChest extends TileLowChest {

	@Override
	protected SoundEvent getOpenSound() {
		return SoundEvents.BLOCK_IRON_DOOR_OPEN;
	}

	@Override
	protected SoundEvent getCloseSound() {
		return SoundEvents.BLOCK_IRON_DOOR_CLOSE;
	}
}
