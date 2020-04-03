package defeatedcrow.hac.magic.block;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatTile;
import net.minecraft.block.BlockFire;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockInfernalFlame extends BlockFire implements IHeatTile {

	public BlockInfernalFlame(String s) {
		super();
	}

	@Override
	public DCHeatTier getHeatTier(World world, BlockPos targrt, BlockPos thisTile) {
		return DCHeatTier.INFERNO;
	}

}
