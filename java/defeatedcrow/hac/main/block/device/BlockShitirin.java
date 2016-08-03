package defeatedcrow.hac.main.block.device;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.main.MainInit;

public class BlockShitirin extends BlockNormalChamber {

	public BlockShitirin(Material m, String s, int max) {
		super(m, s, max);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileShitirin();
	}

	public static void changeLitState(World world, BlockPos pos, boolean f) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == MainInit.shitirin) {
			if (f) {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 1), 3);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 0), 3);
			}
		}
	}

	public static boolean isLit(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != MainInit.shitirin)
			return false;
		int meta = state.getBlock().getMetaFromState(state) & 3;
		return meta == 1;
	}

}
