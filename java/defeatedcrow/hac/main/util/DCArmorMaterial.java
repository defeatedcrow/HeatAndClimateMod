package defeatedcrow.hac.main.util;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import defeatedcrow.hac.main.MainInit;

public class DCArmorMaterial {

	private DCArmorMaterial() {
	}

	public static ItemArmor.ArmorMaterial DC_LINEN;
	public static ItemArmor.ArmorMaterial DC_CLOTH;
	public static ItemArmor.ArmorMaterial DC_BRASS;
	public static ItemArmor.ArmorMaterial DC_STEEL;
	public static ItemArmor.ArmorMaterial DC_SAPPHIRE;
	public static ItemArmor.ArmorMaterial DC_CHALCEDONY;

	public static final ItemArmor.ArmorMaterial[] VAR = {
			DC_BRASS,
			DC_STEEL,
			DC_CHALCEDONY,
			DC_SAPPHIRE,
			DC_LINEN,
			DC_CLOTH };

	public static DCMaterial getMaterial(ItemArmor.ArmorMaterial mat) {
		if (mat == DC_LINEN) {
			return DCMaterial.LINEN;
		}
		if (mat == DC_CLOTH) {
			return DCMaterial.CLOTH;
		}
		if (mat == DC_BRASS) {
			return DCMaterial.BRASS;
		}
		if (mat == DC_STEEL) {
			return DCMaterial.STEEL;
		}
		if (mat == DC_CHALCEDONY) {
			return DCMaterial.CHALCEDONY;
		}
		if (mat == DC_SAPPHIRE) {
			return DCMaterial.SAPPHIRE;
		}
		return null;
	}

	public static float getClimateResistance(ItemArmor.ArmorMaterial mat) {
		if (mat == DCArmorMaterial.DC_LINEN) {
			return DCMaterial.LINEN.prevHeat;
		}
		if (mat == DCArmorMaterial.DC_CLOTH) {
			return DCMaterial.CLOTH.prevHeat;
		}
		if (mat == DCArmorMaterial.DC_BRASS) {
			return DCMaterial.BRASS.prevHeat;
		}
		if (mat == DCArmorMaterial.DC_STEEL) {
			return DCMaterial.STEEL.prevHeat;
		}
		if (mat == DCArmorMaterial.DC_CHALCEDONY) {
			return DCMaterial.CHALCEDONY.prevHeat;
		}
		if (mat == DCArmorMaterial.DC_SAPPHIRE) {
			return DCMaterial.SAPPHIRE.prevHeat;
		}

		if (mat == ArmorMaterial.LEATHER) {
			return 3.0F;
		}
		if (mat == ArmorMaterial.CHAIN) {
			return 0.5F;
		}
		if (mat == ArmorMaterial.IRON) {
			return 0.5F;
		}
		if (mat == ArmorMaterial.GOLD) {
			return 0.5F;
		}
		if (mat == ArmorMaterial.DIAMOND) {
			return 1.0F;
		}

		return 0.5F;
	}

	public static void load() {
		DCArmorMaterial.DC_LINEN = EnumHelper.addArmorMaterial("dcs_" + DCMaterial.LINEN.name, "dcs_" + DCMaterial.LINEN.name,
				DCMaterial.LINEN.armorDur, DCMaterial.LINEN.reduceDam, DCMaterial.LINEN.enchant);
		DCArmorMaterial.DC_LINEN.customCraftingMaterial = MainInit.repairPatty;

		DCArmorMaterial.DC_CLOTH = EnumHelper.addArmorMaterial("dcs_" + DCMaterial.CLOTH.name, "dcs_" + DCMaterial.CLOTH.name,
				DCMaterial.CLOTH.armorDur, DCMaterial.CLOTH.reduceDam, DCMaterial.CLOTH.enchant);
		DCArmorMaterial.DC_CLOTH.customCraftingMaterial = MainInit.repairPatty;

		DCArmorMaterial.DC_BRASS = EnumHelper.addArmorMaterial("dcs_" + DCMaterial.BRASS.name, "dcs_" + DCMaterial.BRASS.name,
				DCMaterial.BRASS.armorDur, DCMaterial.BRASS.reduceDam, DCMaterial.BRASS.enchant);
		DCArmorMaterial.DC_BRASS.customCraftingMaterial = MainInit.repairPatty;

		DCArmorMaterial.DC_STEEL = EnumHelper.addArmorMaterial("dcs_" + DCMaterial.STEEL.name, "dcs_" + DCMaterial.STEEL.name,
				DCMaterial.STEEL.armorDur, DCMaterial.STEEL.reduceDam, DCMaterial.STEEL.enchant);
		DCArmorMaterial.DC_STEEL.customCraftingMaterial = MainInit.repairPatty;

		DCArmorMaterial.DC_CHALCEDONY = EnumHelper.addArmorMaterial("dcs_" + DCMaterial.CHALCEDONY.name, "dcs_"
				+ DCMaterial.CHALCEDONY.name, DCMaterial.CHALCEDONY.armorDur, DCMaterial.CHALCEDONY.reduceDam,
				DCMaterial.CHALCEDONY.enchant);
		DCArmorMaterial.DC_CHALCEDONY.customCraftingMaterial = MainInit.repairPatty;

		DCArmorMaterial.DC_SAPPHIRE = EnumHelper.addArmorMaterial("dcs_" + DCMaterial.SAPPHIRE.name, "dcs_" + DCMaterial.SAPPHIRE.name,
				DCMaterial.SAPPHIRE.armorDur, DCMaterial.SAPPHIRE.reduceDam, DCMaterial.SAPPHIRE.enchant);
		DCArmorMaterial.DC_SAPPHIRE.customCraftingMaterial = MainInit.repairPatty;
	}

}
