package defeatedcrow.hac.machine.block;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.BlockContainerDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * トルク系装置のBlockクラスに似ているが、使い方が違う
 */
public class BlockFaucet_SUS extends BlockContainerDC {

	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.3125D, 0.25D, 0.5D, 0.6875D, 0.75D, 1.0D);
	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.3125D, 0.25D, 0.0D, 0.6875D, 0.75D, 0.5D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.5D, 0.25D, 0.3125D, 1.0D, 0.75D, 0.6875D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.0D, 0.25D, 0.3125D, 0.5D, 0.75D, 0.6875D);
	protected static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 1.0D, 0.6875D);

	public BlockFaucet_SUS(String s) {
		super(Material.ROCK, s);
		this.setHardness(2.0F);
		this.setResistance(15.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.SIDE, EnumSide.DOWN)
				.withProperty(DCState.POWERED, false));
		this.fullBlock = false;
		this.lightOpacity = 0;
	}

	/* GUIは持たない。 */
	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && hand == EnumHand.MAIN_HAND) {
			if (player != null) {
				ItemStack heldItem = player.getHeldItem(hand);
				if (!DCUtil.isEmpty(heldItem)) {
					if (onFill(heldItem, world, player)) {
						world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8F, 2.0F);
						return true;
					}
				}
			}
			boolean power = DCState.getBool(state, DCState.POWERED);
			IBlockState state2 = state.withProperty(DCState.POWERED, !power);
			world.setBlockState(pos, state2, 2);
			world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.8F, 2.0F);
		}
		return true;
	}

	public static boolean onFill(ItemStack item, World world,
			EntityPlayer player) {
		if (!DCUtil.isEmpty(item) && item
				.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
			ItemStack copy = item.copy();
			if (item.getCount() > 1)
				copy.setCount(1);
			IFluidHandlerItem dummy = copy.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
			FluidStack fluid = new FluidStack(FluidRegistry.WATER, 1000);

			// dummyを使った検証
			if (dummy != null && dummy
					.getTankProperties() != null) {
				int max = dummy.getTankProperties()[0].getCapacity();
				ItemStack ret = ItemStack.EMPTY;
				boolean success = false;
				if (dummy.fill(fluid, true) > 0) {
					ret = dummy.getContainer();
					success = true;
				}

				if (success) {
					if (!player.capabilities.isCreativeMode) {
						DCUtil.reduceStackSize(item, 1);
					}
					player.inventory.markDirty();
					if (!DCUtil.isEmpty(ret) && !world.isRemote) {
						EntityItem drop = new EntityItem(world, player.posX, player.posY + 0.25D, player.posZ, ret);
						world.spawnEntity(drop);
					}
					return true;
				}
			}
		}
		return false;
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
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(this, 1, 0));
		return list;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing face = DCState.getSide(state, DCState.SIDE).face;
		switch (face) {
		case EAST:
			return AABB_EAST;
		case NORTH:
			return AABB_NORTH;
		case SOUTH:
			return AABB_SOUTH;
		case WEST:
			return AABB_WEST;
		default:
			return AABB_DOWN;

		}
	}

	// 設置・破壊処理
	@Override
	public IBlockState getPlaceState(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getPlaceState(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		if (facing == EnumFacing.UP) {
			facing = EnumFacing.DOWN;
		}
		state = state.withProperty(DCState.SIDE, EnumSide.fromFacing(facing.getOpposite()));
		return state;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	// state関連
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int m = meta & 7;
		IBlockState state = this.getDefaultState().withProperty(DCState.SIDE, EnumSide.fromIndex(m))
				.withProperty(DCState.POWERED, Boolean.valueOf((meta & 8) > 0));
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		int f = 0;
		int i = 0;

		f = state.getValue(DCState.SIDE).index;
		i = state.getValue(DCState.POWERED) ? 8 : 0;
		return i + f;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.SIDE,
				DCState.POWERED
		});

	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileFaucet_SUS();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Requirement ===");
			tooltip.add(DCName.NON_POWERED.getLocalizedName());
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Output ===");
			tooltip.add("WATER: 1000 mB/s");
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add(DCName.RIGHT_CLICK.getLocalizedName() + ": " + DCName.TURN_OFF.getLocalizedName());
			tooltip.add(TextFormatting.AQUA.toString() + TextFormatting.BOLD.toString() + DCName.COLOR_CHANGE_TARGET
					.getLocalizedName());
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}
}
