package defeatedcrow.hac.plugin.tan;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import defeatedcrow.hac.api.blockstate.DCState;
import defeatedcrow.hac.api.climate.ClimateAPI;
import defeatedcrow.hac.api.climate.DCHeatTier;
import defeatedcrow.hac.api.damage.DamageAPI;
import defeatedcrow.hac.machine.MachineInit;
import defeatedcrow.hac.main.MainInit;
import net.minecraft.item.ItemStack;
import toughasnails.api.TANBlocks;
import toughasnails.api.item.TANItems;
import toughasnails.config.json.ArmorTemperatureData;
import toughasnails.config.json.BlockStatePredicate;
import toughasnails.config.json.BlockTemperatureData;
import toughasnails.init.ModConfig;

public class DCPluginTaN {

	public static void loadInit() {
		registerBlockData();
		registerHeatSource();
		registerArmor();
	}

	private static void registerArmor() {
		ModConfig.armorTemperatureData.add(new ArmorTemperatureData(Lists
				.newArrayList("dcs_climate:dcs_hat_leather", "dcs_climate:dcs_coat_linen", "dcs_climate:dcs_leggins_linen", "dcs_climate:dcs_pants_linen"),
				-1));

		ModConfig.armorTemperatureData.add(new ArmorTemperatureData(Lists
				.newArrayList("dcs_climate:dum2", "dcs_climate:dum3", "dcs_climate:dcs_leggins_track", "dcs_climate:dum4"),
				-2));

		ModConfig.armorTemperatureData.add(new ArmorTemperatureData(Lists
				.newArrayList("dcs_climate:dum5", "dcs_climate:dum6", "dcs_climate:dcs_leggins_combatdress", "dcs_climate:dum7"),
				-2));

		ModConfig.armorTemperatureData.add(new ArmorTemperatureData(Lists
				.newArrayList("dcs_climate:dcs_hat_cotton", "dcs_climate:dcs_coat_trench", "dcs_climate:dcs_leggins_cloth", "dcs_climate:dcs_pants_cloth"),
				1));

		ModConfig.armorTemperatureData.add(new ArmorTemperatureData(Lists
				.newArrayList("dcs_climate:dum9", "dcs_climate:dcs_hoodle_white", "dcs_climate:dcs_leggins_worker", "dcs_climate:dum10"),
				1));

		ModConfig.armorTemperatureData.add(new ArmorTemperatureData(Lists
				.newArrayList("dcs_climate:dum11", "dcs_climate:dcs_coat_black", "dcs_climate:dcs_leggins_suit", "dcs_climate:dum12"),
				1));

		ModConfig.armorTemperatureData.add(new ArmorTemperatureData(Lists
				.newArrayList("dcs_climate:dum13", "dcs_climate:dcs_coat_magic", "dcs_climate:dcs_leggins_magic", "dcs_climate:dum14"),
				1));

		ModConfig.armorTemperatureData.add(new ArmorTemperatureData(Lists
				.newArrayList("dcs_climate:dcs_wear_wool", "dcs_climate:dcs_jacket_wool", "dcs_climate:dum15", "dcs_climate:dcs_boots_wool"),
				2));

		ModConfig.armorTemperatureData.add(new ArmorTemperatureData(Lists
				.newArrayList("dcs_climate:dcs_wear_fur", "dcs_climate:dcs_hoodie_pea", "dcs_climate:dum16", "dcs_climate:dum17"),
				2));

		ModConfig.armorTemperatureData.add(new ArmorTemperatureData(Lists
				.newArrayList("dcs_climate:dum18", "dcs_climate:dcs_hoodie_mods", "dcs_climate:dum16", "dcs_climate:dum17"),
				2));

		ModConfig.armorTemperatureData.add(new ArmorTemperatureData(Lists
				.newArrayList("dcs_climate:dum19", "dcs_climate:dcs_jacket_linen", "dcs_climate:dcs_shirt_linen", "dcs_climate:dcs_climate_skirt_linen"),
				-1));

		ModConfig.armorTemperatureData.add(new ArmorTemperatureData(Lists
				.newArrayList("dcs_climate:dum20", "dcs_climate:dcs_jacket_cloth", "dcs_climate:dcs_shirt_cloth", "dcs_climate:dcs_climate_skirt_cloth"),
				1));
	}

