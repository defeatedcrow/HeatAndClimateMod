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

public class BlockCardboard extends DCSidedBlock implements ITexturePath, IRapidCollectables {

	public BlockCardboard(Material m, String s, int max) {
		super(m, s, max, false);
		this.setSoundType(SoundType.CLOTH);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(5.0F);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"beef",
				"pork",
				"chicken",
				"sheep",
				"egg",
				"wool"
		};
		return name;
	}

	public static ItemStack[] containedItem() {
		ItemStack[] ret = new ItemStack[6];
		ret[0] = new ItemStack(Items.BEEF, 8);
		ret[1] = new ItemStack(Items.PORKCHOP, 8);
		ret[2] = new ItemStack(Items.CHICKEN, 8);
		ret[3] = new ItemStack(Items.MUTTON, 8);
		ret[4] = new ItemStack(Items.EGG, 8);
		ret[5] = new ItemStack(Blocks.WOOL, 8);

		return ret;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 7;
		if (m > 5)
			m = 5;
		String b = "dcs_climate:blocks/cont/";
		switch (side) {
		case 0:
			return b + "cardboard_t";
		case 1:
			return b + "cardboard_b";
		case 2:
		case 3:
			return face ? b + "cardboard_f_" + getNameSuffix()[m] : b + "cardboard_s";
		case 4:
		case 5:
			return face ? b + "cardboard_s" : b + "cardboard_f_" + getNameSuffix()[m];
		}
		return super.getTexture(meta, side, face);
	}

	public static List<String> getTexList() {
		List<String> list = new ArrayList<String>();
		String b = "dcs_climate:blocks/cont/";
		list.add(b + "cardboard_f_beef");
		list.add(b + "cardboard_f_pork");
		list.add(b + "cardboard_f_chicken");
		list.add(b + "cardboard_f_sheep");
		list.add(b + "cardboard_f_egg");
		list.add(b + "cardboard_f_wool");
		list.add(b + "cardboard_s");
		list.add(b + "cardboard_t");
		list.add(b + "cardboard_b");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int m = meta & 7;
		if (m > 5)
			m = 5;
		String b = "dcs_climate:items/block/cont/";
		return b + "cardboard_" + getNameSuffix()[m];
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
