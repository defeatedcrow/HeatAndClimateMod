package defeatedcrow.hac.food.block.crop;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.cultivate.GrowingStage;
import defeatedcrow.hac.core.base.ClimateCropBase;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.worldgen.WorldGenDCTree;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockSaplingDC extends ClimateCropBase implements ITexturePath, IPlantable {

	public BlockSaplingDC(String s) {
		super(Material.PLANTS, s, 3);
		setSoundType(SoundType.PLANT);
		setHardness(0.0F);
		setResistance(3.0F);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return CROP_AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 3;
		String b = "dcs_climate:blocks/crop/sapling_";
		switch (m) {
		case 0:
			return b + "lemon";
		case 1:
			return b + "olive";
		case 2:
			return b + "tea";
		default:
			return b + "tea";
		}
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/crop/";
		list.add(b + "sapling_lemon");
		list.add(b + "sapling_olive");
		list.add(b + "sapling_tea");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int m = meta & 3;
		String b = "dcs_climate:items/block/crop/sapling_";
		switch (m) {
		case 0:
			return b + "lemon";
		case 1:
			return b + "olive";
		case 2:
			return b + "tea";
		default:
			return b + "tea";
		}
	}

	@Override
	public ItemStack getSeedItem(IBlockState thisState) {
		int m = thisState.getValue(DCState.STAGE4);
		return new ItemStack(FoodInit.saplings, 1, m);
	}

	@Override
	public List<ItemStack> getCropItems(IBlockState thisState, int fortune) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		return list;
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = Lists.newArrayList();
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		list.add(new ItemStack(this, 1, 2));
		return list;
	}

	/* ClimateCrop */

	// 得られる作物はない
	@Override
	public boolean onRightClick(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		return false;
	}

	@Override
	public boolean isSuitablePlace(World world, BlockPos pos, IBlockState targetState) {
		if (targetState == null)
			return false;
		return targetState.getMaterial() == Material.GRASS || targetState.getMaterial() == Material.GROUND;
	}

	@Override
	public GrowingStage getCurrentStage(IBlockState thisState) {
		return GrowingStage.YOUNG;
	}

	@Override
	public boolean grow(World world, BlockPos pos, IBlockState thisState) {
		if (thisState != null && thisState.getBlock() instanceof ClimateCropBase) {

			if (!TerrainGen.saplingGrowTree(world, cropRand, pos))
				return false;

			int meta = thisState.getValue(DCState.STAGE4);
			WorldGenerator gen = null;
			if (meta == 0) {
				// lemon
				IBlockState leaves = FoodInit.leavesLemon.getDefaultState().withProperty(DCState.STAGE4, 0)
						.withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
				gen = new WorldGenDCTree(true, leaves);
			} else if (meta == 1) {
				// olive
				IBlockState leaves = FoodInit.leavesOlive.getDefaultState().withProperty(DCState.STAGE4, 0)
						.withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true));
				gen = new WorldGenDCTree(true, leaves);
			}

			if (gen != null) {
				if (gen.generate(world, cropRand, pos)) {
					world.setBlockState(pos,
							Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK),
							3);
				}
			} else {
				IBlockState leaves = FoodInit.leavesTea.getDefaultState();
				world.setBlockState(pos, leaves, 2);
			}
		}
		return false;
	}

	@Override
	public boolean harvest(World world, BlockPos pos, IBlockState thisState, EntityPlayer player) {
		return false;
	}

	@Override
	public boolean isCollectable(ItemStack item) {
		return false;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}

	@Override
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() != this)
			return getDefaultState();
		return state;
	}

}
