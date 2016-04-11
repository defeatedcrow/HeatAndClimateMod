package defeatedcrow.hac.machine.common;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import defeatedcrow.hac.core.base.DCTileBlock;

public class BlockStoveFuel extends DCTileBlock {

	public BlockStoveFuel(Material m, String s, int i) {
		super(m, s, i);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new StoveBase();
	}

}
