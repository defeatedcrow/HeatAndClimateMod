package defeatedcrow.hac.main.achievement;

import java.util.List;

import com.google.common.collect.Lists;

import defeatedcrow.hac.core.DCInit;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.magic.MagicInit;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AchievementClimate {

	private static final String BR = System.getProperty("line.separator");
	public static final List<Achievement> ACHIEVEMENTS = Lists.<Achievement> newArrayList();
	public static AchievementPage page;

	/* climate */
	public static final Achievement CLIMATE_MASTER = (new Achievement("achievement.climate_master",
			"dcs.climate_master", 3, -2, new ItemStack(MainInit.stevenson_screen, 1, 0), (Achievement) null) {
		@Override
		@SideOnly(Side.CLIENT)
		public String getDescription() {
			return super.getDescription() + "\n"
					+ I18n.translateToLocal("dcs.achievement_additional_desc.climate_master");
		}
	}).initIndependentStat().registerStat().setSpecial();

	public static final Achievement CLIMATE_DAMAGE = (new Achievement("achievement.climate_damage",
			"dcs.climate_damage", -2, -2, MagicInit.infernalFlame, (Achievement) null)).initIndependentStat()
					.registerStat();

	public static final Achievement CLIMATE_ARMOR = (new Achievement("achievement.climate_prevent",
			"dcs.climate_prevent", -1, -2, MainInit.linenUnder, CLIMATE_DAMAGE)).registerStat();

	public static final Achievement CLIMATE_CHECKER = (new Achievement("achievement.climate_checker",
			"dcs.climate_checker", 0, -2, DCInit.climate_checker, CLIMATE_ARMOR)).registerStat();

	public static final Achievement CLIMATE_SMELT = (new Achievement("achievement.climate_smelt", "dcs.climate_smelt",
			1, -2, MainInit.bakedApple, CLIMATE_CHECKER)).registerStat();

	/* machine */
	public static final Achievement MACHINE_MASTER = (new Achievement("achievement.machine_master",
			"dcs.machine_master", 3, 0, MachineInit.windmill, (Achievement) null) {
		@Override
		@SideOnly(Side.CLIENT)
		public String getDescription() {
			return super.getDescription() + "\n"
					+ I18n.translateToLocal("dcs.achievement_additional_desc.machine_master");
		}
	}).initIndependentStat().registerStat().setSpecial();

	public static final Achievement MACHINE_GEAR = (new Achievement("achievement.machine_gear", "dcs.machine_gear", -2,
			0, new ItemStack(MainInit.materials, 1, 5), (Achievement) null)).initIndependentStat().registerStat();

	public static final Achievement MACHINE_PLACE = (new Achievement("achievement.machine_place", "dcs.machine_place",
			-1, 0, MachineInit.shaft_s, MACHINE_GEAR)).registerStat();

	public static final Achievement MACHINE_CHANGE = (new Achievement("achievement.machine_rotate",
			"dcs.machine_rotate", 0, 0, MainInit.wrench, MACHINE_PLACE)).registerStat();

	public static final Achievement MACHINE_USING = (new Achievement("achievement.machine_stonemill",
			"dcs.machine_stonemill", 1, 0, MachineInit.stonemill, MACHINE_CHANGE)).registerStat();

	/* magic */
	public static final Achievement MAGIC_MASTER = (new Achievement("achievement.magic_master", "dcs.magic_master", 3,
			2, new ItemStack(MainInit.gems, 1, 4), (Achievement) null) {
		@Override
		@SideOnly(Side.CLIENT)
		public String getDescription() {
			return super.getDescription() + "\n"
					+ I18n.translateToLocal("dcs.achievement_additional_desc.magic_master");
		}
	}).initIndependentStat().registerStat().setSpecial();

	public static final Achievement MAGIC_GEM = (new Achievement("achievement.magic_getgem", "dcs.magic_getgem", -2, 2,
			new ItemStack(MainInit.gems, 1, 0), (Achievement) null)).initIndependentStat().registerStat();

	public static final Achievement MAGIC_GEM_RARE = (new Achievement("achievement.magic_raregem", "dcs.magic_raregem",
			-1, 2, new ItemStack(MainInit.gems, 1, 7), MAGIC_GEM)).registerStat();

	public static final Achievement MAGIC_CHARM = (new Achievement("achievement.magic_charm", "dcs.magic_charm", 0, 2,
			new ItemStack(MagicInit.pendant, 1, 0), MAGIC_GEM_RARE)).registerStat();

	public static final Achievement MAGIC_KNIFE = (new Achievement("achievement.magic_knife", "dcs.magic_knife", 1, 2,
			new ItemStack(MagicInit.daggerSilver, 1, 0), MAGIC_CHARM)).registerStat();

	public static void load() {

		Achievement[] list = new Achievement[] {
				CLIMATE_MASTER,
				CLIMATE_DAMAGE,
				CLIMATE_CHECKER,
				CLIMATE_ARMOR,
				CLIMATE_SMELT,

				MACHINE_MASTER,
				MACHINE_GEAR,
				MACHINE_PLACE,
				MACHINE_CHANGE,
				MACHINE_USING,

				MAGIC_MASTER,
				MAGIC_GEM,
				MAGIC_GEM_RARE,
				MAGIC_CHARM,
				MAGIC_KNIFE };

		page = new AchievementPage("HeatAndClimate", list);
		AchievementPage.registerAchievementPage(page);
	}

}
