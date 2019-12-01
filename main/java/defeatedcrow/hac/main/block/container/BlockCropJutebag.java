package defeatedcrow.hac.main.block.container;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.api.ICompressionRecipe;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCropJutebag extends DCSimpleBlock implements ITexturePath, IRapidCollectables, ICompressionRecipe {

	public BlockCropJutebag(Material m, String s, int max) {
		super(m, s, max, false);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(5.0F);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = { "bean", "chili", "walnut", "date", "grape" };
		return name;
	}

	@Override
	public Object getInputDic(int i) {
		switch (i) {
		case 0:
			return "cropBean";
		case 1:
			return "cropChilipepper";
		case 2:
			return "cropWalnut";
		case 3:
			return "cropDate";
		case 4:
			return "cropGrape";
		}
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack getOutputItem(int i) {
		if (i >= 0 && i < containedItem().length) {
			return containedItem()[i];
		}
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack[] containedItem() {
		ItemStack[] ret = new ItemStack[5];
		ret[0] = new ItemStack(FoodInit.seeds, 8, 10);
		ret[1] = new ItemStack(FoodInit.crops, 8, 13);
		ret[2] = new ItemStack(FoodInit.crops, 8, 16);
		ret[3] = new ItemStack(FoodInit.crops, 8, 17);
		ret[4] = new ItemStack(FoodInit.crops, 8, 20);

		return ret;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 15;
		if (m > 4)
			m = 4;
		String b = "dcs_climate:blocks/cont/jute";
		switch (side) {
		case 0:
			return b + "_t_" + getNameSuffix()[m];
		case 1:
			return b + "_b";
		case 2:
		case 3:
		case 4:
		case 5:
			return b + "_s";
		}
		return super.getTexture(meta, side, face);
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/cont/jute_";
		list.add(b + "t_bean");
		list.add(b + "t_chili");
		list.add(b + "t_walnut");
		list.add(b + "t_date");
		list.add(b + "s");
		list.add(b + "b");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int m = meta & 15;
		if (m > 4)
			m = 4;
		String b = "dcs_climate:items/block/cont/";
		return b + "jute_" + getNameSuffix()[m];
	}

	/* IRapidCollectables */

	@Override
	public boolean isCollectable(ItemStack item) {
		return !DCUtil.isEmpty(item) && item.getItem() instanceof ItemSpade;
	}

	@Override
	public int getCollectArea(ItemStack item) {
		return 1;
	}

	@Override
	public boolean doCollect(World world, BlockPos pos, IBlockState state, EntityPlayer player, ItemStack tool) {
		NonNullList<ItemStack> list = NonNullList.create();
		this.getDrops(list, world, pos, state, 0);
		for (ItemStack item : list) {
			double x = player.posX;
			double y = player.posY + 0.25D;
			double z = player.posZ;
			EntityItem drop = new EntityItem(world, x, y, z, item);
			world.spawnEntity(drop);
		}
		world.setBlockToAir(pos);
		return true;
	}

}
