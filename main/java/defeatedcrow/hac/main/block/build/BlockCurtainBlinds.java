package defeatedcrow.hac.main.block.build;

import java.util.List;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.BlockContainerDC;
import defeatedcrow.hac.main.util.DCName;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCurtainBlinds extends BlockContainerDC {

	protected static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0D, 0D, 0.875D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0D, 0D, 0D, 1D, 1D, 0.125D);
	protected static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.875D, 0D, 0D, 1D, 1D, 1D);
	protected static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0D, 0D, 0D, 0.125D, 1D, 1D);

	// Type上限
	public final int maxMeta;

	public BlockCurtainBlinds(String s) {
		super(Material.CLOTH, s);
		this.setHardness(0.2F);
		this.setResistance(5.0F);
		this.setSoundType(SoundType.CLOTH);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.FACING, EnumFacing.SOUTH)
				.withProperty(DCState.POWERED, false)
				.withProperty(DCState.DOUBLE, false));
		this.maxMeta = 0;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
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
			return AABB_NORTH;

		}
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		IBlockState target = world.getBlockState(pos);
		if (target != null && target.getBlock() == this) {
			int up = 0;
			int down = 0;
			for (int i = 1; pos.getY() + i < 255; i++) {
				IBlockState st = world.getBlockState(pos.up(i));
				if (st.getBlock() == this && DCState.getFace(st, DCState.FACING) == DCState
						.getFace(target, DCState.FACING)) {
					up = i;
				} else {
					break;
				}
			}
			for (int i = 1; pos.getY() - i > 0; i++) {
				IBlockState st = world.getBlockState(pos.down(i));
				if (st.getBlock() == this && DCState.getFace(st, DCState.FACING) == DCState
						.getFace(target, DCState.FACING)) {
					down = i;
				} else {
					break;
				}
			}
			boolean p = DCState.getBool(state, DCState.POWERED);
			for (int j = -down; j <= up; j++) {
				target = world.getBlockState(pos.up(j));
				target = target.withProperty(DCState.POWERED, !p);
				world.setBlockState(pos.up(j), target);
			}
			world.markBlockRangeForRenderUpdate(pos.down(down), pos.up(up));
			world.playSound((EntityPlayer) null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos
					.getZ() + 0.5D, SoundEvents.BLOCK_CLOTH_PLACE, SoundCategory.BLOCKS, 0.5F, world.rand
							.nextFloat() * 0.1F + 0.9F);

		}
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean getUseNeighborBrightness(IBlockState state) {
		return true;
	}

	@Override
	public int getLightOpacity(IBlockState state) {
		boolean flag = DCState.getBool(state, DCState.POWERED);
		return flag ? 1 : 255;
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}

	// 接してる面側が水だったら、その接してる水の側面を描画しない
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		boolean b = world.getBlockState(pos.up()).getMaterial() == Material.AIR;
		if (!b && world.getBlockState(pos.offset(face)).getMaterial() == Material.WATER)
			return true;
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getPlaceState(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getPlaceState(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		if (placer == null)
			return state;
		state = state.withProperty(DCState.FACING, placer.getHorizontalFacing().getOpposite());
		boolean b = world.getBlockState(pos.up()).getBlock() == this;
		state = state.withProperty(DCState.DOUBLE, b);
		return state;
	}

	// state関連
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int i = meta & 3;
		boolean b2 = i > 1;
		boolean b1 = ((i & 1) != 0);
		int f = 5 - (meta >> 2);
		IBlockState state = this.getDefaultState().withProperty(DCState.DOUBLE, b1).withProperty(DCState.POWERED, b2);
		state = state.withProperty(DCState.FACING, EnumFacing.getFront(f));
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		boolean b1 = DCState.getBool(state, DCState.DOUBLE);
		boolean b2 = DCState.getBool(state, DCState.POWERED);
		int i = b1 ? 1 : 0;
		int f = 0;

		if (b2) {
			i += 2;
		}

		f = 5 - state.getValue(DCState.FACING).getIndex();
		f = f << 2;
		return i + f;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		boolean b = world.getBlockState(pos.up()).getBlock() == this;
		state = state.withProperty(DCState.DOUBLE, b);
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.FACING,
				DCState.DOUBLE,
				DCState.POWERED
		});
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileWindowBlinds();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		tooltip.add(TextFormatting.AQUA.toString() + DCName.COLOR_CHANGE_TARGET.getLocalizedName());
	}

}
