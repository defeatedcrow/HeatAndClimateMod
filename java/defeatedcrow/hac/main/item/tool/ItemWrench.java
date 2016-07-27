package defeatedcrow.hac.main.item.tool;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import defeatedcrow.hac.api.energy.IWrenchDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.energy.BlockTorqueBase;

public class ItemWrench extends DCItem implements IWrenchDC {

	private final int maxMeta;

	private static String[] names = { "brass" };

	public ItemWrench() {
		super();
		maxMeta = 0;
		this.setFull3D();
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (stack != null && !world.isRemote && player != null && player.isSneaking()) {
			IBlockState tile = world.getBlockState(pos);
			if (tile != null && tile.getBlock() instanceof BlockTorqueBase) {
				List<ItemStack> drops = tile.getBlock().getDrops(world, pos, tile, 0);
				for (ItemStack ret : drops) {
					EntityItem drop = new EntityItem(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
							ret);
					world.spawnEntityInWorld(drop);
				}
				world.setBlockToAir(pos);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.PASS;
	}

	@Override
	public int getMaxMeta() {
		return maxMeta;
	}

	@Override
	public String[] getNameSuffix() {
		return names;
	}

	@Override
	public String getTexPath(int meta, boolean f) {
		String s = "items/tool/wrench_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
