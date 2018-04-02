package defeatedcrow.hac.main.block.container;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCropBasket extends DCSimpleBlock implements ITexturePath, IRapidCollectables {

	public BlockCropBasket(Material m, String s, int max) {
		super(m, s, max, false);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(5.0F);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"rice",
				"onion",
				"spinach",
				"tomato",
				"coffee",
				"cotton",
				"lemon",
				"olive",
				"tea",
				"lotus",
				"mulberry",
				"cocoon"
		};
		return name;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 15;
		if (m > 11) {
			m = 11;
		}
		String b = "dcs_climate:blocks/cont/basket";
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
		String b = "dcs_climate:blocks/cont/basket_";
		list.add(b + "t_rice");
		list.add(b + "t_onion");
		list.add(b + "t_spinach");
		list.add(b + "t_tomato");
		list.add(b + "t_coffee");
		list.add(b + "t_cotton");
		list.add(b + "t_lemon");
		list.add(b + "t_olive");
		list.add(b + "t_tea");
		list.add(b + "s");
		list.add(b + "b");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int m = meta & 15;
		if (m > 11) {
			m = 11;
		}
		String b = "dcs_climate:items/block/cont/";
		return b + "basket_" + getNameSuffix()[m];
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
		List<ItemStack> list = Lists.newArrayList();
		list.addAll(this.getDrops(world, pos, state, 0));
		for (ItemStack item : list) {
			double x = player.posX;
			double y = player.posY + 0.25D;
			double z = player.posZ;
			EntityItem drop = new EntityItem(world, x, y, z, item);
			world.spawnEntityInWorld(drop);
		}
		world.setBlockToAir(pos);
		return true;
	}

}
