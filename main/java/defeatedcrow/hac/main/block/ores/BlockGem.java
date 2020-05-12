package defeatedcrow.hac.main.block.ores;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.IThermalInsulationBlock;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockGem extends DCSimpleBlock implements ITexturePath, IThermalInsulationBlock {

	public BlockGem(Material m, String s, int max) {
		super(m, s, max, false);
		this.setTickRandomly(false);
		this.setHardness(3.0F);
		this.setResistance(15.0F);
	}

	private static String[] names = {
		"chal_blue",
		"chal_red",
		"chal_white",
		"gypsum",
		"sapphire",
		"salt",
		"marble",
		"schorl",
		"compressed",
		"serpentine",
		"olivine",
		"almandine",
		"bedrock" };

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta >= names.length) {
			meta = names.length - 1;
		}
		String s = "blocks/ores/gemblock_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public int getReductionAmount(World world, BlockPos pos, IBlockState state) {
		int meta = DCState.getInt(state, DCState.TYPE16);
		return meta == 3 ? -1 : 0;
	}

	@Override
	public float getExplosionResistance(World world, BlockPos pos, @Nullable Entity exploder, Explosion explosion) {
		IBlockState state = world.getBlockState(pos);
		if (DCState.getInt(state, DCState.TYPE16) == 12) {
			return 10000F;
		}
		return super.getExplosionResistance(world, pos, exploder, explosion);
	}
}
