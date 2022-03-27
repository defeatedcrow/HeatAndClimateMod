package defeatedcrow.hac.main.block.build;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockSmallTable extends BlockTableBase {

	protected static final AxisAlignedBB AABB_HALF = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.5D, 0.875D);

	public BlockSmallTable(String s, boolean full) {
		super(s, false);
		setHalf();
		this.setSoundType(SoundType.WOOD);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB_HALF;
	}

}
