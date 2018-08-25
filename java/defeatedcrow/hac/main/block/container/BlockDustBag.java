package defeatedcrow.hac.main.block.container;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.core.base.DCSidedBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.food.FoodInit;
import defeatedcrow.hac.main.MainInit;
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

public class BlockDustBag extends DCSidedBlock implements ITexturePath, IRapidCollectables {

	public BlockDustBag(Material m, String s, int max) {
		super(m, s, max, false);
		this.setSoundType(SoundType.CLOTH);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(5.0F);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"sugar",
				"salt",
				"flour",
				"rice",
				"starch",
				"seed",
				"soy"
		};
		return name;
	}

	public static ItemStack[] containedItem() {
		ItemStack[] ret = new ItemStack[7];
		ret[0] = new ItemStack(Items.SUGAR, 8);
		ret[1] = new ItemStack(MainInit.foodMaterials, 8, 0);
		ret[2] = new ItemStack(MainInit.foodMaterials, 8, 1);
		ret[3] = new ItemStack(MainInit.foodMaterials, 8, 2);
		ret[4] = new ItemStack(MainInit.foodMaterials, 8, 3);
		ret[5] = new ItemStack(Items.WHEAT_SEEDS, 8);
		ret[6] = new ItemStack(FoodInit.seeds, 8, 9);

		return ret;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 7;
		if (m > 6)
			m = 6;
		String b = "dcs_climate:blocks/cont/bags";
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
		String b = "dcs_climate:blocks/cont/bags";
		list.add(b + "_t_sugar");
		list.add(b + "_t_salt");
		list.add(b + "_t_flour");
		list.add(b + "_t_rice");
		list.add(b + "_t_starch");
		list.add(b + "_t_seed");
		list.add(b + "_s");
		list.add(b + "_b");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int m = meta & 7;
		if (m > 6)
			m = 6;
		String b = "dcs_climate:items/block/cont/";
		return b + "bags_" + getNameSuffix()[m];
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
