package defeatedcrow.hac.machine.item;

import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.DCLogger;
import defeatedcrow.hac.core.base.DCItem;
import defeatedcrow.hac.core.energy.TileTorqueBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemTorqueChecker extends DCItem {

	private final int maxMeta;

	private static String[] names = {
			"steel"
	};

	public ItemTorqueChecker() {
		super();
		maxMeta = 0;
		this.setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse2(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		if (world != null && !world.isRemote) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile != null && tile instanceof TileTorqueBase) {
				TileTorqueBase tq = (TileTorqueBase) tile;
				float torque = tq.prevTorque;
				player.sendMessage(new TextComponentString("== Torque Checker =="));
				player.sendMessage(new TextComponentString("Current Amount: " + torque));
				DCLogger.debugLog("Current Amount(dbg): " + torque);
			}
		}
		return EnumActionResult.SUCCESS;
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
		String s = "items/tool/torque_checker_" + names[meta];
		if (f) {
			s = "textures/" + s;
		}
		return ClimateCore.PACKAGE_ID + ":" + s;
	}

}
