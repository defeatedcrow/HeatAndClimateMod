package defeatedcrow.hac.main.block.device;

import java.util.Random;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.base.DCTileBlock;
import defeatedcrow.hac.core.fluid.DCFluidUtil;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.api.MainAPIManager;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCookingStove extends DCTileBlock implements IHeatTile {

	public BlockCookingStove(Material m, String s, int max) {
		super(m, s, max);
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!player.world.isRemote && player != null && hand == EnumHand.MAIN_HAND) {
			TileEntity tile = world.getTileEntity(pos);
			if (!player.isSneaking() && tile instanceof TileCookingStove) {
				boolean flag = false;
				ItemStack held = player.getHeldItem(hand);
				if (!DCUtil.isEmpty(held)
						&& held.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, side)) {
					IFluidHandler cont = held.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, side);
					if (cont != null && cont.drain(1000, false) != null) {
						FluidStack f = cont.drain(1000, false);
						if (MainAPIManager.fuelRegister.isRegistered(f.getFluid())) {
							if (DCFluidUtil.onActivateDCTank(tile, held, world, state, side, player)) {
								flag = true;
							}
						}
					}
				}
				if (!flag) {
					player.openGui(ClimateMain.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
				}
				return true;
			} else {
				this.changePowerState(world, pos);
				world.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.8F, 2.0F);
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileCookingStove();
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		// block側の気候処理は無し
	}

	@Override
	public boolean onClimateChange(World world, BlockPos pos, IBlockState state, IClimate clm) {
		return false;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public DCHeatTier getHeatTier(World world, BlockPos from, BlockPos to) {
		IBlockState state = world.getBlockState(to);
		int meta = this.getMetaFromState(state);
		if ((meta & 3) == 1 && (to.equals(from.down()) || to.equals(from.down(2))))
			return DCHeatTier.SMELTING;
		return DCHeatTier.NORMAL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		int meta = this.getMetaFromState(state) & 3;
		return meta == 1 ? 15 : 0;
	}

	public static void changeLitState(World world, BlockPos pos, boolean f) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == MainInit.fuelStove) {
			int m = DCState.getInt(state, DCState.TYPE4);
			int power = m & 2;
			if (f) {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, 1 + power), 3);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, power), 3);
			}
		}
	}

	public static void changePowerState(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == MainInit.fuelStove) {
			int m = DCState.getInt(state, DCState.TYPE4);
			int lit = m & 1;
			boolean power = (m & 2) == 0;
			if (power) {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, lit + 2), 3);
			} else {
				world.setBlockState(pos, state.withProperty(DCState.TYPE4, lit), 3);
			}
		}
	}

	public static boolean isLit(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != MainInit.fuelStove)
			return false;
		int meta = state.getBlock().getMetaFromState(state) & 1;
		return meta == 1;
	}

	public static boolean isPower(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != MainInit.fuelStove)
			return false;
		int meta = state.getBlock().getMetaFromState(state) & 2;
		return meta == 0;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

}
