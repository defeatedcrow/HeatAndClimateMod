package defeatedcrow.hac.main.block.fluid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IAirflowTile;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.api.climate.IHumidityTile;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.client.particle.ParticleCloudDC;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DCFluidBlockBase extends BlockFluidClassic
		implements ITexturePath, IHeatTile, IHumidityTile, IAirflowTile {

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
		list.add(b + "black_liquor_still");
		list.add(b + "hotspring_still");
		list.add(b + "vegetable_still");
		list.add(b + "stock_still");
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
			return getFluidHeatTier(temp);
		}
		return DCHeatTier.NORMAL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state != null && state.getBlock() == this && this.stack.getFluid() != null) {
			int temp = this.stack.getFluid().getTemperature();
			if (temp > 320 && rand.nextFloat() < 0.25F) {
				double x = pos.getX() + 0.5D + rand.nextDouble() * 0.25D;
				double y = pos.getY() + 0.85D + rand.nextDouble() * 0.25D;
				double z = pos.getZ() + 0.5D + rand.nextDouble() * 0.25D;
				double dx = rand.nextDouble() * 0.05D - 0.025D;
				double dy = 0D;
				double dz = rand.nextDouble() * 0.05D - 0.025D;
				Particle cloud = new ParticleCloudDC.Factory().createParticle(0, world, x, y, z, dx, dy, dz, null);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
			}
		}
	}

	public static DCHeatTier getFluidHeatTier(int temp) {
		if (temp >= 7300)
			return DCHeatTier.INFERNO;
		else if (temp >= 3300)
			return DCHeatTier.UHT;
		else if (temp >= 1800)
			return DCHeatTier.SMELTING;
		else if (temp >= 1000)
			return DCHeatTier.KILN;
		else if (temp >= 500)
			return DCHeatTier.OVEN;
		else if (temp >= 320)
			return DCHeatTier.HOT;
		else if (temp >= 290)
			return DCHeatTier.WARM;
		else if (temp > 270)
			return DCHeatTier.NORMAL;
		else if (temp > 250)
			return DCHeatTier.COOL;
		else if (temp > 200)
			return DCHeatTier.COLD;
		else if (temp > 70)
			return DCHeatTier.FROSTBITE;
		else
			return DCHeatTier.ABSOLUTE;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity != null && entity instanceof EntityLivingBase && !world.isRemote) {
			EntityLivingBase living = (EntityLivingBase) entity;
			if (this.getFluid() != null && this.getFluid() == FoodInit.hotSpring) {
				Collection<PotionEffect> effs = living.getActivePotionEffects();
				if (effs.isEmpty())
					return;

				List<Potion> removes = new ArrayList<Potion>();
				for (PotionEffect eff : effs) {
					if (eff != null && eff.getPotion() != null && eff.getPotion().isBadEffect()) {
						removes.add(eff.getPotion());
					}
				}
				for (Potion eff : removes) {
					living.removePotionEffect(eff);
				}
			}
		}
	}

	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getMaterial().isLiquid())
			return state.getBlock().getMetaFromState(state) > 7;
		return super.canDisplace(world, pos);
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getMaterial().isLiquid())
			return state.getBlock().getMetaFromState(state) > 7;
		return super.displaceIfPossible(world, pos);
	}

}
