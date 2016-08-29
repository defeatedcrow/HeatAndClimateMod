package defeatedcrow.hac.main.block.device;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state != null && BlockShitirin.isLit(world, pos)) {
			double x = (double) pos.getX() + 0.5D + rand.nextDouble() * 0.15D;
			double y = (double) pos.getY() + 0.75D + rand.nextDouble() * 0.15D;
			double z = (double) pos.getZ() + 0.5D + rand.nextDouble() * 0.15D;

			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}

}
