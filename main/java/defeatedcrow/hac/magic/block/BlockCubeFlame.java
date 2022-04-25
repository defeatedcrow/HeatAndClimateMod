package defeatedcrow.hac.magic.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.base.BlockContainerDC;
import defeatedcrow.hac.core.base.EnumStateType;
import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.client.particle.ParticleCloudRev;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCubeFlame extends BlockContainerDC implements IHeatTile {
	public BlockCubeFlame(String s) {
		super(Material.ICE, s);
		this.setSoundType(SoundType.GLASS);
		this.setHardness(0.5F);
		this.setResistance(10.0F);
		this.setTickRandomly(true);
		this.setLightLevel(0.5F);
	}

	@Override
	public int tickRate(World world) {
		return 10;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
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
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(this, 1, 0));
		return list;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileCubeFlame();
	}

	@Override
	public DCHeatTier getHeatTier(World world, BlockPos to, BlockPos from) {
		return DCHeatTier.INFERNO;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		int c = ClimateMain.proxy.getParticleCount();
		if (ClimateMain.proxy.getParticleCount() > 0) {
			for (int i = 0; i < 3; i++) {
				double x = pos.getX() + 0.25D + rand.nextDouble() * 0.5D;
				double y = pos.getY() + 0.25D + rand.nextDouble() * 0.5D;
				double z = pos.getZ() + 0.25D + rand.nextDouble() * 0.5D;
				Particle p = new ParticleCloudRev.Factory().createParticle(0, world, x, y, z, 0.0D, 0.0D, 0.0D, new int[0]);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(p);
			}
		}
	}

	@Override
	public IProperty[] ignoreTarget() {
		return null;
	}

	@Override
	public EnumStateType getType() {
		return EnumStateType.NORMAL;
	}
}
