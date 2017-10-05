package defeatedcrow.hac.magic.block;

import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.base.DCTileBlock;
import defeatedcrow.hac.main.util.DCChunkloadContoroller;
import defeatedcrow.hac.main.util.DCChunkloadContoroller.IChunkBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTimeCage extends DCTileBlock implements IChunkBlock {

	public BlockTimeCage(String s) {
		super(Material.GLASS, s, 0);
		this.setHardness(0.5F);
		this.setResistance(30.0F);
		this.setLightLevel(0.5F);
		this.setTickRandomly(false);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileTimeCage();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		return super.onBlockActivated(world, pos, state, player, hand, heldItem, side, hitX, hitY, hitZ);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		super.breakBlock(world, pos, state);

		// チャンクのローダーを削除
		int coordX = pos.getX() >> 4;
		int coordZ = pos.getZ() >> 4;
		int d = world.provider.getDimension();
		DCChunkloadContoroller.getInstance().deleteBlockTicket(world, pos.getX(), pos.getY(), pos.getZ(), coordX,
				coordZ, d);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		super.onBlockAdded(world, pos, state);
		int coordX = pos.getX() >> 4;
		int coordZ = pos.getZ() >> 4;
		int d = world.provider.getDimension();
		DCChunkloadContoroller.getInstance().setBlockTicket(world, pos.getX(), pos.getY(), pos.getZ(), coordX, coordZ,
				d);
	}

	@Override
	public boolean canLoad(World world, int x, int y, int z) {
		return true;
	}
}
