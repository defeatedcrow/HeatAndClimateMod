package defeatedcrow.hac.machine;

import defeatedcrow.hac.main.ClimateMain;
import defeatedcrow.hac.main.achievement.AchievementClimate;
import net.minecraft.entity.player.EntityPlayer;

public enum MachineTier {
	Tier1(
			1),
	Tier2(
			2),
	Tier3(
			3);

	private final int id;

	private MachineTier(int i) {
		id = i;
	}

	public MachineTier getTierByID(int i) {
		switch (i) {
		case 1:
			return Tier1;
		case 2:
			return Tier2;
		case 3:
			return Tier3;
		default:
			return Tier1;
		}
	}

	public static boolean canHandleTier(EntityPlayer player, int i) {
		if (i > 2) {
			return ClimateMain.proxy.hasAchivement(player, AchievementClimate.MACHINE_MASTER);
		} else {
			return true;
		}
	}

}
