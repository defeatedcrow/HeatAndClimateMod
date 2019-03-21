package defeatedcrow.hac.magic.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.client.particle.ParticleBlink;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockColorCube extends DCSimpleBlock implements ITexturePath {

	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
	protected static final AxisAlignedBB AABB2 = new AxisAlignedBB(0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);

	public BlockColorCube(Material m, String s) {
		super(m, s, 9, false);
		this.setTickRandomly(true);
		this.setHardness(1.0F);
		this.setResistance(15.0F);
	}

	private static String[] names = {
			"u1",
			"g1",
			"r1",
			"b1",
			"w1",
			"u2",
			"g2",
			"r2",
			"b2",
			"w2"
	};

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta >= names.length)
			meta = names.length - 1;
		String s = "blocks/magic/color_crystal_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return DCState.getInt(state, DCState.TYPE16) > 4 ? AABB2 : AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return DCState.getInt(state, DCState.TYPE16) > 4 ? AABB2 : AABB;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		boolean b = world.getBlockState(pos.up()).getMaterial() == Material.AIR;
		if (!b && world.getBlockState(pos.offset(face)).getMaterial() == Material.WATER)
			return true;
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		int c = ClimateMain.proxy.getParticleCount();
		if (DCState.getInt(state, DCState.TYPE16) > 4 || suitablePlace(world, pos, state))
			if (ClimateMain.proxy.getParticleCount() > 0) {
				double x = pos.getX() + rand.nextDouble();
				double y = pos.getY() + 0.25D + rand.nextDouble();
				double z = pos.getZ() + rand.nextDouble();

				Particle p = new ParticleBlink.Factory()
						.createParticle(0, world, x, y, z, 0.0D, 0.0D, 0.0D, new int[0]);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(p);

			}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
		if (stack.getItemDamage() < 5) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(I18n.format("dcs.tip.color_cube" + stack.getItemDamage()));
		}
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		if (!world.isRemote && suitablePlace(world, pos, state)) {
			int st = DCState.getInt(state, DCState.TYPE16);
			if (world.rand.nextInt(5) == 0 && st != -1 && st < 5) {
				IBlockState retS = this.getStateFromMeta(st + 5);
				if (world.setBlockState(pos, retS, 2)) {
					world.notifyNeighborsOfStateChange(pos, this, true);

					// 効果音
					world.playSound(null, pos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.BLOCKS, 0.8F, 2.0F);
				}
			}
		}
	}

	private boolean suitablePlace(World world, BlockPos pos, IBlockState state) {
		int num = DCState.getInt(state, DCState.TYPE16);
		if (num == 0) {
			for (int y = 1; y < 5; y++) {
				if (world.getBlockState(pos.up(y)).getMaterial() != Material.WATER) {
					return false;
				}
			}
			return true;
		}
		if (num == 1) {
			for (int x = -1; x <= 1; x++) {
				for (int z = -1; z <= 1; z++) {
					if (x == 0 && z == 0) {
						continue;
					} else {
						IBlockState tb = world.getBlockState(pos.add(x, 0, z));
						if (tb.getMaterial() != Material.PLANTS && tb.getMaterial() != Material.LEAVES) {
							return false;
						}
					}
				}
			}
			return true;
		}
		if (num == 2) {
			if (ClimateAPI.calculator.getAverageTemp(world, pos).getTier() >= DCHeatTier.SMELTING.getTier())
				return true;
		}
		if (num == 3) {
			if (pos.getY() < 11 && world.getLight(pos) == 0)
				return true;
		}
		if (num == 4) {
			if (world.canBlockSeeSky(pos) && pos.getY() > 135)
				return true;
		}
		return false;
	}
}
