package defeatedcrow.hac.main.block.build;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.BlockContainerDC;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDoorHikido extends BlockContainerDC {

	protected static final AxisAlignedBB SOUTH_AABB_H = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.5D);
	protected static final AxisAlignedBB NORTH_AABB_H = new AxisAlignedBB(0.0D, 0.0D, 0.5D, 1.0D, 1.0D, 0.625D);
	protected static final AxisAlignedBB WEST_AABB_H = new AxisAlignedBB(0.5D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
	protected static final AxisAlignedBB EAST_AABB_H = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D);

	public BlockDoorHikido(String s, Material material) {
		super(Material.WOOD, s);
		this.setSoundType(SoundType.CLOTH);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.FACING, EnumFacing.NORTH)
				.withProperty(DCState.FLAG, false)
				.withProperty(DCState.POWERED, false));
	}

	// foundation data

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	public boolean isPassable(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		return DCState.getBool(state, DCState.POWERED);
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return DCState.getBool(state, DCState.FLAG) ? Items.AIR : super.getItemDropped(state, rand, fortune);
	}

	@Override
	public IBlockState getPlaceState(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getPlaceState(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		if (placer == null)
			return state;

		EnumFacing face = placer.isSneaking() ? placer.getHorizontalFacing().getOpposite() : placer
				.getHorizontalFacing();
		state = state.withProperty(DCState.FACING, face);
		return state;
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		EnumFacing enumfacing = DCState.getFace(state, DCState.FACING);
		boolean half = DCState.getBool(state, DCState.FLAG);

		switch (enumfacing) {
		case EAST:
		default:
			return EAST_AABB_H;
		case SOUTH:
			return SOUTH_AABB_H;
		case WEST:
			return WEST_AABB_H;
		case NORTH:
			return NORTH_AABB_H;
		}
	}

	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState) {
		if (!isActualState) {
			state = state.getActualState(worldIn, pos);
		}

		EnumFacing enumfacing = DCState.getFace(state, DCState.FACING);
		boolean half = DCState.getBool(state, DCState.FLAG);
		boolean pow = DCState.getBool(state, DCState.POWERED);

		if (!pow) {
			switch (enumfacing) {
			case EAST:
			default:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, EAST_AABB_H);
				break;
			case SOUTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, SOUTH_AABB_H);
				break;
			case WEST:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, WEST_AABB_H);
				break;
			case NORTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_AABB_H);
				break;
			}
		}
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.CLOTH;
	}

	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		if (pos.getY() >= worldIn.getHeight() - 1) {
			return false;
		} else {
			IBlockState state = worldIn.getBlockState(pos.down());
			return (state.isTopSolid() || state.getBlockFaceShape(worldIn, pos
					.down(), EnumFacing.UP) == BlockFaceShape.SOLID) && super.canPlaceBlockAt(worldIn, pos);
		}
	}

	// 動作
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		BlockPos p = pos;
		if (DCState.getBool(state, DCState.FLAG)) {
			p = pos.down();
		}
		IBlockState click = world.getBlockState(p);
		if (checkBlock(world, p, p.up())) {
			click = click.cycleProperty(DCState.POWERED);
			world.setBlockState(p, click, 10);
			world.markBlockRangeForRenderUpdate(p, p.up());
			world.playSound((EntityPlayer) null, p, SoundEvents.BLOCK_CLOTH_BREAK, SoundCategory.BLOCKS, 0.5F, world.rand
					.nextFloat() * 0.25F + 1.2F);
			return true;
		} else {
			return false;
		}
	}

	// 2ブロック合体管理
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(world, pos, state, placer, stack);
		IBlockState upper = MainInit.doorHikido.getDefaultState().withProperty(DCState.FLAG, Boolean.valueOf(true));
		world.setBlockState(pos.up(), upper);
	}

	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {

		if (DCState.getBool(state, DCState.FLAG) == true) {
			BlockPos down = pos.down();
			IBlockState under = world.getBlockState(down);

			if (under == null || under.getBlock() != this) {
				world.setBlockToAir(pos);
			} else if (block != this) {
				under.neighborChanged(world, down, block, fromPos);
			} else if (checkBlock(world, down, pos)) {
				boolean pow = DCState.getBool(under, DCState.POWERED);
				if (DCState.getBool(state, DCState.POWERED) != pow) {
					IBlockState next = state.withProperty(DCState.POWERED, Boolean.valueOf(pow));
					world.setBlockState(pos, next, 2);
				}
			}

		} else {

			BlockPos up = pos.up();
			IBlockState upper = world.getBlockState(up);

			if (!checkBlock(world, pos, up)) {
				world.setBlockToAir(pos);
			}

			if (!world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP)) {
				world.setBlockToAir(pos);
				if (upper != null && upper.getBlock() == this) {
					world.setBlockToAir(up);
				}
			}

			boolean pow = world.isBlockPowered(pos) || world.isBlockPowered(up);
			if (block != this && (pow || block.getDefaultState().canProvidePower())) {
				if (pow != ((Boolean) state.getValue(DCState.POWERED)).booleanValue()) {
					IBlockState next = state.withProperty(DCState.POWERED, Boolean.valueOf(pow));
					world.setBlockState(pos, next, 2);
					world.markBlockRangeForRenderUpdate(pos, pos.up());
					world.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_CLOTH_BREAK, SoundCategory.BLOCKS, 0.5F, world.rand
							.nextFloat() * 0.25F + 1.2F);
				}
			}
		}
	}

	public boolean checkBlock(World world, BlockPos under, BlockPos upper) {
		IBlockState upperState = world.getBlockState(upper);
		IBlockState underState = world.getBlockState(under);
		if (underState == null || underState.getBlock() != this || DCState.getBool(underState, DCState.FLAG)) {
			return false;
		}
		if (upperState == null || upperState.getBlock() != this || !DCState.getBool(upperState, DCState.FLAG)) {
			return false;
		}
		return true;
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
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	// state関連
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int i = meta & 3;
		boolean b2 = i > 1;
		boolean b1 = ((i & 1) != 0);
		int f = 5 - (meta >> 2);
		IBlockState state = this.getDefaultState().withProperty(DCState.FLAG, b1).withProperty(DCState.POWERED, b2);
		state = state.withProperty(DCState.FACING, EnumFacing.getFront(f));
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		boolean b1 = DCState.getBool(state, DCState.FLAG);
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
		if (DCState.getBool(state, DCState.FLAG)) {
			IBlockState under = world.getBlockState(pos.down());

			if (under.getBlock() == this) {
				state = state.withProperty(DCState.FACING, DCState.getFace(under, DCState.FACING))
						.withProperty(DCState.POWERED, DCState.getBool(under, DCState.POWERED));
			}
		}

		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.FACING,
				DCState.FLAG,
				DCState.POWERED
		});
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileDoorHikido();
	}

}
