package defeatedcrow.hac.main.block.device;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.ClimateSupplier;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFirestand extends BlockNormalChamber {

	public BlockFirestand(String s) {
		super(Material.CLAY, s, 3);
		this.setTickRandomly(true);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileFirestand();
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return side == EnumFacing.DOWN;
	}

	@Override
	public int tickRate(World worldIn) {
		return 20;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (isLit(world, pos)) {
			boolean ext = false;
			if (world.isRaining()) {
				ext = true;
			} else {
				ClimateSupplier sup = new ClimateSupplier(world, pos);
				if (sup.get().getHumidity() == DCHumidity.UNDERWATER) {
					ext = true;
				} else if (sup.get().getHumidity() == DCHumidity.WET) {
					ext = world.rand.nextInt(4 + sup.get().getAirflow().getID() * 3) == 0;
				}
			}

			if (ext) {
				changeLitState(world, pos, false);
				world.playSound(pos.getX() + 0.5F, pos.getY() + 0.5F, pos
						.getZ() + 0.5F, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.8F, 1.0F, false);
			}
		}
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (isLit(world, pos)) {
			if (player != null && hand == EnumHand.MAIN_HAND) {
				if (!world.isRemote)
					changeLitState(world, pos, false);
				player.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 0.8F, 1.0F);
				return true;
			}
		}
		return false;
	}

	@Override
	public DCHeatTier getHeatTier(World world, BlockPos from, BlockPos to) {
		IBlockState state = world.getBlockState(to);
		int meta = DCState.getInt(state, DCState.TYPE4);
		if (meta == 1) {
			return DCHeatTier.KILN;
		}
		return DCHeatTier.NORMAL;
	}

	public static void changeLitState(World world, BlockPos pos, boolean f) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == MainInit.firestand) {
			if (f) {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 1), 3);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 2), 3);
			}
		}
	}

	public static boolean isLit(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != MainInit.firestand)
			return false;
		int meta = DCState.getInt(state, DCState.TYPE4);
		return meta == 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state != null && isLit(world, pos)) {
			int c = ClimateMain.proxy.getParticleCount();
			if (ClimateMain.proxy.getParticleCount() > 0 && rand.nextInt(c) == 0) {
				double x = pos.getX() + 0.5D + rand.nextDouble() * 0.15D;
				double y = pos.getY() + 0.75D + rand.nextDouble() * 0.15D;
				double z = pos.getZ() + 0.5D + rand.nextDouble() * 0.15D;

				world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(I18n.format("dcs.tip.firestand1"));
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
			tooltip.add(DCName.HEAT.getLocalizedName() + ": " + TextFormatting.RED.toString() + "KILN");
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(I18n.format("dcs.tip.firestand2"));
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
		tooltip.add(TextFormatting.BOLD.toString() + "Tier 1");
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return false;
	}

	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		return 0;
	}

	public static void changePowerState(World world, BlockPos pos) {
		// なにもしない
	}

	@Override
	public void onNeighborChange(IBlockState state, World world, BlockPos pos, Block block, BlockPos from) {
		// なにもしない
	}

}
