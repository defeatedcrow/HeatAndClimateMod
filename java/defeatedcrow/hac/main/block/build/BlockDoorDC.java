package defeatedcrow.hac.main.block.build;

import java.util.Random;

import defeatedcrow.hac.main.MainInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDoorDC extends BlockDoor {

	public BlockDoorDC(String s, Material materialIn) {
		super(materialIn);
		this.setUnlocalizedName(s);
		this.setHardness(0.2F);
		this.setResistance(5.0F);
	}

	@Override
	public MapColor getMapColor(IBlockState state) {
		return MapColor.STONE;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? null : this.getItem();
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this.getItem());
	}

	private Item getItem() {
		if (this == MainInit.doorMarble)
			return MainInit.itemDoorMarble;
		if (this == MainInit.doorSteel)
			return MainInit.itemDoorSteel;
		else
			return MainInit.itemDoorMarble;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack item, EnumFacing facing, float hitX, float hitY, float hitZ) {

		BlockPos blockpos = state.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
		IBlockState iblockstate = pos.equals(blockpos) ? state : worldIn.getBlockState(blockpos);

		if (iblockstate.getBlock() != this)
			return false;
		else {
			state = iblockstate.cycleProperty(OPEN);
			worldIn.setBlockState(blockpos, state, 10);
			worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
			worldIn.playEvent(playerIn, state.getValue(OPEN).booleanValue() ? 1005 : 1011, pos, 0);
			return true;
		}
	}

	@Override
	public void toggleDoor(World worldIn, BlockPos pos, boolean open) {
		IBlockState iblockstate = worldIn.getBlockState(pos);

		if (iblockstate.getBlock() == this) {
			BlockPos blockpos = iblockstate.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? pos : pos.down();
			IBlockState iblockstate1 = pos == blockpos ? iblockstate : worldIn.getBlockState(blockpos);

			if (iblockstate1.getBlock() == this && iblockstate1.getValue(OPEN).booleanValue() != open) {
				worldIn.setBlockState(blockpos, iblockstate1.withProperty(OPEN, Boolean.valueOf(open)), 10);
				worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
				worldIn.playEvent((EntityPlayer) null, open ? 1005 : 1011, pos, 0);
			}
		}
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		if (state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER) {
			BlockPos blockpos = pos.down();
			IBlockState iblockstate = worldIn.getBlockState(blockpos);

			if (iblockstate.getBlock() != this) {
				worldIn.setBlockToAir(pos);
			} else if (blockIn != this) {
				iblockstate.neighborChanged(worldIn, blockpos, blockIn);
			}
		} else {
			boolean flag1 = false;
			BlockPos blockpos1 = pos.up();
			IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

			if (iblockstate1.getBlock() != this) {
				worldIn.setBlockToAir(pos);
				flag1 = true;
			}

			if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP)) {
				worldIn.setBlockToAir(pos);
				flag1 = true;

				if (iblockstate1.getBlock() == this) {
					worldIn.setBlockToAir(blockpos1);
				}
			}

			if (flag1) {
				if (!worldIn.isRemote) {
					this.dropBlockAsItem(worldIn, pos, state, 0);
				}
			} else {
				boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(blockpos1);

				if (blockIn != this && (flag || blockIn.getDefaultState().canProvidePower())
						&& flag != iblockstate1.getValue(POWERED).booleanValue()) {
					worldIn.setBlockState(blockpos1, iblockstate1.withProperty(POWERED, Boolean.valueOf(flag)), 2);

					if (flag != state.getValue(OPEN).booleanValue()) {
						worldIn.setBlockState(pos, state.withProperty(OPEN, Boolean.valueOf(flag)), 2);
						worldIn.markBlockRangeForRenderUpdate(pos, pos);
						worldIn.playEvent((EntityPlayer) null, flag ? 1005 : 1011, pos, 0);
					}
				}
			}
		}
	}

}
