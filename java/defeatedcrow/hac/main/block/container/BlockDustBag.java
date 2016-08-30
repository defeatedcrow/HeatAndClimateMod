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
import defeatedcrow.hac.core.base.DCSidedBlock;
import defeatedcrow.hac.core.base.ITexturePath;

public class BlockDustBag extends DCSidedBlock implements ITexturePath, IRapidCollectables {

	public BlockDustBag(Material m, String s, int max) {
		super(m, s, max, true);
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
				"rice" };
		return name;
	}

	@Override
	public String getTexture(int meta, int side, boolean face) {
		int m = meta & 3;
		if (m > 3)
			m = 3;
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
		list.add(b + "_s");
		list.add(b + "_b");
		return list;
	}

	@Override
	public String getTexPath(int meta, boolean isFull) {
		int m = meta & 3;
		String b = "dcs_climate:items/block/cont/";
		return b + "bags_" + getNameSuffix()[m];
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
	public boolean doCollect(World world, BlockPos pos, IBlockState state, EntityPlayer player, ItemStack tool) {
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
