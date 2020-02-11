package defeatedcrow.hac.main.block.container;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.api.ICompressionRecipe;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockEnemyCont extends DCSimpleBlock implements ITexturePath, IRapidCollectables, ICompressionRecipe {

	public BlockEnemyCont(Material m, String s, int max) {
		super(m, s, max, false);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(5.0F);
		this.setSoundType(SoundType.STONE);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = { "rotten", "bone", "spider", "ender", "powder", "blaze" };
		return name;
	}

	@Override
	public Object getInputDic(int i) {
		switch (i) {
		case 0:
			return new ItemStack(Items.ROTTEN_FLESH);
		case 1:
			return "bone";
		case 2:
			return new ItemStack(Items.SPIDER_EYE);
		case 3:
			return "enderpearl";
		case 4:
			return "gunpowder";
		case 5:
			return "stickBlaze";
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
		ItemStack[] ret = new ItemStack[6];
		ret[0] = new ItemStack(Items.ROTTEN_FLESH, 8);
		ret[1] = new ItemStack(Items.BONE, 8);
		ret[2] = new ItemStack(Items.SPIDER_EYE, 8);
		ret[3] = new ItemStack(Items.ENDER_PEARL, 8);
		ret[4] = new ItemStack(Items.GUNPOWDER, 8);
		ret[5] = new ItemStack(Items.BLAZE_ROD, 8);

		return ret;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 15;
		if (m > 5)
			m = 5;
		String b = "dcs_climate:blocks/cont/";
		switch (side) {
		case 0:
			return b + "metalbox_t_" + getNameSuffix()[m];
		case 1:
			return b + "metalbox_b";
		case 2:
		case 3:
		case 4:
		case 5:
			return b + "metalbox_s";
		}
		return super.getTexture(meta, side, face);
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/cont/metalbox_";
		list.add(b + "t_rotten");
		list.add(b + "t_bone");
		list.add(b + "t_spider");
		list.add(b + "t_ender");
		list.add(b + "t_powder");
		list.add(b + "s");
		list.add(b + "b");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int m = meta & 15;
		if (m > 5)
			m = 5;
		String b = "dcs_climate:items/block/cont/";
		return b + "metalbox_" + getNameSuffix()[m];
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
