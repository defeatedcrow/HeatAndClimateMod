package defeatedcrow.hac.magic.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.IHeatTile;
import defeatedcrow.hac.core.base.BlockContainerDC;
import defeatedcrow.hac.main.ClimateMain;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockInfernalFlame extends BlockContainerDC implements IHeatTile {

	public BlockInfernalFlame(String s) {
		super(Material.FIRE, s);
		this.setSoundType(SoundType.SNOW);
		this.setTickRandomly(true);
		this.setLightLevel(1.0F);
	}

	@Override
	public int tickRate(World world) {
		return 60;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		super.onBlockAdded(world, pos, state);
		world.scheduleUpdate(pos, this, this.tickRate(world));
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		if (!world.isRemote) {
			if (world.rand.nextInt(12) == 5) {
				world.setBlockToAir(pos);
			} else {
				this.updateFire(world, pos, state, rand);
				world.scheduleUpdate(pos, this, this.tickRate(world));
			}
		}
	}

	private void updateFire(World world, BlockPos pos, IBlockState state, Random rand) {
		int r = 1 + rand.nextInt(3);
		int i = 0;
		int count = 0;
		while (i < 5 && count < r) {
			int x = -3 + rand.nextInt(7);
			int z = -3 + rand.nextInt(7);
			BlockPos p2 = pos.add(x, 0, z);
			IBlockState s2 = world.getBlockState(p2);

			if (s2.getBlock().isReplaceable(world, p2)) {
				for (int y = 1; y < 4; y++) {
					BlockPos p3 = p2.down(y);
					if (!world.isAirBlock(p3) && world.getBlockState(p3).getMaterial() != Material.FIRE) {
						world.setBlockState(p2, Blocks.FIRE.getDefaultState(), 2);
						count++;
						break;
					}
				}
			} else {
				for (int y = 1; y < 4; y++) {
					BlockPos p3 = p2.up(y);
					if (world.getBlockState(p3).getBlock().isReplaceable(world, p3) && world.getBlockState(
							p3).getMaterial() != Material.FIRE) {
						world.setBlockState(p3, Blocks.FIRE.getDefaultState(), 2);
						count++;
						break;
					}
				}
			}
			i++;
		}
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
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(this, 1, 0));
		return list;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileInfernalFlame();
	}

	@Override
	public DCHeatTier getHeatTier(World world, BlockPos to, BlockPos from) {
		return DCHeatTier.INFERNO;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		int c = ClimateMain.proxy.getParticleCount();
		if (ClimateMain.proxy.getParticleCount() > 0 && rand.nextInt(c) == 0) {
			if (rand.nextInt(24) == 0) {
				world.playSound(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, SoundEvents.BLOCK_FIRE_AMBIENT,
						SoundCategory.BLOCKS, 1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
			}

			double x = pos.getX() + rand.nextDouble();
			double y = pos.getY() + 0.25D + rand.nextDouble() * 0.5D;
			double z = pos.getZ() + rand.nextDouble();

			world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, x, y, z, 0.0D, 0.0D, 0.0D, new int[0]);

		}
	}

}
