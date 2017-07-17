package defeatedcrow.hac.main.achievement;

import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

	public static boolean hasClimateSP(EntityPlayer player) {
		if (player != null) {
			if (player.worldObj.isRemote)
				return ClimateMain.proxy.hasAchivement(player, AchievementClimate.CLIMATE_SPECIALIST);
			else
				return player.hasAchievement(AchievementClimate.CLIMATE_SPECIALIST);
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
		if (player.worldObj.isRemote) {
			a = ClimateMain.proxy.hasAchivement(player, AchievementClimate.CLIMATE_CHECKER);
			b = ClimateMain.proxy.hasAchivement(player, AchievementClimate.CLIMATE_DAMAGE);
			c = ClimateMain.proxy.hasAchivement(player, AchievementClimate.CLIMATE_ARMOR);
			d = ClimateMain.proxy.hasAchivement(player, AchievementClimate.CLIMATE_SMELT);
		}

		if (a && b && c && d && !hasClimateMaster(player)) {
			player.addStat(AchievementClimate.CLIMATE_MASTER);
			return true;
		}
		return false;
	}

	public static boolean checkClimateSP(EntityPlayer player) {
		if (player == null)
			return false;
		boolean a = player.hasAchievement(AchievementClimate.METAL_TIER1);
		boolean b = player.hasAchievement(AchievementClimate.METAL_CHAMBER);
		boolean c = player.hasAchievement(AchievementClimate.METAL_TIER2);
		boolean d = player.hasAchievement(AchievementClimate.METAL_TIER3);
		if (a && b && c && d && !hasMachineSP(player)) {
			player.addStat(AchievementClimate.CLIMATE_SPECIALIST);
			if (!player.worldObj.isRemote) {
				ItemStack item = new ItemStack(MainInit.achievementShield, 1, 0);
				String id = player.getDisplayNameString();
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString("owner", id);
				item.setTagCompound(tag);
				EntityItem drop = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, item);
				player.worldObj.spawnEntityInWorld(drop);
			}
			return true;
		}
		return false;
	}

	public static boolean addClimateAcievement(EntityPlayer player, Achievement acv) {
		if (player != null && !player.hasAchievement(acv)) {
			player.addStat(acv);
			checkClimateMaster(player);
			checkClimateSP(player);
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

	public static boolean hasMachineSP(EntityPlayer player) {
		if (player != null) {
			if (player.worldObj.isRemote)
				return ClimateMain.proxy.hasAchivement(player, AchievementClimate.MACHINE_SPECIALIST);
			else
				return player.hasAchievement(AchievementClimate.MACHINE_SPECIALIST);
		} else
			return false;
	}

	public static boolean checkMachineMaster(EntityPlayer player) {
		if (player == null)
			return false;
		boolean a = player.hasAchievement(AchievementClimate.MACHINE_GEAR);
		boolean b = player.hasAchievement(AchievementClimate.MACHINE_PLACE);
		boolean c = player.hasAchievement(AchievementClimate.MACHINE_CHANGE);
		boolean d = player.hasAchievement(AchievementClimate.MACHINE_ALLOY);
		if (a && b && c && d && !hasMachineMaster(player)) {
			player.addStat(AchievementClimate.MACHINE_MASTER);
			return true;
		}
		return false;
	}

	public static boolean checkMachineSP(EntityPlayer player) {
		if (player == null)
			return false;
		boolean a = player.hasAchievement(AchievementClimate.MACHINE_USING);
		boolean b = player.hasAchievement(AchievementClimate.MACHINE_EXCHANGER);
		boolean c = player.hasAchievement(AchievementClimate.MACHINE_SUS);
		boolean d = player.hasAchievement(AchievementClimate.MACHINE_TIER3);
		if (a && b && c && d && !hasMachineSP(player)) {
			player.addStat(AchievementClimate.MACHINE_SPECIALIST);
			if (!player.worldObj.isRemote) {
				ItemStack item = new ItemStack(MainInit.achievementShield, 1, 1);
				String id = player.getDisplayNameString();
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString("owner", id);
				item.setTagCompound(tag);
				EntityItem drop = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, item);
				player.worldObj.spawnEntityInWorld(drop);
			}
			return true;
		}
		return false;
	}

	public static boolean addMachineAcievement(EntityPlayer player, Achievement acv) {
		if (player != null && !player.hasAchievement(acv)) {
			player.addStat(acv);
			checkMachineMaster(player);
			checkMachineSP(player);
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

	public static boolean hasMagicSP(EntityPlayer player) {
		if (player != null) {
			if (player.worldObj.isRemote)
				return ClimateMain.proxy.hasAchivement(player, AchievementClimate.MAGIC_SPECIALIST);
			else
				return player.hasAchievement(AchievementClimate.MAGIC_SPECIALIST);
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

	public static boolean checkMagicSP(EntityPlayer player) {
		if (player == null)
			return false;
		boolean a = player.hasAchievement(AchievementClimate.MAGIC_DAGGER);
		boolean b = player.hasAchievement(AchievementClimate.MAGIC_ELESTIAL);
		boolean c = player.hasAchievement(AchievementClimate.MAGIC_MACE);
		boolean d = player.hasAchievement(AchievementClimate.MAGIC_BIRD);
		if (a && b && c && d && !hasMagicSP(player)) {
			player.addStat(AchievementClimate.MAGIC_SPECIALIST);
			if (!player.worldObj.isRemote) {
				ItemStack item = new ItemStack(MainInit.achievementShield, 1, 2);
				String id = player.getDisplayNameString();
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString("owner", id);
				item.setTagCompound(tag);
				EntityItem drop = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, item);
				player.worldObj.spawnEntityInWorld(drop);
			}
			return true;
		}
		return false;
	}

	public static boolean addMagicAcievement(EntityPlayer player, Achievement acv) {
		if (player != null && !player.hasAchievement(acv)) {
			player.addStat(acv);
			checkMagicMaster(player);
			checkMagicSP(player);
			return true;
		} else
			return false;
	}

	public static void forceOpenMaster(EntityPlayer player) {
		player.addStat(AchievementClimate.CLIMATE_MASTER);
		player.addStat(AchievementClimate.MACHINE_MASTER);
		player.addStat(AchievementClimate.MAGIC_MASTER);
	}

	public static void forceAllClimate(EntityPlayer player) {
		player.addStat(AchievementClimate.CLIMATE_DAMAGE);
		player.addStat(AchievementClimate.CLIMATE_ARMOR);
		player.addStat(AchievementClimate.CLIMATE_CHECKER);
		player.addStat(AchievementClimate.CLIMATE_SMELT);
		player.addStat(AchievementClimate.CLIMATE_MASTER);
		player.addStat(AchievementClimate.METAL_TIER1);
		player.addStat(AchievementClimate.METAL_CHAMBER);
		player.addStat(AchievementClimate.METAL_TIER2);
		player.addStat(AchievementClimate.METAL_TIER3);
		player.addStat(AchievementClimate.CLIMATE_SPECIALIST);
	}

	public static void forceAllMachine(EntityPlayer player) {
		player.addStat(AchievementClimate.MACHINE_GEAR);
		player.addStat(AchievementClimate.MACHINE_PLACE);
		player.addStat(AchievementClimate.MACHINE_CHANGE);
		player.addStat(AchievementClimate.MACHINE_ALLOY);
		player.addStat(AchievementClimate.MACHINE_MASTER);
		player.addStat(AchievementClimate.MACHINE_USING);
		player.addStat(AchievementClimate.MACHINE_EXCHANGER);
		player.addStat(AchievementClimate.MACHINE_SUS);
		player.addStat(AchievementClimate.MACHINE_TIER3);
		player.addStat(AchievementClimate.MACHINE_SPECIALIST);
	}

	public static void forceAllMagic(EntityPlayer player) {
		player.addStat(AchievementClimate.MAGIC_GEM);
		player.addStat(AchievementClimate.MAGIC_GEM_RARE);
		player.addStat(AchievementClimate.MAGIC_CHARM);
		player.addStat(AchievementClimate.MAGIC_KNIFE);
		player.addStat(AchievementClimate.MAGIC_MASTER);
		player.addStat(AchievementClimate.MAGIC_DAGGER);
		player.addStat(AchievementClimate.MAGIC_ELESTIAL);
		player.addStat(AchievementClimate.MAGIC_MACE);
		player.addStat(AchievementClimate.MAGIC_BIRD);
		player.addStat(AchievementClimate.MAGIC_SPECIALIST);
	}

}
