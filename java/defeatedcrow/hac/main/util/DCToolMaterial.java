package defeatedcrow.hac.main.util;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class DCToolMaterial {

	private DCToolMaterial() {
	}

	public static Item.ToolMaterial DC_SILVER;
	public static Item.ToolMaterial DC_NICKELSILVER;
	public static Item.ToolMaterial DC_BRASS;
	public static Item.ToolMaterial DC_STEEL;
	public static Item.ToolMaterial DC_CHALCEDONY;
	public static Item.ToolMaterial DC_SAPPHIRE;

	public static Item.ToolMaterial getToolMaterial(int i) {

		if (i == 0) {
			return DCToolMaterial.DC_BRASS;
		}
		if (i == 1) {
			return DCToolMaterial.DC_STEEL;
		}
		if (i == 2) {
			return DCToolMaterial.DC_SILVER;
		}
		if (i == 3) {
			return DCToolMaterial.DC_NICKELSILVER;
		}
		if (i == 4) {
			return DCToolMaterial.DC_CHALCEDONY;
		}
		if (i == 5) {
			return DCToolMaterial.DC_SAPPHIRE;
		}
		return null;
	}

	public static DCMaterial getMaterial(int i) {
		if (i == 0) {
			return DCMaterial.BRASS;
		}
		if (i == 1) {
			return DCMaterial.STEEL;
		}
		if (i == 2) {
			return DCMaterial.SILVER;
		}
		if (i == 3) {
			return DCMaterial.NICKELSILVER;
		}
		if (i == 4) {
			return DCMaterial.CHALCEDONY;
		}
		if (i == 5) {
			return DCMaterial.SAPPHIRE;
		}
		return null;
	}

	public static void load() {
		DCToolMaterial.DC_SILVER = EnumHelper.addToolMaterial("dcs_" + DCMaterial.SILVER.name, DCMaterial.SILVER.harvestTier,
				DCMaterial.SILVER.duration, DCMaterial.SILVER.efficiency, DCMaterial.SILVER.attackDam, DCMaterial.SILVER.enchant);
		DCToolMaterial.DC_SILVER.setRepairItem(DCMaterial.SILVER.repairItem);

		DCToolMaterial.DC_NICKELSILVER = EnumHelper.addToolMaterial("dcs_" + DCMaterial.NICKELSILVER.name,
				DCMaterial.NICKELSILVER.harvestTier, DCMaterial.NICKELSILVER.duration, DCMaterial.NICKELSILVER.efficiency,
				DCMaterial.NICKELSILVER.attackDam, DCMaterial.NICKELSILVER.enchant);
		DCToolMaterial.DC_NICKELSILVER.setRepairItem(DCMaterial.NICKELSILVER.repairItem);

		DCToolMaterial.DC_BRASS = EnumHelper.addToolMaterial("dcs_" + DCMaterial.BRASS.name, DCMaterial.BRASS.harvestTier,
				DCMaterial.BRASS.duration, DCMaterial.BRASS.efficiency, DCMaterial.BRASS.attackDam, DCMaterial.BRASS.enchant);
		DCToolMaterial.DC_BRASS.setRepairItem(DCMaterial.BRASS.repairItem);

		DCToolMaterial.DC_STEEL = EnumHelper.addToolMaterial("dcs_" + DCMaterial.STEEL.name, DCMaterial.STEEL.harvestTier,
				DCMaterial.STEEL.duration, DCMaterial.STEEL.efficiency, DCMaterial.STEEL.attackDam, DCMaterial.STEEL.enchant);
		DCToolMaterial.DC_STEEL.setRepairItem(DCMaterial.STEEL.repairItem);

		DCToolMaterial.DC_CHALCEDONY = EnumHelper.addToolMaterial("dcs_" + DCMaterial.CHALCEDONY.name, DCMaterial.CHALCEDONY.harvestTier,
				DCMaterial.CHALCEDONY.duration, DCMaterial.CHALCEDONY.efficiency, DCMaterial.CHALCEDONY.attackDam,
				DCMaterial.CHALCEDONY.enchant);
		DCToolMaterial.DC_CHALCEDONY.setRepairItem(DCMaterial.CHALCEDONY.repairItem);

		DCToolMaterial.DC_SAPPHIRE = EnumHelper.addToolMaterial("dcs_" + DCMaterial.SAPPHIRE.name, DCMaterial.SAPPHIRE.harvestTier,
				DCMaterial.SAPPHIRE.duration, DCMaterial.SAPPHIRE.efficiency, DCMaterial.SAPPHIRE.attackDam, DCMaterial.SAPPHIRE.enchant);
		DCToolMaterial.DC_SAPPHIRE.setRepairItem(DCMaterial.SAPPHIRE.repairItem);
	}

}
