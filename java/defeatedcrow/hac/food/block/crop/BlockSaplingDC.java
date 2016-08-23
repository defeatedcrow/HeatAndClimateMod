package defeatedcrow.hac.food.block.crop;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.cultivate.GrowingStage;
import defeatedcrow.hac.core.base.ClimateCropBase;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.ClimateMain;

public class BlockSaplingDC extends ClimateCropBase implements ITexturePath {

	public BlockSaplingDC(String s) {
		super(Material.PLANTS, s, 3);
		setSoundType(SoundType.PLANT);
		setHardness(0.0F);
		setResistance(3.0F);
		setCreativeTab(ClimateMain.food);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return CROP_AABB;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
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
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
		list.add(new ItemStack(item, 1, 2));
	}

	/* ClimateCrop */

	// 得られる作物はない
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack held, EnumFacing side, float hitX, float hitY, float hitZ) {
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
			// TODO
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

}
