package defeatedcrow.hac.main.util;

import defeatedcrow.hac.api.damage.DamageAPI;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class DCArmorMaterial {

	private DCArmorMaterial() {}

	public static ItemArmor.ArmorMaterial DC_LINEN;
	public static ItemArmor.ArmorMaterial DC_CLOTH;
	public static ItemArmor.ArmorMaterial DC_BRASS;
	public static ItemArmor.ArmorMaterial DC_STEEL;
	public static ItemArmor.ArmorMaterial DC_SAPPHIRE;
	public static ItemArmor.ArmorMaterial DC_CHALCEDONY;
	public static ItemArmor.ArmorMaterial DC_TITANIUM;
	public static ItemArmor.ArmorMaterial DC_SYNTHETIC;
	public static ItemArmor.ArmorMaterial DC_WOOL;

	public static final ItemArmor.ArmorMaterial[] VAR = {
			DC_BRASS, DC_STEEL, DC_CHALCEDONY, DC_SAPPHIRE, DC_LINEN, DC_CLOTH, DC_TITANIUM, DC_SYNTHETIC, DC_WOOL
	};

	public static DCMaterialEnum getMaterial(ItemArmor.ArmorMaterial mat) {
		if (mat == DC_LINEN)
			return DCMaterialEnum.LINEN;
		if (mat == DC_CLOTH)
			return DCMaterialEnum.CLOTH;
		if (mat == DC_BRASS)
			return DCMaterialEnum.BRASS;
		if (mat == DC_STEEL)
			return DCMaterialEnum.STEEL;
		if (mat == DC_CHALCEDONY)
			return DCMaterialEnum.CHALCEDONY;
		if (mat == DC_SAPPHIRE)
			return DCMaterialEnum.SAPPHIRE;
		if (mat == DC_TITANIUM)
			return DCMaterialEnum.TITANIUM;
		if (mat == DC_SYNTHETIC)
			return DCMaterialEnum.SYNTHETIC;
		if (mat == DC_WOOL)
			return DCMaterialEnum.WOOL;
		return null;
	}

	public static void load() {
		DCArmorMaterial.DC_LINEN = EnumHelper.addArmorMaterial("dcs_" + DCMaterialEnum.LINEN.name,
				"dcs_" + DCMaterialEnum.LINEN.name, DCMaterialEnum.LINEN.armorDur, DCMaterialEnum.LINEN.reduceDam,
				DCMaterialEnum.LINEN.enchant, DCMaterialEnum.LINEN.sound, DCMaterialEnum.LINEN.toughness);
		DCArmorMaterial.DC_LINEN.repairMaterial = DCMaterialEnum.LINEN.repairItem;

		DCArmorMaterial.DC_CLOTH = EnumHelper.addArmorMaterial("dcs_" + DCMaterialEnum.CLOTH.name,
				"dcs_" + DCMaterialEnum.CLOTH.name, DCMaterialEnum.CLOTH.armorDur, DCMaterialEnum.CLOTH.reduceDam,
				DCMaterialEnum.CLOTH.enchant, DCMaterialEnum.CLOTH.sound, DCMaterialEnum.CLOTH.toughness);
		DCArmorMaterial.DC_CLOTH.repairMaterial = DCMaterialEnum.CLOTH.repairItem;

		DCArmorMaterial.DC_BRASS = EnumHelper.addArmorMaterial("dcs_" + DCMaterialEnum.BRASS.name,
				"dcs_" + DCMaterialEnum.BRASS.name, DCMaterialEnum.BRASS.armorDur, DCMaterialEnum.BRASS.reduceDam,
				DCMaterialEnum.BRASS.enchant, DCMaterialEnum.BRASS.sound, DCMaterialEnum.BRASS.toughness);
		DCArmorMaterial.DC_BRASS.repairMaterial = DCMaterialEnum.BRASS.repairItem;

		DCArmorMaterial.DC_STEEL = EnumHelper.addArmorMaterial("dcs_" + DCMaterialEnum.STEEL.name,
				"dcs_" + DCMaterialEnum.STEEL.name, DCMaterialEnum.STEEL.armorDur, DCMaterialEnum.STEEL.reduceDam,
				DCMaterialEnum.STEEL.enchant, DCMaterialEnum.STEEL.sound, DCMaterialEnum.STEEL.toughness);
		DCArmorMaterial.DC_STEEL.repairMaterial = DCMaterialEnum.STEEL.repairItem;

		DCArmorMaterial.DC_CHALCEDONY = EnumHelper.addArmorMaterial("dcs_" + DCMaterialEnum.CHALCEDONY.name,
				"dcs_" + DCMaterialEnum.CHALCEDONY.name, DCMaterialEnum.CHALCEDONY.armorDur,
				DCMaterialEnum.CHALCEDONY.reduceDam, DCMaterialEnum.CHALCEDONY.enchant, DCMaterialEnum.CHALCEDONY.sound,
				DCMaterialEnum.CHALCEDONY.toughness);
		DCArmorMaterial.DC_CHALCEDONY.repairMaterial = DCMaterialEnum.CHALCEDONY.repairItem;

		DCArmorMaterial.DC_SAPPHIRE = EnumHelper.addArmorMaterial("dcs_" + DCMaterialEnum.SAPPHIRE.name,
				"dcs_" + DCMaterialEnum.SAPPHIRE.name, DCMaterialEnum.SAPPHIRE.armorDur,
				DCMaterialEnum.SAPPHIRE.reduceDam, DCMaterialEnum.SAPPHIRE.enchant, DCMaterialEnum.SAPPHIRE.sound,
				DCMaterialEnum.SAPPHIRE.toughness);
		DCArmorMaterial.DC_SAPPHIRE.repairMaterial = DCMaterialEnum.SAPPHIRE.repairItem;

		DCArmorMaterial.DC_SYNTHETIC = EnumHelper.addArmorMaterial("dcs_" + DCMaterialEnum.SYNTHETIC.name,
				"dcs_" + DCMaterialEnum.SYNTHETIC.name, DCMaterialEnum.SYNTHETIC.armorDur,
				DCMaterialEnum.SYNTHETIC.reduceDam, DCMaterialEnum.SYNTHETIC.enchant, DCMaterialEnum.SYNTHETIC.sound,
				DCMaterialEnum.SYNTHETIC.toughness);
		DCArmorMaterial.DC_SYNTHETIC.repairMaterial = DCMaterialEnum.SYNTHETIC.repairItem;

		DCArmorMaterial.DC_TITANIUM = EnumHelper.addArmorMaterial("dcs_" + DCMaterialEnum.TITANIUM.name,
				"dcs_" + DCMaterialEnum.TITANIUM.name, DCMaterialEnum.TITANIUM.armorDur,
				DCMaterialEnum.TITANIUM.reduceDam, DCMaterialEnum.TITANIUM.enchant, DCMaterialEnum.TITANIUM.sound,
				DCMaterialEnum.TITANIUM.toughness);
		DCArmorMaterial.DC_TITANIUM.repairMaterial = DCMaterialEnum.TITANIUM.repairItem;

		DCArmorMaterial.DC_WOOL = EnumHelper.addArmorMaterial("dcs_" + DCMaterialEnum.WOOL.name,
				"dcs_" + DCMaterialEnum.WOOL.name, DCMaterialEnum.WOOL.armorDur, DCMaterialEnum.WOOL.reduceDam,
				DCMaterialEnum.WOOL.enchant, DCMaterialEnum.WOOL.sound, DCMaterialEnum.WOOL.toughness);
		DCArmorMaterial.DC_WOOL.repairMaterial = DCMaterialEnum.WOOL.repairItem;

		// 耐性登録
		DamageAPI.armorRegister.registerMaterial(DCArmorMaterial.DC_LINEN, DCMaterialEnum.LINEN.prevHeat,
				DCMaterialEnum.LINEN.prevCold);
		DamageAPI.armorRegister.registerMaterial(DCArmorMaterial.DC_CLOTH, DCMaterialEnum.CLOTH.prevHeat,
				DCMaterialEnum.CLOTH.prevCold);
		DamageAPI.armorRegister.registerMaterial(DCArmorMaterial.DC_SYNTHETIC, DCMaterialEnum.SYNTHETIC.prevHeat,
				DCMaterialEnum.SYNTHETIC.prevCold);
		DamageAPI.armorRegister.registerMaterial(DCArmorMaterial.DC_CHALCEDONY, 1.0F);
		DamageAPI.armorRegister.registerMaterial(DCArmorMaterial.DC_SAPPHIRE, 0.5F);
		DamageAPI.armorRegister.registerMaterial(DCArmorMaterial.DC_TITANIUM, DCMaterialEnum.TITANIUM.prevHeat,
				DCMaterialEnum.TITANIUM.prevCold);
		DamageAPI.armorRegister.registerMaterial(DCArmorMaterial.DC_WOOL, DCMaterialEnum.WOOL.prevHeat,
				DCMaterialEnum.WOOL.prevCold);
	}

}
