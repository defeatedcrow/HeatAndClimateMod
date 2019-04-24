package defeatedcrow.hac.main.block.build;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.BlockDC;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.MainInit;
import defeatedcrow.hac.main.client.particle.ParticleOrb;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLightOrb extends BlockDC {

	protected static final AxisAlignedBB AABB_PANEL = new AxisAlignedBB(0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);

	// Type上限
	public final int maxMeta;

	public BlockLightOrb(String s) {
		super(Material.CIRCUITS, s);
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
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(this, 1, 0));
		return list;
	}

	@Override
	public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {
		if (DCUtil.isPlayerHeldItem(new ItemStack(MagicInit.magicCard, 1, 4), player) || DCUtil
				.isPlayerHeldItem(new ItemStack(MainInit.cartridge, 1, 5), player)) {
			return 10.0F;
		} else {
			return 0.0F;
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB_PANEL;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
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
		if (DCUtil.isPlayerHeldItem(new ItemStack(MainInit.cartridge, 1, 5), ClimateCore.proxy.getPlayer()) || DCUtil
				.isPlayerHeldItem(new ItemStack(MagicInit.magicCard, 1, 4), ClimateCore.proxy.getPlayer())) {
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
}
