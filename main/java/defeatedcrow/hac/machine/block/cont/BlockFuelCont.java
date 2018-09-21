package defeatedcrow.hac.machine.block.cont;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;
import defeatedcrow.hac.core.util.DCUtil;
import defeatedcrow.hac.machine.MachineInit;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockFuelCont extends DCSimpleBlock implements ITexturePath, IRapidCollectables {

	public BlockFuelCont(String s) {
		super(Material.CLAY, s, 3, true);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(5.0F);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"coke",
				"carbide",
				"smokeless",
				"synthetic"
		};
		return name;
	}

	public static ItemStack[] containedItem() {
		ItemStack[] ret = new ItemStack[4];
		ret[0] = new ItemStack(MachineInit.reagent, 9, 13);
		ret[1] = new ItemStack(MachineInit.reagent, 9, 9);
		ret[2] = new ItemStack(MachineInit.reagent, 9, 8);
		ret[3] = new ItemStack(MachineInit.reagent, 9, 2);

		return ret;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 15;
		if (m > 3)
			m = 3;
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
		list.add(b + "t_coke");
		list.add(b + "t_carbide");
		list.add(b + "t_smokeless");
		list.add(b + "t_synthetic");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int m = meta & 15;
		if (m > 3)
			m = 3;
		String b = "dcs_climate:items/block/cont/";
		return b + "metalbox_" + getNameSuffix()[m];
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
