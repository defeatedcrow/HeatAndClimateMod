package defeatedcrow.hac.core.event;

import java.util.EnumSet;

import defeatedcrow.hac.api.util.DCState;
import defeatedcrow.hac.machine.material.block.EnergyTileBaseDC;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.LightningRodBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent.NeighborNotifyEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ThunderstruckEventDC {

	@SubscribeEvent
	public static void onLodUpdate(NeighborNotifyEvent event) {
		if (event.getLevel() != null && event.getPos() != null) {
			BlockEntity tile = event.getLevel().getBlockEntity(event.getPos());
			if (tile instanceof EnergyTileBaseDC machine) {
				for (Direction dir : EnumSet.allOf(Direction.class)) {
					BlockPos p1 = event.getPos().relative(dir);
					BlockState state = event.getLevel().getBlockState(p1);
					if (state.getBlock() instanceof LightningRodBlock && DCState.getBool(state, LightningRodBlock.POWERED)) {
						machine.getEnergyHandler().generateEnergy(1000);
					}
				}
			}
		}
	}

}
