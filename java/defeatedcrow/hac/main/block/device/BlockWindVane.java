package defeatedcrow.hac.main.block.device;

import defeatedcrow.hac.core.base.DCTileBlock;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWindVane extends DCTileBlock {

	public BlockWindVane(String s) {
		super(Material.GROUND, s, 0);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileWindVane();
	}

}
