package defeatedcrow.hac.main.block.container;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.core.base.DCSidedBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLogCont extends DCSidedBlock implements ITexturePath, IRapidCollectables {

	public BlockLogCont(Material m, String s, int max) {
		super(m, s, max, true);
		this.setSoundType(SoundType.WOOD);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(5.0F);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"oak",
				"spruce",
				"birch",
				"jungle",
				"acacia",
				"dark",
				"charcoal"
		};
		return name;
	}

	public static ItemStack[] containedItem() {
		ItemStack[] ret = new ItemStack[7];
		ret[0] = new ItemStack(Blocks.LOG, 8, 0);
		ret[1] = new ItemStack(Blocks.LOG, 8, 1);
		ret[2] = new ItemStack(Blocks.LOG, 8, 2);
		ret[3] = new ItemStack(Blocks.LOG, 8, 3);
		ret[4] = new ItemStack(Blocks.LOG2, 8, 0);
		ret[5] = new ItemStack(Blocks.LOG2, 8, 1);
		ret[6] = new ItemStack(Items.COAL, 8, 1);

		return ret;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 7;
		if (m > 6)
			m = 6;
		String b = "dcs_climate:blocks/cont/";
		switch (side) {
		case 0:
		case 1:
			return b + "logbox_s_" + getNameSuffix()[m];
		case 2:
		case 3:
			return face ? b + "logbox_f_" + getNameSuffix()[m] : b + "logbox_s_" + getNameSuffix()[m];
		case 4:
		case 5:
			return face ? b + "logbox_s_" + getNameSuffix()[m] : b + "logbox_f_" + getNameSuffix()[m];
		}
		return super.getTexture(meta, side, face);
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/cont/";
		list.add(b + "logbox_f_oak");
		list.add(b + "logbox_f_birch");
		list.add(b + "logbox_f_spruce");
		list.add(b + "logbox_f_jungle");
		list.add(b + "logbox_f_dark");
		list.add(b + "logbox_f_acacia");
		list.add(b + "logbox_s_oak");
		list.add(b + "logbox_s_birch");
		list.add(b + "logbox_s_spruce");
		list.add(b + "logbox_s_jungle");
		list.add(b + "logbox_s_dark");
		list.add(b + "logbox_s_acacia");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int m = meta & 7;
		if (m > 6)
			m = 6;
		String b = "dcs_climate:items/block/cont/";
		return b + "logbox_" + getNameSuffix()[m];
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
