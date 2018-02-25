package defeatedcrow.hac.food.block.crop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.DCAirflow;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.cultivate.GrowingStage;
import defeatedcrow.hac.core.base.ClimateCropBase;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockLeavesTea extends ClimateCropBase implements ITexturePath, IShearable {

	public BlockLeavesTea(String s) {
		super(Material.PLANTS, s, 3);
		setSoundType(SoundType.PLANT);
		setHardness(0.0F);
		setResistance(3.0F);
	}

	@Override
	public String[] getNameSuffix() {
		return null;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta > 0 ? 1 : 0;
		String b = "dcs_climate:blocks/crop/";
		return b + "tea_" + m;
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/crop/";
		list.add(b + "tea_0");
		list.add(b + "tea_1");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int m = meta > 0 ? 1 : 0;
		String b = "dcs_climate:items/block/crop/";
		return b + "tea_" + m;
	}

	@Override
	public ItemStack getSeedItem(IBlockState thisState) {
		return new ItemStack(FoodInit.saplings, 1, 2);
	}

	@Override
	public List<ItemStack> getCropItems(IBlockState thisState, int fortune) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		list.add(new ItemStack(FoodInit.crops, 1, 8));
		return list;
	}

	@Override
	public IBlockState getGrownState() {
		return this.getDefaultState().withProperty(DCState.STAGE4, 1);
	}

	@Override
	public List<ItemStack> getSubItemList() {
		List<ItemStack> list = super.getSubItemList();
		list.add(new ItemStack(this, 1, 1));
		return list;
	}

	/* IShearable */

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return Arrays.asList(new ItemStack(this, 1, 0));
	}

	/* ClimateCrop */

	@Override
	public boolean isSuitablePlace(World world, BlockPos pos, IBlockState state) {
		if (state == null)
			return false;
		return state.getMaterial() == Material.GRASS || state.getMaterial() == Material.GROUND;
	}

	@Override
	public GrowingStage getCurrentStage(IBlockState thisState) {
		if (thisState == null)
			return GrowingStage.DEAD;
		else {
			if (DCState.getInt(thisState, DCState.STAGE4) > 0) {
				return GrowingStage.GROWN;
			} else {
				return GrowingStage.YOUNG;
			}
		}
	}

	@Override
	public boolean grow(World world, BlockPos pos, IBlockState thisState) {
		if (thisState != null && thisState.getBlock() instanceof ClimateCropBase) {
			GrowingStage stage = this.getCurrentStage(thisState);
			if (stage == GrowingStage.DEAD) {
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				return false;
			} else if (stage == GrowingStage.GROWN) {
				return false;
			} else {
				if (DCState.getInt(thisState, DCState.STAGE4) == 0) {
					IBlockState next = this.getDefaultState().withProperty(DCState.STAGE4, 1);
					return world.setBlockState(pos, next, 3);
				}
			}
		}
		return false;
	}

	@Override
	public boolean canStayOnHarvest() {
		return true;
	}

	@Override
	public List<DCHeatTier> getSuitableTemp(IBlockState thisState) {
		List<DCHeatTier> ret = new ArrayList<DCHeatTier>();
		ret.add(DCHeatTier.NORMAL);
		ret.add(DCHeatTier.WARM);
		ret.add(DCHeatTier.HOT);
		return ret;
	}

	@Override
	public List<DCHumidity> getSuitableHum(IBlockState thisState) {
		List<DCHumidity> ret = new ArrayList<DCHumidity>();
		ret.add(DCHumidity.NORMAL);
		ret.add(DCHumidity.WET);
		return ret;
	}

	@Override
	public List<DCAirflow> getSuitableAir(IBlockState thisState) {
		List<DCAirflow> ret = new ArrayList<DCAirflow>();
		ret.add(DCAirflow.FLOW);
		ret.add(DCAirflow.WIND);
		return ret;
	}
}
