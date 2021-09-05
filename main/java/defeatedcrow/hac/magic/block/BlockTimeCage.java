package defeatedcrow.hac.magic.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCTileBlock;
import defeatedcrow.hac.main.util.DCChunkloadContoroller;
import defeatedcrow.hac.main.util.DCChunkloadContoroller.IChunkBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTimeCage extends DCTileBlock implements IChunkBlock {

	public static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.75D, 1.0D, 1.0D, 1.0D);
	public static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.25D, 1.0D, 1.0D);
	public static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.25D);
	public static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.75D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

	public BlockTimeCage(String s) {
		super(Material.GLASS, s, 3);
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
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing face = DCState.getFace(state, DCState.FACING);
		switch (face) {
		case NORTH:
			return NORTH_AABB;
		case SOUTH:
			return SOUTH_AABB;
		case WEST:
			return WEST_AABB;
		case EAST:
			return EAST_AABB;
		default:
			return NORTH_AABB;
		}
	}

	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		return super.onRightClick(world, pos, state, player, hand, side, hitX, hitY, hitZ);
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
		DCChunkloadContoroller.getInstance().deleteBlockTicket(world, pos.getX(), pos.getY(), pos
				.getZ(), coordX, coordZ, d);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		super.onBlockAdded(world, pos, state);
		int coordX = pos.getX() >> 4;
		int coordZ = pos.getZ() >> 4;
		int d = world.provider.getDimension();
		DCChunkloadContoroller.getInstance().setBlockTicket(world, pos.getX(), pos.getY(), pos
				.getZ(), coordX, coordZ, d);
	}

	@Override
	public boolean canLoad(World world, int x, int y, int z) {
		return true;
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
	public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
		if (ClimateCore.proxy.isShiftKeyDown()) {
			tooltip.add(TextFormatting.YELLOW.toString() + TextFormatting.BOLD.toString() + "=== Tips ===");
			tooltip.add("The chunk loader device.");
		} else {
			tooltip.add(TextFormatting.ITALIC.toString() + "=== Lshift key: expand tooltip ===");
		}
	}
}
