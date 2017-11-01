package defeatedcrow.hac.machine.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.base.DCTileBlock;
import defeatedcrow.hac.core.fluid.DCFluidUtil;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.api.MainAPIManager;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
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

public class BlockGasBurner extends DCTileBlock implements IHeatTile {

	public BlockGasBurner(Material m, String s, int max) {
		super(Material.ROCK, s, 3);
		this.setHardness(1.5F);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItemIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!player.worldObj.isRemote && player != null) {
			ItemStack heldItem = player.getHeldItem(hand);
			TileEntity tile = world.getTileEntity(pos);
			if (!player.isSneaking() && tile instanceof TileGasBurner && hand == EnumHand.MAIN_HAND) {
				boolean flag = false;
				if (heldItem != null && heldItem.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side)) {
					IFluidHandler cont = heldItem.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
					if (cont != null && cont.drain(1000, false) != null) {
						FluidStack f = cont.drain(1000, false);
						if (MainAPIManager.fuelRegister.isRegistered(f.getFluid().getName())) {
							if (DCFluidUtil.onActivateDCTank(tile, heldItem, world, state, side, player)) {
								world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8F,
										2.0F);
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
		return new TileGasBurner();
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
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
		int m = DCState.getInt(state, DCState.TYPE4);
		if (m >= 0) {
			if ((m & 3) == 1 && (to.equals(from.down()) || to.equals(from.down(2))))
				return DCHeatTier.UHT;
		}
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
		int m = DCState.getInt(state, DCState.TYPE4);
		if (m >= 0) {
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
		int m = DCState.getInt(state, DCState.TYPE4);
		if (m >= 0) {
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
		int meta = DCState.getInt(state, DCState.TYPE4);
		return meta == 1 || meta == 3;
	}

	public static boolean isPower(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		int meta = DCState.getInt(state, DCState.TYPE4);
		return meta == 0 || meta == 1;
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
