package defeatedcrow.hac.main.block.container;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.main.MainInit;
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

public class BlockCropCont extends DCSimpleBlock implements ITexturePath, IRapidCollectables {

	public BlockCropCont(Material m, String s, int max) {
		super(m, s, max, false);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(5.0F);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"apple",
				"potato",
				"carrot",
				"pumpkin",
				"melon",
				"cactus",
				"reed",
				"wart",
				"cocoa",
				"baked_apple",
				"baked_potato"
		};
		return name;
	}

	public static ItemStack[] containedItem() {
		ItemStack[] ret = new ItemStack[11];
		ret[0] = new ItemStack(Items.APPLE, 8);
		ret[1] = new ItemStack(Items.POTATO, 8);
		ret[2] = new ItemStack(Items.CARROT, 8);
		ret[3] = new ItemStack(Blocks.PUMPKIN, 8);
		ret[4] = new ItemStack(Blocks.MELON_BLOCK, 8);
		ret[5] = new ItemStack(Blocks.CACTUS, 8);
		ret[6] = new ItemStack(Items.REEDS, 8);
		ret[7] = new ItemStack(Items.NETHER_WART, 8);
		ret[8] = new ItemStack(Items.DYE, 8, 3);
		ret[9] = new ItemStack(MainInit.bakedApple, 8);
		ret[10] = new ItemStack(Items.BAKED_POTATO, 8);

		return ret;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 15;
		if (m > 10)
			m = 10;
		boolean f = m > 8;
		String b = "dcs_climate:blocks/cont/";
		if (f) {
			switch (side) {
			case 0:
				return b + "burntbox_t_" + getNameSuffix()[m];
			case 1:
				return b + "burntbox_b";
			case 2:
			case 3:
			case 4:
			case 5:
				return b + "burntbox_s";
			}
		} else {
			switch (side) {
			case 0:
				return b + "woodbox_t_" + getNameSuffix()[m];
			case 1:
				return b + "woodbox_b";
			case 2:
			case 3:
			case 4:
			case 5:
				return b + "woodbox_s";
			}
		}
		return super.getTexture(meta, side, face);
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/cont/woodbox_";
		String b2 = "dcs_climate:blocks/cont/burntbox_";
		list.add(b + "t_apple");
		list.add(b + "t_potato");
		list.add(b + "t_carrot");
		list.add(b + "t_pumpkin");
		list.add(b + "t_melon");
		list.add(b + "t_cactus");
		list.add(b + "t_reed");
		list.add(b + "t_wart");
		list.add(b + "t_cocoa");
		list.add(b + "s");
		list.add(b + "b");
		list.add(b2 + "t_baked_apple");
		list.add(b2 + "t_baked_potato");
		list.add(b2 + "s");
		list.add(b2 + "b");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int m = meta & 15;
		if (m > 10)
			m = 10;
		String b = "dcs_climate:items/block/cont/";
		return b + "cropbox_" + getNameSuffix()[m];
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
