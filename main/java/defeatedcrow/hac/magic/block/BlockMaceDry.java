package defeatedcrow.hac.magic.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMaceDry extends BlockMace {

	public BlockMaceDry(String s) {
		super(s);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileMaceDry();
	}

}
