package defeatedcrow.hac.main.block.ores;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import defeatedcrow.hac.api.climate.DCHumidity;
import defeatedcrow.hac.api.climate.IClimate;
import defeatedcrow.hac.api.placeable.IRapidCollectables;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCSimpleBlock;
import defeatedcrow.hac.core.base.ITexturePath;

public class BlockDusts extends DCSimpleBlock implements ITexturePath, IRapidCollectables {

	public BlockDusts(Material m, String s, int max) {
		super(m, s, max, true);
		this.setTickRandomly(true);
		this.setHardness(1.5F);
		this.setResistance(15.0F);
	}

	@Override
	public boolean onClimateChange(World world, BlockPos pos, IBlockState state, IClimate clm) {
		if (clm != null && clm.getHumidity() == DCHumidity.UNDERWATER) {
			return false;
		}
		return super.onClimateChange(world, pos, state, clm);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		world.scheduleUpdate(pos, this, this.tickRate(world));
	}

	private static String[] names = {
			"copper",
			"zinc",
			"nickel",
			"silver",
			"brass",
			"steel",
			"nickelsilver",
			"magnet",
			"tin",
			"bronze" };

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		if (meta >= names.length)
			meta = names.length - 1;
		String s = "blocks/ores/dustblock_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
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
