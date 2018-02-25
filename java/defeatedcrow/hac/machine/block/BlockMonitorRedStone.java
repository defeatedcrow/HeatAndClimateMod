package defeatedcrow.hac.machine.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMonitorRedStone extends BlockMonitorPanel {

	public BlockMonitorRedStone(String s) {
		super(s);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileMonitorRedStone();
	}

}
