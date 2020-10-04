package defeatedcrow.hac.main.api.brewing;

import defeatedcrow.hac.api.cultivate.IClimateCrop;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public enum EnumHabitat {
	SOIL,
	FLOWER,
	CROP,
	ANIMAL,
	WATER;

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}

	public String localize() {
		return I18n.format("dcs.tip." + this.name().toLowerCase());
	}

	public static EnumHabitat getHabitat(World world, BlockPos pos) {
		if (world != null && pos != null) {
			IBlockState state = world.getBlockState(pos);
			if (state.getBlock() instanceof BlockLiquid && state.getMaterial() == Material.WATER) {
				return WATER;
			}
			if (state.getBlock() instanceof BlockDirt || state.getBlock() instanceof BlockGrass) {
				return SOIL;
			}
			if (state.getMaterial() == Material.PLANTS && state.getBlock().getRegistryName().toString()
					.contains("flower")) {
				return FLOWER;
			}
			if (state.getBlock() instanceof IClimateCrop || state.getBlock() instanceof IGrowable) {
				return CROP;
			}
		}
		return null;
	}
}
