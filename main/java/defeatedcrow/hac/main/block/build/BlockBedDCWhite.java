package defeatedcrow.hac.main.block.build;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockBedDCWhite extends BlockBedDC {

	protected static final AxisAlignedBB AABB_HALF = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

	public BlockBedDCWhite(String s) {
		super(s);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileBedDCWhite();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 1;
	}

}
