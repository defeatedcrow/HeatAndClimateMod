package defeatedcrow.hac.main.block.device;

import defeatedcrow.hac.core.base.DCTileBlock;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockThermometer extends DCTileBlock {

	public BlockThermometer(String s) {
		super(Material.GLASS, s, 0);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileThermometer();
	}

}
