package defeatedcrow.hac.machine.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockShaft_TB_Steel extends BlockShaft_TB {

	public BlockShaft_TB_Steel(String s) {
		super(s);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileShaft_TB_Steel();
	}

}
