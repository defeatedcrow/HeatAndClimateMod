package defeatedcrow.hac.machine.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockMonitorTemp extends BlockMonitorPanel {

	public BlockMonitorTemp(String s) {
		super(s);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileMonitorTemp();
	}

	@Override
	protected int calcRedstone(TileEntity tile) {
		if (tile != null && tile instanceof TileMonitorBase) {
			float amo = ((TileMonitorBase) tile).getGauge();
			if (amo > 0) {
				return MathHelper.ceil(amo * 15F);
			}
		}
		return 0;
	}

}
