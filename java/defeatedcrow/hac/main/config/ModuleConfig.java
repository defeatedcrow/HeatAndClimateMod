package defeatedcrow.hac.main.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ModuleConfig {

	private ModuleConfig() {
	}

	public static final ModuleConfig INSTANCE = new ModuleConfig();
	private final String BR = System.getProperty("line.separator");

	public static boolean machine = true;
	public static boolean magic = true;
	public static boolean food = true;

	public void load(Configuration cfg) {

		try {
			cfg.load();

			cfg.addCustomCategoryComment("module setting", "This setting is for module parmission. " + BR
					+ "If you set false, that module will not add recipes and creative tab items." + BR
					+ "Please understand that you can not delete item/block registerations by this setting.");

			Property machine_b = cfg.get("module setting", "EnableMachineModule", machine);
			Property magic_b = cfg.get("module setting", "EnableMagicModule", magic);
			Property food_b = cfg.get("module setting", "EnableFoodModule", food);

			machine = machine_b.getBoolean();
			magic = magic_b.getBoolean();
			food = food_b.getBoolean();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cfg.save();
		}

	}

}
