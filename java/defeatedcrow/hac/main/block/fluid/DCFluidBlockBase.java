package defeatedcrow.hac.main.block.fluid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IAirflowTile;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.api.climate.IHumidityTile;
import defeatedcrow.hac.core.base.ITexturePath;

public class DCFluidBlockBase extends BlockFluidClassic implements ITexturePath, IHeatTile, IHumidityTile, IAirflowTile {

	protected final String name;

	public DCFluidBlockBase(Fluid fluid, String n) {
		super(fluid, Material.WATER);
		name = n;
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/fluid/";
		list.add(b + "blacktea_still");
		list.add(b + "greentea_still");
		list.add(b + "coffee_still");
		list.add(b + "seedoil_still");
		list.add(b + "cream_still");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		String b = "dcs_climate:blocks/fluid/";
		return b + name;
	}

	@Override
	public DCAirflow getAirflow(World world, BlockPos to, BlockPos from) {
		return DCAirflow.TIGHT;
	}

	@Override
	public DCHumidity getHumdiity(World world, BlockPos to, BlockPos from) {
		return DCHumidity.UNDERWATER;
	}

	@Override
	public DCHeatTier getHeatTier(World world, BlockPos to, BlockPos from) {
		if (this.stack.getFluid() != null) {
			int temp = this.stack.getFluid().getTemperature();
			if (temp >= 7300) {
				return DCHeatTier.INFERNO;
			} else if (temp >= 3300) {
				return DCHeatTier.UHT;
			} else if (temp >= 1800) {
				return DCHeatTier.SMELTING;
			} else if (temp >= 1000) {
				return DCHeatTier.KILN;
			} else if (temp >= 520) {
				return DCHeatTier.OVEN;
			} else if (temp >= 350) {
				return DCHeatTier.HOT;
			} else if (temp > 290) {
				return DCHeatTier.NORMAL;
			} else if (temp > 230) {
				return DCHeatTier.COLD;
			} else if (temp > 200) {
				return DCHeatTier.FROSTBITE;
			} else {
				return DCHeatTier.ABSOLUTE;
			}
		}
		return DCHeatTier.NORMAL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state != null && state.getBlock() == this && this.stack.getFluid() != null) {
			int temp = this.stack.getFluid().getTemperature();
			if (temp > 350 && rand.nextFloat() < 0.25F) {
				double x = (double) pos.getX() + 0.5D + rand.nextDouble() * 0.25D;
				double y = (double) pos.getY() + 0.7D + rand.nextDouble() * 0.25D;
				double z = (double) pos.getZ() + 0.5D + rand.nextDouble() * 0.25D;
				double dx = rand.nextDouble() * 0.05D;
				double dy = rand.nextDouble() * 0.05D;
				double dz = rand.nextDouble() * 0.05D;

				world.spawnParticle(EnumParticleTypes.CLOUD, x, y, z, 0.0D, dy, 0.0D, new int[0]);
			}
		}
	}

}
