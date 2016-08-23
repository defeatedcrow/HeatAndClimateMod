package defeatedcrow.hac.food.block.crop;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import defeatedcrow.hac.core.base.ClimateCropBase;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.ClimateMain;

public class BlockOnion extends ClimateCropBase implements ITexturePath {

	public BlockOnion(String s) {
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
		String b = "dcs_climate:blocks/crop/";
		return b + "onion_" + m;
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/crop/";
		list.add(b + "onion_0");
		list.add(b + "onion_1");
		list.add(b + "onion_2");
		list.add(b + "onion_3");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int m = meta & 3;
		String b = "dcs_climate:items/block/crop/";
		return b + "onion_" + m;
	}

	@Override
	public ItemStack getSeedItem(IBlockState thisState) {
		return new ItemStack(FoodInit.seeds, 1, 1);
	}

	@Override
	public List<ItemStack> getCropItems(IBlockState thisState, int fortune) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(FoodInit.crops, 1, 1));
		return list;
	}

}