	private static void registerBlockData() {
		BlockTemperatureData data1 = new BlockTemperatureData(new BlockStatePredicate(MainInit.chamber.getDefaultState()
				.withProperty(DCState.TYPE4, 1), Sets.newHashSet(DCState.TYPE4)), 10.0F);
		BlockTemperatureData data2 = new BlockTemperatureData(new BlockStatePredicate(MainInit.shitirin
				.getDefaultState().withProperty(DCState.TYPE4, 1), Sets.newHashSet(DCState.TYPE4)), 5.0F);
		BlockTemperatureData data3 = new BlockTemperatureData(new BlockStatePredicate(MainInit.fuelStove
				.getDefaultState().withProperty(DCState.TYPE4, 1), Sets.newHashSet(DCState.TYPE4)), 15.0F);
		BlockTemperatureData data4 = new BlockTemperatureData(new BlockStatePredicate(MachineInit.burner
				.getDefaultState().withProperty(DCState.TYPE4, 1), Sets.newHashSet(DCState.TYPE4)), 15.0F);
		ModConfig.blockTemperatureData.put("TempKeysdcs_climate:dcs_device_chamber", Lists.newArrayList(data1));
	}

	private static void registerHeatSource() {
		ClimateAPI.registerBlock.registerHeatBlock(TANBlocks.campfire, 1, DCHeatTier.OVEN);
		ClimateAPI.registerBlock.registerHeatBlock(TANBlocks.campfire, 3, DCHeatTier.OVEN);
		ClimateAPI.registerBlock.registerHeatBlock(TANBlocks.campfire, 5, DCHeatTier.OVEN);
		ClimateAPI.registerBlock.registerHeatBlock(TANBlocks.campfire, 7, DCHeatTier.OVEN);
		ClimateAPI.registerBlock.registerHeatBlock(TANBlocks.campfire, 9, DCHeatTier.OVEN);
		ClimateAPI.registerBlock.registerHeatBlock(TANBlocks.campfire, 11, DCHeatTier.OVEN);
		ClimateAPI.registerBlock.registerHeatBlock(TANBlocks.campfire, 13, DCHeatTier.BOIL);
		ClimateAPI.registerBlock.registerHeatBlock(TANBlocks.campfire, 15, DCHeatTier.HOT);
		ClimateAPI.registerBlock.registerHeatBlock(TANBlocks.temperature_coil, 9, DCHeatTier.OVEN);
		ClimateAPI.registerBlock.registerHeatBlock(TANBlocks.temperature_coil, 8, DCHeatTier.COLD);

		DamageAPI.itemRegister.registerMaterial(new ItemStack(TANItems.jelled_slime_boots, 1, 32767), 2.0F, 0.0F);
		DamageAPI.itemRegister.registerMaterial(new ItemStack(TANItems.jelled_slime_chestplate, 1, 32767), 2.0F, 0.0F);
		DamageAPI.itemRegister.registerMaterial(new ItemStack(TANItems.jelled_slime_helmet, 1, 32767), 2.0F, 0.0F);
		DamageAPI.itemRegister.registerMaterial(new ItemStack(TANItems.jelled_slime_leggings, 1, 32767), 2.0F, 0.0F);
		DamageAPI.itemRegister.registerMaterial(new ItemStack(TANItems.wool_boots, 1, 32767), 0.0F, 2.0F);
		DamageAPI.itemRegister.registerMaterial(new ItemStack(TANItems.wool_chestplate, 1, 32767), 0.0F, 2.0F);
		DamageAPI.itemRegister.registerMaterial(new ItemStack(TANItems.wool_helmet, 1, 32767), 0.0F, 2.0F);
		DamageAPI.itemRegister.registerMaterial(new ItemStack(TANItems.wool_leggings, 1, 32767), 0.0F, 2.0F);
	}

}
