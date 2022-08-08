package defeatedcrow.hac.main.block.device;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.base.BlockDC;
import defeatedcrow.hac.core.base.EnumStateType;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockIgniter extends BlockDC {

	protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 0.75D, 0.75D);

	public BlockIgniter(String s) {
		super(Material.CLAY, s);
		this.setHardness(0.1F);
		this.setResistance(15.0F);
		this.setSoundType(SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(DCState.FACING, EnumFacing.SOUTH).withProperty(DCState.FLAG, false));
		this.fullBlock = false;
		this.lightOpacity = 0;
		this.setTickRandomly(true);
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
	public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
		int meta = state.getBlock().getMetaFromState(state);
		return (meta & 3) > 0 ? 12 : 0;
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
		return AABB;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	// RS

	public int tickRate(World world) {
		return 20;
	}

	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY,
			float hitZ) {
		if (DCState.getBool(state, DCState.FLAG)) {
			return true;
		} else {
			world.setBlockState(pos, state.withProperty(DCState.FLAG, Boolean.valueOf(true)), 3);
			world.markBlockRangeForRenderUpdate(pos, pos);
			world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 0.3F, 0.6F);
			this.onIgnite(world, pos, DCState.getFace(state, DCState.FACING));
			world.scheduleUpdate(pos, this, 20);
			return true;
		}
	}

	public void onIgnite(World world, BlockPos pos, EnumFacing face) {
		if (!world.isRemote) {
			BlockPos offset = pos.offset(face);
			IBlockState target = world.getBlockState(offset);
			if (target.getBlock() == MainInit.swedishTorch) {
				BlockSwedishTorch.changeLitState(world, offset, true);
			} else if (target.getBlock() == MainInit.firestand) {
				BlockFirestand.changeLitState(world, offset, true);
			} else if (world.isAirBlock(offset)) {
				world.setBlockState(offset, Blocks.FIRE.getDefaultState(), 11);
			}
			world.scheduleUpdate(offset, target.getBlock(), 20);
		}
	}

	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		boolean flag = world.isBlockPowered(pos) || world.isBlockPowered(pos.down());
		boolean flag1 = DCState.getBool(state, DCState.FLAG);
		if (flag && !flag1) {
			world.setBlockState(pos, state.withProperty(DCState.FLAG, Boolean.valueOf(true)), 3);
			world.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 0.3F, 0.6F);
			if (!world.isRemote)
				this.onIgnite(world, pos, DCState.getFace(state, DCState.FACING));
		} else if (!flag && flag1) {
			world.scheduleUpdate(pos, this, 20);
		}
	}

	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote) {
			boolean flag = world.isBlockPowered(pos) || world.isBlockPowered(pos.down());
			if (DCState.getBool(state, DCState.FLAG) && !flag) {
				world.setBlockState(pos, state.withProperty(DCState.FLAG, Boolean.valueOf(false)));
				world.markBlockRangeForRenderUpdate(pos, pos);
			}
		}
	}

	@Override
	public IBlockState getPlaceState(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ,
			int meta, EntityLivingBase placer, EnumHand hand) {
		IBlockState state = super.getPlaceState(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);
		state = state.withProperty(DCState.FACING, placer.getHorizontalFacing());
		return state;
	}

	// state関連
	@Override
	public IBlockState getStateFromMeta(int meta) {
		int i = meta & 3;
		boolean flag = false;
		if (i > 0) {
			flag = false;
		}
		int f = 5 - (meta >> 2);
		IBlockState state = this.getDefaultState().withProperty(DCState.FLAG, flag);
		state = state.withProperty(DCState.FACING, EnumFacing.getFront(f));
		return state;
	}

	// state
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		int f = 0;
		if (state.getValue(DCState.FLAG)) {
			i = 1;
		}
		f = 5 - state.getValue(DCState.FACING).getIndex();
		f = f << 2;
		return i + f;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {
				DCState.FACING,
				DCState.FLAG
		});
	}

	@Override
	public IProperty[] ignoreTarget() {
		return null;
	}

	@Override
	public EnumStateType getType() {
		return EnumStateType.CUSTOM;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

}
