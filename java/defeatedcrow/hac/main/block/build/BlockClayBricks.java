package defeatedcrow.hac.main.block.build;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.IThermalInsulationBlock;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockClayBricks extends DCSimpleBlock implements ITexturePath, IThermalInsulationBlock {

	public BlockClayBricks(String s) {
		super(Material.ROCK, s, 15, false);
		this.setTickRandomly(false);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"white",
				"orange",
				"magenta",
				"light_blue",
				"yellow",
				"lime",
				"pink",
				"gray",
				"silver",
				"cyan",
				"purple",
				"blue",
				"brown",
				"green",
				"red",
				"black"
		};
		return name;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta >= 16) {
			meta = 15;
		}
		String s = "blocks/build/bricks_" + EnumDyeColor.byMetadata(meta);
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public int getReductionAmount(World world, BlockPos pos, IBlockState state) {
		int meta = DCState.getInt(state, DCState.TYPE16);
		return meta == 0 ? -1 : 0;
	}
}
