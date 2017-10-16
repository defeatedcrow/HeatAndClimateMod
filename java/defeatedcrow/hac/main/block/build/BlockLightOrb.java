package defeatedcrow.hac.main.block.build;

import java.util.Random;

import javax.annotation.Nullable;

import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.client.particle.ParticleOrb;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLightOrb extends Block {

	protected static final AxisAlignedBB AABB_PANEL = new AxisAlignedBB(0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);

	// Type上限
	public final int maxMeta;

	public BlockLightOrb(String s) {
		super(Material.CIRCUITS);
		this.setUnlocalizedName(s);
		this.setHardness(0.1F);
		this.setResistance(5.0F);
		this.setLightLevel(1.0F);
		this.maxMeta = 0;
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
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
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
		return BlockRenderLayer.SOLID;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
		if (DCUtil.machCreativeTab(tab, getCreativeTabToDisplayOn())) {
			list.add(new ItemStack(this, 1, 0));
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB_PANEL;
	}

	@Override
	public boolean isCollidable() {
		return false;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (state != null && state.getBlock() == this) {
			double x = pos.getX() + 0.5D;
			double y = pos.getY() + 0.5D;
			double z = pos.getZ() + 0.5D;
			double dx = 0D;
			double dy = 0.01D;
			double dz = 0D;
			Particle cloud = new ParticleOrb.Factory().createParticle(0, world, x, y, z, dx, dy, dz, null);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(cloud);
		}
	}
}
