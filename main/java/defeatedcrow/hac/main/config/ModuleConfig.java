package defeatedcrow.hac.main.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ModuleConfig {

	private ModuleConfig() {}

	public static final ModuleConfig INSTANCE = new ModuleConfig();
	private final String BR = System.getProperty("line.separator");

	public static boolean machine = true;
	public static boolean magic = true;
	public static boolean food = true;
	public static boolean tool = true;
	public static boolean world = true;

	public static boolean machine_advanced = true;
	public static boolean magic_advanced = true;
	public static boolean food_advanced = true;
	public static boolean build_advanced = true;
	public static boolean weapon_advanced = true;
	public static boolean clothes_advanced = true;

	public static boolean delete_old = false;

	public static boolean village = true;
	public static boolean agri = true;
	public static boolean crop = true;

	public static boolean ffm = true;
	public static boolean mek = true;
	public static boolean ic2 = true;
	public static boolean bop = true;
	public static boolean cofh = true;
	public static boolean bc = true;
	public static boolean mce = true;
	public static boolean tan = true;
	public static boolean eio = true;
	public static boolean ugb = false;

	public static boolean r_mill = true;
	public static boolean r_spinning = true;
	public static boolean r_fluid = true;
	public static boolean r_reactor = true;
	public static boolean r_crusher = true;

	public void load(Configuration cfg) {

		try {
			cfg.load();

			cfg.addCustomCategoryComment("module setting", "This setting is for module parmission. " + BR + "If you set false, that module will not add recipes and creative tab items." + BR + "Please understand that you can not delete item/block registrations by this setting.");

			cfg.addCustomCategoryComment("plugin setting", "This setting is for plugin installation parmission. " + BR + "If you set false, that plugin will be disabled.");

			cfg.addCustomCategoryComment("recipe setting", "This setting is for recipe registration parmission. " + BR + "If you set false, that recipe will be removed.");

			Property machine_b = cfg.get("module setting", "EnableMachineModule", machine);
			Property magic_b = cfg.get("module setting", "EnableMagicModule", magic);
			Property food_b = cfg.get("module setting", "EnableFoodModule", food);
			Property tool_b = cfg.get("module setting", "EnableToolModule", tool);
			Property village_b = cfg.get("module setting", "EnableVillager", village);
			Property agri_b = cfg.get("module setting", "EnableCraftingCropRecipe", agri);
			Property world_b = cfg.get("module setting", "EnableWorldgen", world);
			Property crop_b = cfg.get("module setting", "EnableCropSeasonality", agri);

			Property machine_a = cfg.get("module setting", "EnableAdvancedMachine", machine_advanced);
			Property magic_a = cfg.get("module setting", "EnableAdvancedMagic", magic_advanced);
			Property food_a = cfg.get("module setting", "EnableAdvancedFood", food_advanced);
			Property weapon_a = cfg.get("module setting", "EnableAdvancedWeapon", weapon_advanced);
			Property build_a = cfg.get("module setting", "EnableAdvancedBuildingBlocks", build_advanced);
			Property build_c = cfg.get("module setting", "EnableAdvancedClothes", clothes_advanced);

			Property del_old = cfg
					.get("module setting", "DisableOldMaterials", delete_old, "This mod leaves some deprecated items for world compatibility with older versions." + BR + "If you do not need them, please set it to true.");

			Property mek_b = cfg.get("plugin setting", "MekanismPlugin", mek);
			Property for_b = cfg.get("plugin setting", "ForestryPlugin", ffm);
			Property ic2_b = cfg.get("plugin setting", "IndustrialCraft2Plugin", ic2);
			Property bop_b = cfg.get("plugin setting", "BiomesOPlentyPlugin", bop);
			Property cofh_b = cfg.get("plugin setting", "CoFHPlugin", bop);
			Property bc_b = cfg.get("plugin setting", "BuildcraftPlugin", bc);
			Property mce_b = cfg.get("plugin setting", "MCEconomyPlugin", mce);
			Property tan_b = cfg.get("plugin setting", "ToughAsNailsPlugin", tan);
			Property eio_b = cfg.get("plugin setting", "EnderIO", eio);
			Property ugb_b = cfg.get("plugin setting", "UndergroundBiomes", ugb);

			Property mill = cfg.get("recipe setting", "EnableMillRecipe", r_mill);
			Property spinning = cfg.get("recipe setting", "EnableSpinningRecipe", r_spinning);
			Property fluid = cfg.get("recipe setting", "EnableCookingPanRecipe", r_fluid);
			Property reactor = cfg.get("recipe setting", "EnableReactorRecipe", r_reactor);
			Property crusher = cfg.get("recipe setting", "EnableHammerMillRecipe", r_crusher);

			machine = machine_b.getBoolean();
			magic = magic_b.getBoolean();
			food = food_b.getBoolean();
			tool = tool_b.getBoolean();
			village = village_b.getBoolean();
			agri = agri_b.getBoolean();
			world = world_b.getBoolean();
			crop = crop_b.getBoolean();

			machine_advanced = machine_a.getBoolean();
			magic_advanced = magic_a.getBoolean();
			food_advanced = food_a.getBoolean();
			weapon_advanced = weapon_a.getBoolean();
			build_advanced = build_a.getBoolean();
			clothes_advanced = build_c.getBoolean();

			delete_old = del_old.getBoolean();

			mek = mek_b.getBoolean();
			ffm = for_b.getBoolean();
			ic2 = ic2_b.getBoolean();
			bop = bop_b.getBoolean();
			cofh = cofh_b.getBoolean();
			bc = bc_b.getBoolean();
			mce = mce_b.getBoolean();
			tan = tan_b.getBoolean();
			eio = eio_b.getBoolean();
			ugb = ugb_b.getBoolean();

			r_mill = mill.getBoolean();
			r_spinning = spinning.getBoolean();
			r_fluid = fluid.getBoolean();
			r_reactor = reactor.getBoolean();
			r_crusher = crusher.getBoolean();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cfg.save();
		}

	}

}
