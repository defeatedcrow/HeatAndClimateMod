package defeatedcrow.hac.main.block.container;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;

public class BlockEnemyCont extends DCSimpleBlock implements ITexturePath, IRapidCollectables {

	public BlockEnemyCont(Material m, String s, int max) {
		super(m, s, max, false);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setResistance(5.0F);
		this.setSoundType(SoundType.STONE);
	}

	@Override
	public String[] getNameSuffix() {
		String[] name = {
				"rotten",
				"bone",
				"spider",
				"ender",
				"powder" };
		return name;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 15;
		if (m > 4)
			m = 4;
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
		if (m > 4)
			m = 4;
		String b = "dcs_climate:items/block/cont/";
		return b + "metalbox_" + getNameSuffix()[m];
	}

	/* IRapidCollectables */

	@Override
	public boolean isCollectable(ItemStack item) {
		return item != null && item.getItem() != null && item.getItem() instanceof ItemSpade;
	}

	@Override
	public int getCollectArea(ItemStack item) {
		return 1;
	}

	@Override
	public boolean doMining(World world, BlockPos pos, IBlockState state, EntityPlayer player, ItemStack tool) {
		List<ItemStack> list = this.getDrops(world, pos, state, 0);
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
