package defeatedcrow.hac.main.block.device;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockShitirin extends BlockNormalChamber {

	public BlockShitirin(Material m, String s, int max) {
		super(m, s, max);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileShitirin();
	}

}
