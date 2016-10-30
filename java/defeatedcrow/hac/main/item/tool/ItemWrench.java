package defeatedcrow.hac.main.item.tool;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.blockstate.EnumSide;
import defeatedcrow.hac.api.energy.IWrenchDC;
import defeatedcrow.hac.core.ClimateCore;
import defeatedcrow.hac.core.energy.BlockTorqueBase;
import defeatedcrow.hac.main.achievement.AchievementClimate;
import defeatedcrow.hac.main.achievement.AcvHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWrench extends ItemPickaxeDC implements IWrenchDC {

	private final int maxMeta;

	private static String[] names = {
			"brass" };

	public ItemWrench(ToolMaterial m) {
		super(m, "brass");
		maxMeta = 0;
		this.setFull3D();
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player != null && stack != null && !world.isRemote && player.isSneaking()) {
			IBlockState tile = world.getBlockState(pos);
			if (tile != null && tile.getBlock() instanceof BlockTorqueBase) {
				EnumFacing face = tile.getValue(DCState.SIDE).getFacing();
				world.setBlockState(pos, tile.withProperty(DCState.SIDE, EnumSide.fromFacing(face.getOpposite())));

				// achievement
				if (!player.hasAchievement(AchievementClimate.MACHINE_CHANGE)) {
					AcvHelper.addMachineAcievement(player, AchievementClimate.MACHINE_CHANGE);
				}

				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.PASS;
	}

	public int getMaxMeta() {
		return maxMeta;
	}

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
