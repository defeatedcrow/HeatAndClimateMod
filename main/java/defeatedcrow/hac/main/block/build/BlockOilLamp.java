package defeatedcrow.hac.main.block.build;

import java.util.Random;

import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.main.client.particle.ParticleFlameDC;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOilLamp extends DCSimpleBlock {

	protected static final AxisAlignedBB AABB_FULL = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_SMALL = new AxisAlignedBB(0.25D, 0D, 0.25D, 0.75D, 1D, 0.75D);

	public BlockOilLamp(String s) {
		super(Material.GLASS, s, 3, false);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.fullBlock = false;
		this.setLightLevel(1.0F);
		this.setTickRandomly(true);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = { "wood", "gold", "chalcedony", "serpentine" };
		return name;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB_SMALL;
	}

	@Override
	protected boolean canSilkHarvest() {
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

	// 接してる面側が水だったら、その接してる水の側面を描画しない
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		boolean b = world.getBlockState(pos.up()).getMaterial() == Material.AIR;
		if (!b && world.getBlockState(pos.offset(face)).getMaterial() == Material.WATER)
			return true;
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos,
			EnumFacing side) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state != null && state.getBlock() == this) {
			double x = pos.getX() + 0.5D;
			double y = pos.getY() + 0.5D;
			double z = pos.getZ() + 0.5D;
			double dx = 0D;
			double dy = 0D;
			double dz = 0D;
			Particle cloud = new ParticleFlameDC.Factory().createParticle(0, world, x, y, z, dx, dy, dz, null);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
		}
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.CENTER_SMALL;
	}

}
