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
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.core.base.ClimateDoubleCropBase;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.ClimateMain;

public class BlockCoffee extends ClimateDoubleCropBase implements ITexturePath {

	public BlockCoffee(String s) {
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
		int m = meta & 7;
		boolean d = meta > 7;
		boolean shrub = m < 4;
		String b = "dcs_climate:blocks/crop/";
		String db = d ? "_under" : "_upper";
		return shrub ? b + "shrub_" + m : b + "coffee_" + m + db;
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/crop/";
		list.add(b + "coffee_4_upper");
		list.add(b + "coffee_5_upper");
		list.add(b + "coffee_6_upper");
		list.add(b + "coffee_7_upper");
		list.add(b + "coffee_4_under");
		list.add(b + "coffee_5_under");
		list.add(b + "coffee_6_under");
		list.add(b + "coffee_7_under");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int m = meta & 7;
		boolean d = meta > 7;
		boolean shrub = m < 4;
		String b = "dcs_climate:blocks/crop/";
		String db = d ? "_under" : "_upper";
		return shrub ? b + "shrub_" + m : b + "coffee_" + m + db;
	}

	@Override
	public ItemStack getSeedItem(IBlockState thisState) {
		return new ItemStack(FoodInit.seeds, 1, 4);
	}

	@Override
	public List<ItemStack> getCropItems(IBlockState thisState, int fortune) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		int i = 1 + rand.nextInt(1 + fortune);
		list.add(new ItemStack(FoodInit.crops, i, 4));
		return list;
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(IBlockState thisState) {
		List<DCHeatTier> ret = new ArrayList<DCHeatTier>();
		ret.add(DCHeatTier.NORMAL);
		ret.add(DCHeatTier.HOT);
		ret.add(DCHeatTier.OVEN);
		return ret;
	}

	@Override
	public List<DCHumidity> getSuitableHum(IBlockState thisState) {
		List<DCHumidity> ret = new ArrayList<DCHumidity>();
		ret.add(DCHumidity.NORMAL);
		ret.add(DCHumidity.WET);
		return ret;
	}

}
