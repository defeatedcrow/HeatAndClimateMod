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
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.client.particle.ParticleCloudDC;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
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

public class DCFluidBlockBase extends BlockFluidClassic implements ITexturePath, IHeatTile, IHumidityTile,
		IAirflowTile {

	protected final String name;
	protected boolean canDisplace = false;

	public DCFluidBlockBase(Fluid fluid, String n) {
		super(fluid, Material.WATER);
		name = n;
	}

	public DCFluidBlockBase(Fluid fluid, String n, Material mat) {
		super(fluid, mat);
		name = n;
	}

	public DCFluidBlockBase setCanDisplace() {
		canDisplace = true;
		return this;
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
			int c = ClimateMain.proxy.getParticleCount();
			if (ClimateMain.proxy.getParticleCount() > 0 && rand.nextInt(c) == 0) {
				int temp = this.stack.getFluid().getTemperature();
				if (temp > 320) {
					double x = pos.getX() + 0.5D + rand.nextDouble() * 0.25D;
					double y = pos.getY() + 0.85D + rand.nextDouble() * 0.25D;
					double z = pos.getZ() + 0.5D + rand.nextDouble() * 0.25D;
					double dx = 0D;
					double dy = 0D;
					double dz = 0D;
					Particle cloud = new ParticleCloudDC.Factory().createParticle(0, world, x, y, z, dx, dy, dz, null);
					FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
				}
			}
		}
	}

	public static DCHeatTier getFluidHeatTier(int temp) {
		return DCHeatTier.getTypeByTemperature(temp);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity != null && entity instanceof EntityLivingBase && !world.isRemote) {
			EntityLivingBase living = (EntityLivingBase) entity;
			if (this.getFluid() != null && this.getFluid() == MainInit.hotSpring) {
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
		if (state.getBlock() instanceof BlockLiquid) {
			if (state.getMaterial() == Material.WATER) {
				if (this.getFluid() == MainInit.nitrogen)
					return true;
			}
		} else if (state.getMaterial().isLiquid())
			return state.getBlock().getMetaFromState(state) > 7;
		return super.canDisplace(world, pos);
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() instanceof BlockLiquid) {
			if (state.getMaterial() == Material.WATER) {
				if (this.getFluid() == MainInit.nitrogen)
					return true;
			}
		}
		if (state.getMaterial().isLiquid())
			return state.getBlock().getMetaFromState(state) > 7;
		return super.displaceIfPossible(world, pos);
	}

	// nitrogen
	@Override
	protected void flowIntoBlock(World world, BlockPos pos, int meta) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() instanceof BlockLiquid) {
			if (state.getMaterial() == Material.WATER) {
				if (this.getFluid() == MainInit.nitrogen) {
					world.setBlockState(pos, Blocks.ICE.getDefaultState(), 3);
					return;
				}
			}
		}
		super.flowIntoBlock(world, pos, meta);
	}
}
