package defeatedcrow.hac.main.achievement;

import defeatedcrow.hac.main.ClimateMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.Achievement;

// 非リモート環境のみ
public class AcvHelper {

	public static boolean hasClimateMaster(EntityPlayer player) {
		if (player != null) {
			if (player.worldObj.isRemote)
				return ClimateMain.proxy.hasAchivement(player, AchievementClimate.CLIMATE_MASTER);
			else
				return player.hasAchievement(AchievementClimate.CLIMATE_MASTER);
		} else
			return false;
	}

	public static boolean checkClimateMaster(EntityPlayer player) {
		if (player == null)
			return false;
		boolean a = player.hasAchievement(AchievementClimate.CLIMATE_CHECKER);
		boolean b = player.hasAchievement(AchievementClimate.CLIMATE_DAMAGE);
		boolean c = player.hasAchievement(AchievementClimate.CLIMATE_ARMOR);
		boolean d = player.hasAchievement(AchievementClimate.CLIMATE_SMELT);
		if (a && b && c && d && !hasClimateMaster(player)) {
			player.addStat(AchievementClimate.CLIMATE_MASTER);
			return true;
		}
		return false;
	}

	public static boolean addClimateAcievement(EntityPlayer player, Achievement acv) {
		if (player != null && !player.hasAchievement(acv)) {
			player.addStat(acv);
			checkClimateMaster(player);
			return true;
		} else
			return false;
	}

	public static boolean hasMachineMaster(EntityPlayer player) {
		if (player != null) {
			if (player.worldObj.isRemote)
				return ClimateMain.proxy.hasAchivement(player, AchievementClimate.MACHINE_MASTER);
			else
				return player.hasAchievement(AchievementClimate.MACHINE_MASTER);
		} else
			return false;
	}

	public static boolean checkMachineMaster(EntityPlayer player) {
		if (player == null)
			return false;
		boolean a = player.hasAchievement(AchievementClimate.MACHINE_GEAR);
		boolean b = player.hasAchievement(AchievementClimate.MACHINE_PLACE);
		boolean c = player.hasAchievement(AchievementClimate.MACHINE_CHANGE);
		boolean d = player.hasAchievement(AchievementClimate.MACHINE_ARROY);
		if (a && b && c && d && !hasMachineMaster(player)) {
			player.addStat(AchievementClimate.MACHINE_MASTER);
			return true;
		}
		return false;
	}

	public static boolean addMachineAcievement(EntityPlayer player, Achievement acv) {
		if (player != null && !player.hasAchievement(acv)) {
			player.addStat(acv);
			checkMachineMaster(player);
			return true;
		} else
			return false;
	}

	public static boolean hasMagicMaster(EntityPlayer player) {
		if (player != null) {
			if (player.worldObj.isRemote)
				return ClimateMain.proxy.hasAchivement(player, AchievementClimate.MAGIC_MASTER);
			else
				return player.hasAchievement(AchievementClimate.MAGIC_MASTER);
		} else
			return false;
	}

	public static boolean checkMagicMaster(EntityPlayer player) {
		if (player == null)
			return false;
		boolean a = player.hasAchievement(AchievementClimate.MAGIC_GEM);
		boolean b = player.hasAchievement(AchievementClimate.MAGIC_GEM_RARE);
		boolean c = player.hasAchievement(AchievementClimate.MAGIC_CHARM);
		boolean d = player.hasAchievement(AchievementClimate.MAGIC_KNIFE);
		if (a && b && c && d && !hasMagicMaster(player)) {
			player.addStat(AchievementClimate.MAGIC_MASTER);
			return true;
		}
		return false;
	}

	public static boolean addMagicAcievement(EntityPlayer player, Achievement acv) {
		if (player != null && !player.hasAchievement(acv)) {
			player.addStat(acv);
			checkMagicMaster(player);
			return true;
		} else
			return false;
	}

	public static void forceOpenMaster(EntityPlayer player) {
		player.addStat(AchievementClimate.CLIMATE_MASTER);
		player.addStat(AchievementClimate.MACHINE_MASTER);
		player.addStat(AchievementClimate.MAGIC_MASTER);
	}

}
