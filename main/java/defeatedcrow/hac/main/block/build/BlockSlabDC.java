package defeatedcrow.hac.main.block.build;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockSlabDC extends BlockSlabBase {

	public BlockSlabDC() {
		super(Material.GLASS, "dcs_build_slab", 5, true);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = { "gypsum", "glass", "marble", "serpentine", "bedrock", "dirtbrick" };
		return name;
	}

	@Override
	public float getExplosionResistance(World world, BlockPos pos, @Nullable Entity exploder, Explosion explosion) {
		IBlockState state = world.getBlockState(pos);
		if (DCState.getInt(state, DCState.TYPE16) == 4) {
			return 10000F;
		}
		return super.getExplosionResistance(world, pos, exploder, explosion);
	}

}
