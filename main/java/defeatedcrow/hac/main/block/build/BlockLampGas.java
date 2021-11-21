package defeatedcrow.hac.main.block.build;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLampGas extends BlockLampCarbide {

	public BlockLampGas(String s) {
		super(s);
		this.setHardness(0.2F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileLampGas();
	}

}
