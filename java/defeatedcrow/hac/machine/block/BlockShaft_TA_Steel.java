package defeatedcrow.hac.machine.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockShaft_TA_Steel extends BlockShaft_TA {

	public BlockShaft_TA_Steel(String s) {
		super(s);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileShaft_TA_Steel();
	}

}
