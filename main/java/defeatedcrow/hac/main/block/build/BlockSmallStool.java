package defeatedcrow.hac.main.block.build;

import java.util.List;

import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.main.entity.EntityCution;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSmallStool extends DCSimpleBlock implements ITexturePath {

	protected static final AxisAlignedBB AABB_HALF = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 0.375D, 0.75D);

	public BlockSmallStool(String s) {
		super(Material.WOOD, s, 6, false);
		this.setTickRandomly(false);
		this.setHardness(3.0F);
		this.setResistance(30.0F);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB_HALF;
	}

	// すわる
	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!player.world.isRemote && player != null && hand == EnumHand.MAIN_HAND) {
			List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player, AABB_HALF);
			if (list.isEmpty()) {
				double y1 = 0.25D;
				EntityCution cution = new EntityCution(world, pos.getX() + 0.5D, pos.getY() + y1, pos.getZ() + 0.5D);
				world.spawnEntity(cution);
				if (player.isRiding()) {
					player.dismountRidingEntity();
				}
				player.startRiding(cution);
			}
		}
		return true;
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"wood",
				"white",
				"dark",
				"pink",
				"green",
				"blue",
				"yellow"
		};
		return name;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
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
	public boolean isSideSolid(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public int getMaxMeta() {
		return 6;
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
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		return "dcs_climate:blocks/build/panel_wood";
	}
}
