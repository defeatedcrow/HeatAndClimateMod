package defeatedcrow.hac.main.block.ores;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.recipe.IClimateSmelting;
import defeatedcrow.hac.api.recipe.RecipeAPI;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;

public class BlockDusts extends DCSimpleBlock implements ITexturePath {

	public BlockDusts(Material m, String s, int max) {
		super(m, s, max);
		this.setTickRandomly(true);
		this.setHardness(1.5F);
		this.setResistance(15.0F);
	}

	@Override
	public void onUpdateClimate(World world, BlockPos pos, IBlockState state) {
		DCHeatTier heat = ClimateAPI.calculator.getHeatTier(world, pos, 2, false);
		DCHumidity hum = ClimateAPI.calculator.getHumidity(world, pos, 1, false);
		DCAirflow air = ClimateAPI.calculator.getAirflow(world, pos, 1, false);
		IClimate c = ClimateAPI.register.getClimateFromParam(heat, hum, air);
		if (hum != DCHumidity.UNDERWATER) {
			ItemStack check = new ItemStack(this, 1, this.getMetaFromState(state));
			IClimateSmelting recipe = RecipeAPI.registerSmelting.getRecipe(c, check);
			if (recipe != null) {
				ItemStack output = recipe.getOutput();
				if (output != null && output.getItem() instanceof ItemBlock) {
					Block ret = ((ItemBlock) output.getItem()).block;
					IBlockState retS = ret.getStateFromMeta(output.getItemDamage());
					if (world.setBlockState(pos, retS, 3)) {
						world.markBlockForUpdate(pos);
						// DCLogger.debugLog("smelting!" + ret.getRegistryName());

						// 効果音
						double d0 = pos.getX();
						double d1 = pos.getY();
						double d2 = pos.getZ();
						world.playSoundEffect(d0 + 0.5D, d1 + 0.5D, d2 + 0.5D, "random.fizz", 0.5F, 2.5F);
					}
				}
			}
		}
		world.scheduleUpdate(pos, this, this.tickRate(world));
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		world.scheduleUpdate(pos, this, this.tickRate(world));
	}

	private static String[] names = {
			"copper",
			"zinc",
			"nickel",
			"silver",
			"brass",
			"steel",
			"nickelsilver",
			"magnet" };

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta >= names.length)
			meta = names.length - 1;
		String s = "blocks/ores/dustblock_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}
}
