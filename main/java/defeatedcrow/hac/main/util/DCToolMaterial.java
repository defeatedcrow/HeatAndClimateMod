package defeatedcrow.hac.main.util;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class DCToolMaterial {

	private DCToolMaterial() {}

	public static Item.ToolMaterial DC_SILVER;
	public static Item.ToolMaterial DC_NICKELSILVER;
	public static Item.ToolMaterial DC_BRASS;
	public static Item.ToolMaterial DC_STEEL;
	public static Item.ToolMaterial DC_CHALCEDONY;
	public static Item.ToolMaterial DC_SAPPHIRE;
	public static Item.ToolMaterial DC_TITANIUM;
	public static Item.ToolMaterial DC_GARNET;
	public static Item.ToolMaterial DC_TOOLMETAL;

	public static Item.ToolMaterial getToolMaterial(int i) {

		if (i == 0)
			return DCToolMaterial.DC_BRASS;
		if (i == 1)
			return DCToolMaterial.DC_STEEL;
		if (i == 2)
			return DCToolMaterial.DC_SILVER;
		if (i == 3)
			return DCToolMaterial.DC_NICKELSILVER;
		if (i == 4)
			return DCToolMaterial.DC_CHALCEDONY;
		if (i == 5)
			return DCToolMaterial.DC_SAPPHIRE;
		if (i == 6)
			return DCToolMaterial.DC_TITANIUM;
		if (i == 7)
			return DCToolMaterial.DC_GARNET;
		if (i == 8)
			return DCToolMaterial.DC_TOOLMETAL;
		return null;
	}

	public static DCMaterialEnum getMaterial(int i) {
		if (i == 0)
			return DCMaterialEnum.BRASS;
		if (i == 1)
			return DCMaterialEnum.STEEL;
		if (i == 2)
			return DCMaterialEnum.SILVER;
		if (i == 3)
			return DCMaterialEnum.NICKELSILVER;
		if (i == 4)
			return DCMaterialEnum.CHALCEDONY;
		if (i == 5)
			return DCMaterialEnum.SAPPHIRE;
		if (i == 6)
			return DCMaterialEnum.TITANIUM;
		if (i == 7)
			return DCMaterialEnum.GARNET;
		if (i == 8)
			return DCMaterialEnum.TOOLMETAL;
		return null;
	}

	public static void load() {
		DCToolMaterial.DC_SILVER = EnumHelper
				.addToolMaterial("dcs_" + DCMaterialEnum.SILVER.name, DCMaterialEnum.SILVER.harvestTier, DCMaterialEnum.SILVER.duration, DCMaterialEnum.SILVER.efficiency, DCMaterialEnum.SILVER.attackDam, DCMaterialEnum.SILVER.enchant);
		DCToolMaterial.DC_SILVER.setRepairItem(DCMaterialEnum.SILVER.repairItem);

		DCToolMaterial.DC_NICKELSILVER = EnumHelper
				.addToolMaterial("dcs_" + DCMaterialEnum.NICKELSILVER.name, DCMaterialEnum.NICKELSILVER.harvestTier, DCMaterialEnum.NICKELSILVER.duration, DCMaterialEnum.NICKELSILVER.efficiency, DCMaterialEnum.NICKELSILVER.attackDam, DCMaterialEnum.NICKELSILVER.enchant);
		DCToolMaterial.DC_NICKELSILVER.setRepairItem(DCMaterialEnum.NICKELSILVER.repairItem);

		DCToolMaterial.DC_BRASS = EnumHelper
				.addToolMaterial("dcs_" + DCMaterialEnum.BRASS.name, DCMaterialEnum.BRASS.harvestTier, DCMaterialEnum.BRASS.duration, DCMaterialEnum.BRASS.efficiency, DCMaterialEnum.BRASS.attackDam, DCMaterialEnum.BRASS.enchant);
		DCToolMaterial.DC_BRASS.setRepairItem(DCMaterialEnum.BRASS.repairItem);

		DCToolMaterial.DC_STEEL = EnumHelper
				.addToolMaterial("dcs_" + DCMaterialEnum.STEEL.name, DCMaterialEnum.STEEL.harvestTier, DCMaterialEnum.STEEL.duration, DCMaterialEnum.STEEL.efficiency, DCMaterialEnum.STEEL.attackDam, DCMaterialEnum.STEEL.enchant);
		DCToolMaterial.DC_STEEL.setRepairItem(DCMaterialEnum.STEEL.repairItem);

		DCToolMaterial.DC_CHALCEDONY = EnumHelper
				.addToolMaterial("dcs_" + DCMaterialEnum.CHALCEDONY.name, DCMaterialEnum.CHALCEDONY.harvestTier, DCMaterialEnum.CHALCEDONY.duration, DCMaterialEnum.CHALCEDONY.efficiency, DCMaterialEnum.CHALCEDONY.attackDam, DCMaterialEnum.CHALCEDONY.enchant);
		DCToolMaterial.DC_CHALCEDONY.setRepairItem(DCMaterialEnum.CHALCEDONY.repairItem);

		DCToolMaterial.DC_SAPPHIRE = EnumHelper
				.addToolMaterial("dcs_" + DCMaterialEnum.SAPPHIRE.name, DCMaterialEnum.SAPPHIRE.harvestTier, DCMaterialEnum.SAPPHIRE.duration, DCMaterialEnum.SAPPHIRE.efficiency, DCMaterialEnum.SAPPHIRE.attackDam, DCMaterialEnum.SAPPHIRE.enchant);
		DCToolMaterial.DC_SAPPHIRE.setRepairItem(DCMaterialEnum.SAPPHIRE.repairItem);

		DCToolMaterial.DC_TITANIUM = EnumHelper
				.addToolMaterial("dcs_" + DCMaterialEnum.TITANIUM.name, DCMaterialEnum.TITANIUM.harvestTier, DCMaterialEnum.TITANIUM.duration, DCMaterialEnum.TITANIUM.efficiency, DCMaterialEnum.TITANIUM.attackDam, DCMaterialEnum.TITANIUM.enchant);
		DCToolMaterial.DC_TITANIUM.setRepairItem(DCMaterialEnum.TITANIUM.repairItem);

		DCToolMaterial.DC_GARNET = EnumHelper
				.addToolMaterial("dcs_" + DCMaterialEnum.GARNET.name, DCMaterialEnum.GARNET.harvestTier, DCMaterialEnum.GARNET.duration, DCMaterialEnum.GARNET.efficiency, DCMaterialEnum.GARNET.attackDam, DCMaterialEnum.GARNET.enchant);
		DCToolMaterial.DC_GARNET.setRepairItem(DCMaterialEnum.GARNET.repairItem);

		DCToolMaterial.DC_TOOLMETAL = EnumHelper
				.addToolMaterial("dcs_" + DCMaterialEnum.TOOLMETAL.name, DCMaterialEnum.TOOLMETAL.harvestTier, DCMaterialEnum.TOOLMETAL.duration, DCMaterialEnum.TOOLMETAL.efficiency, DCMaterialEnum.TOOLMETAL.attackDam, DCMaterialEnum.TOOLMETAL.enchant);
		DCToolMaterial.DC_TOOLMETAL.setRepairItem(DCMaterialEnum.TOOLMETAL.repairItem);
	}

}
