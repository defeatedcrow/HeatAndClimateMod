package defeatedcrow.hac.machine.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import defeatedcrow.hac.core.energy.BlockTorqueBase;

public class BlockCrank_S extends BlockTorqueBase {

	public BlockCrank_S(String s) {
		super(Material.ROCK, s, 0);
		this.setSoundType(SoundType.METAL);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileCrank_S();
	}

}
