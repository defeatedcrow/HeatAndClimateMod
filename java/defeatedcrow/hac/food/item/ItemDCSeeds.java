package defeatedcrow.hac.food.item;

import defeatedcrow.hac.api.cultivate.IClimateCrop;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDCSeeds extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"rice", "onion", "spinach", "tomato", "coffee", "cotton", "lotus", "herb"
	};

	public ItemDCSeeds(int max) {
		super();
		maxMeta = max;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/food/seed_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

	/* IPlantable */

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		if (player == null || DCUtil.isEmpty(player.getHeldItem(hand)))
			return EnumActionResult.FAIL;

		ItemStack stack = player.getHeldItem(hand);
		int meta = stack.getItemDamage();
		IBlockState state = world.getBlockState(pos);
		if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, stack)) {
			Block crop = getCropBlock(meta);
			if (crop instanceof IClimateCrop) {
				if (((IClimateCrop) crop).isSuitablePlace(world, pos, state)) {
					world.setBlockState(pos.up(), crop.getDefaultState(), 3);
					DCUtil.reduceStackSize(stack, 1);
					return EnumActionResult.SUCCESS;
				}
			}

		}
		return EnumActionResult.FAIL;
	}

	private Block getCropBlock(int meta) {
		switch (meta) {
		case 0:
			return FoodInit.cropRice;
		case 1:
			return FoodInit.cropOnion;
		case 2:
			return FoodInit.cropSpinach;
		case 3:
			return FoodInit.cropTomato;
		case 4:
			return FoodInit.cropCoffee;
		case 5:
			return FoodInit.cropCotton;
		case 6:
			return FoodInit.cropLotus;
		case 7:
			return FoodInit.cropHerb;
		default:
			return FoodInit.cropRice;
		}
	}

}
