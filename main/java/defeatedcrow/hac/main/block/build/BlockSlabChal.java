package defeatedcrow.hac.main.block.build;

import defeatedcrow.hac.api.blockstate.DCState;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockSlabChal extends BlockSlabBase {
	public BlockSlabChal() {
		super(Material.GLASS, "dcs_build_slab", 6, true);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"blue",
				"red",
				"white",
				"blue_lit",
				"red_lit",
				"white_lit",
				"wood_lit"
		};
		return name;
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		return DCState.getInt(state, BlockSlabBase.TYPE) > 2 ? 15 : 0;
	}
}
