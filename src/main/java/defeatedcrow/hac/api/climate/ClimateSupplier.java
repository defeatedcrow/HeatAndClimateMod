package defeatedcrow.hac.api.climate;

import java.util.function.Supplier;

import defeatedcrow.hac.api.ClimateAPI;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class ClimateSupplier implements Supplier<IClimate> {
	private IClimate cached;
	private Level world;
	private BlockPos pos;

	public ClimateSupplier(Level world, BlockPos pos) {
		this.world = world;
		this.pos = pos;
	}

	@Override
	public IClimate get() {
		if (cached == null)
			cached = ClimateAPI.calculator.getClimate(world, pos);
		return cached;
	}
}
