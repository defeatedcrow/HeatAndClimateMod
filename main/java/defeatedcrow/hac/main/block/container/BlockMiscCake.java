package defeatedcrow.hac.main.block.container;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
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

public class BlockMiscCake extends DCSimpleBlock implements ITexturePath, IRapidCollectables, ICompressionRecipe {

	public BlockMiscCake(Material m, String s, int max) {
		super(m, s, max, false);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(5.0F);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = { "bran", "presscake", "ash", "plant", "soycake" };
		return name;
	}

	@Override
	public ItemStack[] containedItem() {
		ItemStack[] ret = new ItemStack[5];
		ret[0] = new ItemStack(MainInit.foodDust, 8);
		ret[1] = new ItemStack(MainInit.miscDust, 8, 4);
		ret[2] = new ItemStack(MainInit.miscDust, 8, 5);
		ret[3] = new ItemStack(MainInit.foodDust, 8, 1);
		ret[4] = new ItemStack(MainInit.foodDust, 8, 4);

		return ret;
	}

	@Override
	public ItemStack getOutputItem(int i) {
		if (i >= 0 && i < containedItem().length) {
			return containedItem()[i];
		}
		return ItemStack.EMPTY;
	}

	@Override
	public Object getInputDic(int i) {
		switch (i) {
		case 0:
			return "dustBran";
		case 1:
			return new ItemStack(MainInit.miscDust, 1, 4);
		case 2:
			return "dustAsh";
		case 3:
			return "dustPlant";
		case 4:
			return new ItemStack(MainInit.foodDust, 1, 4);
		}
		return ItemStack.EMPTY;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 15;
		if (m > 4)
			m = 4;
		String b = "dcs_climate:blocks/cont/";
		return b + "cake_" + getNameSuffix()[m];
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/cont/cake_";
		list.add(b + "bran");
		list.add(b + "presscake");
		list.add(b + "ash");
		list.add(b + "plant");
		list.add(b + "soycake");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int m = meta & 15;
		if (m > 4)
			m = 4;
		String b = "dcs_climate:items/block/cont/";
		return b + "cake_" + getNameSuffix()[m];
	}

	/* IRapidCollectables */

	@Override
	public String getCollectableTool() {
		return "shovel";
	}

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
